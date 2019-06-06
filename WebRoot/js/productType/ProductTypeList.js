Content.ProductType.Info = function() {
	this.editor = null;
	this.artDialog = null;
	this.oTable1 = null;
};

Content.ProductType.Info.validate = function() {
	var flag = true;
	if (!Content.ProductType.Info.ProductTypeName()) {
		flag = false;
	}
	return flag;
};

Content.ProductType.Info.ProductTypeName = function() {
	if ($("#name").val() == "") {
		$("#nameDiv").addClass("error");
		$("#nameSpan").html("类型名称不能为空！");
		return false;
	}else{
		$("#nameDiv").removeClass("error").addClass("success");
		$("#nameSpan").html("通过验证");
		return true;
	}
};

Content.ProductType.Info.showResponse = function(response, statusText) {
	Content.ProductType.Info.disableAllButton();
	if (response.isSuccess == "true") {
		ajaxAlertSuccessMsg(response.msg, true);
		refreshFrameDataTableInLayer('Content.ProductType.Info.oTable1');
		autoCloseCommonModal(5);
	} else {
		ajaxAlertErrorMsg(response.msg, true);
		Content.ProductType.Info.enableAllButton();
	}
};

Content.ProductType.Info.disableAllButton = function() {
	$("#save").attr('disabled', "true");
	$("#reset").attr('disabled', "true");
};

//显示新增
Content.ProductType.Info.addObj = function() {
	var code =  $("#code").val();
	var url = $('#ctx').val() + "/pages/productType/form/add?code=" + code;
	var commonModalCss = {
		"width": "450px",
		"margin": "100px 0 0 -450px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

//显示新增子类
Content.ProductType.Info.subtypeObj = function(id,code) {
	var url = $('#ctx').val() + "/pages/productType/form/subtype?id=" + id + "&code=" +code;
	var commonModalCss = {
		"width": "450px",
		"margin": "100px 0 0 -450px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

//显示修改
Content.ProductType.Info.modObj = function(id) {
	var url = $('#ctx').val() + "/pages/productType/form/edit?id=" + id;
	var commonModalCss = {
		"width": "450px",
		"margin": "100px 0 0 -450px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

Content.ProductType.Info.delObj = function(id) {
	openConfirmModalInFrame("您确定执行删除分类操作吗？", function() {
		var url = $('#ctx').val() + "/pages/productType/form/delete?id=" + id;
		$.ajax({
			"dataType" : 'json',
			"type" : "POST",
			"url" : url,
			"cache" : false,
			"success" : function(response) {
				if (response.isSuccess == "true") {
					window.parent.alertMsg('successModal', 'successMsg', response.msg);
					refreshFrameDataTableInFrame('Content.ProductType.Info.oTable1');
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

Content.ProductType.Info.query = function() {
	// 重新刷新页面Table
	refreshFrameDataTableInFrame('Content.ProductType.Info.oTable1');
};

Content.ProductType.Info.retrieveData = function(sSource, aoData, fnCallback) {
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
	var code =  $("#code").val();
	
	$(".on").click(function(){
		$(".on-down").slideToggle();
	});
	
	Content.ProductType.Info.oTable1 = $('#table_report').dataTable({
		"bFilter" : false, // 开关，是否启用客户端过滤器
		"bProcessing" : true, // 当datatable获取数据时候是否显示正在处理提示信息。
		"bAutoWidth" : false, // 自适应宽度
		"sPaginationType" : "full_numbers", // 分页样式
		"bServerSide" : true, // 从服务器端取数据
		"sAjaxSource" : $('#ctx').val() + "/pages/productType/form/manager?code=" + code, // mvc后台ajax调用接口。
		"fnServerParams": function(aoData) {
       		aoData.push({"name": "name", "value": $("#query_name").val()});
       	},
		"fnServerData" : Content.ProductType.Info.retrieveData,
		"fnDrawCallback" : function(oSettings) {
			for ( var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
				$('td:eq(0)', oSettings.aoData[oSettings.aiDisplay[i]].nTr).html(i + oSettings._iDisplayStart + 1);
			}
		},
		"aoColumns" : [ {
			"sTitle" : "序号",
			"mDataProp" : "id"
		}, {
            "sTitle" : "类型名称",
            "mDataProp" : "name"
        }, {
			"sTitle" : "所属大类",
			"mDataProp" : "treeCode",
               "fnRender": function (oObj) {
            	   if(null==oObj.aData.treeCode){
            		   return "";
            	   }else{
            		   return  oObj.aData.treeCode.name;
            	   }
        	}
		}, {
			"sTitle" : "操作",
			"mData" : null,
			"aTargets" : [ -1 ],
			// 自定义列的样式
			"fnRender" : function(oObj) {
				var start = '<div class="hidden-phone visible-desktop btn-group">';
				if(""==oObj.aData.treeCode){
					var subtype = '<button class="btn btn-mini btn-info" title="添加子分类" onclick="Content.ProductType.Info.subtypeObj(\'' + oObj.aData.id + '\', \'' + oObj.aData.code + '\')"><i class="icon-plus-sign bigger-120"></i></button>';
				}
				var edit = '<button class="btn btn-mini btn-warning" title="修改" onclick="Content.ProductType.Info.modObj(\'' + oObj.aData.id + '\')"><i class="icon-edit bigger-120"></i></button>';
				var trash = '<button class="btn btn-mini btn-danger" title="删除" onclick="Content.ProductType.Info.delObj(\'' + oObj.aData.id + '\')"><i class="icon-trash bigger-120"></i></button>';
				var end = '</div>';
				return start + subtype + edit + trash + end;
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