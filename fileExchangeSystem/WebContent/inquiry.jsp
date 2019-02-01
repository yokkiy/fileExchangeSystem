<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G016問い合わせ画面</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div id="box">

		<p>
			<a href="./studentTop.jsp">ホーム</a>
		</p>
	</div>
	<div id="box1">
	<form action="Login" method="post">
	${requestScope.m}
		<p>件名<input type="text" name="text" /></p>
		<p hidden>
			識別追加<input type="hidden" name="inq" value="inq" />
		</p>
		<p>内容</p>
		<input type="text" name="question" style="WIDTH: 100%; HEIGHT: 250px">

		<button type="submit" name="check" value="send1">送信</button>
		</form>
	</div>
</body>
</html>