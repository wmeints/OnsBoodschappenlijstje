(function(angular) {
    function MainController() {
        var vm = this;

        vm.newItemText = '';

        vm.items = [
            {
                done: true,
                item: 'Voorbeeld item',
                winkel: 'AH'
            }
        ];
	
	vm.removeItem = function(item) {
		vm.items.remove(item);
	};

        vm.saveItem = function() {
            // Make the input simple, by allowing the user to enter Stuff @ Winkel
            var itemParts = vm.newItemText.match(/(.*)@(.*)/i);

            var itemText = (itemParts && itemParts.length == 3 && itemParts[1].trim()) || vm.newItemText;
            var winkelText = (itemParts && itemParts.length == 3 && itemParts[2].trim()) || '';

            vm.items.push({
                done: false,
                item: itemText,
                winkel: winkelText
            });

            vm.newItemText = '';
            vm.newItemWinkel = '';
        };
    };

    MainController.$inject = [];

    angular.module('boodschappenlijstje').controller('MainCtrl',MainController);
})(angular);
