(function() {
    'use strict';
    angular
        .module('enigmaUiApp')
        .factory('Language', Language);

    Language.$inject = ['$resource'];

    function Language ($resource) {
        var resourceUrl =  'enigmaquiz/api/languages/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
