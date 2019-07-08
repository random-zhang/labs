<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String message = "";
	session.setAttribute("username", username);
	if (username.equals("蜡笔小新") && password.equals("hello123")){
		response.sendRedirect("success.jsp");
	} else {
		response.sendRedirect("error.jsp");
	}
	%>
</body>
</html>