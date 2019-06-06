Content.Latest.ActivityInfo = function() {
	this.editor = null;
	this.artDialog = null;
	this.oTable1 = null;
};

Content.Latest.ActivityInfo.validate = function() {
	var flag = true;
	if (!Content.Latest.ActivityInfo.LatestTitle()) {
		flag = false;
	}
	if (!Content.Latest.ActivityInfo.UploadFile()) {
		flag = false;
	}
	return flag;
};

Content.Latest.ActivityInfo.LatestTitle = function() {
	if ($("#title").val() == "") {
		$("#titleDiv").addClass("error");
		$("#titleSpan").html("活动主题不能为空！");
		return false;
	}else{
		$("#titleDiv").removeClass("error").addClass("success");
		$("#titleSpan").html("通过验证");
		return true;
	}
};

Content.Latest.ActivityInfo.UploadFile = function() {
	if ($("#UploadFile").val() != "") {
		var val = $("#UploadFile").val().substring($("#UploadFile").val().lastIndexOf(".") + 1);
		if(!(val=="zip"||val=="ZIP"||val=="rar"||val=="RAR")){
			$("#UploadFileDiv").addClass("error");
			$("#UploadFileSpan").html("请上传正确的zip/rar格式压缩包！");
			return false;
		}else{
			$("#UploadFileDiv").removeClass("error").addClass("success");
			$("#UploadFileSpan").html("通过验证");
			return true;
		}
	}else{
		$("#UploadFileDiv").removeClass("error").addClass("success");
		$("#UploadFileSpan").html("通过验证");
		return true;
	}
};

Content.Latest.ActivityInfo.StopOn = function() {
	if ($("#stopStr").val().trim()<$("#startStr").val().trim()) {
		alert("结束日期不能小于开始日期");
	}
};

Content.Latest.ActivityInfo.showResponse = function(response, statusText) {
	Content.Latest.ActivityInfo.disableAllButton();
	if (response.isSuccess == "true") {
		ajaxAlertSuccessMsg(response.msg, true);
		refreshFrameDataTableInLayer('Content.Latest.ActivityInfo.oTable1');
		autoCloseCommonModal(5);
	} else {
		ajaxAlertErrorMsg(response.msg, true);
		Content.Latest.ActivityInfo.enableAllButton();
	}
};

Content.Latest.ActivityInfo.disableAllButton = function() {
	$("#save").attr('disabled', "true");
	$("#reset").attr('disabled', "true");
};

//编辑
$(function() {

	var options = {
		beforeSubmit : Content.Latest.ActivityInfo.validate,
		success : Content.Latest.ActivityInfo.showResponse,
		url : $('#ctx').val() + "/pages/latest/form/save",
		type : 'post',
		clearForm : false,
		timeout : 3000
	};
	$('#LatestForm').ajaxForm(options);
});