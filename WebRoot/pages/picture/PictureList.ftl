<#include "Common.ftl" />
<#include "Context.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>图片信息列表</title>
	</head>
	
	<body>
		<div class="clearfix">
			<div id="page-content" class="clearfix">
				<div class="row-fluid">
					<div class="row-fluid">
					   <div class="ace-thumbnails">
							<span class="fr"></span>
							<button class="btn btn-small btn-primary" onclick="Content.Picture.PictureInfo.addObj();">
								<i class="icon-plus-sign bigger-125"></i>新增图片
							</button>
							<button class="btn btn-small btn-primary" name="checkAll">
								<i class="icon-plus-sign bigger-125"></i>全选
							</button>
							<button class="btn btn-small btn-primary" onclick="Content.Picture.PictureInfo.delObj();">
								<i class="icon-plus-sign bigger-125"></i>批量删除
							</button>
						</div>
						<div class="table-header on">
							<i class="icon-caret-down"></i>&nbsp;&nbsp;查询条件
						</div>
						<div class="on-down">
							<form id="form" commandName="form" action="manager" class="form-horizontal">
								<div class="row-fluid">
									<div class="control-group span3">
										<label class="control-label" for="form-field-2">标题：</label>
										<div class="controls">
											<input id="query_title" class="span10" placeholder="图片的标题"/>
										</div>
									</div>
									<div class="control-group span3">
										<label class="control-label" for="form-field-2">关键字：</label>
										<div class="controls">
											<input id="query_keyword" class="span10" placeholder="图片的关键词"/>
										</div>
									</div>
									<div class="control-group span3">
										<label class="control-label" for="form-field-2">类型组：</label>
										<div class="controls">
										<select id="query_type" class="span10">
										   <option value="">请选择分类</option>
										   <#if ptypeList??>
		                                       <#list ptypeList as ptype>
		                                           <option value="${ptype.id!}">${ptype.name!}</option>
		                                       </#list>
	                                       </#if>
	                                    </select>
										</div>
									</div>
								</div>
								<div style="text-align: center;">
									<button class="btn btn-small btn-success" type="button" onclick="Content.Picture.PictureInfo.query();">
										<i class="icon-zoom-in bigger-110"></i> 查询
									</button>
									&nbsp;&nbsp;
									<button type="reset" class="btn btn-small btn-inverse">
										<i class="icon-repeat bigger-110"></i>重置
									</button>
								</div>
							</form>
						</div>
						<div class="table-header">
							<i class="icon-flag"></i>&nbsp;&nbsp;图片信息列表
						</div>
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th width="5%" align="center"></th>
									<th width="5%" align="center"></th>
									<th width="6%" align="center"></th>
									<th width="10%" align="center"></th>
									<th width="10%" align="center"></th>
									<th width="8%" align="center"></th>
								</tr>
							</thead>
							<tbody align="center" style="line-height: 18px; border: 1px solid #97bbdc;"></tbody>
							<tfoot>
								<tr>
									<th width="5%" align="center"></th>
									<th width="5%" align="center"></th>
									<th width="6%" align="center"></th>
									<th width="10%" align="center"></th>
									<th width="10%" align="center"></th>
									<th width="8%" align="center"></th>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/picture/PictureList.js"></script>
	</body>
</html>
