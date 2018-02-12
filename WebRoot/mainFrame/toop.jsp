<%@page import="JavaBean.UserNameBean" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><s:text name="个人管理系统"></s:text> </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body bgcolor="#CCDDEE">
   	<%
   		String loginname=null;
   		ArrayList login=(ArrayList)session.getAttribute("userName");
   		if(login==null || login.size()==0){
   			loginname="水木清华";
   		}
   		else{
   			for(int i=login.size()-1;i>=0;i--){
   				UserNameBean nm=(UserNameBean)login.get(i);
   				loginname=nm.getUserName();
   			}
   		}
   	 %>
   	 <table width="100%" align="right" bgcolor="blue">
   	 	<tr height="10" bgcolor="gray" align="center">
   	 		<td><a href="./personMessage/lookMessage.jsp" target="main">个人信息管理</a> </td>
   	 		<td><a href="./friendManager/lookFriends.jsp" target="main">通讯录管理</a></td>
   	 		<td><a href="./dateTimeManager/lookDay.jsp" target="main">日程安排管理</a></td>
   	 		<td><a href="./fileManager/lookFile.jsp" target="main">个人文件管理</a></td>
   	 		<td><a href="./login/index.jsp" target="_top">退出主页面</a></td>
   	 		<td>欢迎<%= loginname %>使用本系统！</td>
   	 	</tr>
   	 </table>
  </body>
</html>
