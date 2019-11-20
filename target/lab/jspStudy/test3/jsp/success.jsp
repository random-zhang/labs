<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/4/3
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
   <head>
       <title>登录成功</title>
   </head>
   <body>
       <div>欢迎<%=(String)session.getAttribute("username")%> </div>使用本系统!<br>
   </body>
</html>
