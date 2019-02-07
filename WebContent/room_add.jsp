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
				<form id="frm" action="RoomAddServlet" method="post" onkeydown="if(event.keyCode==13) return false;" >
					<center>
					<table>
						<thead>
							<tr>
								<td><label class="form-label"><font style="color:red;" >*</font>机房名称：</label>
					<input class="form-control" name="name" type="text" value="${room.name }" />
					</td><td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" class="btn btn-primary" onclick="submitt()" value="保存" />
					</td></tr>
					<tr><td>
					<label class="form-label">配置：</label><input class="form-control" name="config" type="text" value="${room.config }" />
					</td></tr>
					<tr><td><label class="form-label">安装软件：</label><input class="form-control" name="software" type="text" value="${room.software }" />
					</td></tr>
					<tr><td><label class="form-label">负责人：</label><input class="form-control" name="liabler" type="text" value="${room.liabler }" />
					</td></tr>
					<tr><td>
					<label class="form-label">机房大小：</label>
					<input id="width" type="number" name="width" min="1" max="200" value="${room.width }" />*
					<input id="height" type="number" name="height" min="1" max="200" value="${room.height }" />
					</td></tr>
					</thead></table>
					</center>
					<table id="edit_com">
					<tr height="50">
						<td width="50" ><img id="com" src="images/computer_usable.png" draggable="true" ondragstart="drag(event)" width="40" height="40"  /></td>
						<td id="delet_block" width="50"  ondrop="delet_com(event)" ondragover="allowDrop(event)"  ><font color="#99999" style="font-size:12px;" >拖到此处删除电脑</font></td>
					</tr>
					</table>
					<table id="layout" border="1" ondrop="drop(event)" ondragover="allowDrop(event)" >
						<script type="text/javascript">
							//tw和th表格表格表格里每个单元格的宽度和高度
							var tw = 50;
							var th = 50;
						</script>
						<tr height="50" >
							<td width="50" ></td>
						</tr>
					</table>
					<input name="com_num" type="hidden" value="0" />
					
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
	<script type="text/javascript" src="js/room_edit.js" ></script>
	<script type="text/javascript" >
		changeSize();
	</script>
	<%
		Integer comNum = (Integer)request.getAttribute("com_num");
		if(comNum != null && comNum > 0){
			String []layoutX = (String [])request.getAttribute("layoutX");
			String []layoutY = (String [])request.getAttribute("layoutY");
			for(int i = 0; i < comNum; i++){
				System.out.println("("+layoutX[i]+","+layoutY[i]+")");
		%>
			<script type="text/javascript" >
				var tab = document.getElementById("layout");
				var img = document.createElement("img");
				img.id="com" + count;
				count++;
				img.src="images/computer_usable.png";
				img.draggable="true";
				img.width="40";
				img.height="40";
				tab.rows[<%=layoutY[i]%>].cells[<%=layoutX[i]%>].appendChild(img);
			</script>
		<%
			}
		}
	%>
</body>
</html>


