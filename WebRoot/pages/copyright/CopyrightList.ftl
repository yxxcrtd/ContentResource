<#include "Common.ftl" />
<#include "Context.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>作者版权信息列表</title>
	</head>
	
	<body>
		<div class="clearfix">
			<div id="page-content" class="clearfix">
				<div class="row-fluid">
					<div class="row-fluid">
					   <div class="ace-thumbnails">
							<span class="fr"></span>
							<button class="btn btn-small btn-primary" onclick="Content.Copyright.AuthorInfo.addObj();">
								<i class="icon-plus-sign bigger-125"></i>新增版权信息
							</button>
						</div>
						<div class="table-header on">
							<i class="icon-caret-down"></i>&nbsp;&nbsp;查询条件
						</div>
						<div class="on-down">
							<form id="form" commandName="form" action="manager" class="form-horizontal">
								<div class="row-fluid">
									<div class="control-group span3">
										<label class="control-label" for="form-field-2">作者：</label>
										<div class="controls">
											<input id="query_author" class="span10" placeholder="版权法人"/>
										</div>
									</div>
									<div class="control-group span3">
										<label class="control-label" for="form-field-2">合同名称：</label>
										<div class="controls">
											<input id="query_name" class="span10" placeholder="合同名称"/>
										</div>
									</div>
									<div class="control-group span3">
										<label class="control-label" for="form-field-2">起始时间：</label>
										<div id="invoiceDate" class="controls">
											<input id="query_starton" class="span10" style="width: 100px;" placeholder="合同起始时间" data-format="yyyy-MM-dd" onblur="Content.Copyright.AuthorInfo.CopyrightSigningDate();"/>
											<span class="add-on"> <i data-time-icon="icon-time" data-date-icon="icon-calendar" > </i></span>
										</div>
									</div>
									<div class="control-group span3">
										<label class="control-label" for="form-field-2">结束时间：</label>
										<div id="invoiceDate2" class="controls">
											<input id="query_stopon" class="span10" style="width: 100px;" placeholder="合同结束时间" data-format="yyyy-MM-dd" onblur="Content.Copyright.AuthorInfo.CopyrightStartDate();"/>
											<span class="add-on"> <i data-time-icon="icon-time" data-date-icon="icon-calendar" > </i></span>
										</div>
									</div>
								</div>
								<div style="text-align: center;">
									<button class="btn btn-small btn-success" type="button" onclick="Content.Copyright.AuthorInfo.query();">
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
							<i class="icon-flag"></i>&nbsp;&nbsp;作者版权信息列表
						</div>
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th width="4%" align="center"></th>
									<th width="5%" align="center"></th>
									<th width="6%" align="center"></th>
									<th width="6%" align="center"></th>
									<th width="8%" align="center"></th>
									<th width="8%" align="center"></th>
									<th width="8%" align="center"></th>
									<th width="6%" align="center"></th>
									<th width="8%" align="center"></th>
									<th width="8%" align="center"></th>
								</tr>
							</thead>
							<tbody align="center" style="line-height: 18px; border: 1px solid #97bbdc;"></tbody>
							<tfoot>
								<tr>
									<th width="4%" align="center"></th>
									<th width="5%" align="center"></th>
									<th width="6%" align="center"></th>
									<th width="6%" align="center"></th>
									<th width="8%" align="center"></th>
									<th width="8%" align="center"></th>
									<th width="8%" align="center"></th>
									<th width="6%" align="center"></th>
									<th width="8%" align="center"></th>
									<th width="8%" align="center"></th>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/copyright/CopyrightList.js"></script>
	</body>
</html>
