<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
 <link rel="stylesheet" type="text/css" href='<%=request.getContextPath()+"/styles/bootstrap/css/bootstrap.css"%>'>
 <link rel="stylesheet" type="text/css" href='<%=request.getContextPath()+"/styles/loginRegisterStyle.css"%>'>
 <script src="../bootstrap/jquery-1.11.3.js"></script>
 <script src="../bootstrap/js/bootstrap.js"></script>

<title>登录</title>
</head>
<body style="background-image: url('<%=request.getContextPath()+"/img/login.jpg"%>'); background-attachment: fixed;">


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


<div class="container">
		
			<div class="login">
				
				<form action="<%=request.getContextPath()+"/LoginServlet"%>" method="post">
					<h3 style='color:white;font-weight:bold'>登录</h3>
					<label>用户名</label>
					<input id="inputEmail" name="inputEmail" type="text" value='<%=login %>' required/>
					<br/>
					<label>密码</label>
					<input type="password" name="inputPassword" required/>
					<br/>
					<input type="submit" value="登录" class ="logButton"></input>
					<br/><br/>
					<label>还没有账号？<a href="<%=request.getContextPath()+"/register/register.jsp"%>">立即注册</a></label>
				</form>
			</div>
			
		</div>
</body>
</html>