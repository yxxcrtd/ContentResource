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

//保存信息
$(function() {
	var options = {
		success : Content.Product.ProductInfo.showResponse,
		url : $('#ctx').val() + '/pages/compress/form/save',
		type : 'post',
		clearForm : false,
		timeout : 3000
	};
	$('#CompressForm').ajaxForm(options);
});