/**
 * Created by xg.chen on 2018/11/29.
 */
var prefix = "/data";
$(function () {
    $('#datetimepicker1').datetimepicker({
        format: 'YYYY年MM月DD日',
        locale: moment.locale('zh-cn')
    });
    $('#datetimepicker2').datetimepicker({
        format: 'YYYY年MM月DD日',
        locale: moment.locale('zh-cn')
    });
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
        {
            method: 'get', // 服务器数据的请求方式 get or post
            url: prefix + "/submitNumDataList", // 服务器数据的加载地址
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
                    offset: params.offset,//页码
                    planAddress: $('#planAddress').val(),
                    planStartDate: $('#subDateStart').val(),
                    planEndDate: $('#subDateEnd').val(),
                    planKunnrName : $('#planKunnrName').val()
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
                    field: 'planAddress',
                    title: '活动地址'
                },
                {
                    field: 'loginId',
                    title: '用户ID'
                },
                {
                    field: 'userName',
                    title: '用户姓名'
                },
                {
                    field: 'planKunnrName',
                    title: '经销商'
                },
                {
                    field: 'orgName',
                    title: '经销商所属组织'
                },
                {
                    field: 'skuName',
                    title: '品项名称'
                },
                {
                    field: 'brand',
                    title: '品牌',
                    formatter: function (value) {
                        if (value == "X") {
                            return "香飘飘";
                        } else if (value == "M") {
                            return "MECO";
                        } else if (value == "G") {
                            return "蜜谷";
                        } else if (value == "L") {
                            return "兰芳园";
                        }
                    }
                },
                {
                    field: 'num',
                    title: '数量'
                },
                {
                    field: 'unit',
                    title: '单位'
                },
                {
                    field: 'subDate',
                    title: '提报时间'
                }
            ]
        });
}
/**
 * 查询
 */
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
/**
 * 重置
 */
function clearData() {
    $('#planAddress').val(''),
    $('#subDateStart').val(),
    $('#subDateEnd').val(),
        $('#planKunnrName').val()
}
/**
 * 导出数据
 */
function downloadData() {
    window.open(prefix + '/downloadSubNumData?planAddress=' + $('#planAddress').val()
    + '&subDateStart=' + $('#subDateStart').val()
    + '&subDateEnd=' + $('#subDateEnd').val()
    + '&planKunnrName=' + $('#planKunnrName').val());
}