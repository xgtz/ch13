<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="个人信息管理系统->注册"></s:text> </title>
    
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
	  	<s:text name="请填写以下注册信息"></s:text>
	  	<hr>
	  	<s:form action="registerAction" method="post">
	  		<table align="center" border="2">
	  			<tr>
	  				<td>
	  					<s:textfield name="userName" label="登录名"></s:textfield>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  					<s:password name="password1" label="密码" size="21"></s:password>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  					<s:password name="password2" label="再次输入密码" size="21"></s:password>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  					<s:textfield name="name" label="用户真实姓名"></s:textfield>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  					<s:text name="用户性别:"></s:text>
	  					<input type="radio" name="sex" value="男" checked/>男
	  					<input type="radio" name="sex" value="女"/>女
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  					<s:textfield name="birth" label="出生日期"></s:textfield>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  					<s:textfield name="nation" label="用户民族"></s:textfield>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  					<s:select name="edu" label="用户学历" headerValue="-----请选择-----"
	  						headerKey="1" list="{'博士','硕士','学士','专科','高中','初中','小学','其他'}">
	  					</s:select>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  					<s:select name="word" label="用户职称" headerValue="-----请选择-----" headerKey="1"
	  					list="{'测试','开发','设计','教师','职员','经理','老板','其他'}"></s:select>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  					<s:textfield name="phone" label="用户电话"></s:textfield>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  					<s:textfield name="place" label="用户住址"></s:textfield>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  					<s:textfield name="email" label="用户邮箱"></s:textfield>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td colspan="2" align="right">
	  					<input type="submit" value="确定" />
	  					<input type="reset" value="清空" />
	  					<s:a href="/login/index.jsp">返回</s:a>
	  				</td>
	  			</tr>
	  		</table>
	  	</s:form>
	  </center>
  </body>
</html>
