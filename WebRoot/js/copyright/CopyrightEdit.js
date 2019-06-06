Content.Copyright.AuthorInfo = function() {
	this.editor = null;
	this.artDialog = null;
	this.oTable1 = null;
};

Content.Copyright.AuthorInfo.validate = function() {
	var flag = true;
	if (!Content.Copyright.AuthorInfo.CopyrightAuthor()) {
		flag = false;
	}
	if (!Content.Copyright.AuthorInfo.CopyrightPhone()) {
		flag = false;
	}
	if (!Content.Copyright.AuthorInfo.CopyrightPerformer()) {
		flag = false;
	}
	if (!Content.Copyright.AuthorInfo.CopyrightContractName()) {
		flag = false;
	}
	if (!Content.Copyright.AuthorInfo.CopyrightMargin()) {
		flag = false;
	}
	if (!Content.Copyright.AuthorInfo.LargeAmount()) {
		flag = false;
	}
	if (!Content.Copyright.AuthorInfo.MinAmount()) {
		flag = false;
	}
	if (!Content.Copyright.AuthorInfo.UploadFile()) {
		flag = false;
	}
	return flag;
};

Content.Copyright.AuthorInfo.CopyrightAuthor = function() {
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

Content.Copyright.AuthorInfo.CopyrightPhone = function() {
	if ($("#phone").val() == "") {
		$("#phoneDiv").addClass("error");
		$("#phoneSpan").html("联系方式不能为空！");
		return false;
	}else{
		$("#phoneDiv").removeClass("error").addClass("success");
		$("#phoneSpan").html("通过验证");
		return true;
	}
};

Content.Copyright.AuthorInfo.CopyrightPerformer = function() {
	if ($("#performer").val() == "") {
		$("#performerDiv").addClass("error");
		$("#performerSpan").html("版权归属不能为空！");
		return false;
	}else{
		$("#performerDiv").removeClass("error").addClass("success");
		$("#performerSpan").html("通过验证");
		return true;
	}
};

Content.Copyright.AuthorInfo.CopyrightContractName = function() {
	if ($("#contractName").val() == "") {
		$("#contractNameDiv").addClass("error");
		$("#contractNameSpan").html("合同名称不能为空！");
		return false;
	}else{
		$("#contractNameDiv").removeClass("error").addClass("success");
		$("#contractNameSpan").html("通过验证");
		return true;
	}
};

Content.Copyright.AuthorInfo.CopyrightMargin = function() {
	if($("#margin").val() != ""){
		var regex = /^\d+(\.\d{2})?$/;
		var val = $("#margin").val();
		if(!regex.test(val)) {
			$("#marginDiv").addClass("error");
			$("#marginSpan").html("请输入数字类型");	
			return false;
		} else if(val>parseInt('100000000')) {
			$("#marginDiv").addClass("error");
			$("#marginSpan").html("合同保证金最大值不能超过100,000,000!");
			return false;
		}else{
			$("#marginDiv").removeClass("error").addClass("success");
			$("#marginSpan").html("通过验证");
			return true;
		}
	}else{
		$("#marginDiv").removeClass("error").addClass("success");
		$("#marginSpan").html("通过验证");
		return true;
	}
};

Content.Copyright.AuthorInfo.LargeAmount = function() {
	if($("#largeAmount").val() != ""){
		var regex = /^\d+(\.\d{2})?$/;
		var val = $("#largeAmount").val();
		if(!regex.test(val)) {
			$("#largeAmountDiv").addClass("error");
			$("#largeAmountSpan").html("请输入数字类型");	
			return false;
		} else if(val>parseInt('100000000')) {
			$("#largeAmountDiv").addClass("error");
			$("#largeAmountSpan").html("合同最多金额最大值不能超过100,000,000!");
			return false;
		}else{
			$("#largeAmountDiv").removeClass("error").addClass("success");
			$("#largeAmountSpan").html("通过验证");
			return true;
		}
	}else{
		$("#largeAmountDiv").removeClass("error").addClass("success");
		$("#largeAmountSpan").html("通过验证");
		return true;
	}
};

Content.Copyright.AuthorInfo.MinAmount = function() {
	if($("#minAmount").val() != ""){
		var regex = /^\d+(\.\d{2})?$/;
		var val = $("#minAmount").val();
		if(!regex.test(val)) {
			$("#minAmountDiv").addClass("error");
			$("#minAmountSpan").html("请输入数字类型");	
			return false;
		} else if(val>parseInt('100000000')) {
			$("#minAmountDiv").addClass("error");
			$("#minAmountSpan").html("合同最小金额合同保证金最大值不能超过100,000,000!");
			return false;
		}else{
			$("#minAmountDiv").removeClass("error").addClass("success");
			$("#minAmountSpan").html("通过验证");
			return true;
		}
	}else{
		$("#minAmountDiv").removeClass("error").addClass("success");
		$("#minAmountSpan").html("通过验证");
		return true;
	}
};

Content.Copyright.AuthorInfo.UploadFile = function() {
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

Content.Copyright.AuthorInfo.CopyrightStopDate = function() {
	if ($("#stopStr").val().trim()<$("#startStr").val().trim()) {
		alert("结束日期不能小于开始日期");
	}
};

Content.Copyright.AuthorInfo.showResponse = function(response, statusText) {
	Content.Copyright.AuthorInfo.disableAllButton();
	if (response.isSuccess == "true") {
		ajaxAlertSuccessMsg(response.msg, true);
		refreshFrameDataTableInLayer('Content.Copyright.AuthorInfo.oTable1');
		autoCloseCommonModal(5);
	} else {
		ajaxAlertErrorMsg(response.msg, true);
		Content.Copyright.AuthorInfo.enableAllButton();
	}
};

Content.Copyright.AuthorInfo.disableAllButton = function() {
	$("#save").attr('disabled', "true");
	$("#reset").attr('disabled', "true");
};

//编辑
$(function() {

	var options = {
		beforeSubmit : Content.Copyright.AuthorInfo.validate,
		success : Content.Copyright.AuthorInfo.showResponse,
		url : $('#ctx').val() + "/pages/copyright/form/save",
		type : 'post',
		clearForm : false,
		timeout : 3000
	};
	$('#CopyrightForm').ajaxForm(options);
});