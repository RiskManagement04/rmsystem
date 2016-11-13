<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
 <link rel="stylesheet" type="text/css" href="../styles/bootstrap/css/bootstrap.css">
 <link rel="stylesheet" type="text/css" href="../styles/loginRegisterStyle.css" />
 <script src="../bootstrap/jquery-1.11.3.js"></script>
 <script src="../bootstrap/js/bootstrap.js"></script>

<title>登录</title>
</head>
<body style="background-image: url('../img/login.jpg'); background-attachment: fixed;">


<%
	session = request.getSession(true);
	String login="";
	
	Cookie cookie = null;
	Cookie[] cookies = request.getCookies();
	
	//查找存放登录信息的cookie
	if(cookies!=null){
		for(int i=0;i<cookies.length;i++){
			cookie=cookies[i];
			if(cookie.getName().equals("LoginCookie")){
				login=cookie.getValue();
				break;
			}
		}
	}
	
%>
<!-- div class="container-fluid">
	
	<div class="row-fluid">
		<div class="span4">
		</div>
		<div class="span4" style='padding-top: 15%;'>
			<form class="form-horizontal" style='center' method='POST' action='../LoginServlet'>
				<div class="control-group">
					 <label class="control-label" for="inputEmail">用户名</label>
					<div class="controls">
						<input id="inputEmail" name="inputEmail" type="text" required/>
					</div>
				</div>
				<div class="control-group">
					 <label class="control-label" for="inputPassword">密码</label>
					<div class="controls">
						<input id="inputPassword" name="inputPassword" type="password"  required/>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						 <div>
						 	 <input type="submit" class="btn" value='登录'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						  	<a href="<%=request.getContextPath()+"/register/register.jsp"%>"><input type='button' value="注册" class="btn"/></a>
						 </div>
						 
					</div>
				</div>
			</form>
		</div>
		<div class="span4">
		</div>
	</div>
</div-->

<div class="container">
			<!-- <div class="slogan">
			<h2>快来加入<br/>我们吧</h2>

			
			</div> -->
			
			<div class="login">
				
				<form action='../LoginServlet' method="post">
					<h3 style='color:white;font-weight:bold'>登录</h3>
					<label>昵称</label>
					<input type="text" name="name" />
					<br/><br/>
					<label>密码</label>
					<input type="password" name="password" />
					<br/><br/>
					<input type="submit" value="登录" class ="logButton" "></input>
					<br/><br/>
					<label>还没有账号？<a href="<%=request.getContextPath()+"/register/register.jsp"%>">立即注册</a></label>
				</form>
			</div>
			
		</div>
</body>
</html>