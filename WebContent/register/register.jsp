<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
 <link rel="stylesheet" type="text/css" href="../styles/bootstrap/css/bootstrap.css">
 <link rel="stylesheet" type="text/css" href="../styles/loginRegisterStyle.css" />
 <script src="../bootstrap/jquery-1.11.3.js"></script>
 <script src="../bootstrap/js/bootstrap.js"></script>

<title>注册</title>
</head>
	<body style="background-image: url('../img/login.jpg'); background-attachment: fixed;">


	<!-- div class="container-fluid">
	
	<div class="row-fluid">
		<div class="span4">
		</div>
		<div class="span4" style='padding-top: 15%;'>
			<form class="form-horizontal" style='center' method='POST' action="<%=request.getContextPath()+"/RegisterServlet"%>">
				<div class="control-group">
					 <label class="control-label" for="inputEmail">用户名</label>
					<div class="controls">
						<input id="inputEmail" name="inputEmail" type="text" required/>
					</div>
				</div>
				<div class="control-group">
					 <label class="control-label" for="inputEmail">姓名</label>
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
						<input id="developer" type="radio" checked="checked" name="1" />开发人员 &nbsp;&nbsp;<input id="manager" type="radio"  name="1"/>项目经理
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						
						 <div>
						 	<a href="<%=request.getContextPath()+"/login/login.jsp"%>"><input type='button' value="返回" class="btn"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						  	<input type='submit' value="注册" class="btn"/>
						  
						 </div>
						 
					</div>
				</div>
			</form>
		</div>
		<div class="span4">
		</div>
	</div>
</div-->
<div class = "Container">
			
			
			<div class="login" >
				
				<form class="form-horizontal" style='center' method='POST' action="<%=request.getContextPath()+"/RegisterServlet"%>">
					<h3>注册</h3>
					<label>用户名</label>
					<input id="inputEmail" name="inputEmail" type="text" required/>
					<br/>
					<label>姓名</label>
					<input id="inputEmail" name="inputName" type="text" required/>
					<br/>
					<label>密码</label>
					<input type="password" name="inputPassword" required/>
					<br/>
					<input id="developer" type="radio" checked="checked" name="1" />开发人员 &nbsp;&nbsp;<input id="manager" type="radio"  name="1"/>项目经理
					<br/>
					<a href="<%=request.getContextPath()+"/login/login.jsp"%>"><input type='button' value="返回" class="btn"/></a>
					<input type="submit" value="注册" class ="logButton"  name='register'"></input>

				</form>
			</div>
		</div>
</body>
</html>