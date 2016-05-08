(function() {
    'use strict';

    angular
        .module('enigmaUiApp')
        .controller('LanguageDetailController', LanguageDetailController);

    LanguageDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Language', 'Question'];

    function LanguageDetailController($scope, $rootScope, $stateParams, entity, Language, Question) {
        var vm = this;
        vm.language = entity;
        vm.load = function (id) {
            Language.get({id: id}, function(result) {
                vm.language = result;
            });
        };
        var unsubscribe = $rootScope.$on('enigmaUiApp:languageUpdate', function(event, result) {
            vm.language = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
