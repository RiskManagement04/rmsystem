<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		</div><
		<div class="span6" style='margin:0 auto;'>
			<form class="form-horizontal" style='center' action="<%=request.getContextPath()+"/LoginServlet"%>" method='post'>
				<div class="control-group">
					 <label class="control-label" for="inputEmail">用户名</label>
					<div class="controls">
						<input id="inputEmail" type="text" />
					</div>
				</div>
				<div class="control-group">
					 <label class="control-label" for="inputPassword">密码</label>
					<div class="controls">
						<input id="inputPassword" type="password" />
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						 <label class="checkbox"><input type="checkbox" /> Remember me</label> <button type="submit" class="btn">登陆</button>
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