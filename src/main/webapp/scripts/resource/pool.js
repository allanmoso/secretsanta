define(function (require) {
    var Vue = require('vendor/vue');
    require('vendor/vue-resource')(Vue);

    return {
        getPool: function (poolId, callback) {
            Vue.http.get('/api/pool/' + poolId)
                .then(callback);
        },
        save: function(pool, callback) {
            Vue.http.post('/api/pool', pool)
                .then(callback);
        },
        draw: function (poolId, callback) {
            Vue.http.get('/api/pool/' + poolId + '/draw')
                .then(callback);
        },
        addUser: function (poolId, user, callback) {
            Vue.http.post('/api/pool/' + poolId + '/user', user)
                .then(callback);
        }
    }
});
