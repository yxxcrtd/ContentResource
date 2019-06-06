//显示修改
Content.Latest.ActivityInfo.editObj = function(id) {
	var url=$('#ctx').val() + "/pages/latest/form/edit?id=" + id;
	var commonModalCss = {
		"width": "600px",
		"height": "500px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

//显示新增
Content.Latest.ActivityInfo.addObj = function() {
	var url=$('#ctx').val() + "/pages/latest/form/edit";
	var commonModalCss = {
		"width": "600px",
		"height": "500px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

//下载附件
Content.Latest.ActivityInfo.download = function(id){
	var url = $('#ctx').val()+"/pages/latest/form/download?id="+id;
	window.location=url;
};

//删除产品
Content.Latest.ActivityInfo.delObj = function(id) {
	openConfirmModalInFrame("您确定执行删除该活动信息操作吗？", function() {
		var url = $('#ctx').val() + "/pages/latest/form/delete?id=" + id;
		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : url,
			"cache" : false,
			"success" : function(response) {
				if (response.isSuccess == "true") {
					window.parent.alertMsg('successModal', 'successMsg', response.msg);
					refreshFrameDataTableInFrame('Content.Latest.ActivityInfo.oTable1');
				} else {
					window.parent.alertMsg('errorModal', 'errorMsg', response.msg);
				}
			},
			"error" : function(response) {
				alert("error");
			}
		});
	}, null, null);
};

Content.Latest.ActivityInfo.query = function() {
	// 重新刷新页面Table
	refreshFrameDataTableInFrame('Content.Latest.ActivityInfo.oTable1');
};

Content.Latest.ActivityInfo.retrieveData = function(sSource, aoData, fnCallback) {
	$.ajax({
		"dataType" : 'json',
		"type" : "POST",
		"url" : sSource,
		"cache" : false,
		"data" : aoData,
		"success" : function(response) {
			fnCallback(response);
		},
		"error" : function(response) {
			alert(response);
			alert("error%%%");
		}
	});
};

$(function() {
	//开始时间
	$('#invoiceDate').datetimepicker({
		language : 'cn',
		pickTime : false
	}).on('changeDate', function(ev) {
		Content.Latest.ActivityInfo.StartOn();
	});
	//结束时间
	$('#invoiceDate2').datetimepicker({
		language : 'cn',
		pickTime : false
	}).on('changeDate', function(ev) {
		Content.Latest.ActivityInfo.StopOn();
	});
	//收缩框
    $(".on").click(function(){
		$(".on-down").slideToggle();
	});
    
    Content.Latest.ActivityInfo.oTable1 = $('#table_report').dataTable({
		"bFilter" : false, // 开关，是否启用客户端过滤器
		"bProcessing" : true, // 当datatable获取数据时候是否显示正在处理提示信息。
		"bAutoWidth" : false, // 自适应宽度
		"sPaginationType" : "full_numbers", // 分页样式
		"bServerSide" : true, // 从服务器端取数据
		"sAjaxSource" : $('#ctx').val() + "/pages/latest/form/manager?now=" + new Date().getTime(), // mvc后台ajax调用接口。
		"fnServerParams" : function(aoData) {
			aoData.push({
				"name" : "title",
				"value" : $("#query_title").val()
			});
			aoData.push({
				"name" : "startStr",
				"value" : $("#query_starton").val()
			});
			aoData.push({
				"name" : "stopStr",
				"value" : $("#query_stopon").val()
			});
		},
		"fnServerData" : Content.Latest.ActivityInfo.retrieveData,
		"fnDrawCallback" : function(oSettings) {
			for ( var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
				$('td:eq(0)', oSettings.aoData[oSettings.aiDisplay[i]].nTr).html(i + oSettings._iDisplayStart + 1);
			}
		},
		"aoColumns" : [ {
			"sTitle" : "序号",
			"mDataProp" : "id"
        }, {
			"sTitle" : "活动主题",
			"mDataProp" : "title"
        }, {
			"sTitle" : "活动简介",
			"mDataProp" : "content",
            "fnRender": function (oObj) {
                return '<span title=' + oObj.aData.content + ' style="display: block; width: 150px; overflow: hidden; white-space: nowrap; -o-text-overflow: ellipsis; text-overflow: ellipsis;">' + oObj.aData.content + '</span>';
        	}
        }, {
            "sTitle" : "开始时间",
            "mDataProp" : "startDate",
			"sType" : 'date',
    		"fnRender" : function(oObj) {
    			var javascriptDate = new Date(oObj.aData.startDate).toLocaleDateString();
    			return "<div class= date>" + javascriptDate + "<div>";
    			}
		}, {
            "sTitle" : "结束时间",
            "mDataProp" : "stopDate",
			"sType" : 'date',
    		"fnRender" : function(oObj) {
    			var javascriptDate = new Date(oObj.aData.stopDate).toLocaleDateString();
    			return "<div class= date>" + javascriptDate + "<div>";
    			}
		}, {
			"sTitle" : "附件分类",
			"mDataProp" : "type"
		}, {
			"sTitle" : "附件名称",
			"mDataProp" : "originName"
		}, {
			"sTitle" : "操作",
			"mData" : null,
			"aTargets" : [ -1 ],
			// 自定义列的样式
			"fnRender" : function(oObj) {
				var start = '<div class="hidden-phone visible-desktop btn-group">';
				var edit = '<button class="btn btn-mini btn-warning" title="查看修改" onclick="Content.Latest.ActivityInfo.editObj(\'' + oObj.aData.id + '\')"><i class="icon-edit bigger-120"></i></button>';
				var trash = '<button class="btn btn-mini btn-danger" title="删除" onclick="Content.Latest.ActivityInfo.delObj(\'' + oObj.aData.id + '\')"><i class="icon-trash bigger-120"></i></button>';
				var download = '<button class="btn btn-mini btn-info" title="下载附件" onclick="Content.Latest.ActivityInfo.download(\'' + oObj.aData.id + '\')"><i class="icon-download-alt bigger-120"></i></button>';
				var end = '</div>';
				return start + edit + trash + download + end;
			}
		} ],

		// 多语言配置
		"oLanguage" : {
			"sProcessing" : "正在加载中......",
			"sLengthMenu" : "每页显示 _MENU_ 条记录",
			"sZeroRecords" : "对不起，查询不到相关数据！",
			"sEmptyTable" : "表中无数据存在！",
			"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
			"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
			"sSearch" : "搜索",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上一页",
				"sNext" : "下一页",
				"sLast" : "末页"
			}
		}
	});
	$('[data-rel=tooltip]').tooltip();
});