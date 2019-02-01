<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G002生徒トップ画面</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
<h1>ファイル交換システム</h1>
</head>
<body>
	<div id="box">
		<p>ようこそ${sessionScope.student.name}さん</p>
		<p2><a href="Logout"> <img src="./img/li2.gif"></a></p2>
		<p>
		<a href="./studentTop.jsp">ホーム</a>
		</p>
		</div>
		<div id="box1">
		<form action="Login" method="post">
		<p>
			<button type="submit" name="check" value="studentinfo"
				style="WIDTH: 100%; HEIGHT: 100px">学生情報</button>
		</p>
		<p>

			<button type="submit" name="check" value="chooseclass"
				style="WIDTH: 100%; HEIGHT: 100px">授業選択</button>
		</p>
		<p>
			<button type="submit" name="check" value ="q"
				style="WIDTH: 100%; HEIGHT: 100px">問い合わせ</button>
		</p>
		</form>
	</div>
</body>
</html>