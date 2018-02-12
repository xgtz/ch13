<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><s:text name="个人信息管理系统"></s:text> </title>
  </head>
  <frameset cols="20%,*" frameborder="no" border="no" framespacing="0">
  	<frame url="../mainFrame/left.jsp" name="left" scrolling="no">
  	<frameset rows="20%,10%,*">
  		<frame url="../mainFrame/top.jsp" name="top"  scrolling="no">
  		<frame url="../mainFrame/toop.jsp" name="toop" scrolling="no">
  		<frame url="../mainFrame/about.jsp" name="main">
  	</frameset>
  </frameset>
</html>
