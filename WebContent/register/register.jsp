<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>注册</title>
</head>
<body>

	<table width=650>
		<tr>
			<td>
				<form name="registerform" id="registerform" action="<%=request.getContextPath()+"/RegisterServlet"%>" method="post">
					<table border="0">
						<tr>
          					<td>用户ID</td>
          					<td><input type="text" name="registerid" width=200 required></td>
       				    </tr>
						<tr>
          					<td>密码</td>
          					<td><input type="password" name="registerpw" width=200></td>
       				    </tr>
       				    <tr>
       				    	<td>
       				    		<input type="submit" value="注册">
       				    	</td>
       				    	<td>
       				    		<a href="<%=request.getContextPath()+"/login/login.jsp"%>">返回</a>
       				    	</td>
       				    </tr>
					</table>
			    </form>
			</td>
		</tr>
	</table>

</body>
</html>