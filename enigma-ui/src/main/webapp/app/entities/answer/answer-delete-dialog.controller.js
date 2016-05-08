(function() {
    'use strict';

    angular
        .module('enigmaUiApp')
        .controller('AnswerDeleteController',AnswerDeleteController);

    AnswerDeleteController.$inject = ['$uibModalInstance', 'entity', 'Answer'];

    function AnswerDeleteController($uibModalInstance, entity, Answer) {
        var vm = this;
        vm.answer = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Answer.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
