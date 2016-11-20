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
<title>风险跟踪列表</title>
</head>
<body>
<jsp:useBean id="riskTrackingList"
		type="bean.RiskTrackingItemListBean"
		scope="session"></jsp:useBean>
	<jsp:useBean id="riskTrackingItem" class="model.RiskTrackingItem"
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
			  <a class="dropdown-toggle cname" id="header_cname" >
			 软件项目风险管理		  </a>
			 
			  <ul class="dropdown-menu dropdown-menu-left" id="cname_list" style="width:250px;">		   
			  </ul>		  
			</div>	
		<div style="float: right;margin-right:20px">
			<a href='<%=request.getContextPath()+"/login/login.jsp"%>' style="float: right;margin-right:20px">退出</a>
		</div>						
	</div>	
	<div class="project-warp">
		<div class="header">
			<%
			int riskItemId=(Integer)session.getAttribute("riskItemIdOfDetails");
			String riskName=DaoFactory.getRiskItemDao().getRiskName(riskItemId);
			%>
			<label style="margin-left:50px; margin-top:20px;  font-size:26px;"> <%=riskName %> 跟踪列表</label>
		</div>
	
		<div  class="iwk-table-wrap">
			<div class="edit-buttons">
	
				<a id="modal-188393" href="<%=request.getContextPath()+"/CheckAgendaRiskItemServlet"%>" role="button" class="btn" style="margin-right:60px;margin-top:10px; margin-bottom:10px;float:right;color:black">返回</a>			
				<a id="modal-188393" href="#modal-container-188393" role="button" class="btn" data-toggle="modal" style="margin-right:60px;margin-top:10px;margin-bottom:10px;float:right">新增</a>	
					
				<div class="modal fade" id="modal-container-188393" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top:-10px">
					<div>
						<div>
							<div class="modal-header">
								 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
								<h4 class="modal-title" id="myModalLabel">
									风险跟踪条目
								</h4>
							</div>
							<form class="form-horizontal" action="<%=request.getContextPath()+"/AddRiskTrackingServlet"%>" method="post">
							<div class="modal-body">										
										<div class="control-group"style="text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">风险状态</label>
											<div class="controls">
												<select class="selectpicker" name="riskStatus">
												  <option value="predicted">未发生</option>
												  <option value="happened">已发生</option>
												  <option value="solved">已解决</option>
												</select>
											</div>
										</div>	
										<div class="control-group" style="margin-top:10px;text-align:center">
											 <label class="control-label" for="inputPassword"style="float:left">风险描述    </label>
											<div class="controls">
												<input id="inputPassword" type="text" name="riskContent"/>
											</div>
										</div>	
										<div class="form-group" style="margin-top:10px;text-align:center">
											 <label class="control-label" for="name" style="float:left">采取措施   </label>
											<div class="controls">
												<textarea class="form-control" rows="5" name="measures"></textarea>
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
						<th>风险状态</th>
						<th>风险描述</th>
						<th>解决方案</th>
						<th>可能性</th>
						<th>影响</th>
						<th>跟踪者名称</th>
						<th style="padding-right:60px">修改时间</th>						
					</tr>
				</thead>
				<tbody>
				<%
					for(int i=0;i<riskTrackingList.getRiskTrackingItemList().size();i++){
						pageContext.setAttribute("riskTrackingItem", riskTrackingList.getriskTrackingItem(i));
				%>
					<tr>
						<th style="font-weight:normal;"><%=i+1 %></th>
						<%
						if(riskTrackingList.getriskTrackingItem(i).getRiskStatus()==model.RiskStatus.PREDICTED){							
						%>
						<th style="font-weight:normal;">未发生</th>
						<%
						}else if(riskTrackingList.getriskTrackingItem(i).getRiskStatus()==model.RiskStatus.HAPPENED){
						%>
						<th style="font-weight:normal;">已发生</th>
						<%
						}else{
						%>
						<th style="font-weight:normal;">未解决</th>
						<%
						}
						%>
						
						<th style="font-weight:normal;"><jsp:getProperty name="riskTrackingItem" property="riskContent"/></th>
						<th style="font-weight:normal;"><jsp:getProperty name="riskTrackingItem" property="measures"/></th>
						
												<%
						if(riskTrackingList.getriskTrackingItem(i).getPossibility()==1){							
						%>
						<th style="font-weight:normal;">低</th>
						<%
						}else if(riskTrackingList.getriskTrackingItem(i).getPossibility()==2){
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
						if(riskTrackingList.getriskTrackingItem(i).getImpact()==1){							
						%>
						<th style="font-weight:normal;">低</th>
						<%
						}else if(riskTrackingList.getriskTrackingItem(i).getImpact()==2){
						%>
						<th style="font-weight:normal;">中</th>
						<%
						}else{
						%>
						<th style="font-weight:normal;">高</th>
						<%
						}
						%>
						
						<th style="font-weight:normal;"><jsp:getProperty name="riskTrackingItem" property="trackerName"/></th>
						<th style="padding-right:60px;font-weight:normal;"><jsp:getProperty name="riskTrackingItem" property="createTime"/></th>
					
					</tr>
				<%
					}
				%>
				</tbody>
			</table>
		</div>


	</div>
	
</div>


</body>
</html>