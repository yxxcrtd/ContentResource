<#include "Common.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>资源统计分析</title>
		<style type="text/css">
			.perBox .perBg0 {
			    background: none repeat scroll 0 0 #f3b1b1;
			}
			.perBox .perBg1 {
			    background: none repeat scroll 0 0 #e7b1f3;
			}
			.perBox .perBg2 {
			    background: none repeat scroll 0 0 #bff3b1;
			}
			.perBox .perBg3 {
			    background: none repeat scroll 0 0 #b1e1f3;
			}
			.perBox .perBg4 {
			    background: none repeat scroll 0 0 #ffff00;
			}
			.perBox .perBg5 {
			    background: none repeat scroll 0 0 #0000ff;
			}
			.perBox .perBg6 {
			    background: none repeat scroll 0 0 #00ff00;
			}
			.perBox .perBg7 {
			    background: none repeat scroll 0 0 #00ffff;
			}
			.perBox .perBg8 {
			    background: none repeat scroll 0 0 #ff00ff;
			}
			.perBox .perBg9 {
			    background: none repeat scroll 0 0 #090909;
			}
			.perBox{display:inline-block;width:300px;height:22px;overflow:hidden;vertical-align:middle; background-color: #E3E3E3;}
			.perBox .perBg{display:block;height:22px;}
			#options{padding-top:8px;}
			#options li{margin-top:12px;height:24px;overflow:hidden;zoom:1;vertical-align:top;}
			.pollPer .perBox {width:0;overflow:hidden;}
			.pollPer .perN, .pollPer .perNum{display:inline-block;width:50px;font-size:12px;vertical-align:middle; color: #BCBCBC;}
			.pollPer .perNum{width:32px; color: #000;}
			.pollPer b{font-size:12px;color:#999;font-weight:normal;vertical-align:middle;}
		</style>
	</head>
	
	<body>
		<div class="clearfix">
			<div id="page-content" class="clearfix">
				<div class="row-fluid">
					<div class="row-fluid">
						<div class="table-header">
							<i class="icon-flag"></i>&nbsp;&nbsp;资源统计分析
						</div>
						
						<div id="options" style="padding: 40px 0 0 40px;">
							<ul>
							<#list typesList as t> 
							<li>
								<p class="pollPer">
									<span style="display: inline-block; width: 80px;">${t.name}</span>
									<span class="perBox"><i class="perBg perBg${t_index % 10}" style="width: 1%;"></i></span>&nbsp;&nbsp;
									<em class="perNum">${t.total}</em>&nbsp;&nbsp;
									<em class="perN"></em>
								</p>
							</li>
							</#list>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		<!--
		$(function() {
			var pollBar = $("#options").find(".perBox");
			pollBar.animate({"width" : "600px"}, 1500);
			
			var nowNum = 0;
			pollBar.siblings(".perNum").each(function() {
				nowNum = parseInt($(this).text());
				if (isNaN(nowNum/${total})) {
					$(this).siblings(".perN").text("0%");
					$(this).siblings(".perBox").children("i").css("width", "1%");
				} else {
					$(this).siblings(".perN").text(parseFloat((nowNum / ${total}) * 100).toFixed(1) + "%");
					$(this).siblings(".perBox").children("i").css("width", parseFloat((nowNum / ${total}) * 100).toFixed(1) + "%");
				}
			});
			
		});
		//-->
		</script>
	</body>
</html>
