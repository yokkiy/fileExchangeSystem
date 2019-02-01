<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List"%> <%@ page import="chat.Chat"%> <% List
<Chat> chatList = (List<Chat>)
session.getAttribute("chatList"); %>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>チャットログ</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
<script type="text/javascript">
<!--
	function delok() {
		if (window.confirm('コメントを削除してよろしいですか？')) {
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
		<p>
			<a onclick="location.href='./managerTop.jsp'">ホーム</a>
		</p>
		<p2> <img src="./img/li2.gif"></p2>
	</div>

<div id="box1">
		<p>ルーム：${sessionScope.nowRoom}のチャット</p>

	<% if (session.getAttribute("addchatError") != null && (Boolean)
	session.getAttribute("addchatError").equals(1)) { out.println("<p>投稿できませんでした</p>"); } %>



		<form action="Login" method="post" onsubmit="return delok()">
			<button type="submit" name="check" value="alldelete"
				style="WIDTH: 100px; HEIGHT: 20px">一括削除</button>

			<button type="submit" name="check" value="delete"
				style="WIDTH: 100px; HEIGHT: 20px">削除</button>

			<!-- チャット履歴表示 -->

			<div style="height: 400px; overflow-x: scroll;">
				<% for (Chat chat : chatList) { %> <% out.println("<inputtype=checkbox ");out.println("name=" +
			"chats" + " value=" + chat.getChatid() + ">" +chat.getCname() + " : " + chat.getComment() + "<br>"); %> <% }
				%>
			</div>
		</form>


		<p hidden>
			識別追加<input type="hidden" name="check" value="chat" />
		</p>
		<!-- チャット投稿 -->
		<form action="Login" method="post">
			<p>
				<input type="text" name="chatcomment" maxlength=140 />
				<button type="submit" name="check" value="chatpost">投稿</button>
			</p>
		</form>

	</div>
</body>
</html>