define(function (require) {
    var Vue = require('vendor/vue');
    var Pool = require('resource/pool');


    //noinspection JSUnusedGlobalSymbols
    new Vue({
        el: '#content',
        data: {
            pool: {},
            addUserShown: false,
            newUser: {}
        },
        methods: {
            showAddUser: function () {
                //noinspection JSUnusedGlobalSymbols
                this.addUserShown = true;
            },
            addUser: function () {
                var _this = this;

                Pool.addUser(_poolId, _this.newUser, function (response) {
                    //noinspection JSUnresolvedVariable
                    _this.pool.users.push(response.body);
                });

                //noinspection JSUnusedGlobalSymbols
                this.addUserShown = false;
                this.newUser = {};
            },
            draw: function () {
                Pool.draw(_poolId, function (response) {
                });
            }
        },
        beforeCreate: function () {
            var _this = this;
            Pool.getPool(_poolId, function (response) {
                _this.pool = response.body;
            });
        }

    });
});
