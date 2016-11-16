define(function (require) {
    var Vue = require('vendor/vue');
    require('vendor/vue-resource');

    var app = new Vue({
        el: '#content',
        data: {
            vMessage: 'Hello there!'
        }
    });
});
