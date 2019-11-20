<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="myRegister" class="jspStudy.User" >
<jsp:setProperty name="myRegister" property="*" />

    <%   String[] likes=request.getParameterValues("like");
         String l=likes[0];
         for(int i=1;i<likes.length;i++){
             l+=","+likes[i];
         }
         String selProvince=request.getParameter("selProvince");
        String selCity=request.getParameter("selCity");
    %>
    <jsp:setProperty name="myRegister" property="like"  value= "<%=l%>"/>
</jsp:useBean>
<body>
<h2>注册信息如下：</h2>
<hr>
<jsp:getProperty name="myRegister" property="username" /><br>
<jsp:getProperty name="myRegister" property="userpwd" /><br>
<jsp:getProperty name="myRegister" property="sex" /><br>
<jsp:getProperty name="myRegister" property="birthday" /><br>
<jsp:getProperty name="myRegister" property="mail" /><br>
<jsp:getProperty name="myRegister" property="work" /><br>
<jsp:getProperty name="myRegister" property="like" /><br>
<jsp:getProperty name="myRegister" property="intro" /><br>
</body>
