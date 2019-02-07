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
<title>用户管理</title>
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
	function add() {
		window.location.href = 'user_add.jsp';
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
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-9">
						<form name="frm" class="form-inline" action="UserManageServlet"
							method="post">
							<div class="form-group">
								<label class="form-label">班级：</label><input
									class="form-control" type="text" name="name"
									v-model="currentData.loanRate" maxlength="8"
								onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')" value='<c:out value="${name  }"></c:out>'>
							</div>
							<div class="form-group">
								<label class="form-label">人数：</label><input
									class="form-control" type="text" name="stuNum"
									v-model="currentData.loanRate" maxlength="7"
								onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')" value='<c:out value="${stuNum  }"></c:out>'>
							</div>
							<p>&nbsp;</p>
							<div class="form-group">
								<label class="form-label">最低上机学时：</label><input
									class="form-control" type="text" name="minPeriod"
									v-model="currentData.loanRate" maxlength="7"
								onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')" value='<c:out value="${minPeriod  }"></c:out>'>
							</div>
							<button type="submit" class="btn btn-info">查找</button>
							<input type="hidden" name="cur_page" value="">
							<!-- 增加隐藏变量，记录当前页码 -->
						</form>
					</div>
					<div class="btn-group col-sm-3">
						<button type="button" class="btn btn-info dropdown-toggle"
							data-toggle="dropdown" onclick='window.location="class_add.jsp"' aria-haspopup="true" aria-expanded="false">
							添加班级 
						</button>
					
					</div>
				</div>
				<div></div>
				<div class="table-responsive">
					<form onSubmit="checkForm()" action="ClassesDeleteServlet">
					<p>&nbsp;</p>
						<input type="submit" class="btn btn-primary"
							value="批量删除" /> 
						<table class="table table-striped">
							<thead>
								<tr>
									<th><input type="checkbox" onclick="cheAll()" id="che" name="chk_uid" >
										<label class="form-label">全选</label></th>
									<th>班级</th>
									<th>人数</th>
									<th>最低上机学时</th>
									<th>操作</th>
								</tr>

							</thead>
							<tbody>
								<c:forEach items="${list }" var="bean">
									<tr>
										<td><input type="checkbox" onclick="" name="ck" value='<c:out value="${bean.classId  }"></c:out>'></td>
										<td>${bean.name }</td>
										<td>${bean.stuNum }</td>
										<td>${bean.minPeriod }</td>
										<td>
											<a class="btn btn-primary"
											href='ClassEditServlet?id=${bean.classId  }'>修改信息</a> 
											<a class="btn btn-primary"
											href='StudentManageServlet?id=${bean.classId   }'>查看详情</a> 
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<script>
						function checkForm(){
						    var boxs =document.getElementsByName("ck");
						    for( i=0 ; i<boxs.length ; i++) {
						        if(boxs[i].checked==true){
						            if(confirm("确定批量删除这些数据吗？")){
						                return true;
						            }else{
						                return false;
						            }
						        }
						    }
						    alert("至少选中一条数据……");
						    return false;
						}
						
  						
							//全选
							function cheAll() {
								var cek = $("#che")[0].checked;
								var ck = $("input[name='ck']");
								for (var i = 0; i < ck.length; i++) {
									ck[i].checked = cek;
								}

							}
						</script>

						<div id="pageFooter" class="btn-group">
							<label>&nbsp;&nbsp;共&nbsp;${pager.totalPage }&nbsp;页&nbsp;${pager.totalRecord }&nbsp;条数据
								，导航至：${pager.curPage } </label>
							<c:forEach var="i" begin="1" end="${pager.totalPage }">
								<button type="button"
									class='btn <c:if test="${i == pager.curPage}">btn-primary</c:if> btn-default'
									onclick="turnPage(${i})">${i }</button>
							</c:forEach>

						</div>
					</form>

				</div>
			</div>
		</div>
	</div>


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

