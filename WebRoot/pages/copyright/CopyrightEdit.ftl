<#include "Context.ftl" />
<#assign ingentatag=JspTaglibs["/WEB-INF/taglib/ingenta-taglib.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
		<link href="${request.contextPath}/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${request.contextPath}/css/font-awesome.min.css" />
		<!--page specific plugin styles-->
		<link rel="stylesheet" href="${request.contextPath}/css/colorbox.css" type="text/css" />
	    <link rel="stylesheet" href="${request.contextPath}/css/jquery-ui-1.10.3.custom.min.css" />
	    <link rel="stylesheet" href="${request.contextPath}/css/chosen.css" />
	    <link rel="stylesheet" href="${request.contextPath}/css/bootstrap-datetimepicker.min.css" />
	    <link rel="stylesheet" href="${request.contextPath}/css/daterangepicker.css" />
	    <link rel="stylesheet" href="${request.contextPath}/css/colorpicker.css" />    
		<!--fonts-->

		<!--ace styles-->
		<link rel="stylesheet" href="${request.contextPath}/css/ace.min.css" />
		<link rel="stylesheet" href="${request.contextPath}/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="${request.contextPath}/css/ace-skins.min.css" />
		<link rel="stylesheet" href="${request.contextPath}/css/ColReorder.css" />
		<link rel="stylesheet" href="${request.contextPath}/css/ColVis.css" />
		<link rel="stylesheet" href="${request.contextPath}/css/ace.digital.css" />
		<link rel="stylesheet" href="${request.contextPath}/css/ace.new.min.css" />
	</head>
