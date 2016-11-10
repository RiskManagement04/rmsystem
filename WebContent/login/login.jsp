<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
 <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css">
 <script src="../bootstrap/jquery-1.11.3.js"></script>
 <script src="../bootstrap/js/bootstrap.js"></script>

<title>登录</title>
</head>
<body>

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
<div class="container-fluid">
	
	<div class="row-fluid">
		<div class="span2">
		</div>
		<div class="span6" style='padding-top: 120px;'>
			<form class="form-horizontal" style='center' action="<%=request.getContextPath()+"/LoginServlet"%>" method='post'>
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
						 <label class="checkbox"><input type="checkbox" /> Remember me</label>
						 <div>
						 	 <button type="submit" class="btn">登录</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						  	<button type="submit" class="btn">注册  </button>
						 </div>
						 
					</div>
				</div>
			</form>
		</div>
		<div class="span4">
		</div>
	</div>
</div>

</body>
</html>