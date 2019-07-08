<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/4/9
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body{
            background-color:rgb(242,242,242);
        }
        .box{
            position:relative;
            line-height: 160%;
            margin:6px;}
        .i-label{
            position:absolute;
            left:0px;
            width:80px;
            line-height: 160%;
            text-align:right;}
        .i-text{
            position:relative;
            left:95px;
            width:200px;
            line-height: 160%;}
        .banner{
            position:relative;
            background:url(../../images/theme-pic.jpg) no-repeat 0 200px;
            margin:0 auto;
            width:1000px;
            height:678px;}
        .aside{
            position:absolute;
            left:60%;
            top:120px;
            width:400px;
            padding:20px;
            background:rgb(252, 252, 252);
            box-shadow: 0px 1px 5px 0px rgba(0,0,0,0.3);
            border-radius:8px;}
        .import_prompt{
            border: solid 1px #ffcd00;
            background-color:#ffffda;
            padding-left: 5px;
            padding-right:5px;
            line-height:20px;
        }
        .error_prompt{
            border: solid 1px #ff3300;
            background-color: #fff2e5;
            background-image: url(../../images/li_err.gif);
            background-repeat: no-repeat;
            background-position: 5px 2px;
            padding: 2px 5px 0px 25px;
            line-height: 20px;
        }
        .ok_prompt{
            border: solid 1px #01be00;
            background-color:#e6fee4;
            background-image:url(../../images/li_ok.gif);
            background-repeat:no-repeat;
            background-position:5px 2px;
            padding: 2px 5px 0px 25px;
            line-height: 20px;
        }
    </style>
</head>
<body>
<script type="text/javascript" src="../../register.js"></script>
<div class="banner">
    <div class="aside">
        <form name="form1" action="check.jsp" method="post"  >
            <div class="box"  >
                <label class="i-label">*用户名</label>
                <input type="text" id="username" name="username" class="i-text"
                       onFocus="usernameFocus();" onBlur="usernameBlur();" />
            </div>
            <div id="usernameId"></div>
            <div class="box">
                <label class="i-label">*密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                <input type="password" name="userpwd"class="i-text"/>
            </div>
            <div class="box">
                <label class="i-label">密码确认</label>
                <input type="password" name="userpwd2" class="i-text"/>
            </div>
            <div class="box">
                <label class="i-label">性&nbsp;&nbsp;&nbsp;&nbsp;别</label>
                <span class="i-text">
                <input type="radio" name="sex" checked>男
                <input type="radio" name="sex">女
                </span>
            </div>
            <div class="box">
                <label class="i-label">出生年月</label>
                <input type="date" name="birthday" class="i-text">
            </div>
            <div class="box">
                <label class="i-label">*邮&nbsp;&nbsp;&nbsp;&nbsp;箱</label>
                <input type="text" name="mail" class="i-text">
            </div>
            <div class="box">
                <div style="width:80px;float:left;padding-left:25px;">
                    <label >现居住地</label>
                </div>
                <div style="display:inline-block;">
                    <select name="selProvince" size="1" style="padding:5px" onChange="changeCity();">

                    </select>
                </div>
                <div style="display:inline-block;">
                    <select name="selCity" size="1"  style="padding:5px">
                    </select>
                </div>
            </div>
            <div class="box">
                <label class="i-label">职&nbsp;&nbsp;&nbsp;&nbsp;业</label>
                <select name="work" size="1" class="i-text" style="padding:5px">
                    <option value="老师">教师</option>
                    <option value="学生" selected="selected">学生</option>
                </select>
            </div>
            <div class="box">
                <label class="i-label">个人爱好</label>
                <span class="i-text" style="display:block;width:300px;">
	            <input type="checkbox" name="like" value="电脑网络" checked>电脑网络
		        <input type="checkbox" name="like" value="影视娱乐">影视娱乐
		        <input type="checkbox" name="like" value="棋牌娱乐">棋牌娱乐
	        </span >
                <span class="i-text" style="display:block;width:300px;">
	            <input type="checkbox" name="like" value="读书读报">读书读报
		        <input type="checkbox" name="like" value="美洒佳肴">美酒佳肴
		        <input type="checkbox" name="like" value="绘画书法">绘画书法
			</span>
            </div>
            <div class="box">
                <label class="i-label">个人说明</label>
                <textarea rows="8" cols="30" name="intro" class="i-text"></textarea>
            </div>
            <div class="box">
		    <span class="i-text">
			    <input type="submit"value="提交">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" value="清 除">
			</span>
            </div>
        </form>
    </div>
</div>
</body>
</html>
