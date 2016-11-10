<%@page import="model.RiskStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
 <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css">
 <script src="../bootstrap/jquery-1.11.3.js"></script>
 <script src="../bootstrap/js/bootstrap.js"></script>

<title>风险列表</title>
</head>
<body>
	<jsp:useBean id="riskItemList"
		type="bean.RiskItemListBean"
		scope="session"></jsp:useBean>
	<jsp:useBean id="riskItem" class="model.RiskItem"
		scope="page"></jsp:useBean>
		
	<div class="container-fluid" style="padding-top: 5%">
	<div class="row-fluid">
		<div class="span2">
		</div>
		<div class="span8">
			<h3>
				风险条目列表
			</h3>
			<table class="table">
				<thead>
					<tr>
						<th>风险序号</th>
						<th>风险编号</th>
						<th>风险名称</th>
						<th>风险描述</th>
						<th>可能性</th>
						<th>影响程度</th>
						<th>触发器</th>
						<th>提交者</th>
						<th>风险状态</th>
						<th>创建时间</th>
						<th>跟踪目录</th>
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
						<th>
							<form method='POST' action="<%=request.getContextPath()+"/CheckRiskTrackingServlet"%>">
								<input name="riskItemId" value="<%=riskItemList.getRiskItem(i).getRiskItemId()%>"/>
								<input type="submit" class="btn" value='跟踪记录'/>
							</form>
						</th>
					</tr>
				<%
					}
				%>
				</tbody>
			</table>
		</div>
		<div class="span2">
		</div>
	</div>
</div>

</body>
</html>