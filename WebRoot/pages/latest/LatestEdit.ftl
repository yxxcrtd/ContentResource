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
						<h1>最新活动信息<small> <i class="icon-double-angle-right"></i>&nbsp;&nbsp;<#if ("" == form.id || 0 == form.id)>新增最新活动信息<#else>修改最新活动信息</#if></small></h1>
					</div>
					<div class="row-fluid">
						<div class="table-header on">基本信息</div>
						<form id="LatestForm" commandName="form" class="form-horizontal" enctype="multipart/form-data">
							<div class="on-down">
								<div class="control-group" id="titleDiv">
									<label class="control-label">活动主题：</label>
									<div class="controls">
										<input id="title" name="obj.title" class="span6" value="${form.obj.title}" placeholder="活动主题" onblur="Content.Latest.ActivityInfo.LatestTitle();"/>
										<span id="titleSpan" class="help-inline"></span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">简介：</label>
									<div class="controls">
										<textarea name="obj.content" id="content" class="span10 h100">${form.obj.content}</textarea>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">负责人：</label>
									<div class="controls">
										<input id="head" name="obj.head" class="span6" value="${form.obj.head}" placeholder="活动负责人" />
										<span class="help-inline"></span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">开始时间：</label>
									<div class="controls" id="invoiceDate">
										<input name="obj.startStr" id="startStr" class="span6" placeholder="活动开始时间" value="${form.obj.startDate}" data-format="yyyy-MM-dd" onblur="Content.Latest.ActivityInfo.StartOn();"/>
                                        <span class="add-on"> <i data-time-icon="icon-time" data-date-icon="icon-calendar" > </i>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">结束时间：</label>
									<div class="controls" id="invoiceDate2">
										<input name="obj.stopStr" id="stopStr" class="span6" placeholder="活动结束时间" value="${form.obj.stopDate}" data-format="yyyy-MM-dd" onblur="Content.Latest.ActivityInfo.StopOn();"/>
                                        <span class="add-on"> <i data-time-icon="icon-time" data-date-icon="icon-calendar" > </i>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">主题图片：</label>
									<div class="controls">
										<#if ("" != form.obj.picture)>
										   <image src="/upload/latest/${form.obj.picture}" width="80">
                                           <input type="File" name="FilePic" id="FilePic" class="span6"/>
                                        <#else>
                                           <input type="File" name="FilePic" id="FilePic" class="span6"/>
                                        </#if>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">附件分类：</label>
									<div class="controls">
										<select id="type" name="obj.type" class="span6" value="${form.obj.type}">
					                        <#list ["综合", "文档" , "照片", "图片" , "音视频"] as t>
						                        <option <#if (t == form.obj.type)>selected="selected"</#if>>${t}</option>
					                        </#list>
			                            </select>
									</div>
								</div>
								<div class="control-group" id="UploadFileDiv">
									<label class="control-label">附件：</label>
									<div class="controls">
										<#if ("" != form.obj.originName)>
										   ${form.obj.originName}&nbsp;&nbsp;
                                           <input type="File" name="UploadFile" id="UploadFile" class="span6" onblur="Content.Latest.ActivityInfo.UploadFile();"/>
                                        <#else>
                                           <input type="File" name="UploadFile" id="UploadFile" class="span6" onblur="Content.Latest.ActivityInfo.UploadFile();"/><span style="color:red;">请上传zip/rar压缩包</span>
                                        </#if>
                                        <span id="UploadFileSpan" class="help-inline"></span>
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
		<script type="text/javascript" src="${request.contextPath}/js/latest/LatestEdit.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/latest/LatestList.js"></script>
	</body>
</html>