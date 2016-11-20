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
	<jsp:useBean id="riskItemList"
		type="bean.RiskItemListBean"
		scope="session"></jsp:useBean>
	<jsp:useBean id="riskItem" class="model.RiskItem"
		scope="page"></jsp:useBean>
		
<div class="guideList">
	<div class="network_logo">
		<img alt="" src="https://www.iworker.cn/i/avatars/thumbs2/company_avatar.png">
	</div>
	<ul class="nav nohover auto_overflow">
			<li class="home focus"><i></i><a href='<%=request.getContextPath()+"/CheckRiskListServlet"%>' style="color: #f4f4f4;background-color: #4a90e2;">风险列表</a></li>
			<li class="schedule "><i></i><a href='<%=request.getContextPath()+"/CheckAgendaListServlet"%>'>计划列表</a></li>
			<%
			User user=(User)session.getAttribute("LoginUser");
			if(user.getIdentity()==UserType.MANAGER){
			%>
			<li class="home"><i></i><a href='<%=request.getContextPath()+"/CheckProjectListServlet"%>'>项目列表</a></li>
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
			<label style="margin-left:50px; margin-top:20px;  font-size:26px;"> 风险列表</label>
		</div>
	
		<div  class="iwk-table-wrap">
			<div class="edit-buttons">

				
				<div class="modal fade hide in" id="modal-container-188393" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top:-10px">
					<div>
						<div>
							<div class="modal-header">
								 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
								<h4 class="modal-title" id="myModalLabel">
									风险条目
								</h4>
							</div>
							<%
								int userId=(Integer)session.getAttribute("LoginId");
								List<Project> projectList=DaoFactory.getProjectDao().getProjectByUser(userId);
							%>
							<form class="form-horizontal" action="<%=request.getContextPath()+"/AddRiskItemServlet"%>" method="post">
							<div class="modal-body">
								
										<div class="control-group" style="text-align:center">
											 <label class="control-label" for="inputPassword" style="float:left">项目名称</label>
											<div class="controls">
												<select class="selectpicker" name="projectName">
												<%
												for(int i=0;i<projectList.size();i++){
												%>
													<option value='<%=projectList.get(i).getProjectId()%>'><%=projectList.get(i).getProjectName() %></option>
												<%
												}
												%>
												</select>
											</div>
										</div>
										
										<div class="control-group" style="margin-top:5px;text-align:center">
											 <label class="control-label" for="inputEmail"style="float:left">风险名称              </label>
											<div class="controls">
												<input id="inputEmail" type="text" name="riskName"/>
											</div>
										</div>
										<div class="control-group" style="margin-top:5px;text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">风险内容    </label>
											<div class="controls">
												<input id="inputPassword" type="text" name="riskContent"/>
											</div>
										</div>
										<div class="control-group"style="margin-top:5px;text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">风险触发器</label>
											<div class="controls">
												<input id="inputPassword" type="text" name="trigger"/>
											</div>
										</div>
										<div class="control-group"style="margin-top:5px;text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">可能性 </label>
											<div class="controls">
												<select class="selectpicker" name="possibility">
												  <option value="high">高</option>
												  <option value="middle">中</option>
												  <option value="low">低</option>
												</select>
											</div>
										</div>
										<div class="control-group"style="margin-top:5px;text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">影响程度</label>
											<div class="controls">
												<select class="selectpicker"name="impact">
													<option value="high">高</option>
												  <option value="middle">中</option>
												  <option value="low">低</option>
												</select>
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
						
						<th>项目</th>
						<th>风险名称</th>
						<th>风险描述</th>
						<th>风险类别</th>
						<th>触发器</th>
						<th>风险状态</th>
						<th>解决方案</th>
						<th>可能性</th>
						<th>影响程度</th>						
						<th>跟踪者</th>

						<th style="padding-right:60px">跟踪目录</th>
					</tr>
				</thead>
				<tbody>
				<%
					for(int i=0;i<riskItemList.getRiskItemList().size();i++){
						pageContext.setAttribute("riskItem", riskItemList.getRiskItem(i));
				%>
					<tr style="font-weight:normal;">
						
						<th style="font-weight:normal;"><jsp:getProperty name="riskItem" property="projectName"/></th>
						<th style="font-weight:normal;"><jsp:getProperty name="riskItem" property="riskName"/></th>
						<th style="font-weight:normal;"><jsp:getProperty name="riskItem" property="riskContent"/></th>
					<%
						RiskType riskType=riskItemList.getRiskItem(i).getRiskType();
						String type=riskItemList.getRiskItem(i).convertRiskTypeToShow(riskType);
					%>
						<th style="font-weight:normal;"><%=type %></th>	
						<th style="font-weight:normal;"><jsp:getProperty name="riskItem" property="trigger"/></th>	
					<%
						if(riskItemList.getRiskItem(i).getRiskStatus()==RiskStatus.PREDICTED){
					%>
						<th style="font-weight:normal;">未发生</th>
					<%
						}else if(riskItemList.getRiskItem(i).getRiskStatus()==RiskStatus.HAPPENED){
					%>
						<th style="font-weight:normal;">已发生</th>
					<%
						}else{
					%>
						<th style="font-weight:normal;">已解决</th>
					<%
						}
					%>		
						<th style="font-weight:normal;"><jsp:getProperty name="riskItem" property="measures"/></th>			
							
					<%
						if(riskItemList.getRiskItem(i).getPossibility()==1){
					%>
						<th style="font-weight:normal;">低</th>
					<%
						}else if(riskItemList.getRiskItem(i).getPossibility()==2){
					%>
						<th style="font-weight:normal;">中</th>
					<%
						}else{
					%>
						<th style="font-weight:normal;">高</th>
					<%
						}
					%>
					
					<%
						if(riskItemList.getRiskItem(i).getImpact()==1){
					%>
						<th style="font-weight:normal;">低</th>
					<%
						}else if(riskItemList.getRiskItem(i).getImpact()==2){
					%>
						<th style="font-weight:normal;">中</th>
					<%
						}else{
					%>
						<th style="font-weight:normal;">高</th>
					<%
						}
					%>
						
					
					<%
						ArrayList<User> trackers=riskItemList.getRiskItem(i).getTrackers();
					%>
						<th style="font-weight:normal;">
						<%
						for(int j=0;j<trackers.size();j++){
							User t=trackers.get(j);
							String name=t.getTrueName();
						%>
						<p><%=name %></p>
						<%
						}
						%>
						</th>									
						
						<th style="margin-right:60px;font-weight:normal">
							<form method='POST' action="<%=request.getContextPath()+"/CheckRiskTrackingServlet"%>">
								<input type="hidden" name="riskItemId" value="<%=riskItemList.getRiskItem(i).getRiskItemId()%>"/>
								<input type="submit" class="btn" value='详细'/>
							</form>
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