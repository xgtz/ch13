<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="个人信息管理系统"></s:text> </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <center>
  	<h1>个人信息管理系统</h1>
  	<hr/>
  	<s:form action="loginAction" method="post">
  		<div>
  			<s:textfield name="userName" label="登录名" size="16"></s:textfield></div>
	  	<br>
	  	<div>
	  		<s:password name="password" label="登录密码" size="18"></s:password></div>
	  	<br>
	  	<div>
	  		<input type="submit" value="确定"/>
			<input type="reset" value="清空"/>
		</div>
		<br>
		<div>
	  		<s:a href="../login/register.jsp">注册</s:a></div>	
	</s:form>
	</center>
  </body>
</html>
