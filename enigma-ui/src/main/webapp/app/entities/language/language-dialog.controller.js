(function() {
    'use strict';

    angular
        .module('enigmaUiApp')
        .controller('LanguageDialogController', LanguageDialogController);

    LanguageDialogController.$inject = ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Language', 'Question'];

    function LanguageDialogController ($scope, $stateParams, $uibModalInstance, entity, Language, Question) {
        var vm = this;
        vm.language = entity;
        vm.questions = Question.query();
        vm.load = function(id) {
            Language.get({id : id}, function(result) {
                vm.language = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('enigmaUiApp:languageUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.language.id !== null) {
                Language.update(vm.language, onSaveSuccess, onSaveError);
            } else {
                Language.save(vm.language, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
    }
})();
