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
	alert("请选择产品包!");
}
};

//修改为允许下载状态
Content.Compress.Info.allowObj = function() {
	if(chbox()){
		var params = "?a=1";
		var checkeds = $("input[name=ids]:checked");
		for (var i = 0; i < checkeds.length; i++) {
			params += "&" + checkeds[i].name + "=" + checkeds[i].value;
		}
		openConfirmModalInFrame("您确定执行将这些文件设置成允许下载的操作吗？", function() {
			var url = $('#ctx').val() + "/pages/compress/form/allow" + params;
			$.ajax({
				"dataType" : 'json',
				"type" : "POST",
				"url" : url,
				"cache" : false,
				"success" : function(response) {
					if (response.isSuccess == "true") {
						window.parent.alertMsg('successModal', 'successMsg', response.msg);
						refreshFrameDataTableInFrame('Content.Compress.Info.oTable1');
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

//修改为禁止下载状态
Content.Compress.Info.banObj = function() {
	if(chbox()){
		var params = "?a=1";
		var checkeds = $("input[name=ids]:checked");
		for (var i = 0; i < checkeds.length; i++) {
			params += "&" + checkeds[i].name + "=" + checkeds[i].value;
		}
		openConfirmModalInFrame("您确定执行将这些文件设置成禁止下载的操作吗？", function() {
			var url = $('#ctx').val() + "/pages/compress/form/status" + params;
			$.ajax({
				"dataType" : 'json',
				"type" : "POST",
				"url" : url,
				"cache" : false,
				"success" : function(response) {
					if (response.isSuccess == "true") {
						window.parent.alertMsg('successModal', 'successMsg', response.msg);
						refreshFrameDataTableInFrame('Content.Compress.Info.oTable1');
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

//下载产品包
Content.Compress.Info.load = function(id){
	var url = $('#ctx').val()+"/pages/compress/form/download?id="+id;
	window.location=url;
};

//删除信息
Content.Compress.Info.delObj = function(id) {
	openConfirmModalInFrame("您确定删除该产品包吗？",function(){
		var url = $('#ctx').val()+"/pages/compress/form/delete?id="+id;
		$.ajax( {
			"dataType": 'json',
			"type": "POST",
			"url": url,
			"cache": false,
			"success": function(response) {
				if (response.isSuccess == "true") {
					window.parent.alertMsg('successModal', 'successMsg', response.msg);
					refreshFrameDataTableInFrame('Content.Compress.Info.oTable1');
				} else {
					window.parent.alertMsg('errorModal', 'errorMsg', response.msg);
				}
			},
			"error": function(response) {
				alert("error");
			}
		} );
	},null,null);
};

Content.Compress.Info.query = function() {
	// 重新刷新页面Table
	refreshFrameDataTableInFrame('Content.Compress.Info.oTable1');
};

Content.Compress.Info.retrieveData = function(sSource, aoData, fnCallback) {
    $.ajax( {
        "dataType": 'json',
        "type": "POST",
        "url": sSource,
        "cache": false,
        "data": aoData,
        "success": function(response) {
     	   fnCallback(response);
        },
        "error": function(response) {
     	   alert("error");
        }
    } );
};

$(function() {
	Content.Compress.Info.oTable1 = $('#table_report').dataTable( {
            "bFilter": false, //开关，是否启用客户端过滤器
            "bProcessing": true, //当datatable获取数据时候是否显示正在处理提示信息。
            "bAutoWidth": false, //自适应宽度
            "sPaginationType": "full_numbers", //分页样式
            "bServerSide": true, //从服务器端取数据
           	"sAjaxSource": $('#ctx').val()+"/pages/compress/form/manager?now=" + new Date().getTime(), //mvc后台ajax调用接口。
           	"fnServerParams": function(aoData) {
           		aoData.push({"name": "title", "value": $("#query_title").val()});
           	},
            "fnServerData": Content.Compress.Info.retrieveData,
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
    			"sTitle": "序号",
    			"mDataProp": "id"
            }, { 
        		"sTitle": "包名",
            	"mDataProp": "title"
            },{
				"sTitle": "路径",
    			"mDataProp": "path",
    			"fnRender" : function(oObj) {
    				return  oObj.aData.title + ".zip";
    			}
			},{
        		"sTitle": "操作",
        		"mData": null,
        	    "aTargets": [ -1 ],
                //自定义列的样式
                "fnRender": function (oObj) {
                	var start = '<div class="hidden-phone visible-desktop btn-group">';
                	if(0==oObj.aData.status){
                		var download = '<button class="btn btn-mini btn-warning" title="下载产品包" onclick="Content.Compress.Info.load(\'' + oObj.aData.id + '\')"><i class=" icon-arrow-down"></i></button>';
                	}
                	var trash = '<button class="btn btn-mini btn-danger" title="删除" onclick="Content.Compress.Info.delObj(\'' + oObj.aData.id + '\')"><i class="icon-trash bigger-120"></i></button>';
                    var end = '</div>';
                    return start + download + trash + end;
                }
			}],
          	//多语言配置
            "oLanguage": {
                "sProcessing": "正在加载中......",
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "对不起，查询不到相关数据！",
                "sEmptyTable": "表中无数据存在！",
                "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
                "sSearch": "搜索",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast": "末页"
                }
            }
    } );

	$('[data-rel=tooltip]').tooltip();
});