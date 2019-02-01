<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List"%>
<%@ page import="classes.Classes"%>
<%
List<Classes>list=(List<Classes>)session.getAttribute("classList");
%>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G021チャットルーム追加画面</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div id="box">
		<p>ようこそ${sessionScope.student.name}管理者さん</p>
		<p2><a href="Logout"> <img src="./img/li2.gif"></a></p2>
		<p>
			<a href="./managerTop.jsp">ホーム</a>
			-
			<a href="./chatManager.jsp">チャット管理</a>
			-
			<a href="addChatRoom.jsp">チャットルーム追加</a>
		</p>
	</div>
	<div id="box1">
		<div id ="box2">
		<form action="Login" method="post">
			<p>
				授業名<input type="text" name="classname"  list="cl"
				style="WIDTH:200px; HEIGHT:30px"/>
				<datalist id="cl">
				<% for(Classes classes:list){
					out.println("<option value="+classes.getName()+">"+classes.getName()+"</option>");
					%>
				<% }%>

			    </datalist>
			</p>
			ルーム名<input type="text" name="chatroomname"
			style="WIDTH:200px; HEIGHT:30px"/>
			<p hidden>
				識別追加<input type="hidden" name="check" value="roomcreate" />
			</p>
			<p>
				<button type="submit" name="add">追加</button>
			</p>
		</form>
		</div>
	</div>
</body>
</html>