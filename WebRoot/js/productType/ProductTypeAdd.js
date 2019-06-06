//新增
$(function() {
	var options = {
		beforeSubmit : Content.ProductType.Info.validate,
		success : Content.ProductType.Info.showResponse,
		url : $('#ctx').val() + "/pages/productType/form/save",
		type : 'post',
		clearForm : false,
		timeout : 3000
	};
	$('#ProductTypeForm').ajaxForm(options);
});