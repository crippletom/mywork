<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<html>
<head>
<meta charset="UTF-8">
<title>rsdl</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/res/easyui/themes/default/easyui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/res/easyui/themes/icon.css'/>">
<script type="text/javascript" src="<c:url value='/res/jquery/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/res/easyui/jquery.easyui.min.js'/>"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height:50px;padding:10px">人事代理系统, <shiro:principal/>  </div>
	<div data-options="region:'west',split:true,title:'菜单'"
		style="width:200px;">
		<ul class="easyui-tree">
			 <shiro:hasAnyRoles name="role01">
			<li><span>水果</span>
				<ul>
					<li><a href="javascript:addTab('苹果','apple');">苹果</a></li>
					<li><a href="javascript:addTab('橘子','orange');">橘子</a></li>
				</ul>
			</li>
			</shiro:hasAnyRoles>
			<shiro:hasAnyRoles name="role02">
			<li><span>蔬菜</span>
				<ul>
					<li><a href="javascript:addTab('土豆','tomato');">土豆</a></li>
					<li><a href="javascript:addTab('白菜','cabbage');">白菜</a></li>
					<li><a href="javascript:addTab('西红柿','potato');">西红柿</a></li>
				</ul>
			</li>
			</shiro:hasAnyRoles>
			<li><span>示例</span>
				<ul>
					<li><a href="javascript:addTab('DEMO','<c:url value="/demo/index" />');">demo</a></li>
				</ul>
			</li>
		</ul>
	</div>
	<!-- <div data-options="region:'east',split:true,collapsed:true,title:'备注'"
		style="width:100px;padding:10px;"></div> -->
	<div data-options="region:'south',border:false"
		style="height:40px;padding:10px;">copy right</div>
	<div data-options="region:'center'" id="mainTabs" class="easyui-tabs"
		style="width:100%;height:100%;">
		<div title="Home">hello world !</div>
	</div>
</body>
<script type="text/javascript">

	function addTab(title, url){
		if ($('#mainTabs').tabs('exists', title)){
			$('#mainTabs').tabs('select', title);
		} else {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
			$('#mainTabs').tabs('add',{
				title:title,
				content:content,
				closable:true
			});
		}
	}
</script>
</html>