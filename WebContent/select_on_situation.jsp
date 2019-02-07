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
	function turnPage(i) {
		document.frm.cur_page.value = i;   // 跳转页面时，设置i为要跳转的页面，然后提交表单
		document.frm.submit();
	}
</script>
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
				<h1 class="page-header">查看上机情况</h1>
				<form method="post" action="SelectOnSituationServlet"
					class="form-inline" name = "frm">
					<div class="form-group">
						<label class="form-label">起始时间：</label><input class="form-control"
							type="text" name="start" value='<c:out value="${start}"></c:out>'>
					</div>
					<div class="form-group">
						<label class="form-label">结束时间：</label><input class="form-control"
							type="text" name="end" value='<c:out value="${end}"></c:out>'>
					</div>
					<p>&nbsp;</p>
					<div class="form-group">
						<label class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机房：</label><input
							class="form-control" type="text" name="room"
							value='<c:out value="${room}"></c:out>'>
					</div>
					<div class="form-group">
						<label class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号：</label><input
							class="form-control" type="text" name="user_id"
							value='<c:out value="${userId}"></c:out>'>
					</div>

					<button type="submit" class="btn btn-info">查找</button>
					<button type="button" class="btn btn-info"
						onclick='window.location="DownExcel?room=${room }&user_id=${userId }&strat=${start }&end=${end}"'>导出</button>

					<input type="hidden" name="cur_page" value="">
					<!-- 增加隐藏变量，记录当前页码 -->
				</form>


				<table class="table table-hover">

					<thead>
						<tr>
							<th>机房号</th>
							<th>机位号</th>
							<th>学号</th>
							<th>上机时间</th>
							<th>下机时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="user">
							<tr>
								<td>${user.roomId }</td>
								<td>${user.comId }</td>
								<td>${user.userId}</td>
								<td>${user.startTime}</td>
								<td>${user.endTime}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				<div id="pageFooter" class="btn-group">
					<label>共${pager.totalPage }页${pager.totalRecord }条数据
						，导航至：${pager.curPage } </label>
					<c:forEach var="i" begin="1" end="${pager.totalPage }">
						<button type="button"
							class='btn <c:if test="${i == pager.curPage}">btn-primary</c:if> btn-default'
							onclick="turnPage(${i})">${i }</button>
					</c:forEach>

				</div>
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

