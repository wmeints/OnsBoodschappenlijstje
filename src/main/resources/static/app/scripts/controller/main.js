(function(angular) {
    function MainController($mdDialog, $mdToast, BoodschappenService) {
        var vm = this;

        var toastPosition = {
            bottom: true,
            right: true,
            top: false,
            left: false
        };
        
        vm.newItemText = '';
        vm.items = [];
        vm.isAddingItem = false;
        
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
            });
        };

        vm.removeItem = function(item) {
            BoodschappenService.remove(item.id).success(function() {
                BoodschappenService.findAll().success(function(data) {
                    vm.items = data;
                }).error(function() {
                    $mdToast.simple()
                        .content('Oeps, kon de lijst niet vernieuwen. Neem contact op met de beheerder voor ondersteuning.')
                        .position(toastPosition)
                        .hideDelay(3000);
                });
            }).error(function() {
                $mdToast.simple()
                    .content('Oeps, kon het item niet verwijderen. Neem contact op met de beheerder voor ondersteuning.')
                    .position(toastPosition)
                    .hideDelay(3000);
            });
        };

        vm.saveItem = function() {
            // Do not add items when the user hasn't entered any text.
            if(!vm.newItemText) {
                return;
            }
            
            vm.isAddingItem = true;

            // Make the input simple, by allowing the user to enter Stuff @ Winkel
            var itemParts = vm.newItemText.match(/(.+)@(.*)/i);

            // Try to set the item text and shop based on the input of the user.
            // If the user hasn't entered a proper item text, use the defaults instead.
            var itemText = (itemParts && itemParts.length == 3 && itemParts[1].trim()) || vm.newItemText;
            var winkelText = (itemParts && itemParts.length == 3 && itemParts[2].trim()) || '';

            BoodschappenService.create({
                item: itemText,
                winkel: winkelText
            }).success(function(data) {
                vm.items.push(data);
                
                vm.isAddingItem = false;
                vm.newItemText = '';
            }).error(function() {
                $mdToast.simple()
                    .content('Oeps, kon het item niet op de lijst zetten. Neem contact op met de beheerder voor ondersteuning.')
                    .position(toastPosition)
                    .hideDelay(3000);
            });
        };

        vm.editItem = function(item) {
            $mdDialog.show({
                templateUrl: 'app/templates/taskdialog.tmpl.html',
                locals: {
                    item: item,
                    BoodschappenService: BoodschappenService
                },
                controller: TaskDialogController
            });
        };
        
        vm.refresh = function() {
            BoodschappenService.findAll().success(function(data) {
                vm.items = data;
            }).error(function() {
                $mdToast.simple()
                    .content('Oeps, kon de lijst niet vernieuwen. Neem contact op met de beheerder voor ondersteuning.')
                    .position(toastPosition)
                    .hideDelay(3000);
            });
        };

        function init() {
            BoodschappenService.findAll().success(function(data) {
               vm.items = data;
            }).error(function() {
                $mdToast.simple()
                    .content('Oeps, kon de lijst niet vernieuwen. Neem contact op met de beheerder voor ondersteuning.')
                    .position(toastPosition)
                    .hideDelay(3000);
            });
        }
    };

    function TaskDialogController($scope,$mdDialog,item, BoodschappenService) {
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

            // Send the update operation to the backend
            // When that is succesful, update the frontend and close the dialog.
            BoodschappenService.update(item.id, {
                item: itemText,
                winkel: winkelText
            }).success(function() {
                item.item = itemText;
                item.winkel = winkelText;
                
                $mdDialog.hide();
            }).error(function() {
                $mdToast.simple()
                    .content('Oeps, kon het item niet opslaan. Neem contact op met de beheerder voor ondersteuning.')
                    .position(toastPosition)
                    .hideDelay(3000);
            });
        };

        $scope.cancel = function() {
            $mdDialog.cancel();
        };
    };

    MainController.$inject = ['$mdDialog', '$mdToast', 'BoodschappenService'];

    angular.module('boodschappenlijstje').controller('MainCtrl',MainController);
})(angular);
