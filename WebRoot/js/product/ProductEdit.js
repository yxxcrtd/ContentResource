Content.Product.ProductInfo = function() {
	this.editor = null;
	this.artDialog = null;
	this.oTable1 = null;
};

Content.Product.ProductInfo.validate = function() {
	var flag = true;
	if (!Content.Product.ProductInfo.ProductName()) {
		flag = false;
	}
	if (!Content.Product.ProductInfo.ProductISBN()) {
		flag = false;
	}
	if (!Content.Product.ProductInfo.ProductPrice()) {
		flag = false;
	}
	if (!Content.Product.ProductInfo.ProductPageNum()) {
		flag = false;
	}
	if (!Content.Product.ProductInfo.ProductAuthor()) {
		flag = false;
	}
	return flag;
};

Content.Product.ProductInfo.ProductName = function() {
	if ($("#title").val() == "") {
		$("#titleDiv").addClass("error");
		$("#titleSpan").html("书名不能为空！");
		return false;
	}else{
		$("#titleDiv").removeClass("error").addClass("success");
		$("#titleSpan").html("通过验证");
		return true;
	}
};

Content.Product.ProductInfo.ProductISBN = function() {
	if ($("#isbn").val() == "") {
		$("#isbnDiv").addClass("error");
		$("#isbnSpan").html("ISBN不能为空！");
		return false;
	}else{
		$("#isbnDiv").removeClass("error").addClass("success");
		$("#isbnSpan").html("通过验证");
		return true;
	}
};

Content.Product.ProductInfo.ProductAuthor = function() {
	if ($("#author").val() == "") {
		$("#authorDiv").addClass("error");
		$("#authorSpan").html("作者不能为空！");
		return false;
	}else{
		$("#authorDiv").removeClass("error").addClass("success");
		$("#authorSpan").html("通过验证");
		return true;
	}
};

Content.Product.ProductInfo.ProductPrice = function() {
	var regex = /^\d+(\.\d{2})?$/;
	var val = $("#price").val();
	if ($("#price").val() == "") {
		$("#priceDiv").addClass("error");
		$("#priceSpan").html("价格不能为空！");
		return false;
	} else if(!regex.test(val)) {
		$("#priceDiv").addClass("error");
		$("#priceSpan").html("请输入数字类型，最多支持两位小数！");	
		return false;
	} else if(val>parseInt('100000000')) {
		$("#priceDiv").addClass("error");
		$("#priceSpan").html("价格最大值不能超过100,000,000！");
		return false;
	}else{
		$("#priceDiv").removeClass("error").addClass("success");
		$("#priceSpan").html("通过验证");
		return true;
	}
};

Content.Product.ProductInfo.ProductPageNum = function() {
	if($("#pageNum").val() != ""){
		var regex = /^[0-9]*$/;
		var val = $("#pageNum").val();
		if(!regex.test(val)) {
			$("#pageNumDiv").addClass("error");
			$("#pageNumSpan").html("请输入数字类型");	
			return false;
		}else{
			$("#pageNumDiv").removeClass("error").addClass("success");
			$("#pageNumSpan").html("通过验证");
			return true;
		}
	}else{
		$("#pageNumDiv").removeClass("error").addClass("success");
		$("#pageNumSpan").html("通过验证");
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

//编辑
$(function() {
	
	$('#invoiceDate').datetimepicker({
		language : 'cn',
		pickTime : false
	}).on('changeDate', function(ev) {
		Content.Product.ProductInfo.datePublication();
	});
	
	var options = {
		beforeSubmit : Content.Product.ProductInfo.validate,
		success : Content.Product.ProductInfo.showResponse,
		url : $('#ctx').val() + "/pages/product/form/save",
		type : 'post',
		clearForm : false,
		timeout : 3000
	};
	$('#ProductForm').ajaxForm(options);
});