<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="riskTrackingList"
		type="bean.RiskTrackingItemListBean"
		scope="session"></jsp:useBean>
	<jsp:useBean id="riskTrackingItem" class="model.RiskTrackingItem"
		scope="page"></jsp:useBean>
		
<div class="container-fluid" style="padding-top: 5%">
	<div class="row-fluid">
		<div class="span2">
		</div>
		<div class="span8">
			<h3>
				 跟踪条目列表
			</h3>
			<table class="table">
				<thead>
					<tr>
						<th>序号</th>
						<th>跟踪条目编号</th>
						<th>跟踪者名称</th>
						<th>修改时间</th>
						<th>风险状态</th>
						<th>风险描述</th>
						<th>应对措施</th>						
					</tr>
				</thead>
				<tbody>
				<%
					for(int i=0;i<riskTrackingList.getRiskTrackingItemList().size();i++){
						pageContext.setAttribute("riskTrackingItem", riskTrackingList.getriskTrackingItem(i));
				%>
					<tr>
						<th><%=i+1 %></th>
						<th><jsp:getProperty name="riskTrackingItem" property="riskTrackingItemId"/></th>
						<th><jsp:getProperty name="riskTrackingItem" property="trackerName"/></th>
						<th><jsp:getProperty name="riskTrackingItem" property="createTime"/></th>
						<%
						if(riskTrackingList.getriskTrackingItem(i).getRiskStatus()==model.RiskStatus.PREDICTED){							
						%>
						<th>未发生</th>
						<%
						}else if(riskTrackingList.getriskTrackingItem(i).getRiskStatus()==model.RiskStatus.HAPPENED){
						%>
						<th>已发生</th>
						<%
						}else{
						%>
						<th>未解决</th>
						<%
						}
						%>
						<th><jsp:getProperty name="riskTrackingItem" property="riskContent"/></th>
						<th><jsp:getProperty name="riskTrackingItem" property="measures"/></th>
					
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