<body>
    <div class="clearfix">
    <#include "AjaxMsg.ftl" />
      <div id="page-content" class="clearfix">
          <div class="row-fluid">
              <div class="row-fluid">
                 <form id="CopyrightForm" commandName="form" class="form-horizontal" enctype="multipart/form-data">
                   <div class="table-header on" id="baseInfoDiv"><i class="icon-caret-down"></i>&nbsp;&nbsp;作者版权基本信息</div>
                    <div id="baseInfoContentDiv" class="on-down">
                       <div class="row-fluid">
                           <div class="control-group span6" id="authorDiv">
                              <label class="control-label" for="form-field-1">作者：</label>
                              <div class="controls">
                                 <input name="obj.author" id="author" class="span8" placeholder="版权法人" value="${form.obj.author}" onblur="Content.Copyright.AuthorInfo.CopyrightAuthor();"/>
                                 <span id="authorSpan" class="help-inline"></span>
                              </div>
                           </div>
                           <div class="control-group span6" id="phoneDiv">
                              <label class="control-label" for="form-field-2">联系方式：</label>
                              <div class="controls">
                                 <input name="obj.phone" id="phone" class="span8" placeholder="联系方式" value="${form.obj.phone}" onblur="Content.Copyright.AuthorInfo.CopyrightPhone();"/>
                                 <span id="phoneSpan" class="help-inline"></span>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-1">地址：</label>
                              <div class="controls">
                                 <input name="obj.address" id="address" class="span8" placeholder="作者真实居住地址" value="${form.obj.address}"/>
                              </div>
                           </div>
                           <div class="control-group span6" id="performerDiv">
                              <label class="control-label" for="form-field-2">版权归属：</label>
                              <div class="controls">
                                 <input name="obj.performer" id="performer" class="span8" placeholder="版权执行后版权所有人" value="${form.obj.performer}" onblur="Content.Copyright.AuthorInfo.CopyrightPerformer();"/>
                                 <span id="performerSpan" class="help-inline"></span>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-1">版权所在地：</label>
                              <div class="controls">
                                 <input name="obj.area" id="area" class="span8" placeholder="版权的有效区域" value="${form.obj.area}"/>
                              </div>
                           </div>
                           <div class="control-group span6" id="contractNameDiv">
                              <label class="control-label" for="form-field-2">合同名称：</label>
                              <div class="controls">
                                 <input name="obj.contractName" id="contractName" class="span8" placeholder="合同名称" value="${form.obj.contractName}" onblur="Content.Copyright.AuthorInfo.CopyrightContractName();"/>
                                 <span id="contractNameSpan" class="help-inline"></span>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-1">合同来源：</label>
                              <div class="controls">
                                 <input name="obj.source" id="source" class="span8" placeholder="合同来源" value="${form.obj.source}"/>
                              </div>
                           </div>
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-2">签订日期：</label>
                              <div class="controls" id="invoiceDate">
                                 <input name="obj.signingStr" id="signingStr" class="span8" placeholder="合同签订日期" value="${form.obj.signingDate}" data-format="yyyy-MM-dd" onblur="Content.Copyright.AuthorInfo.CopyrightSigningDate();"/>
                                 <span class="add-on"> <i data-time-icon="icon-time" data-date-icon="icon-calendar" > </i>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-1">签订原因：</label>
                              <div class="controls">
                                 <input name="obj.cause" id="cause" class="span8" placeholder="合同基于什么原因签订" value="${form.obj.cause}"/>
                              </div>
                           </div>
                           <div class="control-group span6" id="marginDiv">
                              <label class="control-label" for="form-field-2">合同保证金：</label>
                               <div class="controls">
                                 <input name="obj.margin" id="margin" class="span8" placeholder="合同保证金" value="${form.obj.margin}" onblur="Content.Copyright.AuthorInfo.CopyrightMargin();"/>
                                 <span id="marginSpan" class="help-inline"></span>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group span6" id="largeAmountDiv">
                              <label class="control-label" for="form-field-1">合同最多金额：</label>
                              <div class="controls">
                                 <input name="obj.largeAmount" id="largeAmount" class="span8" placeholder="合同最多金额" value="${form.obj.largeAmount}" onblur="Content.Copyright.AuthorInfo.LargeAmount();"/>
                                 <span id="largeAmountSpan" class="help-inline"></span>
                              </div>
                           </div>
                           <div class="control-group span6" id="minAmountDiv">
                              <label class="control-label" for="form-field-2">合同最小金额：</label>
                              <div class="controls">
                                 <input name="obj.minAmount" id="minAmount" class="span8" placeholder="合同最小金额" value="${form.obj.minAmount}" onblur="Content.Copyright.AuthorInfo.MinAmount();"/>
                                 <span id="minAmountSpan" class="help-inline"></span>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-1">合同开始日期：</label>
                               <div class="controls" id="invoiceDate2">
                                 <input name="obj.startStr" id="startStr" class="span8" placeholder="合同开始执行的有效时间" value="${form.obj.startDate}" data-format="yyyy-MM-dd" onblur="Content.Copyright.AuthorInfo.CopyrightStartDate();"/>
                                 <span class="add-on"> <i data-time-icon="icon-time" data-date-icon="icon-calendar" > </i>
                              </div>
                           </div>
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-2">合同结束日期：</label>
                              <div class="controls" id="invoiceDate3">
                                 <input name="obj.stopStr" id="stopStr" class="span8" placeholder="合同终止时间" value="${form.obj.stopDate}" data-format="yyyy-MM-dd" onblur="Content.Copyright.AuthorInfo.CopyrightStopDate();"/>
                                 <span class="add-on"> <i data-time-icon="icon-time" data-date-icon="icon-calendar" > </i>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-1">合同状态：</label>
                              <div class="controls">
                                 <select id="status" name="obj.status" class="span8" value="${form.obj.status}">
					                  <#list ["未开始", "执行中" , "已结束", "意外中止"] as sta>
						                  <option <#if (sta == form.obj.status)>selected="selected"</#if>>${sta}</option>
					                  </#list>
			                     </select>
                              </div>
                           </div>
                           <div class="control-group span6" id="UploadFileDiv">
                              <label class="control-label" for="form-field-2">合同附件：</label>
                              <div class="controls">
                                 <#if ("" != form.obj.originName)>
                                     ${form.obj.originName}&nbsp;&nbsp;
                                     <input type="File" name="UploadFile" id="UploadFile" class="span8" onblur="Content.Copyright.AuthorInfo.UploadFile();"/>
                                 <#else>
                                     <input type="File" name="UploadFile" id="UploadFile" class="span8" onblur="Content.Copyright.AuthorInfo.UploadFile();"/><span style="color:red;">请上传zip/rar格式的压缩包</span>
                                 </#if>
                                 <span id="UploadFileSpan" class="help-inline"></span>
                              </div>
                           </div>
                       </div>
                       <input type="hidden" name="obj.id" value="${form.obj.id}" />
                   </div>
                   <div class="form-actions span12" style="text-align: center; padding-left: 0px;">
                     <button class="btn btn-success" id="save"><i class="icon-save bigger-110"></i> 保存</button>&nbsp; &nbsp; &nbsp;
                     <button class="btn btn-inverse" type="reset"><i class="icon-undo bigger-110"></i> 清空</button>
                   </div>
               </form>
            </div>
         </div>
      </div>
   </div>
   <script type="text/javascript" src="${request.contextPath}/js/common.js"></script>
   <script type="text/javascript" src="${request.contextPath}/js/copyright/CopyrightEdit.js"></script>
   <script type="text/javascript" src="${request.contextPath}/js/copyright/CopyrightList.js"></script>
</body>
</html>