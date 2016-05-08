(function() {
    'use strict';

    angular
        .module('enigmaUiApp')
        .controller('VirtualController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService'];

    function HomeController ($scope, Principal, LoginService) {
        var vm = this;
        vm.viewPortWidth = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
        vm.viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
    }
})();
