<%
    request.setCharacterEncoding("UTF-8");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    session.setAttribute("username",username);
    String userVCode=request.getParameter("userVCode");
    String validateCode=(String)session.getAttribute("validateCode");
    if (userVCode.equalsIgnoreCase(validateCode)) {
        if(username.equals("蜡笔小新") && password.equals("hello123") ) {
            session.setAttribute("isLogin", "true");
            response.sendRedirect("success.jsp");
        }
        else {
            session.setAttribute("isLogin", "false");
            response.sendRedirect("error.jsp");
        }
    }
    else
        out.println("<script>alert('验证码输入错误');history.go(-1);</script>");
%>
