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
		else if(${!("comrmer" eq user.type)}){
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
					<li><a href="LogoutServlet?id=${user.userId}">logout</a></li>
				</ul>

			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul id="ul2" class="nav nav-tabs nav-stacked">
					<li class="active"><a href="index.jsp"> <i
							class="glyphicon glyphicon-th-large"></i> 首页
					</a></li>
					
					<li><a href="RoomListServlet">机房管理</a></li>

				</ul>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="table-responsive">
					<form id="frm" action="RoomDeleteServlet" method="post">
						<table class="table table-striped">
							<tr>
								<th><input type="checkbox" onclick="cheAll()" id="che"></th>
								<th>名称</th>
								<th>总机器数</th>
								<th>可用机器数</th>
								<th>是否可用</th>
								<th>负责人</th>
								<th>详细信息</th>
								<th>编辑</th>
							</tr>

							<c:forEach items="${room_list }" var="room" varStatus="id">
								<tr>
									<td><input name="choose" type="checkbox"
										value="${room.roomId }" /></td>
									<td>${room.name }</td>
									<td>${room.comNum }</td>
									<td>${room.usableNum }</td>
									<td>${room.unuseTime }</td>
									<td>${room.liabler }</td>
									<td><a class="btn btn-info dropdown-toggle"
										href="RoomShowServlet?room_id=${room.roomId }&user_type=${user.type}">点击查看</a></td>
									<td><a class="btn btn-primary"
										href="RoomEditServlet?room_id=${room.roomId }">点击编辑</a></td>
								</tr>
							</c:forEach>
							<input id="choose_num" type="hidden" value="0" />

						</table>
					</form>
					<a class="btn btn-danger" href="javascript:delet()">删除选中机房</a> <a
						class="btn btn-info dropdown-toggle" href="room_add.jsp">添加机房</a>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	//全选
	function cheAll() {
		var cek = $("#che")[0].checked;
		var ck = $("input[name='choose']");
		for (var i = 0; i < ck.length; i++) {
			ck[i].checked = cek;
		}

	}


	</script>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document.write('<script src="js/jquery.min.js"><\/script>')
	</script>
	<script
		src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="asset/vendor/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="asset/ie10-viewport-bug-workaround.js"></script>
	<script type="text/javascript">
		function delet(){
			if(confirm("您确认要删除吗？")){
				var frm = document.getElementById("frm");
				frm.submit();
			}
		}
	</script>
</body>
</html>


