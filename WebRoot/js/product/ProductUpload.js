Content.Product.ProductInfo.validateUpload = function() {
	var flag = true;
	if (!Content.Product.ProductInfo.UploadDown()) {
		flag = false;
	}
	return flag;
};

Content.Product.ProductInfo.UploadDown = function() {
	var val = $("#txtFile").val().substring($("#txtFile").val().lastIndexOf(".") + 1);
	if ($("#txtFile").val() == "") {
		$("#txtFileDiv").addClass("error");
		$("#txtFileSpan").html("上传文件不能为空！");
		return false;
	}else if(!(val=="xls"||val=="xlsx"||val=="XLS"||val=="XLSX")){
		$("#txtFileDiv").addClass("error");
		$("#txtFileSpan").html("请上传xls或xlsx格式的文件！");
		return false;
	}else{
		$("#txtFileDiv").removeClass("error").addClass("success");
		$("#txtFileSpan").html("通过验证");
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
		beforeSubmit : Content.Product.ProductInfo.validateUpload,
		success : Content.Product.ProductInfo.showResponse,
		url : $('#ctx').val() + '/pages/product/form/uploadAdd',
		type : 'post',
		clearForm : false,
		timeout : 30000
	};
	$('#ProductForm').ajaxForm(options);
});