(function() {
    'use strict';
    angular
        .module('enigmaUiApp')
        .factory('Question', Question);

    Question.$inject = ['$resource'];

    function Question ($resource) {
        var resourceUrl =  'enigmaquiz/api/questions/:id';

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
