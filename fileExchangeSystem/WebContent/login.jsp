<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
<meta charset="utf-8">
<title>ログイン画面</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<% if(session.getAttribute("login")!=null &&
	!(Boolean)session.getAttribute("login")) { out.println("<p>ユーザ名またはパスワードが違います</p>"); } %>

	<div id="box">
		<h1>	<img src="./img/Logo.png" width="100%"></h1>
	<!-- <form action="Login" method="post"> -->

	</div>
	<form action="Login" method="post">
	<div id="box1">
		<p>
			ID <input type="text" name="mail" placeholder="ここにIDを入力" />
		</p>
		<p>
			Pass<input type="password" name="pass" placeholder="ここにパスワードを入力" />
		</p>
	<button type="submit" name="check" value="login">ログイン</button>
	<p2><a href="./mailChecker.jsp"><img src="./img/NewStudent.png"height="20px"></a><p2>
	</div>
	</form>
</body>
</html>