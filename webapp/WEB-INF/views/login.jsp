<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>登录</title>
  </head>
  <body>
    请登录<br/>
    <form action="login" method="post">
    	登录名：<input type="text" name="username" /><br>
    	密码：<input type="password" name="password" /><br>
    	验证码：<input type="text" name="verifycode" /><br>
    			<img src="<c:url value="/verifyCode" />?rnd=2" onclick="changeValidateCode(this)" style="cursor:pointer;" title="点击图片刷新验证码">
    			<br>
    	<input type="submit" value="登录">
    </form>
  </body>
  <script type="text/javascript">
  	function changeValidateCode(obj) {
		var timenow = new Date().getTime();
		obj.src = '<c:url value="/verifyCode" />?rnd=' + timenow;
	}
  </script>
</html>
