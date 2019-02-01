<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G019学生情報変更画面</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div id="box">
		<p>ようこそ${sessionScope.student.name}さん</p>
		<p2><a href="Logout"> <img src="./img/li2.gif"></a></p2>
		<p>
			<a href="./studentTop.jsp">ホーム</a>
			-
			<a href="./studentsInfo.jsp">学生情報</a>
			-
			<a href="./studentsInfoChange.jsp">学生情報変更</a>
		</p>
		</div>
	<div id="box1">
	<form action="Login" method="post">
	    <p>${requestScope.message1}</p>
	    <p>名前: ${sessionScope.student.name}</p>
		<p>ユーザー名: <input type="text" name="user" /></p>
		<p>学籍番号: ${sessionScope.student.studentID}</p>
		<p>所属学科: ${sessionScope.student.subject}</p>
		<p>パスワード: ${sessionScope.student.password}</p>
		<p>${requestScope.message4}
		   ${requestScope.message3}</p>
		<p>新しいパスワード:<input type="text" name="pass" /></p>
		<p>確認パスワード:<input type="text" name="newpass" /></p>
		<p>メールアドレス: ${sessionScope.student.mailAddress}</p>
		<p>${requestScope.message2}</p>
		<p>クレジット情報: <input type="text" name="credit" /></p>

	<p hidden>
		識別追加<input type="hidden" name="suinｆchange" value="suinｆchange" />
	</p>
	<button type="submit" name="check" value="send2">確定</button>
</form>
	</div>

</body>
</html>