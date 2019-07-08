<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/4/9
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
 <%
    String[] like = (String[])request.getParameterValues("like");//将复选框得到的数组值取出

      String likes ="";
      for(int i=0;i<like.length;i++)
         if(likes.trim().equals(""))
             likes = likes+""+like[i].toString()+"";
             else
                 likes = likes+","+like[i].toString()+"";//连接结束后,如果字符串最后是逗号,则去掉该逗号
              if(likes.trim().substring(0,likes.trim().length()).equals(",")) {
                  likes = likes.trim().substring(0,likes.trim().length()-1);
              }
 %>
    <div>
        <span>用户名：<%=request.getParameter("username")%>         </span><br>
        <span>密码：  <%=request.getParameter("userpwd")%>      </span><br>
        <span>性别：   <%=request.getParameter("sex")%>      </span><br>
        <span>出生年月   <%=request.getParameter("birthday")%>      </span><br>
        <span>邮箱：  <%=request.getParameter("mail")%>        </span><br>
        <span>现居住地：   <%=request.getParameter("selProvince")%>-<%=request.getParameter("selCity") %>      </span><br>
        <span>职业：   <%=request.getParameter("work")%>       </span><br>
        <span>个人爱好：   <%=likes%>       </span><br>
        <span>个人说明：   <%=request.getParameter("intro")%>       </span><br>
    </div>

</body>
</html>
