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
	<jsp:useBean id="agendaList"
		type="bean.RiskAgendaListBean"
		scope="session"></jsp:useBean>
	<jsp:useBean id="riskTypeRank" class="model.RiskTypeRank"
		scope="page"></jsp:useBean>
		
<div class="guideList">
	<div class="network_logo">
		<img alt="" src="https://www.iworker.cn/i/avatars/thumbs2/company_avatar.png">
	</div>
	<ul class="nav nohover auto_overflow">
			<li class="home"><i></i><a href='<%=request.getContextPath()+"/CheckRiskItemServlet"%>' >风险列表</a></li>
			<li class="schedule focus"><i></i><a href="<%=request.getContextPath()+"/CheckAgendaListServlet"%>" onclick="" class="apps/schedule">计划列表</a></li>

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
		<div  class="iwk-table-wrap">
			<div class="edit-buttons" style="margin-left:60px; margin-top:25px;">
				
                
               	开始时间<input class="txt" type="date" value="" style="width:120px; margin-left:15px;margin-right:15px;"/>
               	 结束时间<input class="txt" type="date" value="" style="width:120px; margin-left:15px;margin-right:15px;"/>
                <select class="txt" style="width:120px; margin-left:15px;margin-right:15px;">
                    <option value="被识别最多" selected="selected">被识别最多</option>
                    <option value="演变成问题最多">演变成问题最多</option>
                </select>
                <input class="btn" type="button" name="check" value="查询" style="width:80px; margin-top:-12px;margin-left:10px;margin-right:15px;" />
                
            
				<a id="modal-188393" href="#modal-container-188393" role="button" class="btn" data-toggle="modal" style="margin-right:45px;margin-top:-2px; margin-bottom:10px;float:right;width:50px;">新增</a>			
				<div class="modal fade" id="modal-container-188393" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top:-10px">
					<div>
						<div>
							<div class="modal-header">
								 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
								<h4 class="modal-title" id="myModalLabel">
									计划条目
								</h4>
							</div>
						
							<form class="form-horizontal" action="<%=request.getContextPath()+"/AddRiskAgendaServlet"%>" method="post">
							<div class="modal-body">
								
										<div class="control-group" style="text-align:center">
											 <label class="control-label" for="inputPassword" style="float:left">计划名称</label>
											 <div class="controls">
												<input id="inputEmail" type="text" name="riskName"/>
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
			<div class="span12">
					<canvas id="myChart" width="600" height="400"></canvas>
					<script type="text/javascript">
						var ctx = document.getElementById("myChart").getContext("2d");
						var data = {
								labels : ["January","February","March","April","May","June","July"],
								datasets : [
									
									{
										fillColor : "rgba(151,187,205,0.5)",
										strokeColor : "rgba(151,187,205,1)",
										pointColor : "rgba(151,187,205,1)",
										pointStrokeColor : "#fff",
										data : [1000,8765,11083,8000,6975,8743,5679]
									}
								]
						}
						var options = {scaleLineColor : "rgba(240,255,240,0.5)",scaleFontColor : "#080808", scaleGridLineColor : "rgba(0,0,0,.2)",};

						var myNewChart=new Chart(ctx).Line(data,options);

					</script>
			</div>
		
		</div>
	</div>
	
	
</div>


</body>
</html>