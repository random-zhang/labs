<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="common.jsp"   />
	<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
	<script type="text/javascript">
        var xmlhttp;
        function mylogin() {
			var employeeId = $("#employeeId").val();
			var password = $("#password").val();
            if ("" == employeeId || undefined == employeeId) {
                alert("工号不能为空");
                return;
            }
            if ("" == password || undefined == password) {
                alert("密码不能为空");
                return;
            }
			var data ={"employeeId":employeeId,"password": password}
			//data.employeeId = employeeId;
			//data.password = password;
            //xmlhttp.send(data);
          //发送数据，开始和服务器进行交互。

            $.ajax({
				type : 'post',
				url : 'LoginController/loginCheck.do',
				data : JSON.stringify(data),
				dataType:'json',
				contentType : 'application/json;charset=UTF-8',
				cache : false,
				sync : true,
				success : function(d) {
				    if(d.msg.toString()=="密码错误")
                    alert(d.msg)
					else  if(d.msg.toString()=="密码过于简单")
						window.location.href="wanho/updatepassword.jsp";
					else if (d.msg.toString()=="密码正确")
                    {
                        //转到登录后页面

                    }
					//$("#formlogin").submit();
				},
				error : function() {
					alert("请求失败!");
				}
			});
		}
    </script>
<title>用户登陆</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/wanho/css/login.css" />
<style>
</style>
</head>
<body>
	<div class="form_login">
		<div class="form_logo">
			<h1>CRM</h1>
		</div>
		<form method="post" id="formlogin"
			action="LoginServlet?method=loginToCms">
			<div class="form-group">
				<i class="fa fa-user"></i> <input type="text" class="form-control"
					name="employeeId" id="employeeId" placeholder="输入工号">
			</div>
			<div class="form-group">
				<i class="fa fa-key"></i> <input type="password"
					class="form-control" name="password" id="password"
					placeholder="输入密码">
			</div>
			<!--  <div class="form-group" style="height:25px; line-height:25px; text-align:left;">
            <input type="checkbox" name="checkbox" id="checkbox">
            <span class="checkfont">记住我的帐号</span>
        </div> -->
			<div class="form-group">
				<button type="button" id="loginsubmit"
					class="btn btn-primary btn-block btn-login" onclick="mylogin()">登录</button>
			</div>
		</form>
		<div class="form_footer">
			<h4>@2017 江苏万和系统工程有限公司</h4>
		</div>
	</div>
</body>

</html>
