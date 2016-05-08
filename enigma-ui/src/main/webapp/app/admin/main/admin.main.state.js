(function() {
    'use strict';

    angular
        .module('enigmaUiApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('admin.main', {
            parent: 'app',
            url: '/administration',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/admin/main/admin.main.html',
                    controller: 'AdminMainController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('home');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
