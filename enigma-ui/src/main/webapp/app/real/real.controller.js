(function () {
    'use strict';

    angular
        .module('enigmaUiApp')
        .controller('RealController', RealController)

    RealController.$inject = ['$scope', 'Principal', 'LoginService', 'MapService', 'AlertService'];


    function RealController($scope, Principal, LoginService, MapService, AlertService) {
        var vm = this;

        $scope.bodyClass = 'main-body';

        vm.viewPortWidth = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
        vm.viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

        console.log("viewPortHeight", vm.viewPortHeight);

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        $scope.$on('authenticationSuccess', function () {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function (account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }

        MapService.initMap().then(function () {
            var options = {
                center: {lat: 44.4200741, lng: 26.0799932},
                zoom: 15
            };
            $scope.map = new google.maps.Map(document.getElementById('enigmaMap'), options);

            MapService.getData().then(onGraphLoad);

            function onGraphLoadError(error) {
                AlertService.error(error.data.message);
            }

            function onGraphLoad(result) {
                console.log("GRAPH DATA", result);
                angular.forEach(result.data, function (item, position) {
                    MapService.addMarker($scope.map, item);
                })
            }
        });
    }
})();
