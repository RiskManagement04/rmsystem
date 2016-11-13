<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../styles/commonStyle.css"/>
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
			<div class="uname_wrap dropdown clearfix">
				  <a href="javascript:;"  class="dropdown-toggle uname" data-toggle="dropdown">
					  <!--img  alt="" src="https://file.iworker.cn/inside/avatar/id:5827da69bb6630687f626214"-->
					  <span class="name">biubiu</span>
					  <span class="glyphicon glyphicon-menu-down"></span>
				  </a>
				  <ul class="dropdown-menu dropdown-menu-right">
				     	<li>
					 		<a href="javascript:;" onclick="personal.go_center()">进入个人中心</a>
					 	</li>
					 	<li>
						  <a href="https://www.iworker.cn/inside/user/settings" title="我的设置" class="item">我的设置</a>
					  	</li>
					  	<li>
						  <a href="javascript:;" onclick="iwk_notify.open_send_modal();" class="item">开启桌面推送</a>
					  	</li>
					 	<li>
					 		<a  href="https://www.iworker.cn/signout">退出</a>
					 	</li>
				  </ul>
			</div>
			
	</div>
	
</div>
</body>
</html>