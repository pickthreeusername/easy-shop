var App = function () {

    var handlerInitCheckbox = function () {
        //iCheck for checkbox and radio inputs
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });

    }
    return{
        init:function () {
            handlerInitCheckbox();
        }
    }
}();
$(document).ready(function () {
    App.init();
})