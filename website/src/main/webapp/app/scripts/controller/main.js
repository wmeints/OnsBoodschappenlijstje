(function(angular) {
    function MainController($mdDialog, BoodschappenService) {
        var vm = this;

        vm.newItemText = '';
        vm.items = [];

        init();

        vm.isSelected = function(item) {
            return item.done;
        };

        vm.updateItemStatus = function(item) {
            item.done = !item.done;

            BoodschappenService.update(item.id, {
                item: item.item,
                winkel: item.winkel,
                done: item.done
            }).success(function() {
                BoodschappenService.findAll().success(function(data) {
                    vm.items = data;
                });
            });
        };

        vm.removeItem = function(item) {
            BoodschappenService.remove(item.id).then(function() {
                BoodschappenService.findAll().success(function(data) {
                    vm.items = data;
                });
            });
        };

        vm.saveItem = function() {
            // Do not add items when the user hasn't entered any text.
            if(!vm.newItemText) {
                return;
            }

            // Make the input simple, by allowing the user to enter Stuff @ Winkel
            var itemParts = vm.newItemText.match(/(.+)@(.*)/i);

            // Try to set the item text and shop based on the input of the user.
            // If the user hasn't entered a proper item text, use the defaults instead.
            var itemText = (itemParts && itemParts.length == 3 && itemParts[1].trim()) || vm.newItemText;
            var winkelText = (itemParts && itemParts.length == 3 && itemParts[2].trim()) || '';

            vm.items.push({
                done: false,
                item: itemText,
                winkel: winkelText
            });

            BoodschappenService.create({
                item: itemText,
                winkel: winkelText
            }).success(function() {
                BoodschappenService.findAll().success(function(data) {
                    vm.items = data;
                });
            });

            vm.newItemText = '';
            vm.newItemWinkel = '';
        };

        vm.editItem = function(item) {
            $mdDialog.show({
                templateUrl: 'app/templates/taskdialog.tmpl.html',
                locals: {
                    item: item
                },
                controller: TaskDialogController
            }).then(function() {
                BoodschappenService.findAll().success(function(data) {
                    vm.items = data;
                });
            });
        };

        function init() {
            BoodschappenService.findAll().success(function(data) {
               vm.items = data;
            });
        }
    };

    function TaskDialogController($scope,$mdDialog,item) {
        $scope.item = item;
        $scope.updatedItemText = item.item;

        // Update the item text in the dialog with the winkel when available
        if(item.winkel) {
            $scope.updatedItemText += '@' + item.winkel;
        }

        $scope.confirm = function() {
            // Make the input simple, by allowing the user to enter Stuff @ Winkel
            var itemParts = $scope.updatedItemText.match(/(.+)@(.*)/i);

            // Try to set the item text and shop based on the input of the user.
            // If the user hasn't entered a proper item text, use the defaults instead.
            var itemText = (itemParts && itemParts.length == 3 && itemParts[1].trim()) || $scope.updatedItemText;
            var winkelText = (itemParts && itemParts.length == 3 && itemParts[2].trim()) || '';

            item.item = itemText;
            item.winkel = winkelText;

            BoodschappenService.update(item.id, {
                item: itemText,
                winkel: winkelText
            }).success(function() {
                $mdDialog.hide();
            });
        };

        $scope.cancel = function() {
            $mdDialog.cancel();
        };
    };

    MainController.$inject = ['$mdDialog', 'BoodschappenService'];

    angular.module('boodschappenlijstje').controller('MainCtrl',MainController);
})(angular);
