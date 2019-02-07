<%@ page contentType="text/html; charset=utf-8" language="java"
	errorPage=""%>
<%@ page import="util.*, bean.*, java.util.*"%>
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
					<form id="frm" action="RoomEditDoServlet" method="post"
						onkeydown="if(event.keyCode==13) return false;">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>机房</th>
									<th>大小</th>
									<th>配置</th>
									<th>安装软件</th>
									<th>负责人</th>
									<th>操作</th>
								</tr>
								<tr>
									<td>${room.name }</td>
									<td>${room.width }x${room.height }</td>
									<td>${room.config }</td>
									<td>${room.software }</td>
									<td>${room.liabler }</td>
									<c:if test="${room.usableNum != 0 }">
										<td><a href="javascript:class_use(${room.roomId })">安排班级上机</a></td>
									</c:if>
									<c:if test="${is_using }">
										<td><a href="AllUnuseServlet?room_id=${room.roomId }">全部下机</a></td>
									</c:if>
								</tr>
							</thead>
						</table>
						<p>&nbsp;</p>

						<table class="table table-striped" id="layout" border="1"
							ondrop="drop(event)" ondragover="allowDrop(event)">
							<script type="text/javascript">
							//tw和th表格表格表格里每个单元格的宽度和高度
							var tw = 50;
							var th = 50;
						</script>
							<%
								int cont = 0;
								ComputerRoomBean room = (ComputerRoomBean) request.getAttribute("room");
								List<ComputerBean> list = (List<ComputerBean>) request.getAttribute("com_list");
								int x = -1, y = -1;
								if (list != null && list.size() > 0) {
									x = list.get(0).getLayoutX();
									y = list.get(0).getLayoutY();
								}
								for (int i = 0; i < room.getHeight(); i++) {
							%>
							<tr height="50">
								<%
									for (int j = 0; j < room.getWidth(); j++) {
								%>
								<td width="50">
									<%
										if (i == y && j == x) {
									%>
									<div align="center">
										<img id="com<%=cont%>"
											<%if (list.get(cont).getSatus().equals(ComputerBean.USABLE)) {%>
											src="images/computer_usable.png" <%}%>
											<%if (list.get(cont).getSatus().equals(ComputerBean.CUSING)) {%>
											src="images/computer_cusing.png" <%}%>
											<%if (list.get(cont).getSatus().equals(ComputerBean.SUSING)) {%>
											src="images/computer_susing.png" <%}%>
											<%if (list.get(cont).getSatus().equals(ComputerBean.REPAIR)) {%>
											src="images/computer_repair.jpg" <%}%>
											<%if (list.get(cont).getSatus().equals(ComputerBean.UNUSE)) {%>
											src="images/computer_unuse.jpg" <%}%>
											onclick="show_win(<%=cont%>)"
											onmouseout="close_win(<%=cont%>)" width="50" height="50" />
									</div>
									<ul id="menu<%=cont%>" onmousemove="show_win(<%=cont%>)"
										onmouseout="close_win(<%=cont%>)"
										style="position: absolute; left: 0px; top: 0px; z-index: 10; display: none; background: #fff;">
										<%
											if (list.get(cont).getSatus().equals(ComputerBean.USABLE)) {
										%>
										<li><a
											href="javascript:student_use(<%=list.get(cont).getComId()%>)">安排上机</a></li>
										<li><a
											href="ComRepairServlet?com_id=<%=list.get(cont).getComId()%>">维修</a></li>
										<%
											}
										%>
										<%
											if (list.get(cont).getSatus().equals(ComputerBean.UNUSE)
																|| list.get(cont).getSatus().equals(ComputerBean.CUSING)) {
										%>
										<li><a
											href="ComRepairServlet?com_id=<%=list.get(cont).getComId()%>">维修</a></li>
										<%
											}
										%>
										<%
											if (list.get(cont).getSatus().equals(ComputerBean.REPAIR)) {
										%>
										<li><a
											href="ComRepairServlet?com_id=<%=list.get(cont).getComId()%>">维修完成</a></li>
										<%
											}
										%>
										<%
											if (list.get(cont).getSatus().equals(ComputerBean.SUSING)) {
										%>
										<li><a
											href="StuDeplaneServlet?com_id=<%=list.get(cont).getComId()%>
									">下机</a></li>
										<li><a
											href="ComRepairServlet?com_id=<%=list.get(cont).getComId()%>">维修</a></li>
										<%
											}
										%>
									</ul> <%
 	cont++;
 				if (cont < list.size()) {
 					x = list.get(cont).getLayoutX();
 					y = list.get(cont).getLayoutY();
 				}
 			}
 %>
								</td>
								<%
									}
								%>
							</tr>
							<%
								}
							%>
						</table>
					</form>
				</div>
			</div>
		</div>

		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://cdn.bootcss.com/jquery/1.9.0/jquery.js"></script>
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
		function class_use(id){
			var name;
			name = prompt("请输入上机的班级", "");
			window.location.href="ClassUseServlet?class_name="+name+"&room_id="+id+"";
		}
		
		function student_use(id){
			var userNo;
			userNo = prompt("请输入上机的学生学号", "");
			window.location.href="StudentUseServlet?user_no="+userNo+"&com_id="+id+"";
		}
		
		function show_win(cont){
			var win = document.getElementById("menu" + cont);
			var top = getAbsoluteTop("com" + cont)-20;
			var left = getAbsoluteLeft("com" + cont)-220;
			win.style.top=top+"px";
			win.style.left=left+"px";
			win.style.display="block";
		}
		
		function close_win(cont){
			var win = document.getElementById("menu" + cont);
			win.style.display="none";
		}
		
		function getAbsoluteTop(objectId) {
			o = document.getElementById(objectId);
			oTop = o.offsetTop;
			while(o.offsetParent!=null)
			{  
				oParent = o.offsetParent;
				oTop += oParent.offsetTop;
				o = oParent;
			}
			return oTop;
		}

		function getAbsoluteLeft(objectId) {
			o = document.getElementById(objectId);
			oLeft =o.offsetLeft;
			while(o.offsetParent!=null) { 
				oParent = o.offsetParent;
				oLeft += oParent.offsetLeft;
				o = oParent;
			}
			return oLeft;
		}
	</script>
</body>
</html>
