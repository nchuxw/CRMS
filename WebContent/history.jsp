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
		else if(${!("student" eq user.type)}){
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

				<!-- 学生 -->
				<ul id="ul3" class="nav nav-tabs nav-stacked">
					<li class="active"><a href="index.jsp"> <i
							class="glyphicon glyphicon-th-large"></i> 首页
					</a></li>
					<li><a href="#systemSetting" class="nav-header collapsed"
						data-toggle="collapse"> 查看上机 <span
							class="pull-right glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul id="systemSetting" class="nav nav-list collapse secondmenu"
							style="height: 0px;" id="ul3">
							<li><a href="NowStatusServlet">当前状态</a></li>
							<li><a href="history.jsp">历史记录</a></li>
							<li><a href="GradeServlet?userId=${user.userId }">查询成绩</a></li>
						</ul></li>
					<li><a href="RoomListServlet?user_type=${user.type }">查看机位情况</a></li>
				</ul>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-2 main">
			<form method="post" action="HistoryServlet">
			<table>
				<tr><td><div class="form-group">
						<label class="form-label">时间段：</label> <select class="form-control" name="times">
							<option value="">请选择</option>
							<option value="1" >最近一天</option>
							<option value="3" >最近三天</option>
							<option value="7" >最近一周</option>
							<option value="30" >最近一个月</option>
						</select></div></td><td>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" class="btn btn-primary"
							value="确定" /> </td></tr>
					
					</table>
				<div class="table-responsive">
					<table class="table table-striped">
							<thead>
								<tr>
									<th>机房</th>
									<th>机位</th>
									<th>登录时间</th>
									<th>退出时间</th>
								</tr>
								<c:forEach items="${list }" var="bean">
								<tr>
									<td>${bean.roomId }</td>
									<td>${bean.comId }</td>
									<td>${bean.startTime }</td>
									<td>${bean.endTime }</td>
									<td>
									</td>
								</c:forEach>
							</thead>
						</table>
						</div>
					</form>
				</div>

			</div>
		</div>


		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
		<script>
			window.jQuery
					|| document
							.write('<script src="js/jquery.min.js"><\/script>')
		</script>
		<script
			src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
		<script src="asset/vendor/holder.min.js"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="asset/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

