var App = function () {
    var _checkBoxMaster;
    var _checkBoxList;
    var handlerInitCheckbox = function () {
        //iCheck for checkbox and radio inputs
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });
    }
    var handlerSelectAll = function () {
        _checkBoxMaster = $('input[type="checkbox"].icheck_master');
        _checkBoxList = $("tbody").find("[type='checkbox']").not("[disabled]");
        _checkBoxMaster.on("ifClicked",function (e) {
            //未选中
            if (e.target.checked) {
                _checkBoxList.iCheck("uncheck");
            }
            //选中
            else {
                _checkBoxList.iCheck("check");
            }
        });
    }
    return{
        init:function () {
            handlerInitCheckbox();
            handlerSelectAll();
        },
        getCheckBoxList:function () {
            return _checkBoxList;
        }


    }
}();
$(document).ready(function () {
    App.init();
})