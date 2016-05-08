(function() {
    'use strict';

    angular
        .module('enigmaUiApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('virtual', {
            parent: 'app',
            url: '/virtual',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/virtual/virtual.html',
                    controller: 'VirtualController',
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
