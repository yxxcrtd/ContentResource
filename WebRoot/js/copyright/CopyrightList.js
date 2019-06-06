//显示修改
Content.Copyright.AuthorInfo.editObj = function(id) {
	var url=$('#ctx').val() + "/pages/copyright/form/edit?id=" + id;
	var commonModalCss = {
		"width": "800px",
		"height": "500px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

//显示新增
Content.Copyright.AuthorInfo.addObj = function() {
	var url=$('#ctx').val() + "/pages/copyright/form/edit";
	var commonModalCss = {
		"width": "800px",
		"height": "500px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

//下载附件
Content.Copyright.AuthorInfo.download = function(id){
	var url = $('#ctx').val()+"/pages/copyright/form/download?id="+id;
	window.location=url;
};

//删除产品
Content.Copyright.AuthorInfo.delObj = function(id) {
	openConfirmModalInFrame("您确定执行删除该版权信息操作吗？", function() {
		var url = $('#ctx').val() + "/pages/copyright/form/delete?id=" + id;
		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : url,
			"cache" : false,
			"success" : function(response) {
				if (response.isSuccess == "true") {
					window.parent.alertMsg('successModal', 'successMsg', response.msg);
					refreshFrameDataTableInFrame('Content.Copyright.AuthorInfo.oTable1');
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

Content.Copyright.AuthorInfo.query = function() {
	// 重新刷新页面Table
	refreshFrameDataTableInFrame('Content.Copyright.AuthorInfo.oTable1');
};

Content.Copyright.AuthorInfo.retrieveData = function(sSource, aoData, fnCallback) {
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
	//签订时间
	$('#invoiceDate').datetimepicker({
		language : 'cn',
		pickTime : false
	}).on('changeDate', function(ev) {
		Content.Copyright.AuthorInfo.CopyrightSigningDate();
	});
	//开始时间
	$('#invoiceDate2').datetimepicker({
		language : 'cn',
		pickTime : false
	}).on('changeDate', function(ev) {
		Content.Copyright.AuthorInfo.CopyrightStartDate();
	});
	//结束时间
	$('#invoiceDate3').datetimepicker({
		language : 'cn',
		pickTime : false
	}).on('changeDate', function(ev) {
		Content.Copyright.AuthorInfo.CopyrightStopDate();
	});
	//收缩框
    $(".on").click(function(){
		$(".on-down").slideToggle();
	});
    
    Content.Copyright.AuthorInfo.oTable1 = $('#table_report').dataTable({
		"bFilter" : false, // 开关，是否启用客户端过滤器
		"bProcessing" : true, // 当datatable获取数据时候是否显示正在处理提示信息。
		"bAutoWidth" : false, // 自适应宽度
		"sPaginationType" : "full_numbers", // 分页样式
		"bServerSide" : true, // 从服务器端取数据
		"sAjaxSource" : $('#ctx').val() + "/pages/copyright/form/manager?now=" + new Date().getTime(), // mvc后台ajax调用接口。
		"fnServerParams" : function(aoData) {
			aoData.push({
				"name" : "author",
				"value" : $("#query_author").val()
			});
			aoData.push({
				"name" : "contractName",
				"value" : $("#query_name").val()
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
		"fnServerData" : Content.Copyright.AuthorInfo.retrieveData,
		"fnDrawCallback" : function(oSettings) {
			for ( var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
				$('td:eq(0)', oSettings.aoData[oSettings.aiDisplay[i]].nTr).html(i + oSettings._iDisplayStart + 1);
			}
		},
		"aoColumns" : [ {
			"sTitle" : "序号",
			"mDataProp" : "id"
        }, {
			"sTitle" : "合同状态",
			"mDataProp" : "status"
        }, {
			"sTitle" : "作者",
			"mDataProp" : "author"
        }, {
			"sTitle" : "联系方式",
			"mDataProp" : "phone"
        }, {
			"sTitle" : "合同名称",
			"mDataProp" : "contractName",
            "fnRender": function (oObj) {
                return '<span title=' + oObj.aData.contractName + ' style="display: block; width: 150px; overflow: hidden; white-space: nowrap; -o-text-overflow: ellipsis; text-overflow: ellipsis;">' + oObj.aData.contractName + '</span>';
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
			"sTitle" : "到期天数",
			"mDataProp" : "days",
    		"fnRender" : function(oObj) {
    			if(0 > oObj.aData.days){
    				return "<span style='color: #FF0000;'>还剩0天</span>";
    			}else if (4 > oObj.aData.days > 0) {
    				return "<span style='color: #FF0000;'>还剩" + oObj.aData.days + "天</span>";
    			} else {
    				return "还剩" + oObj.aData.days + "天";
    			}
    		}
		}, {
			"sTitle" : "合同附件",
			"mDataProp" : "originName"
		}, {
			"sTitle" : "操作",
			"mData" : null,
			"aTargets" : [ -1 ],
			// 自定义列的样式
			"fnRender" : function(oObj) {
				var start = '<div class="hidden-phone visible-desktop btn-group">';
				var edit = '<button class="btn btn-mini btn-warning" title="查看修改" onclick="Content.Copyright.AuthorInfo.editObj(\'' + oObj.aData.id + '\')"><i class="icon-edit bigger-120"></i></button>';
				var trash = '<button class="btn btn-mini btn-danger" title="删除" onclick="Content.Copyright.AuthorInfo.delObj(\'' + oObj.aData.id + '\')"><i class="icon-trash bigger-120"></i></button>';
				var download = '<button class="btn btn-mini btn-info" title="下载附件" onclick="Content.Copyright.AuthorInfo.download(\'' + oObj.aData.id + '\')"><i class="icon-download-alt bigger-120"></i></button>';
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