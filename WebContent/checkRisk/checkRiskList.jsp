<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../styles/commonStyle.css"/>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">

		<script src="bootstrap/jquery-1.11.3.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
<title>风险列表</title>
</head>
<body>
<div class="guideList">
	<div class="network_logo">
		<img alt="" src="https://www.iworker.cn/i/avatars/thumbs2/company_avatar.png">
	</div>
	<ul class="nav nohover auto_overflow">
			<li class="home focus"><i></i><a href="inside/dashboard" onclick="" class="inside/dashboard">首页</a></li>
	</ul>
</div>


<div class="blue_body">
	<div class="blue_header">
			<div class="cname_wrap dropdown clearfix">
			  <a href="javascript:;" class="dropdown-toggle cname" id="header_cname" >
			 风险管理		  </a>
			  <ul class="dropdown-menu dropdown-menu-left" id="cname_list" style="width:250px;">
			  </ul>
			</div>						
	</div>	
	
	<div class="project-warp">
		<div class="header">
			<label> 风险列表</label>
		</div>
	
		<div  class="iwk-table-wrap">
			<div class="edit-buttons">
				<button type="button"  class="iwk2 btn btn-add" ><i></i>新增</button>
				<button type="button"  class="iwk2 btn btn-close" post-type="close" style="display: none"><i></i>关闭</button>
				<button type="button"  class="iwk2 btn btn-remove" post-type="remove" style="display: none"><i></i>删除</button>
				<button type="button"  class="iwk2 btn btn-restart" post-type="restart" style="display: none"><i></i>重启</button>
				<button type="button"  class="iwk2 btn fr btn-table-filter" ><i></i>筛选</button>
			</div>
			<table id="table_project_list" class="iwk-table" ></table>
			<div id="page_project" class="iwk-table-pager"></div>
		</div>


	</div>
	
</div>


</body>
</html>