(function() {
    'use strict';

    angular
        .module('enigmaUiApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('real', {
            parent: 'app',
            url: '/real',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/real/real.html',
                    controller: 'RealController',
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
