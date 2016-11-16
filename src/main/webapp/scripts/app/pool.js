define(function (require) {
    var Vue = require('vendor/vue');
    require('vendor/vue-resource')(Vue);

    var app = new Vue({
        el: '#content',
        data: {
            users: [],
            addUserShown: false,
            newUser: {}
        },
        methods: {
            showAddUser: function () {
                this.addUserShown = true;
            },
            addUser: function () {
                var _this = this;

                Vue.http.post('/api/user/pool/' + _poolId, _this.newUser)
                    .then(function (response) {
                    _this.users = response.body;
                });

                this.addUserShown = false;
                this.newUser = {};
            }
        },
        beforeCreate: function () {
            var _this = this;
            Vue.http.get('/api/user/pool/' + _poolId)
            .then(function (response) {
                _this.users = response.body;
            });
        }

    });
});
