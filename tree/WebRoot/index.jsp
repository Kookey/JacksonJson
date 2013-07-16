<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>商品树</title>
<link rel="stylesheet" type="text/css"
	href="css/zTreeStyle/zTreeStyle.css">
<style type="text/css">
body {
	background-color: white;
	margin: 0;
	padding: 0;
	text-align: center;
}

div,p,table,th,td {
	list-style: none;
	margin: 0;
	padding: 0;
	color: #333;
	font-size: 5px;
	font-family: dotum, Verdana, Arial, Helvetica, AppleGothic, sans-serif;
}

#testIframe {
	margin-left: 10px;
}
</style>
<script type="text/javascript" src="js/jquery-1.7.js"></script>
<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
 	var zTree;
 	var testIframe;
  var setting = {
   async: {
    enable: true,//启用异步加载
    dataType: "json",//默认是text，如果是json数据处理必须指定类型为json
    url:"CategoryAction", //异步请求地址
    autoParam:["id","name"]//需要传递的参数,为你在ztree中定义的参数名称
   },
   view: {
    dblClickExpand: false,
    showLine: true,
    expandSpeed: ($.browser.msie && parseInt($.browser.version)<=6)?"":"fast"
   },
   data: {
    simpleData: {
     enable: true,
     idKey: "id",
		 pIdKey: "pId",
		 rootPId: "00",
    }
   },
   callback: {
    onAsyncSuccess: onAsyncSuccess,
    beforeClick: function(treeId, treeNode){
    	var zTree = $.fn.zTree.getZTreeObj(treeId);
     	if (treeNode.isParent) {
      zTree.expandNode(treeNode);
      return false;
     } else {
     		var cateId =  treeNode["id"];
     		var strId = "'"+cateId+"'";
      testIframe = $("#testIframe");
      testIframe.attr("src","category.jsp?strId="+strId);
      return true;
     }
    }
   }
  };
 
  function onAsyncSuccess(event, treeId, treeNode, msg) {
   cancelHalf(treeNode);
  }
  function cancelHalf(treeNode) {
   var zTree = $.fn.zTree.getZTreeObj("tree");
   treeNode.halfCheck = true;
   zTree.updateNode(treeNode);   //异步加载成功后刷新树节点
  }

	//初始化页面
	$(function(){
		$.post(
			"CateRootAction",
			{root:"00"},
			function (result){
			$.fn.zTree.init($("#tree"),setting,eval(result));
			});
	});
</script>
</head>
<body>
	<table border=0 height=500px align=left>
		<tr>
			<td width=260px align=left valign=top
				style="BORDER-RIGHT: #999999 1px dashed">
				<ul id="tree" class="ztree" style="width:270;height:600px; overflow:auto;"></ul>
			</td>
			<td width=80% align=left valign=top><iframe id="testIframe"
					Name="testIframe" FRAMEBORDER=0 SCROLLING=AUTO width=95%
					height=100%; SRC="category.jsp"></iframe></td>
		</tr>
	</table>
</body>
</html>
