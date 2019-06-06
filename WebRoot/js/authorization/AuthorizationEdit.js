Content.Authorization.Info = function() {
	this.editor = null;
	this.artDialog = null;
	this.oTable1 = null;
};

Content.Authorization.Info.validate = function() {
	var flag = true;
	if (!Content.Authorization.Info.Company()) {
		flag = false;
	}
	if (!Content.Authorization.Info.Representative()) {
		flag = false;
	}
	if (!Content.Authorization.Info.Phone()) {
		flag = false;
	}
	if (!Content.Authorization.Info.Performer()) {
		flag = false;
	}
	if (!Content.Authorization.Info.ContractName()) {
		flag = false;
	}
	if (!Content.Authorization.Info.AgencyAmount()) {
		flag = false;
	}
	if (!Content.Authorization.Info.Margin()) {
		flag = false;
	}
	if (!Content.Authorization.Info.LargeAmount()) {
		flag = false;
	}
	if (!Content.Authorization.Info.MinAmount()) {
		flag = false;
	}
	if (!Content.Authorization.Info.UploadFile()) {
		flag = false;
	}
	return flag;
};

Content.Authorization.Info.Company = function() {
	if ($("#company").val() == "") {
		$("#companyDiv").addClass("error");
		$("#companySpan").html("授权单位不能为空！");
		return false;
	}else{
		$("#companyDiv").removeClass("error").addClass("success");
		$("#companySpan").html("通过验证");
		return true;
	}
};

Content.Authorization.Info.Representative = function() {
	if ($("#representative").val() == "") {
		$("#representativeDiv").addClass("error");
		$("#representativeSpan").html("法定代表人或法人代表不能为空！");
		return false;
	}else{
		$("#representativeDiv").removeClass("error").addClass("success");
		$("#representativeSpan").html("通过验证");
		return true;
	}
};

Content.Authorization.Info.Phone = function() {
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

Content.Authorization.Info.Performer = function() {
	if ($("#performer").val() == "") {
		$("#performerDiv").addClass("error");
		$("#performerSpan").html("被授权方不能为空！");
		return false;
	}else{
		$("#performerDiv").removeClass("error").addClass("success");
		$("#performerSpan").html("通过验证");
		return true;
	}
};

Content.Authorization.Info.ContractName = function() {
	if ($("#contractName").val() == "") {
		$("#contractNameDiv").addClass("error");
		$("#contractNameSpan").html("授权合同名称不能为空！");
		return false;
	}else{
		$("#contractNameDiv").removeClass("error").addClass("success");
		$("#contractNameSpan").html("通过验证");
		return true;
	}
};

Content.Authorization.Info.AgencyAmount = function() {
	if($("#agencyAmount").val() != ""){
		var regex = /^\d+(\.\d{2})?$/;
		var val = $("#agencyAmount").val();
		if(!regex.test(val)) {
			$("#agencyAmountDiv").addClass("error");
			$("#agencyAmountSpan").html("请输入数字类型");	
			return false;
		} else if(val>parseInt('100000000')) {
			$("#agencyAmountDiv").addClass("error");
			$("#agencyAmountSpan").html("代理费最大值不能超过100,000,000!");
			return false;
		}else{
			$("#agencyAmountDiv").removeClass("error").addClass("success");
			$("#agencyAmountSpan").html("通过验证");
			return true;
		}
	}else{
		$("#agencyAmountDiv").removeClass("error").addClass("success");
		$("#agencyAmountSpan").html("通过验证");
		return true;
	}
};

Content.Authorization.Info.Margin = function() {
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

Content.Authorization.Info.LargeAmount = function() {
	if($("#margin").val() != ""){
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

Content.Authorization.Info.MinAmount = function() {
	if($("#margin").val() != ""){
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

Content.Authorization.Info.UploadFile = function() {
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

Content.Authorization.Info.StopDate = function() {
	if ($("#stopStr").val().trim()<$("#startStr").val().trim()) {
		alert("结束日期不能小于开始日期");
	}
};

Content.Authorization.Info.showResponse = function(response, statusText) {
	Content.Authorization.Info.disableAllButton();
	if (response.isSuccess == "true") {
		ajaxAlertSuccessMsg(response.msg, true);
		refreshFrameDataTableInLayer('Content.Authorization.Info.oTable1');
		autoCloseCommonModal(5);
	} else {
		ajaxAlertErrorMsg(response.msg, true);
		Content.Authorization.Info.enableAllButton();
	}
};

Content.Authorization.Info.disableAllButton = function() {
	$("#save").attr('disabled', "true");
	$("#reset").attr('disabled', "true");
};

//编辑
$(function() {
	
	var options = {
		beforeSubmit : Content.Authorization.Info.validate,
		success : Content.Authorization.Info.showResponse,
		url : $('#ctx').val() + "/pages/authorization/form/save",
		type : 'post',
		clearForm : false,
		timeout : 3000
	};
	$('#AuthorizationForm').ajaxForm(options);
});