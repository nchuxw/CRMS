<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ page import="util.*, bean.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>学院机房管理</title>
<!-- Bootstrap core CSS -->
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link href="asset/ie10-viewport-bug-workaround.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
<script src="assets/ie-emulation-modes-warning.js"></script>
<script type="text/javascript">
		if(${empty user}){
			window.location.href='login.jsp';
		}
		else if(${!("admin" eq user.type)}){
			alert("抱歉，您没有访问此网站的权力");
			window.location.href='login.jsp';
		}
</script>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">CRMS</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">

					<li><a href="#">欢迎您：${user.name}</a></li>
					<li><a href="LogoutServlet?id=${user.userId }">logout</a></li>
				</ul>

			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul id="ul1" class="nav nav-tabs nav-stacked">
					<li class="active"><a href="index.jsp"> <i
							class="glyphicon glyphicon-th-large"></i> 首页
					</a></li>
					<li><a href="#a" class="nav-header collapsed"
						data-toggle="collapse"> 用户管理 <span
							class="pull-right glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul id="a" class="nav nav-list collapse secondmenu"
							style="height: 0px;" id="ul1">
							<li><a href="UserManageServlet">学生管理</a></li>
							<li><a href="RoomadminManageServlet">机房管理员</a></li>
							<li><a href="user_load.jsp">导入用户</a></li>
							<li><a href="UserLogServlet">查看日志</a></li>
						</ul></li>
					<li><a href="#b" class="nav-header collapsed"
						data-toggle="collapse"> 机房管理 <span
							class="pull-right glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul id="b" class="nav nav-list collapse secondmenu"
							style="height: 0px;">
							<li><a href="room_time_manage.jsp">机房使用时间段设置</a></li>
						</ul></li>
					<li><a href="#c" class="nav-header collapsed"
						data-toggle="collapse"> 机房统计 <span
							class="pull-right glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul id="c" class="nav nav-list collapse secondmenu"
							style="height: 0px;">
							<li><a href="SelectOnSituationServlet">查看上机情况</a></li>
							<li><a href="count_on_situation.jsp">统计上机情况</a></li>
						</ul></li>
					<li><a href="#d" class="nav-header collapsed"
						data-toggle="collapse"> 班级管理 <span
							class="pull-right glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul id="d" class="nav nav-list collapse secondmenu"
							style="height: 0px;">
							<li><a href="set_min_score.jsp">设置最低学时</a></li>
							<li><a href="ScoreServlet">查看班级学时</a></li>
						</ul></li>
				</ul>

			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<form id="file_form" action="UserLoadServlet?type=excToSqlServer"
					enctype="multipart/form-data" method="post">
					<input type="file" name="file" id="file_input"> <input
						type="submit" value="导入数据" class="file">
				</form>
			</div>

		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery || document.write('<script src="js/jquery.min.js"><\/script>')
	</script>
	<script
		src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="asset/vendor/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="asset/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

