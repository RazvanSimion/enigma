(function() {
    'use strict';

    angular
        .module('enigmaUiApp')
        .controller('CategoryDetailController', CategoryDetailController);

    CategoryDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Category', 'Question'];

    function CategoryDetailController($scope, $rootScope, $stateParams, entity, Category, Question) {
        var vm = this;
        vm.category = entity;
        vm.load = function (id) {
            Category.get({id: id}, function(result) {
                vm.category = result;
            });
        };
        var unsubscribe = $rootScope.$on('enigmaUiApp:categoryUpdate', function(event, result) {
            vm.category = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
