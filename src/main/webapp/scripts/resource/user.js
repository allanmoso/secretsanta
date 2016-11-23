define(function (require) {
    var Vue = require('vendor/vue');
    require('vendor/vue-resource')(Vue);

    return {
        getUsers: function (poolId, callback) {
            Vue.http.get('/api/user/pool/' + poolId)
                .then(callback);
        },
        getUser: function(userId, callback) {
            Vue.http.get('/api/user/' + userId)
                .then(callback);
        },
        saveUser: function(user, callback) {
            Vue.http.put('/api/user', user)
                .then(callback);
        }
    }
});
