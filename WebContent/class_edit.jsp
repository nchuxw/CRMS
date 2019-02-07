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
		function ret() {
			history.back(-1);
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
			
				<!-- 系统管理员 -->
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
			<div class="container">
			<div class="col-sm-9 col-sm-offset-3 col-md-3 col-md-offset-2 main">
				<form class="form" method="post" action="ClassEditDoServlet" onsubmit="return Check();" >
					<div class="form-group">
						<label class="form-label">班级：</label> <input
							placeholder="请输入6位数字" class="form-control" v-model="currentData.loanRate" maxlength="7"
								 id = "class" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'') " type="text" name="name" value="${bean.name }" />
					</div>
					<script type="text/javascript">
					function formcheck(){
						var x=document.getElementById("class").value;
					    // 如果输入的值 x 不是数字或者小于 1 或者大于 10，则提示错误 Not a Number or less than one or greater than 10
					    if (x.length==0){
					        alert("班级号不能为空！！");
					        return false;
					    }
					    else if (x.length!=6) {
					        alert("班级号长度应该为6位");
					        return false;
					    }
							}
					function test(){
						var x=document.getElementById("class").value;
					    // 如果输入的值 x 不是数字或者小于 1 或者大于 10，则提示错误 Not a Number or less than one or greater than 10
					    if (x.length==0){
					        alert("班级号不能为空！！");
					        return false;
					    }
					    else if (x.length!=6) {
					        alert("班级号长度应该为6位");
					        return false;
					    }
					} 
				
					</script>
					
					<div class="form-group">
						<label class="form-label">最低上机学时：</label> <input class="form-control"
							type="text" v-model="currentData.loanRate" maxlength="7"
								onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')" name="minPeriod" value="${bean.minPeriod }" />
					</div>
					
					<div class="form-group">
					<input type="hidden" name="id" value="${bean.classId }" />
						<button type="submit" class="btn btn-primary">保存</button>
						<button type="button" class="btn btn-default" onclick="ret()">
							返回</button>

					</div>

				</form>
			</div>
</div>
		</div>
	</div>

	
	<script type="text/javascript">
	function ret() {
		history.back(-1);
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
</body>
</html>

