(function() {
    'use strict';
    angular
        .module('enigmaUiApp')
        .factory('Answer', Answer);

    Answer.$inject = ['$resource'];

    function Answer ($resource) {
        var resourceUrl =  'enigmaquiz/api/answers/:id';

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
