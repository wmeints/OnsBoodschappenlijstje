(function(angular) {
    function BoodschappenService($http) {
        function findAll() {
            return $http.get('/api/item');
        }
        
        function findById(id) {
            return $http.get('/api/item/' + id);
        }

        function create(item) {
            return $http.post('/api/item/', item);
        }
        
        function update(id, item) {
            return $http.put('/api/item/' + id, item);
        }
        
        function remove(id) {
            return $http.delete('/api/item/' + id);
        }
        
        
        
        return {
            findAll: findAll,
            findById: findById,
            create: create,
            update: update,
            remove: remove
        }
    }
    
    BoodschappenService.$inject = ['$http'];
    
    angular.module('boodschappenlijstje').factory('BoodschappenService', BoodschappenService);
})(angular);
