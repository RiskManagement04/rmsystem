<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
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
<a href="<%=request.getContextPath()+"/register/register.jsp"%>">注 册</a>
<form method='POST' action='../LoginServlet'>
用户名&nbsp;&nbsp;&nbsp;<input type='text' name='loginid' value='<%=login%>'><br/><br/>
  密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='password' name='loginpassword' width='150px'><br/><br/>
<input type='submit' name='submitlogin' value='登  录'>
</form>

</body>
</html>