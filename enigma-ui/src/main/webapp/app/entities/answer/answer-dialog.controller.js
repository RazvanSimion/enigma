(function() {
    'use strict';

    angular
        .module('enigmaUiApp')
        .controller('AnswerDialogController', AnswerDialogController);

    AnswerDialogController.$inject = ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Answer', 'Question'];

    function AnswerDialogController ($scope, $stateParams, $uibModalInstance, entity, Answer, Question) {
        var vm = this;
        vm.answer = entity;
        vm.questions = Question.query();
        vm.load = function(id) {
            Answer.get({id : id}, function(result) {
                vm.answer = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('enigmaUiApp:answerUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.answer.id !== null) {
                Answer.update(vm.answer, onSaveSuccess, onSaveError);
            } else {
                Answer.save(vm.answer, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
    }
})();
