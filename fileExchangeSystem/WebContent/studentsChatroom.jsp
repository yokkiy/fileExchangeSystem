<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List"%>
<%@ page import="chatRoom.ChatRoom"%>
<%List<ChatRoom>roomlist=(List<ChatRoom>)session.getAttribute("roomList"); %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>チャットルーム選択</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>

	<div id="box">
		<p>
			<a href="studentTop.jsp">ホーム</a>
		</p>
	</div>

	<form action="Login" method="post">
		<div id="box1">
		<p>
		${sessionScope.nowclass}のチャットルームリスト
		</p>
			<p>
				<select name="allroom" size=10 style="WIDTH: 100%; HEIGHT: 250px">
					<%
						for (ChatRoom room : roomlist) {
							out.println("<option value=" + room.getRoomId() + ">" + room.getRoomName()+ "</option>");
					%>
					<%
						}
					%>
				</select>
			</p>
			<button type="submit" name="check" value="studentChat"
			style="WIDTH: 100%; HEIGHT: 100px">チャットへ</button>
		</div>
	</form>




</body>
</html>