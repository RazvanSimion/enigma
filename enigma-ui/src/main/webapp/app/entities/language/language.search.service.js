(function() {
    'use strict';

    angular
        .module('enigmaUiApp')
        .factory('LanguageSearch', LanguageSearch);

    LanguageSearch.$inject = ['$resource'];

    function LanguageSearch($resource) {
        var resourceUrl =  'enigmaquiz/api/_search/languages/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
