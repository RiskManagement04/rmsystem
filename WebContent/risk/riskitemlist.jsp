<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>项目风险</title>
</head>
<body>

	<jsp:useBean id="allExercise"
		type="edu.nju.onlinecheck.action.business.ExerciseListBean"
		scope="session"></jsp:useBean>
	<jsp:useBean id="item" class="edu.nju.onlinecheck.model.Exercise"
		scope="page"></jsp:useBean>
		
	<h3>所 有 作 业：</h3>
	<table width="100%" border="0" cellpadding="0" cellspacing="1">
		<tr>
			<td align="center">编号</td>
			<td align="center">名称</td>
			<td align="center">最后一次提交</td>
			<td align="center">分数</td>
		</tr>
		<%
			for(int i=0;i<allExercise.getExerciseList().size();i++){
				pageContext.setAttribute("item", allExercise.getExerciseList(i));
		%>
		<tr>
			<td align="center"><jsp:getProperty name="item" property="exerciseid"/></td>
			<td align="center"><jsp:getProperty name="item" property="exercisename"/></td>
			<%
				if(allExercise.getExerciseList(i).getSubmitdate()!=null){
			%>
				<td align="center"><jsp:getProperty name="item" property="submitdate"/></td>
			<%
				}else{
			%>
					<td align="center">未提交</td>
			<%
				}
				if(allExercise.getExerciseList(i).getScore()>0){
			%>
				<td align="center"><jsp:getProperty name="item" property="score"/></td>
			<%
				}else{
			%>
				<td align="center">未评分</td>
			<%
				}
			%>
		</tr>
		<%
			}
		%>
	</table>
	<br/>
	<form method='GET' action="<%=request.getContextPath()+"/user/login.jsp"%>">
		<input type='submit' name='Logout' value='退 出'>
	</form>
	<br/>
	
</body>
</html>