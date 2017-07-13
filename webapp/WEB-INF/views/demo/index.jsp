<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<c:url value='/res/easyui/themes/default/easyui.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/res/easyui/themes/icon.css'/>">
<script type="text/javascript" src="<c:url value='/res/jquery/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/res/easyui/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/res/easyui/plugins/jquery.datagrid.js'/>"></script>
<script type="text/javascript" src="<c:url value='/res/easyui/plugins/jquery.dialog.js'/>"></script>
<script type="text/javascript" src="<c:url value='/res/easyui/plugins/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/res/easyui/plugins/jquery.messager.js'/>"></script>
<table id="dg" title="用户列表" class="easyui-datagrid" style="width:100%;height:100%"
		url="<c:url value="/demo/paging"/>"
		toolbar="#toolbar"
		rownumbers="true" fitColumns="true" singleSelect="true">
	<thead>
		<tr>
			<th field="id" >主键</th>
			<th field="name" >姓名</th>
			<th field="gender" >性别</th>
			<th field="birthday" >出生日期</th>
			<th field="createTime" >创建时间</th>
		</tr>
	</thead>
</table>
<div id="toolbar">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
</div>

<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
		closed="true" buttons="#dlg-buttons">
	<div class="ftitle">用户信息</div>
	<form id="fm" method="post">
		<div class="fitem">
			<label>姓名:</label>
			<input name="name" class="easyui-validatebox" required="true">
		</div>
		<div class="fitem">
			<label>性别:</label>
			<input name="gender" class="easyui-validatebox" required="true">
		</div>
		<div class="fitem">
			<label>出生日期:</label>
			<input name="birthday" class="easyui-validatebox" required="true">
		</div>
	</form>
</div>
<div id="dlg-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>
<script>
function newUser(){
	$('#dlg').dialog('open').dialog('setTitle','新增用户');
	$('#fm').form('clear');
	url = 'add';
}
function saveUser(){
	$('#fm').form('submit',{
		url: url,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			if (result.errorMsg){
				$.messager.show({
					title: 'Error',
					msg: result.errorMsg
				});
			} else {
				$('#dlg').dialog('close');		// close the dialog
				$('#dg').datagrid('reload');	// reload the user data
			}
		}
	});
}
function destroyUser(){
	var row = $('#dg').datagrid('getSelected');
	if (row){
		$.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
			if (r){
				$.post('delete',{id:row.id},function(result){
					if (result.success){
						$('#dg').datagrid('reload');	// reload the user data
					} else {
						$.messager.show({	// show error message
							title: 'Error',
							msg: result.errorMsg
						});
					}
				},'json');
			}
		});
	}
}
</script>