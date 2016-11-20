<%@page import="model.*,factory.DaoFactory,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href='<%=request.getContextPath()+"/styles/commonStyle.css"%>'/>
	<link rel="stylesheet" type="text/css" href='<%=request.getContextPath()+"/styles/bootstrap/css/bootstrap.css"%>'/>

	<script src='<%=request.getContextPath()+"/styles/bootstrap/jquery-1.11.3.js"%>'></script>
	<script src='<%=request.getContextPath()+"/styles/bootstrap/js/bootstrap.min.js"%>'></script>
	<title>风险列表</title>
</head>
<body>

	<jsp:useBean id="agendaRiskItemList"
		type="bean.RiskItemListBean"
		scope="session"></jsp:useBean>
	<jsp:useBean id="riskItem" class="model.RiskItem"
		scope="page"></jsp:useBean>
		
<div class="guideList">
	<div class="network_logo">
		<img alt="" src="https://www.iworker.cn/i/avatars/thumbs2/company_avatar.png">
	</div>
	<ul class="nav nohover auto_overflow">
			<li class="home "><i></i><a href='<%=request.getContextPath()+"/CheckRiskListServlet"%>'>风险列表</a></li>
			<li class="schedule "><i></i><a href='<%=request.getContextPath()+"/CheckAgendaListServlet"%>' style="color: #f4f4f4;background-color: #4a90e2;">计划列表</a></li>			
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
		<div class="header">
			<label style="margin-left:50px; margin-top:20px;  font-size:26px;"> 风险列表</label>
		</div>
	
		<div  class="iwk-table-wrap">
			<div style="margin-left:60px; margin-top:25px;">
                <form method="post" action='<%=request.getContextPath()+"/SearchRisk"%>'>			
				 开始时间<input class="txt" type="date" value="" name="startDate" style="width:130px; margin-left:15px;margin-right:15px;" required/>
               	 结束时间<input class="txt" type="date" value="" name="endDate" style="width:130px; margin-left:15px;margin-right:15px;" required/>
                <select class="txt"  name="condition" style="width:140px; margin-left:15px;margin-right:15px;">
                    <option value="被识别最多" selected="selected">被识别最多</option>
                    <option value="演变成问题最多">演变成问题最多</option>
                </select>

                <input class="btn" type="submit" name="check" value="查询" style="width:80px; margin-top:-12px;margin-left:10px;margin-right:15px" />
				<a id="modal-188393" href="#modal-container-188393" role="button" class="btn" data-toggle="modal" style="margin-right:60px;margin-top:-2px;float:right">新增风险</a>	
				</form>		
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
								List<Project> projectList=DaoFactory.getProjectDao().getProjects();
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

										<div class="control-group"style="margin-top:5px;text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">风险类别</label>
											<div class="controls">
												<select class="selectpicker" name="riskType">
												  <option value="Scope_Risk">范围风险</option>
												  <option value="Schedule_Risk">进度风险</option>
												  <option value="Cost_Risk">成本风险</option>
												  <option value="Quality_Risk">质量风险</option>
												  <option value="Technology_Risk">技术风险</option>
												  <option value="Management_Risk">管理风险</option>
												  <option value="Commercial_Risk">商业风险</option>
												  <option value="Legal_Risk">法律风险</option>
												  <option value="SocialEnvironment_Risk">社会环境风险</option>
												</select>
											</div>
										</div>											
										<div class="control-group" style="margin-top:5px;text-align:center">
											 <label class="control-label" for="inputEmail"style="float:left">风险名称              </label>
											<div class="controls">
												<input id="inputEmail" type="text" name="riskName" required/>
											</div>
										</div>
										<div class="control-group"style="margin-top:5px;text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">风险触发器</label>
											<div class="controls">
												<input id="inputPassword" type="text" name="trigger" required/>
											</div>
										</div>
										<div class="control-group" style="margin-top:5px;text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">风险内容    </label>
											<div class="controls">
												<textarea class="form-control" rows="3" name="riskContent"></textarea>
											</div>
										</div>
										<div class="control-group"style="margin-top:5px;text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">解决方案</label>
											<div class="controls">
												<textarea class="form-control" rows="3" name="measures"></textarea>
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
												  <option value="PREDICTED">未发生</option>
												  <option value="HAPPENED">已发生</option>
												  <option value="SOLVED">已解决</option>
												</select>
											</div>
										</div>		
										<div class="control-group" style="margin-top:5px;text-align:center">
											 <label class="control-label" style="float:left">指定跟踪者</label>
											<div class="controls">
				                                <select class="selectpicker" multiple="multiple" name="trackres">
				                                	<%
				                                		ArrayList<User> users=DaoFactory.getUserDao().findAllUsers();
				                                		for(int i=0;i<users.size();i++){
				                                			User u=users.get(i);
				                                			if(u.getUserId()!=user.getUserId()){
				                                	%>
				                                		<option value='<%=u.getUserId()%>'><%=u.getTrueName() %></option>
				                                	<%	
				                                			}
				                                		}
				                                	%>
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
						<th>删除</th>
						<th>项目</th>
						<th>风险名称</th>
						<th>风险描述</th>
						<th>风险类别</th>
						<th>触发器</th>
						<th>风险状态</th>
						<th>解决方案</th>
						<th>可能性</th>
						<th>影响程度</th>						
						<th style="padding-right:60px">跟踪目录</th>
					</tr>
				</thead>
				<tbody>
				<%
					for(int i=0;i<agendaRiskItemList.getRiskItemList().size();i++){
						pageContext.setAttribute("riskItem", agendaRiskItemList.getRiskItem(i));
				%>
					<tr style="font-weight:normal;">
						<th>
						<form action="<%=request.getContextPath()+"/DeleteRiskItemServlet"%>" method="post">		
							<input type="hidden" value='<%=agendaRiskItemList.getRiskItem(i).getRiskItemId() %>' name="deleteRiskItemId"/>				
							<input type="submit" class="btn btn-default btn-sm bg" value='删除' src="/img/delete_icon.jpg"/>
						</form>
						</th>
						<th style="font-weight:normal;"><jsp:getProperty name="riskItem" property="projectName"/></th>
						<th style="font-weight:normal;"><jsp:getProperty name="riskItem" property="riskName"/></th>
						<th style="font-weight:normal;"><jsp:getProperty name="riskItem" property="riskContent"/></th>
					<%
						RiskType riskType=agendaRiskItemList.getRiskItem(i).getRiskType();
						String type=agendaRiskItemList.getRiskItem(i).convertRiskTypeToShow(riskType);
					%>
						<th style="font-weight:normal;"><%=type %></th>
						<th style="font-weight:normal;"><jsp:getProperty name="riskItem" property="trigger"/></th>	
					<%
						if(agendaRiskItemList.getRiskItem(i).getRiskStatus()==RiskStatus.PREDICTED){
					%>
						<th style="font-weight:normal;">未发生</th>
					<%
						}else if(agendaRiskItemList.getRiskItem(i).getRiskStatus()==RiskStatus.HAPPENED){
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
						if(agendaRiskItemList.getRiskItem(i).getPossibility()==1){
					%>
						<th style="font-weight:normal;">低</th>
					<%
						}else if(agendaRiskItemList.getRiskItem(i).getPossibility()==2){
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
						if(agendaRiskItemList.getRiskItem(i).getImpact()==1){
					%>
						<th style="font-weight:normal;">低</th>
					<%
						}else if(agendaRiskItemList.getRiskItem(i).getImpact()==2){
					%>
						<th style="font-weight:normal;">中</th>
					<%
						}else{
					%>
						<th style="font-weight:normal;">高</th>
					<%
						}
					%>
										
						<th style="margin-right:60px;font-weight:normal">
							<form method='POST' action="<%=request.getContextPath()+"/CheckRiskTrackingServlet"%>">
								<input type="hidden" name="riskItemId" value="<%=agendaRiskItemList.getRiskItem(i).getRiskItemId()%>"/>
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