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
                 <form id="ProductForm" commandName="form" class="form-horizontal" enctype="multipart/form-data">
                   <div class="table-header on" id="baseInfoDiv"><i class="icon-caret-down"></i>&nbsp;&nbsp;图书基本信息</div>
                    <div id="baseInfoContentDiv" class="on-down">
                       <div class="row-fluid">
                           <div class="control-group span6" id="titleDiv">
                              <label class="control-label" for="form-field-1">书名：</label>
                              <div class="controls">
                                 <input name="obj.title" id="title" class="span8" value="${form.obj.title}" placeholder="书名" onblur="Content.Product.ProductInfo.ProductName();"/>
                                 <span id="titleSpan" class="help-inline"></span>
                              </div>
                           </div>
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-2">译名：</label>
                              <div class="controls">
                                 <input name="obj.translation" id="translation" class="span8" placeholder="书别名/译名" value="${form.obj.translation}"/>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-1">产品分类：</label>
                              <div class="controls">
                                 <select id="productType.id" name="obj.productType.id" class="span8" value="${form.obj.productType.id}">
	                                <#if typeList??>  
	                                 <#list typeList as type>
	                                    <option value="${type.id}" <#if (type.id == form.obj.productType.id)>selected="selected"</#if>>
	                                    <#if ("" != type.treeCode.id)>
	                                    	<#if type_has_next>│├<#else>│└</#if>
	                                    <#else>
	                                    <#if type_has_next>┌<#else>└</#if>
	                                    </#if>
	                                    ${type.name}</option>
	                                  </#list>
	                                 </#if> 
	                             </select>
	                             
                              </div>
                           </div>
                           <div class="control-group span6" id="isbnDiv">
                              <label class="control-label" for="form-field-2">ISBN：</label>
                              <div class="controls">
                                 <input name="obj.isbn" id="isbn" class="span8" value="${form.obj.isbn}" placeholder="ISBN号" onblur="Content.Product.ProductInfo.ProductISBN();"/>
                                 <span id="isbnSpan" class="help-inline"></span>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-1">装帧：</label>
                              <div class="controls">
                                 <select id="binding" name="obj.binding" class="span8" value="${form.obj.binding}">
					                  <#list ["精装", "平装" , "骑马订", "胶装", "线装", "活页装"] as bind>
						                  <option <#if (bind == form.obj.binding)>selected="selected"</#if>>${bind}</option>
					                  </#list>
			                     </select>
                              </div>
                           </div>
                           <div class="control-group span6" id="priceDiv">
                              <label class="control-label" for="form-field-2">价格：</label>
                              <div class="controls">
                                 <input name="obj.price" id="price" class="span8" value="${form.obj.price}" placeholder="图书价格" onblur="Content.Product.ProductInfo.ProductPrice();"/>
                                 <span id="priceSpan" class="help-inline"></span>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-1">版别版次：</label>
                              <div class="controls">
                                 <input name="obj.edition" id="edition" class="span8" value="${form.obj.edition}" placeholder="第几版第几次"/>
                              </div>
                           </div>
                           <div class="control-group span6" id="pageNumDiv">
                              <label class="control-label" for="form-field-2">页数：</label>
                              <div class="controls">
                                 <input name="obj.pageNum" id="pageNum" class="span8" value="${form.obj.pageNum}" placeholder="书籍页数" onblur="Content.Product.ProductInfo.ProductPageNum();"/>
                                 <span id="pageNumSpan" class="help-inline"></span>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-1">尺寸：</label>
                              <div class="controls">
                                 <input name="obj.size" id="size" class="span8" value="${form.obj.size}" placeholder="图书的尺寸"/>
                              </div>
                           </div>
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-2">排版：</label>
                              <div class="controls">
                                 <select id="compose" name="obj.compose" class="span8" value="${form.obj.compose}">
					                  <#list ["横排", "竖排"] as pose>
						                  <option <#if (pose == form.obj.compose)>selected="selected"</#if>>${pose}</option>
					                  </#list>
			                     </select>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-1">印张：</label>
                              <div class="controls">
                                 <input name="obj.sheet" id="sheet" class="span8" value="${form.obj.sheet}" placeholder="印张"/>
                              </div>
                           </div>
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-2">开本：</label>
                              <div class="controls">
                                 <select id="format" name="obj.format" class="span8" value="${form.obj.format}">
					                  <#list ["4", "8", "16", "24", "32", "64"] as format>
						                  <option <#if (format == form.obj.format)>selected="selected"</#if>>${format}</option>
					                  </#list>
			                     </select>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-1">重量：</label>
                              <div class="controls">
                                 <input name="obj.weight" id="weight" class="span8" value="${form.obj.weight}" placeholder="产品重量"/>
                              </div>
                           </div>
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-2">语种：</label>
                              <div class="controls">
                                 <input name="obj.language" id="language" class="span8" value="${form.obj.language}" placeholder="语种"/>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-1">出版社：</label>
                              <div class="controls">
                                 <input name="obj.publisher" id="publisher" class="span8" value="${form.obj.publisher}" placeholder="所属出版社"/>
                              </div>
                           </div>
                           <div class="control-group span6" id="authorDiv">
                              <label class="control-label" for="form-field-2">作者：</label>
                              <div class="controls">
                                 <input name="obj.author" id="author" class="span8" value="${form.obj.author}" placeholder="作者" onblur="Content.Product.ProductInfo.ProductAuthor();"/>
                                 <span id="authorSpan" class="help-inline"></span>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-1">译者：</label>
                              <div class="controls">
                                 <input name="obj.translator" id="translator" class="span8" value="${form.obj.translator}" placeholder="本书翻译者"/>
                              </div>
                           </div>
                           <div class="control-group span6">
                              <label class="control-label" for="form-field-2">出版日期：</label>
                              <div class="controls" id="invoiceDate">
                                 <input name="obj.dataPublicationStr" id="dataPublicationStr" class="span8" placeholder="出版日期" value="${form.obj.datePublication}" data-format="yyyy-MM-dd" onblur="Content.Product.ProductInfo.datePublication();"/>
                                 <span class="add-on"> <i data-time-icon="icon-time" data-date-icon="icon-calendar" > </i>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group span6" id="FilePicDiv">
                              <label class="control-label" for="form-field-1">封面图片：</label>
                              <div class="controls">
                                 <#if ("" != form.obj.bookCovers)>
                                     <image src="/upload/resource/${form.obj.bookCovers}" width="80">
                                     <input type="File" name="FilePic" id="FilePic" class="span8" onblur="Content.Product.ProductInfo.FilePic();"/>
                                 <#else>
                                     <input type="File" name="FilePic" id="FilePic" class="span8" onblur="Content.Product.ProductInfo.FilePic();"/>
                                 </#if>
                                 <span id="FilePicSpan" class="help-inline"></span>
                              </div>
                           </div>
                           <div class="control-group span6" id="FilePicDiv">
                              <label class="control-label" for="form-field-2">资源文件：</label>
                              <div class="controls">
                                 <#if ("" != form.obj.originName)>
                                     ${form.obj.originName}&nbsp;&nbsp;
                                     <input type="File" name="upLoadFile" id="upLoadFile" class="span8" onblur="Content.Product.ProductInfo.upLoadFile();"/>
                                 <#else>
                                     <input type="File" name="upLoadFile" id="upLoadFile" class="span8" onblur="Content.Product.ProductInfo.upLoadFile();"/>
                                 </#if>
                                 <span id="FilePicSpan" class="help-inline"></span>
                              </div>
                           </div>
                       </div>
                       <div class="row-fluid">
                           <div class="control-group">
                              <label class="control-label" for="form-field-2">备注：</label>
                              <div class="controls">
                                 <textarea name="obj.remark" id="remark" class="span10 h100">${form.obj.remark}</textarea>
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
   <script type="text/javascript" src="${request.contextPath}/js/product/ProductEdit.js"></script>
   <script type="text/javascript" src="${request.contextPath}/js/product/ProductList.js"></script>
</body>
</html>