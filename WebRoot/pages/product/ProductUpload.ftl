<#include "Context.ftl" />
<#assign ingentatag=JspTaglibs["/WEB-INF/taglib/ingenta-taglib.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>
	
	<body>
		<div class="clearfix">
			<#include "AjaxMsg.ftl" />
			<div id="page-content" class="clearfix">
				<div class="row-fluid">
					<div class="page-header position-relative">
						<h1>图书信息<small> <i class="icon-double-angle-right"></i>&nbsp;&nbsp;Excel导入</small></h1>
					</div>
					<div class="row-fluid">
						<div class="table-header on">基本信息</div>
						<form id="ProductForm" commandName="form" class="form-horizontal" enctype="multipart/form-data">
							<div class="on-down">
								<div class="control-group" id="txtFileDiv">
									<label class="control-label">Excel文件上传：</label>
									<div class="controls">
                                           <input type="File" name="txtFile" id="txtFile" class="span6" onblur="Content.Product.ProductInfo.UploadDown();" />
                                           <span id="txtFileSpan" class="help-inline"></span>
									</div>
								</div>
							</div>
							<div class="form-actions" style="text-align: center; padding-left:0px;">
								<button class="btn btn-success" type="submit" id="save"><i class="icon-save bigger-110"></i> 保存</button>&nbsp; &nbsp; &nbsp;
								<button class="btn btn-inverse" type="reset"><i class="icon-undo bigger-110"></i> 清空</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/product/ProductUpload.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/product/ProductList.js"></script>
	</body>
</html>