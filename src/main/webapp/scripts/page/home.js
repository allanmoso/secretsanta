define(function (require) {
    var Vue = require('vendor/vue');
    var Pool = require('resource/pool')

    var app = new Vue({
        el: '#content',
        data: {
            poolName: ""
        },
        methods: {
            newPool: function() {
                //noinspection JSUnresolvedVariable
                Pool.save({
                   name: this.poolName
                },function(response) {
                    window.location.href = "/pool/" + response.body.id;
                });
            }
        }
    });
});
