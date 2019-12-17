/**
 * Created by Administrator on 2019/12/9.
 */
var prefix = "/api/userInfo/admin";
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
        {
            method: 'get', // 服务器数据的请求方式 get or post
            url: prefix + "/proSkuList", // 服务器数据的加载地址
            // showRefresh : true,
            // showToggle : true,
            showColumns: true,
            iconSize: 'outline',
            toolbar: '#exampleToolbar',
            striped: true, // 设置为true会有隔行变色效果
            dataType: "json", // 服务器返回的数据类型
            pagination: true, // 设置为true会在底部显示分页条
            // queryParamsType : "limit",
            // //设置为limit则会发送符合RESTFull格式的参数
            singleSelect: false, // 设置为true将禁止多选
            // contentType : "application/x-www-form-urlencoded",
            // //发送到服务器的数据编码类型
            pageNumber: 1, // 如果设置了分布，首页页码
            pageSize: 10, // 如果设置了分页，每页数据条数
            pageList: [10, 20, 30, 40], //可供选择的每页的行数（*）
            // search : true, // 是否显示搜索框
            //showColumns : false, // 是否显示内容下拉框（选择显示的列）
            sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
            // "server"

            queryParams: function (params) {
                return {
                    // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                    limit: params.limit,//页面大小
                    offset: params.offset//页码
                };
            },
            // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
            // queryParamsType = 'limit' ,返回参数必须包含
            // limit, offset, search, sort, order 否则, 需要包含:
            // pageSize, pageNumber, searchText, sortName,
            // sortOrder.
            // 返回false将会终止请求
            columns: [
                {
                    field: 'id',
                    title: '品项编码'
                },
                {
                    field: 'skuName',
                    title: '品项名称'
                },
                {
                    field: 'brand',
                    title: '所属品牌',
                    formatter: function (value) {
                        if (value == "X") {
                            return "香飘飘";
                        } else if (value == "M") {
                            return "MECO";
                        } else if (value == "L") {
                            return "兰芳园";
                        }
                    }
                },
                {
                    field: 'unit',
                    title: '单位'
                }
            ]
        });
}
/**
 * 新增数据
 */
function addData(){
    // iframe层
    layer.open({
        type : 2,
        title : '增加品项',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/addSku'
    });
}
/**
 * 删除数据
 */
function deleteData() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn : [ '确定', '取消' ]
        // 按钮
    }, function() {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function(i, row) {
            ids[i] = row['userId'];
        });
        $.ajax({
            type : 'POST',
            data : {
                "ids" : ids
            },
            url : prefix + '/deleteData',
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function() {});
}