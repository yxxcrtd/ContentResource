//全选或反选
$(function(){
    $("button[name='checkAll']").click(function(){
    	$("input[type='checkbox']").each(function(){
    		if(this.checked){
        		this.checked = false;
        	}else{
        		this.checked = true;
        	}
    	});
    	var checked = $("input[type='checkbox']").attr("checked");
		$("input[name='ids']").attr("checked",checked);
    });
});
chbox = function(){
var sign = false;
var inputs = document.getElementsByTagName('input');
for(var i=0;i<inputs.length;i++){
		var obj = inputs[i];
		if(obj.type=='checkbox'){
			if(obj.checked==true){
			   sign = true;
			   return true;
		    }
	    } 
}
if(sign == false){
	alert("请选择图片!");
}
};

//删除信息
Content.Picture.PictureInfo.delObj = function() {
	if(chbox()){
		var params = "?a=1";
		var checkeds = $("input[name=ids]:checked");
		for (var i = 0; i < checkeds.length; i++) {
			params += "&" + checkeds[i].name + "=" + checkeds[i].value;
		}
		openConfirmModalInFrame("您确定执行删除这些图片信息操作吗？", function() {
			var url = $('#ctx').val() + "/pages/picture/form/delete" + params;
			$.ajax({
				"dataType" : 'json',
				"type" : "POST",
				"url" : url,
				"cache" : false,
				"success" : function(response) {
					if (response.isSuccess == "true") {
						window.parent.alertMsg('successModal', 'successMsg', response.msg);
						refreshFrameDataTableInFrame('Content.Picture.PictureInfo.oTable1');
					} else {
						window.parent.alertMsg('errorModal', 'errorMsg', response.msg);
					}
				},
				"error" : function(response) {
					alert("error");
				}
			});
		}, null, null);
	}
};

//显示修改
Content.Picture.PictureInfo.editObj = function(id) {
	var url=$('#ctx').val() + "/pages/picture/form/edit?id=" + id;
	var commonModalCss = {
		"width": "600px",
		"height": "450px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

//显示新增
Content.Picture.PictureInfo.addObj = function() {
	var url=$('#ctx').val() + "/pages/picture/form/edit";
	var commonModalCss = {
		"width": "500px",
		"height": "400px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

Content.Picture.PictureInfo.query = function() {
	// 重新刷新页面Table
	refreshFrameDataTableInFrame('Content.Picture.PictureInfo.oTable1');
};

Content.Picture.PictureInfo.retrieveData = function(sSource, aoData, fnCallback) {
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
	
    $(".on").click(function(){
		$(".on-down").slideToggle();
	});
    
    Content.Picture.PictureInfo.oTable1 = $('#table_report').dataTable({
		"bFilter" : false, // 开关，是否启用客户端过滤器
		"bProcessing" : true, // 当datatable获取数据时候是否显示正在处理提示信息。
		"bAutoWidth" : false, // 自适应宽度
		"sPaginationType" : "full_numbers", // 分页样式
		"bServerSide" : true, // 从服务器端取数据
		"sAjaxSource" : $('#ctx').val() + "/pages/picture/form/manager?now=" + new Date().getTime(), // mvc后台ajax调用接口。
		"fnServerParams" : function(aoData) {
			aoData.push({
				"name" : "title",
				"value" : $("#query_title").val()
			});
			aoData.push({
				"name" : "keyword",
				"value" : $("#query_keyword").val()
			});
			aoData.push({
				"name" : "productTypeId",
				"value" : $("#query_type").val()
			});
		},
		"fnServerData" : Content.Picture.PictureInfo.retrieveData,
		"fnDrawCallback" : function(oSettings) {
			for ( var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
				$(
						'td:eq(1)',
						oSettings.aoData[oSettings.aiDisplay[i]].nTr)
						.html(i + oSettings._iDisplayStart + 1);
			}
		},
		"aoColumns" : [{
			"sTitle" : "选择",
			"mData" : null,
			"bSortable" : false,
			"sClass" : "center",
			"fnRender" : function(oObj) {
				var sReturn = "";
				sReturn = '<label><input type="checkbox" name="ids" style="display:none;" value="' + oObj.aData.id + '" /><span class="lbl"></span></label>';
				return sReturn;
			}
		}, {	
			"sTitle" : "序号",
			"mDataProp" : "id"
		}, {
            "sTitle" : "类型",
            "mDataProp" : "productType.name"
        }, {
			"sTitle" : "标题",
			"mDataProp" : "title"
		}, {
			"sTitle" : "图片",
			"mDataProp" : "pic",
               "fnRender": function (oObj) {
            	 return '<img src="'+"/upload/image/" +oObj.aData.pic + '" width="60" onmouseover="width=240;height=180;" onmouseout="this.width=60;"/>';
        	}
		}, {
			"sTitle" : "操作",
			"mData" : null,
			"aTargets" : [ -1 ],
			// 自定义列的样式
			"fnRender" : function(oObj) {
				var start = '<div class="hidden-phone visible-desktop btn-group">';
				var edit = '<button class="btn btn-mini btn-warning" title="修改" onclick="Content.Picture.PictureInfo.editObj(\'' + oObj.aData.id + '\')"><i class="icon-edit bigger-120"></i></button>';
				var end = '</div>';
				return start + edit + end;
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