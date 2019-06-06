Content.Picture.PictureInfo = function() {
	this.editor = null;
	this.artDialog = null;
	this.oTable1 = null;
};

Content.Picture.PictureInfo.validate = function() {
	var flag = true;
	if (!Content.Picture.PictureInfo.PictureTitle()) {
		flag = false;
	}
	if (!Content.Picture.PictureInfo.PictureKeyword()) {
		flag = false;
	}
	return flag;
};

Content.Picture.PictureInfo.PictureTitle = function() {
	if ($("#title").val() == "") {
		$("#titleDiv").addClass("error");
		$("#titleSpan").html("标题不能为空！");
		return false;
	}else{
		$("#titleDiv").removeClass("error").addClass("success");
		$("#titleSpan").html("通过验证");
		return true;
	}
};

Content.Picture.PictureInfo.PictureKeyword = function() {
	if ($("#keyword").val() == "") {
		$("#keywordDiv").addClass("error");
		$("#keywordSpan").html("关键词不能为空！");
		return false;
	}else{
		$("#keywordDiv").removeClass("error").addClass("success");
		$("#keywordSpan").html("通过验证");
		return true;
	}
};

Content.Picture.PictureInfo.showResponse = function(response, statusText) {
	Content.Picture.PictureInfo.disableAllButton();
	if (response.isSuccess == "true") {
		ajaxAlertSuccessMsg(response.msg, true);
		refreshFrameDataTableInLayer('Content.Picture.PictureInfo.oTable1');
		autoCloseCommonModal(5);
	} else {
		ajaxAlertErrorMsg(response.msg, true);
		Content.Picture.PictureInfo.enableAllButton();
	}
};

Content.Picture.PictureInfo.disableAllButton = function() {
	$("#save").attr('disabled', "true");
	$("#reset").attr('disabled', "true");
};

//编辑
$(function() {

	var options = {
		beforeSubmit : Content.Picture.PictureInfo.validate,
		success : Content.Picture.PictureInfo.showResponse,
		url : $('#ctx').val() + "/pages/picture/form/save",
		type : 'post',
		clearForm : false,
		timeout : 3000
	};
	$('#PictureForm').ajaxForm(options);
});