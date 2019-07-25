/**
 * 函数对象
 */
var Validate = function () {
    /**
     * 初始化 jQuery Validation
     * 内部对象，外部无法调用
     */
    var handlerInitValidate = function () {
         //所有id为inputForm的表单都使用表单验证
        $("#inputForm").validate({
            errorElement: 'span',
            errorClass: 'help-block',

            errorPlacement: function (error, element) {
                element.parent().parent().attr("class", "form-group has-error");
                error.insertAfter(element);
            }
        });

    };
    /**
     * 自定义校验规则
     */
    var handlerCustomerValidate = function () {

        $.validator.addMethod("mobile", function(value, element) {
            var length = value.length;
            var mobile =  /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "手机号码格式错误");

    };
    /**
     * 外部可以调用
     */
    return {
        init:function () {
            handlerInitValidate();
            handlerCustomerValidate();
        }
    }
}();
/**
 * 引入js时自动调用
 */
$(document).ready(function () {
    Validate.init();
});