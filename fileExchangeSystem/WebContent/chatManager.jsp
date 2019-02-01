<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List"%>
<%@ page import="chatRoom.ChatRoom"%>
<%
	List<ChatRoom> roomList = (List<ChatRoom>) session.getAttribute("roomList");
%>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G010チャット管理画面</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">

<script type="text/javascript">
<!--
	function delok() {
		if (window.confirm('ルーム内のコメントが全削除されますがルームを削除してよろしいですか？')) {
			return true;
		} else {
			window.alert('キャンセルしました')
			return false;

		}
	}

	-->
</script>

</head>
<body>
	<div id="box">
		<p>ようこそ${sessionScope.student.name}管理者さん</p>
		<p2><a href="Logout"> <img src="./img/li2.gif"></a></p2>
		<p>
			<a href="./managerTop.jsp">ホーム</a>
			-
			<a href="./chatManager.jsp">チャット管理</a>
		</p>
		</div>
		<div id ="box1">
		<form action="Login" method="post">
		<p2>
		<p hidden>
			識別追加<input type="hidden" name="check" value="chatroomcreator" />
		</p>
		<button type="submit" name="action" value="send">チャットルーム作成</button>
		</p2>
		</form>

	<form action="Login" method="post">
			<p>
				<select name="allroom" size=10 style="WIDTH: 100%; HEIGHT: 250px">
					<%
						for (ChatRoom room : roomList) {
							out.println("<option value=" + room.getRoomId() + ">" + room.getRoomName() + "</option>");
					%>
					<%
						}
					%>
				</select>
			</p>
			<button type="submit" name="check" value="chat">チャット</button>
			<button onclick="return delok()" type="submit" name="check" value="deletechatroom">ルーム削除</button>
		</div>
	</form>
	</div>
</body>
</html>