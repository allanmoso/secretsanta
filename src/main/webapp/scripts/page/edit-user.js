define(function (require) {
    var Vue = require('vendor/vue');
    var User = require('resource/user');

    //noinspection JSUnusedGlobalSymbols
    new Vue({
        el: '#content',
        data: {
            user: {},
            saved: false
        },
        methods: {
            saveUser: function () {
                var _this = this;
                User.saveUser(_this.user, function (response) {
                    _this.saved = true;
                });
            }
        },
        beforeCreate: function() {
            var _this = this;
            User.getUser(_userId, function(response) {
                _this.user = response.body;
            });
        }
    });
});
