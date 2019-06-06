Content.Product.ProductInfo.validate = function() {
	var flag = true;
	if (!Content.Product.ProductInfo.UploadXml()) {
		flag = false;
	}
	return flag;
};

Content.Product.ProductInfo.UploadXml = function() {
	var val = $("#xmlFile").val().substring($("#xmlFile").val().lastIndexOf(".") + 1);
	if ($("#xmlFile").val() == "") {
		$("#xmlFileDiv").addClass("error");
		$("#xmlFileSpan").html("上传文件不能为空！");
		return false;
	}else if(!(val=="xml"||val=="XML")){
		$("#xmlFileDiv").addClass("error");
		$("#xmlFileSpan").html("请上传正确的xml或XML格式的文件！");
		return false;
	}else{
		$("#xmlFileDiv").removeClass("error").addClass("success");
		$("#xmlFileSpan").html("通过验证");
		return true;
	}
};

Content.Product.ProductInfo.showResponse = function(response, statusText) {
	Content.Product.ProductInfo.disableAllButton();
	if (response.isSuccess == "true") {
		ajaxAlertSuccessMsg(response.msg, true);
		refreshFrameDataTableInLayer('Content.Product.ProductInfo.oTable1');
		autoCloseCommonModal(5);
	} else {
		ajaxAlertErrorMsg(response.msg, true);
		Content.Product.ProductInfo.enableAllButton();
	}
};

Content.Product.ProductInfo.disableAllButton = function() {
	$("#save").attr('disabled', "true");
	$("#reset").attr('disabled', "true");
};

$(function() {
	var options = {
		beforeSubmit : Content.Product.ProductInfo.validate,
		success : Content.Product.ProductInfo.showResponse,
		url : $('#ctx').val() + '/pages/product/form/uploadXml',
		type : 'post',
		clearForm : false,
		timeout : 30000
	};
	$('#ProductForm').ajaxForm(options);
});