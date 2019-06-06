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
						<h1>图片信息<small> <i class="icon-double-angle-right"></i>&nbsp;&nbsp;<#if ("" == form.id || 0 == form.id)>新增图片信息<#else>修改图片信息</#if></small></h1>
					</div>
					<div class="row-fluid">
						<div class="table-header on">基本信息</div>
						<form id="PictureForm" commandName="form" class="form-horizontal" enctype="multipart/form-data">
							<div class="on-down">
								<div class="control-group" id="titleDiv">
									<label class="control-label">标题：</label>
									<div class="controls">
										<input id="title" name="obj.title" class="span6" value="${form.obj.title}" placeholder="标题" onblur="Content.Picture.PictureInfo.PictureTitle();"/>
										<span id="titleSpan" class="help-inline"></span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">产品分类：</label>
									<div class="controls">
										<select id="productType.id" name="obj.productType.id" class="span6" value="${form.obj.productType.id}">
	                                      <#if typeList??> 
	                                        <#list typeList as type>
	                                           <option value="${type.id}" <#if (type.id == form.obj.productType.id)>selected="selected"</#if>>
	                                           <#if ("" != type.treeCode.id)>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</#if>${type.name}</option>
											</#list>
										   </#if>
	                                    </select>
									</div>
								</div>
								<div class="control-group" id="keywordDiv">
									<label class="control-label">关键词：</label>
									<div class="controls">
										<input id="keyword" name="obj.keyword" class="span6" value="${form.obj.keyword}" placeholder="关键词,多个关键词以英文状态下”,“隔开"  onblur="Content.Picture.PictureInfo.PictureKeyword();"/>
										<span id="keywordSpan" class="help-inline"></span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">图片上传：</label>
									<div class="controls">
										<#if ("" != form.obj.pic)>
										   <image src="/upload/image/${form.obj.pic}" width="80">
                                           <input type="File" name="FilePic" id="FilePic" class="span6"/>
                                        <#else>
                                           <input type="File" name="FilePic" id="FilePic" class="span6"/>
                                        </#if>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">备注：</label>
									<div class="controls">
										<textarea name="obj.remark" id="remark" class="span10 h100">${form.obj.remark}</textarea>
									</div>
								</div>
								<input type="hidden" name="obj.id" value="${form.obj.id}" />
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
		<script type="text/javascript" src="${request.contextPath}/js/picture/PictureEdit.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/picture/PictureList.js"></script>
	</body>
</html>