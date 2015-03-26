(function(angular) {
    var app = angular.module('boodschappenlijstje',['ngMaterial', 'ui']);

    app.config(function($mdThemingProvider) {
        $mdThemingProvider.theme('default')
            .primaryPalette('pink')
            .accentPalette('orange');
    });
})(angular);