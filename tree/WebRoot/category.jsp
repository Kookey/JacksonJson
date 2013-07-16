<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>商品列表</title>
<link rel="stylesheet" type="text/css" href="css/pagination.css">
<style type="text/css">
.odd {
	background: #CCC
}

.even {
	background: #FFF
}

.mouseover {
	background: #80FFFF
}
</style>
<link rel="stylesheet" type="text/css" href="css/foot.css">
<script type="text/javascript" src="js/jquery-1.7.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript">
	var pageIndex = 0;//索引页
	var pageSize = 20;
	var cateId = <%=request.getParameter("strId")%>;
	$(function() {
		Init(0);
		//页面加载时进行绑定
	});
		//点击分页时调用的函数，index为当前页的索引
		function PageCallback(index, jq) {
			Init(index);
		}
	function Init(pageIndex) {
		var trs = "";
		$.post("MaterialAction", {
			pageIndex : pageIndex,
			pageSize : pageSize,
			cateId:cateId
		}, function(result) {
				var lists = result.lists;
				rowCount = result.rowCount;
				$("#Pagination").pagination(rowCount, {
				items_per_page : pageSize,
				current_page : pageIndex,
				next_text : "下一页",
				prev_text : "上一页",
				num_display_entries : 3,
				ellipse_text:".....",
				num_edge_entries : 3,
				callback : PageCallback
			});
				$("#datas tr:gt(0)").remove();//移除所以的数据行
				$.each(lists, function(index, lists) {
					trs += "<tr'>" +
					 					"<td>" + lists.id + "</td>" + 
					 					"<td>" + lists.code + "</td>" + 
					 					"<td>" + lists.name + "</td>" + 
					 					"<td>" + lists.unitName + "</td>" + 
									  "<td>" + lists.materialQuaity + "</td>" + 
					 					"<td>" + lists.shortStandardName + "</td>" + 
					 					"<td>" + lists.standardModel + "</td>" + 
					 					"<td>" + lists.sourceSystemCode + "</td>" + 
					 				"</tr>";
				});
				$("#datas").append(trs);
				//设置奇偶行的颜色
				$("#datas tr:gt(0):odd").attr("class", "odd");
				$("#datas tr:gt(0):even").attr("class", "even");
				//设置鼠标停留当前行的颜色
				$("#datas tr:gt(0)").hover(function() {
					$(this).addClass('mouseover');
				}, function() {
					$(this).removeClass('mouseover');
				});
		}, 'json');
	}
</script>
</head>
<body>
	商品明细信息
	<hr>
	<br>
	<table id="datas" border="1" cellspacing="0"
		style="border-collapse: collapse;background:#5BADFF;font-size: 15px;width:100%">
		<tr>
			<th>序号</th>
			<th>物资编码</th>
			<th>物资名称</th>
			<th>单位</th>
			<th>材质</th>
			<th>规格简称</th>
			<th>规格型号</th>
			<th>来源系统编号</th>
		</tr>
	</table>
	<br>
	<br>
	<div style="text-align: center;">
		<div id="Pagination" class="pagination"></div>
	</div>
</body>
</html>
