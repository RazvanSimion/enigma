(function() {
    'use strict';

    angular
        .module('enigmaUiApp')
        .factory('AnswerSearch', AnswerSearch);

    AnswerSearch.$inject = ['$resource'];

    function AnswerSearch($resource) {
        var resourceUrl =  'enigmaquiz/api/_search/answers/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
