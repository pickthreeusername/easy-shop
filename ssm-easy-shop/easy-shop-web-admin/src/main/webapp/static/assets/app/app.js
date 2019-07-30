var App = function () {
    //iCheck
    //顶部checkbox
    var _checkBoxMaster;
    var _checkBoxList;

    //删除路径
    var deleteUrl;
    //存放要删除的id数组
    var idArray;
    //dropzone默认配置
    var defaultDropzoneOpts = {
        url: "upload/fileUpload", // 文件提交地址
        method: "post",  // 也可用put
        paramName: "dropzFile", // 默认为file
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictRemoveFile: '删除',
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传1个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictCancelUpload: "取消",
        init: function () {
            this.on("addedfile", function (file) {
                // 上传文件时触发的事件
            });
            this.on("success", function (file, data) {
                // 上传成功触发的事件
            });
            this.on("error", function (file, data) {
                // 上传失败触发的事件
            });
            this.on("removedfile", function (file) {
                // 删除文件时触发的方法
            });
        }
    };
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
            //顶部checkbox的e.target.checked为true，未选中，所有checkbox设置为不选中
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
     * 初始化DataTables
     */
    var handlerInitDataTables = function (url, columns) {
        var _dataTable = $("#dataTable").DataTable({
            "autoWidth": false,
            "paging": true,
            "ordering": false,
            "info": true,
            "searching": false,
            "processing": true,
            "serverSide": true,
            "lengthChange": false,
            "deferRender": true,
            "ajax": {
                "url": url
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
        return _dataTable;

    };
    /**
     * 初始化化zTree
     */
    var handlerInitzTree = function (url, param, callback) {

        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                url:url,
                autoParam:param
            }
        };
        $.fn.zTree.init($("#myTree"), setting);
        $("#btn_modal_ok").bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            var nodes = zTree.getSelectedNodes();
            if (nodes.length == 0) {
                alert("请先选择一个父节点");
            }
            else{
                callback(nodes);
            }
        });

    };


    /**
     * 删除操作
     * @param url 删除路径
     * @param id 单个删除时传入的id
     */
    var handlerDelete = function (url, id, msg) {
        if(!msg)msg = null;
        deleteUrl = url;
        idArray = new Array();
        //没有传入单个id，说明是批量删除
        if (isNaN(id)){
            _checkBoxList.each(function () {
                var _id = $(this).attr("id");
                //将选中元素的id放入数组中
                if($(this).is(":checked") && _id != null && _id != "undefined" ){
                    idArray.push($(this).attr("id"));
                }
            });
        }
        //单个删除
        else{
            idArray.push(id);
        }
        var message;
        if (idArray.length === 0) {
            message = "尚未选择要删除的数据";

        }else{
            if (msg != null){
                message = msg;
            }
            else{
                message = "确定删除选中数据？"
            }

        }
        $("#modal_message").html(message);
        //显示模态框，提示用户
        $("#modal-default").modal("show");
        $("#btn_modal_cancel").show();
        //解绑确定按钮的点击事件，避免事件叠加造成重复执行
        $("#btn_modal_ok").off("click");
        $("#btn_modal_ok").on("click", function () {
            modalBtnClick(url);
        })

    }
     //删除时 确定按钮的点击事件
    var modalBtnClick = function (url) {
        //没选择数据,隐藏模态框
        if (idArray.length === 0){
            $("#modal-default").modal("hide");
        }
        //否则ajax异步删除
        else{
            $.ajax({
                url:url,
                type:"post",
                data:{"ids":idArray.toString()},
                dataType:"JSON",
                success:function (result) {

                    //解绑确定按钮的点击事件，避免事件叠加造成重复执行
                    $("#btn_modal_ok").off("click");
                    //插入提示信息
                    $("#modal_message").html(result.message);

                    $("#modal-default").modal("show");
                    //无论删除是否成功，隐藏取消按钮，只显示确定按钮
                    $("#btn_modal_cancel").hide();

                    if (result.status === 200) {

                        $("#btn_modal_ok").on("click",function () {
                            window.location.reload();
                        });
                    }
                    else{
                        $("#btn_modal_ok").on("click",function () {
                            $("#modal-default").modal("hide");
                        });
                    }

                }
            });
        }
    }

    /**
     * 初始化dropzone
     * @param dropzId dropzone元素id
     * @param options dropzone配制对象
     */
    var handlerInitDropzone = function (dropzId, options) {
        //关闭自动发现
        Dropzone.autoDiscover = false;
        //重写默认配置
        $.extend(defaultDropzoneOpts, options);
        $(dropzId).dropzone(defaultDropzoneOpts);
    }


    /**
     * 查看详情,ajax的html请求，将页面装载进模态框中
     */
    var handlerDetailInfo = function(detailUrl) {

        $.ajax({
            "url":detailUrl,
            "type":"GET",
            "dataType":"html",
            success:function (data) {
                var message = $("#modal-detail").find(".modal-body");
                message.html(data);
                $("#modal-detail").modal("show");
            }
        })
    }
    return{
        //初始化
        init:function () {
            handlerInitCheckbox();
            handlerSelectAll();
        },
        //删除数据
        deleteData:function (url, id, msg) {
            handlerDelete(url, id, msg);
        },
        //初始化dataTable
        initDataTables: function (url, columns) {
            return handlerInitDataTables(url, columns);

        },
        //查看详情
        detailInfo: function (detailUrl) {
            handlerDetailInfo(detailUrl);

        },
        //初始化zTree
        initzTree: function (url, param, callback) {
            handlerInitzTree(url, param, callback);
        },
        //初始化 dropzone
        initDropzone: function (dropzId, options) {
            handlerInitDropzone(dropzId, options);
        }
    }
}();
$(document).ready(function () {
    App.init();
})