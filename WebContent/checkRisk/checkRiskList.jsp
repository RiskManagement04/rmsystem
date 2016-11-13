<%@page import="model.*,factory.DaoFactory,java.util.List"%>
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
			<li class="home focus"><i></i><a  class="inside/dashboard">首页</a></li>
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
	</div>	
	
	<div class="project-warp">
		<div class="header">
			<label style="margin-left:50px; margin-top:20px;  font-size:26px;"> 风险列表</label>
		</div>
	
		<div  class="iwk-table-wrap">
			<div class="edit-buttons">
			<form action="<%=request.getContextPath()+"/AddRiskItemServlet"%>" method="post">

				<a id="modal-188393" href="#modal-container-188393" role="button" class="btn" data-toggle="modal" style="margin-left:50px;margin-top:10px">新建</a>			
				<div class="modal fade" id="modal-container-188393" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top:-10px">
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
							<div class="modal-body">
								<form class="form-horizontal" action="<%=request.getContextPath()+"/AddRiskItemServlet"%>" method="post">
										<div class="control-group" style="margin:0px auto;text-align:center">
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
										
										<div class="control-group" style="margin:0px auto;text-align:center">
											 <label class="control-label" for="inputEmail"style="float:left">风险名称              </label>
											<div class="controls">
												<input id="inputEmail" type="text" name="riskName"/>
											</div>
										</div>
										<div class="control-group" style="margin:0px auto;text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">风险内容    </label>
											<div class="controls">
												<input id="inputPassword" type="text" name="riskContent"/>
											</div>
										</div>
										<div class="control-group"style="margin:0px auto;text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">风险触发器</label>
											<div class="controls">
												<input id="inputPassword" type="text" style="margin-left:-12px" name="trigger"/>
											</div>
										</div>
										<div class="control-group"style="margin:0px auto;text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">可能性 </label>
											<div class="controls">
												<select class="selectpicker" style="margin-left:12px" name="possibility">
												  <option value="high">高</option>
												  <option value="middle">中</option>
												  <option value="low">低</option>
												</select>
											</div>
										</div>
										<div class="control-group"style="margin:0px auto;text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">影响程度</label>
											<div class="controls">
												<select class="selectpicker"name="impact">
													<option value="high">高</option>
												  <option value="middle">中</option>
												  <option value="low">低</option>
												</select>
											</div>
										</div>
										<div class="control-group"style="margin:0px auto;text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">风险状态</label>
											<div class="controls">
												<select class="selectpicker" name="riskStatus">
												  <option value="predicted">未发生</option>
												  <option value="happened">已发生</option>
												  <option value="solved">已解决</option>
												</select>
											</div>
										</div>																				
								</form>
							</div>
							<div class="modal-footer">
								 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
								 <button type="submit" class="btn btn-primary">保存</button>
							</div>
						</div>
						
					</div>
					
				</div>
			</form>
			</div>
				<table class="table" style="margin-left:50px;">
				<thead>
					<tr>
						<th>序号</th>
						<th>风险编号</th>
						<th>风险名称</th>
						<th>风险描述</th>
						<th>可能性</th>
						<th>影响程度</th>
						<th>触发器</th>
						<th>提交者</th>
						<th>风险状态</th>
						<th>创建时间</th>
						<th style="padding-right:60px">跟踪目录</th>
					</tr>
				</thead>
				<tbody>
				<%
					for(int i=0;i<riskItemList.getRiskItemList().size();i++){
						pageContext.setAttribute("riskItem", riskItemList.getRiskItem(i));
				%>
					<tr>
						<th><%=i+1 %></th>
						<th><jsp:getProperty name="riskItem" property="riskItemId"/></th>
						<th><jsp:getProperty name="riskItem" property="riskName"/></th>
						<th><jsp:getProperty name="riskItem" property="riskContent"/></th>
					<%
						if(riskItemList.getRiskItem(i).getPossibility()==1){
					%>
						<th>低</th>
					<%
						}else if(riskItemList.getRiskItem(i).getPossibility()==2){
					%>
						<th>中</th>
					<%
						}else{
					%>
						<th>高</th>
					<%
						}
					%>
					
					<%
						if(riskItemList.getRiskItem(i).getImpact()==1){
					%>
						<th>低</th>
					<%
						}else if(riskItemList.getRiskItem(i).getImpact()==2){
					%>
						<th>中</th>
					<%
						}else{
					%>
						<th>高</th>
					<%
						}
					%>
						<th><jsp:getProperty name="riskItem" property="trigger"/></th>
						<th><jsp:getProperty name="riskItem" property="submitterName"/></th>
						
					<%
						if(riskItemList.getRiskItem(i).getRiskStatus()==RiskStatus.PREDICTED){
					%>
						<th>未发生</th>
					<%
						}else if(riskItemList.getRiskItem(i).getRiskStatus()==RiskStatus.HAPPENED){
					%>
						<th>已发生</th>
					<%
						}else{
					%>
						<th>已解决</th>
					<%
						}
					%>											
						<th><jsp:getProperty name="riskItem" property="createDate"/></th>
						<th style="padding-right:60px">
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