<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/4/8
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
</head>
<body>
 <form method="post" action="file/uploadMultipartFile" enctype="multipart/form-data">
     <input type="file" name="file" value="请选择上传的文件">
     <input type="submit" value="提交">
 </form>
</body>
</html>
