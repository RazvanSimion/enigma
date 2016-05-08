(function() {
    'use strict';

    angular
        .module('enigmaUiApp')
        .controller('QuestionDetailController', QuestionDetailController);

    QuestionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Question', 'Answer', 'Language', 'Category'];

    function QuestionDetailController($scope, $rootScope, $stateParams, entity, Question, Answer, Language, Category) {
        var vm = this;
        vm.question = entity;
        vm.load = function (id) {
            Question.get({id: id}, function(result) {
                vm.question = result;
            });
        };
        var unsubscribe = $rootScope.$on('enigmaUiApp:questionUpdate', function(event, result) {
            vm.question = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
