<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/4/3
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<html>
<head>
    <title>登录失败</title><meta http-equiv="refresh" content="10; url=login.html">
</head>
<body>
<div>对不起,<%=(String)session.getAttribute("username")%></div>请您确认用户名和密码!
</body>
</html>
