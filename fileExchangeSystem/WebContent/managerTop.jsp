<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G007管理者トップ画面</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div id="box">
		<p>ようこそ${sessionScope.student.name}管理者さん</p>
		<p2><a href="Logout"> <img src="./img/li2.gif"></a></p2>
		<p>
		<a href="./managerTop.jsp">ホーム</a>
		</p>
		</div>
		<div id ="box1">
		<p>
		<button onclick="location.href='./IDManager.jsp'" type="submit" name="ID管理"
		style="WIDTH: 100%; HEIGHT: 100px">ID管理</button>
		</p><p>
		<button onclick="location.href='./filesManager.jsp'"type="submit" name="ファイル管理"
		style="WIDTH: 100%; HEIGHT: 100px">ファイル管理</button>
		</p>
		<form action="Login" method="post">
		<p>
		<button  type="submit" name="check" value="roomList"
		style="WIDTH: 100%; HEIGHT: 100px">チャット管理</button>
		</p>
		<p>
		<button  type="submit" name="check" value="classManager"
		style="WIDTH: 100%; HEIGHT: 100px">授業管理</button>
		</p>
		<p>
		<button  type="submit" name="check" value="inquiry"
		style="WIDTH: 100%; HEIGHT: 100px">問い合わせ管理</button>
		</p>
		</form>
	</div>
</body>
</html>