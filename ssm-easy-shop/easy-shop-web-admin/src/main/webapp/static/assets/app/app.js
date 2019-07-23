var App = function () {
    //iCheck
    var _checkBoxMaster;
    var _checkBoxList;
    //标志模态框确定按钮是否绑定，如果绑定多次，会执行多次绑定事件
    var _ifBind = false;

    var deleteUrl;
    var idArray;
    /**
     * 初始化iCheck
     */
    var handlerInitCheckbox = function () {
        //iCheck for checkbox and radio inputs
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });
    }
    /**
     * 全选
     */
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
    /**
     * 批量删除
     */
    var deleteMulti = function (url) {
        $("#btn_modal_confirm").show();
        //var _checkBoxes = App.getCheckBoxList();
        deleteUrl = url;
        idArray = new Array();

        _checkBoxList.each(function () {
            var _id = $(this).attr("id");
            //将选中元素的id放入数组中
            if($(this).is(":checked") && _id != null && _id != "undefined" ){
                idArray.push($(this).attr("id"));
            }
        });
        var message;
        if (idArray.length === 0) {
            message = "尚未选择要删除的数据";

        }else{
            message = "确定删除选中数据？"
        }
        $("#modal_message").html(message);
        $("#modal-default").modal("show");

        if (_ifBind == false){
            _ifBind = true;
            $("#btn_modal_confirm").on("click",function () {
                modalBtnClick(idArray,deleteUrl);
            });
        }
        //确定按钮的点击事件
        function modalBtnClick() {
            //没选择数据,隐藏模态框
            if (idArray.length === 0){
                $("#modal-default").modal("hide");
            }

            else{
                $.ajax({
                    url:url,
                    type:"post",
                    data:{"ids":idArray.toString()},
                    dataType:"JSON",
                    success:function (result) {
                        console.log(result);
                        //插入提示信息
                        $("#modal_message").html(result.message);
                        if (result.status === 200) {
                            idArray = new Array();
                            $("#modal-default").modal("show");
                            window.setTimeout("window.location.reload()", 500);
                            //提示删除成功


                        }
                        else{
                            //隐藏确定按钮
                            $("#btn_modal_confirm").hide();
                        }

                    }
                });
            }
        }
    }

    /**
     * 初始化DataTables
     */
    var handlerInitDataTables = function (url, columns) {
        $("#dataTable").dataTable({
            "paging": true,
            "ordering": false,
            "info": true,
            "searching": false,
            "processing": true,
            "serverSide": true,
            "lengthChange": false,
            "ajax": {
                "url": url,
            },
            "columns": columns,
            // 表格重绘的回调函数
            "drawCallback": function (settings) {
                handlerInitCheckbox();
                handlerSelectAll();
            },

            "headerCallback":function( thead, data, start, end, display ) {
                //将表头第一个checkbox默认为uncheck状态
                $(thead).find('th').eq(0).iCheck("uncheck");
             },
            // 国际化
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                }

            }


        });

    };
    /**
     * 查看详情
     */
    var handlerDetailInfo = function(detailUrl) {
        $.ajax({
            "url":detailUrl,
            "type":"GET",
            "dataType":"html",
            success:function (data) {
                $("#modal-detail").modal("show");
                var message = $("#modal-detail").find(".modal-body");
                message.html(data);
            }
        })
    }
    return{
        init:function () {
            handlerInitCheckbox();
            handlerSelectAll();
        },
        getCheckBoxList:function () {
            return _checkBoxList;
        },
        deleteMulti:function (url) {
            deleteMulti(url);
        },
        initDataTables: function (url, columns) {
            handlerInitDataTables(url, columns);

        },
        detailInfo: function (detailUrl) {
            handlerDetailInfo(detailUrl);

        }


    }
}();
$(document).ready(function () {
    App.init();
})