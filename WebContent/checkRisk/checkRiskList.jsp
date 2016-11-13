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

<script type="text/javascript">
function openWindow()
{
	var url='<%=request.getContextPath()+"/checkRisk/AddRiskItem.jsp"%>'; //转向网页的地址;
    var name='新增风险条目';                        //网页名称，可为空;
    var iWidth=610;                          //弹出窗口的宽度;
    var iHeight=600;                       //弹出窗口的高度;
    //获得窗口的垂直位置
    var iTop = (window.screen.availHeight-30-iHeight)/2;
    //获得窗口的水平位置
    var iLeft = (window.screen.availWidth-10-iWidth)/2;
    var params='width='+iWidth
               +',height='+iHeight
               +',top='+iTop
               +',left='+iLeft
               +',channelmode=no'//是否使用剧院模式显示窗口。默认为 no
               +',directories=no'//是否添加目录按钮。默认为 yes
               +',fullscreen=no' //是否使用全屏模式显示浏览器
               +',location=no'//是否显示地址字段。默认是 yes
               +',menubar=no'//是否显示菜单栏。默认是 yes
               +',resizable=no'//窗口是否可调节尺寸。默认是 yes
               +',scrollbars=no'//是否显示滚动条。默认是 yes
               +',status=no'//是否添加状态栏。默认是 yes
               +',titlebar=no'//默认是 yes
               +',toolbar=no'//默认是 yes
               ;
  window.open(url, name,params);
}
</script>

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
			<label style="margin-left:60px; margin-top:20px;  font-size:26px;"> 风险列表</label>
		</div>
	
		<div  class="iwk-table-wrap">
			<div class="edit-buttons">
				<button type="button"  class="iwk2 btn btn-add" onclick="openWindow()"><i></i>新增</button>
				
			</div>
			<table id="table_project_list" class="iwk-table" ></table>
			<div id="page_project" class="iwk-table-pager"></div>
		</div>
	</div>
	
</div>


</body>
</html>