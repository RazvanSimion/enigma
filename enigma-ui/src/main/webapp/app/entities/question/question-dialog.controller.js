(function() {
    'use strict';

    angular
        .module('enigmaUiApp')
        .controller('QuestionDialogController', QuestionDialogController);

    QuestionDialogController.$inject = ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Question', 'Answer', 'Language', 'Category'];

    function QuestionDialogController ($scope, $stateParams, $uibModalInstance, entity, Question, Answer, Language, Category) {
        var vm = this;
        vm.question = entity;
        vm.answers = Answer.query();
        vm.languages = Language.query();
        vm.categorys = Category.query();
        vm.load = function(id) {
            Question.get({id : id}, function(result) {
                vm.question = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('enigmaUiApp:questionUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.question.id !== null) {
                Question.update(vm.question, onSaveSuccess, onSaveError);
            } else {
                Question.save(vm.question, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
    }
})();
