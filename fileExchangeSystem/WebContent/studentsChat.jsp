<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List"%> <%@ page import="chat.Chat"%> <%List
<Chat>chatlist=(List<Chat>)session.getAttribute("chatList");
%>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G006</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div id="box">
		<p>
			<a href="studentsChatroom.jsp">ホーム</a>
		</p>
	</div>
	<div id="box1">
		<p>${sessionScope.nowRoom.roomName} のチャット</p>
		<div id="wrapper"
			style="WIDTH: 100%; HEIGHT: 500px; overflow-x: scroll;">
			<% for(Chat chat:chatlist){ out.println("<p>"+chat.getCname()+":("+chat.getChatdate()+
					")<br>"+chat.getComment()+"</p>"); %> <% } %>


			<p hidden>
				識別追加<input type="hidden" name="check" value="chat" />
			</p>


			<!-- チャット投稿 -->
			<form action="ChatManager" method="post">
				<p>
					<input type="text" name="chatcomment" maxlength=140 />
					<button type="submit" name="check" value="studentchatpost">投稿</button>
				</p>
			</form>
		</div>
	</div>
</body>
</html>