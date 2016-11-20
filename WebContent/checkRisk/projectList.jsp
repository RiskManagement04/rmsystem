<%@page import="model.*,factory.DaoFactory,java.util.*"%>
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
		
<div class="guideList">
	<div class="network_logo">
		<img alt="" src="https://www.iworker.cn/i/avatars/thumbs2/company_avatar.png">
	</div>
	<ul class="nav nohover auto_overflow">
			<li class="home"><i></i><a href='<%=request.getContextPath()+"/CheckRiskListServlet"%>' >风险列表</a></li>
			<li class="schedule "><i></i><a href='<%=request.getContextPath()+"/CheckAgendaListServlet"%>'>计划列表</a></li>
			<%
			User user=(User)session.getAttribute("LoginUser");
			if(user.getIdentity()==UserType.MANAGER){
			%>
			<li class="home"><i></i><a href='<%=request.getContextPath()+"/CheckProjectListServlet"%>' style="color: #f4f4f4;background-color: #4a90e2;">项目列表</a></li>
			<%
			}
			%>			
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
		<div class="header">
			<label style="margin-left:50px; margin-top:20px;  font-size:26px;"> 项目列表</label>
		</div>
	
		<div  class="iwk-table-wrap">
			<div class="edit-buttons">

				<a id="modal-188393" href="#modal-container-188393" role="button" class="btn" data-toggle="modal" style="margin-right:50px;margin-top:-2px;float:right">新建项目</a>	
				<div class="modal fade hide in" id="modal-container-188393" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top:-10px">
					<div>
						<div>
							<div class="modal-header">
								 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
								<h4 class="modal-title" id="myModalLabel">
									项目
								</h4>
							</div>
							<%
								int userId=(Integer)session.getAttribute("LoginId");
								List<Project> projectList=DaoFactory.getProjectDao().getProjectByUser(userId);
							%>
							<form class="form-horizontal" action="<%=request.getContextPath()+"/AddRiskItemServlet"%>" method="post">
							<div class="modal-body">
								
										<div class="control-group" style="margin-top:5px;text-align:center">
											 <label class="control-label" for="inputEmail"style="float:left">项目名称 </label>
											<div class="controls">
												<input id="inputEmail" type="text" name="projectName"/>
											</div>
										</div>
										<div class="control-group" style="margin-top:5px;text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">项目内容 </label>
											<div class="controls">
												<textarea class="form-control" rows="3" name="projectContent"></textarea>
											</div>
										</div>
										<div class="control-group"style="margin-top:5px;text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">风险状态</label>
											<div class="controls">
												<select class="selectpicker" name="riskStatus">
												  <option value="predicted">未发生</option>
												  <option value="happened">已发生</option>
												  <option value="solved">已解决</option>
												</select>
											</div>
										</div>																												
							</div>
							<div class="modal-footer">
								 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
								 <button type="submit" class="btn btn-primary">保存</button>
							</div>
							</form>
						</div>
						
					</div>
					
				</div>
			
			</div>
				<table class="table table-striped" style="margin-left:50px">
				<thead>
				
					<tr>
						<th>序号</th>
						<th>项目名称</th>
						<th>项目内容</th>
						<th style="padding-right:60px">项目组成员</th>
					</tr>
				</thead>
				<tbody>
				<%
					ArrayList<Project> list=(ArrayList<Project>)session.getAttribute("projectsOfManager");
					for(int i=0;i<list.size();i++){
						Project p=list.get(i);
				%>
					<tr style="font-weight:normal;">
						<th style="font-weight:normal;"><%=i+1 %></th>
						<th style="font-weight:normal;"><%=p.getProjectName() %></th>
						<th style="font-weight:normal;"><%=p.getProjectContent() %></th>
						<th style="margin-right:60px;font-weight:normal">
						<%
						ArrayList<User> users=p.getUsers();
						for(int j=0;j<users.size();j++){
						%>
						<p><%=users.get(j).getTrueName() %></p>
						<%
						}
						%>
						</th>
					</tr>
				<%
					}
				%>
				</tbody>
			</table>
			<div id="page_project" class="iwk-table-pager"></div>
		</div>
	</div>
	
</div>


</body>
</html>