<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G012ID追加</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div id="box">
		<p>ようこそ${sessionScope.student.name}管理者さん</p>
		<p2> <a href="Logout"><img src="./img/li2.gif"></a></p2>
		<p>
			<a href="./managerTop.jsp">ホーム</a>
			-
			<a href="./IDManager.jsp">ID管理</a>
			-
			<a href="addID.jsp">ID追加</a>
		</p>
	</div>
	<div id="box1">
	<div id ="box2">
		<form action="Login" method="post">

			<p>
				<input type="radio" name="a" value="Student" checked="checked">学生
				<input type="radio" name="a" value="Manager">管理者
			</p>
			<p>
				名前<input type="text" name="name" />
			</p>
			<p>
				ID　　　　<input type="text" name="id" />
			</p>
			<p>
				学年<input type="number" name="grade" />
			</p>
			<p>
				学科<input type="text" name="subject" />
			</p>
			<p>
				password　<input type="text" name="password" />
			</p>
			<p>
				mailaddress<input type="text" name="mailaddress" />
			</p>
			<p hidden>
				識別追加<input type="hidden" name="c" value="IdAdd" />
			</p>

			<p>
			${requestScope.m}

				<button  type="submit" name="check" value="b" style="WIDTH: 100%; HEIGHT: 80px">登録</button>
			</p>
		</form>
		</div>
	</div>
</body>
</html>