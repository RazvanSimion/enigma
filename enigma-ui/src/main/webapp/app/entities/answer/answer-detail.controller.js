(function() {
    'use strict';

    angular
        .module('enigmaUiApp')
        .controller('AnswerDetailController', AnswerDetailController);

    AnswerDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Answer', 'Question'];

    function AnswerDetailController($scope, $rootScope, $stateParams, entity, Answer, Question) {
        var vm = this;
        vm.answer = entity;
        vm.load = function (id) {
            Answer.get({id: id}, function(result) {
                vm.answer = result;
            });
        };
        var unsubscribe = $rootScope.$on('enigmaUiApp:answerUpdate', function(event, result) {
            vm.answer = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
