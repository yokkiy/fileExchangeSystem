<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G009ファイル管理画面</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
		<div id="box">
		<p>ようこそ${sessionScope.student.name}管理者さん</p>
		<p2><a href="Logout"> <img src="./img/li2.gif"></a></p2>
		<p>
			<a href="managerTop.jsp">ホーム</a>
			-
			<a href="filesManager.jsp">ファイル管理</a>
		</p>
		</div>
		<div id="box1">
		<form action="Login" method="post">
		<p>
		<button  type="submit" name="check" value="addFiles"
		style="WIDTH: 100%; HEIGHT: 100px">ファイル追加</button>
		</p><p>
		<button type="submit" name="check" value="deleteFiles"
		style="WIDTH: 100%; HEIGHT: 100px">ファイル削除</button>
		</p>
		</form>
	</div>
</body>
</html>