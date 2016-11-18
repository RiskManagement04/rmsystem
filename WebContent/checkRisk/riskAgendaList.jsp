<%@page import="model.*,factory.DaoFactory,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href='<%=request.getContextPath()+"/styles/commonStyle.css"%>'/>
<link rel="stylesheet" type="text/css" href='<%=request.getContextPath()+"/styles/bootstrap/css/bootstrap.css"%>'/>
<style type="text/css">


</style>

		<script src='<%=request.getContextPath()+"/styles/bootstrap/jquery-1.11.3.js"%>'></script>
		<script src='<%=request.getContextPath()+"/styles/bootstrap/js/bootstrap.min.js"%>'></script>
<title>风险列表</title>

</head>
<body>
	<jsp:useBean id="riskItemList"
		type="bean.RiskAgendaListBean"
		scope="session"></jsp:useBean>
	<jsp:useBean id="riskItem" class="model.RiskItem"
		scope="page"></jsp:useBean>
		
<div class="guideList">
	<div class="network_logo">
		<img alt="" src="https://www.iworker.cn/i/avatars/thumbs2/company_avatar.png">
	</div>
	<ul class="nav nohover auto_overflow">
			<li class="home"><i></i><a href='<%=request.getContextPath()+"/CheckRiskItemServlet"%>' style="color: #f4f4f4;background-color: #4a90e2;">风险列表</a></li>
			<li class="home focus"><i></i><a href='<%=request.getContextPath()+"/CheckAgendaListServlet"%>' style="color: #f4f4f4;background-color: #4a90e2;">计划列表</a></li>
	</ul>
</div>

<div class="blue_body">
	<div class="blue_header">
			<div class="cname_wrap dropdown clearfix">
			  <a href="javascript:;" class="dropdown-toggle cname" id="header_cname" >
			  软件项目风险管理</a>
			  <ul class="dropdown-menu dropdown-menu-left" id="cname_list" style="width:250px;">
			  </ul>
			</div>	
			<div style="float: right;margin-right:20px">
				<a href='<%=request.getContextPath()+"/login/login.jsp"%>' style="float: right;margin-right:20px">退出</a>
			</div>	
			<div style="float: right;margin-right:40px;font-size: 14pt; color: #4a90e2">
				<%
				User user=(User)session.getAttribute("LoginUser");
				if(user.getIdentity()==UserType.DEVELOPER){
				%>
					<small>开发人员：</small>
				<%
				}else{
				%>
					<small>项目经理：</small>
				<%
				}
				%>
				
				<small><%=user.getTrueName() %></small>
			</div>					
	</div>	
	
	
	<div class="project-warp">
	新建风险管理计划按钮、
	风险管理计划列表
	在这里加
	</div>
	
</div>


</body>
</html>