(function() {
    'use strict';

    angular
        .module('enigmaUiApp')
        .controller('LanguageDeleteController',LanguageDeleteController);

    LanguageDeleteController.$inject = ['$uibModalInstance', 'entity', 'Language'];

    function LanguageDeleteController($uibModalInstance, entity, Language) {
        var vm = this;
        vm.language = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Language.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
