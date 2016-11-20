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
		<script src='<%=request.getContextPath()+"/styles/Chart.js-master/Chart.js"%>'></script>
		<title>风险列表</title>

</head>
<body>
	
	<jsp:useBean id="riskItem" class="model.RiskItem"
		scope="page"></jsp:useBean>
		
<div class="guideList">
	<div class="network_logo">
		<img alt="" src="https://www.iworker.cn/i/avatars/thumbs2/company_avatar.png">
	</div>
	<ul class="nav nohover auto_overflow">
			<li class="home"><i></i><a href='<%=request.getContextPath()+"/CheckRiskListServlet"%>' >风险列表</a></li>
			<li class="schedule focus"><i></i><a href="<%=request.getContextPath()+"/CheckAgendaListServlet"%>" style="color: #f4f4f4;background-color: #4a90e2;" class="apps/schedule">计划列表</a></li>

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
			<label style="margin-left:50px; margin-top:20px;  font-size:26px;"> 计划列表</label>
		</div>
		<div >
			<div class="edit-buttons">
				
                
              
				<a href="<%=request.getContextPath()+"/CheckAgendaRiskItemServlet"%>" role="button" class="btn" style="margin-right:45px;margin-top:-20px; margin-bottom:10px;float:right;width:50px;">返回</a>			
				
			<div style="margin-left:50px; margin-top:20px; width:85%;">
					<canvas id="myChart" style="margin-left:50px;width:400px; height:200px;"></canvas>
					<script type="text/javascript">
						var ctx = document.getElementById("myChart").getContext("2d");
						
						var elabel=' <%=request.getAttribute("riskType")%> ';
						elabel=elabel.split(',');
						
						alert(elabel);
						
						var edata=' <%=request.getAttribute("riskRank")%> ';
						edata=edata.split(',');
						var data = {
								labels :elabel,
								datasets : [
									
									{
										fillColor : "rgba(151,187,205,0.5)",
										strokeColor : "rgba(151,187,205,1)",
										pointColor : "rgba(151,187,205,1)",
										pointStrokeColor : "#fff",
										data :edata
									}
								]
						}
						var options = {scaleLineColor : "rgba(240,255,240,0.5)",scaleFontColor : "#080808", scaleGridLineColor : "rgba(0,0,0,.2)",};

						var myNewChart=new Chart(ctx).Line(data,options);

					</script>
			</div>
			<form class="form-horizontal" action="<%=request.getContextPath()+"/ImportRiskServlet"%>" method="post">
			<div style="margin-top:10px;overflow-y: auto; overflow-x:hidden;height:300px;  width:95%;">
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
						<th >跟踪目录</th>
						<th>
            				<input type="checkbox" name="myTextEditBox" value="checked" style="padding-right:60px"/>是否选中
        				</th>
					</tr>
				</thead>
				<tbody>
				<%
					List riskItemList=(List)session.getAttribute("riskItemList");
					for(int i=0;i<riskItemList.size();i++){
						pageContext.setAttribute("riskItem", riskItemList.get(i));
				%>
					<tr style="font-weight:normal;">
						
						<th style="font-weight:normal;"><jsp:getProperty name="riskItem" property="projectName"/></th>
						<th style="font-weight:normal;"><jsp:getProperty name="riskItem" property="riskName"/></th>
						<th style="font-weight:normal;"><jsp:getProperty name="riskItem" property="riskContent"/></th>
					<%
						RiskType riskType=((RiskItem)riskItemList.get(i)).getRiskType();
						String type=((RiskItem)riskItemList.get(i)).convertRiskTypeToShow(riskType);
					%>
						<th style="font-weight:normal;"><%=type %></th>
						<th style="font-weight:normal;"><jsp:getProperty name="riskItem" property="trigger"/></th>	
					<%
						if(((RiskItem)riskItemList.get(i)).getRiskStatus()==RiskStatus.PREDICTED){
					%>
						<th style="font-weight:normal;">未发生</th>
					<%
						}else if(((RiskItem)riskItemList.get(i)).getRiskStatus()==RiskStatus.HAPPENED){
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
						if(((RiskItem)riskItemList.get(i)).getPossibility()==1){
					%>
						<th style="font-weight:normal;">低</th>
					<%
						}else if(((RiskItem)riskItemList.get(i)).getPossibility()==2){
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
						if(((RiskItem)riskItemList.get(i)).getImpact()==1){
					%>
						<th style="font-weight:normal;">低</th>
					<%
						}else if(((RiskItem)riskItemList.get(i)).getImpact()==2){
					%>
						<th style="font-weight:normal;">中</th>
					<%
						}else{
					%>
						<th style="font-weight:normal;">高</th>
					<%
						}
					%>
										
						<th style="font-weight:normal;"><jsp:getProperty name="riskItem" property="createDate"/></th>
						<th>
            				<input type="checkbox" name="isChoosed" value='<%=((RiskItem)riskItemList.get(i)).getRiskItemId() %>'  style="padding-right:60px"/>
        				</th>
						
						<th>
						
						</th>
					</tr>
				<%
					}
				%>
				</tbody>
			</table>
			</div>
			
			<input  role="button" type="submit"class="btn"  value="导入" style="margin-right:45px;margin-top:20px; margin-bottom:10px;float:right;width:50px;"/>		
			</form>
		</div>
	</div>
	
	
</div>


</body>
</html>