<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<%@ page import="java.util.List" %>
<%@ page import= "java.util.ArrayList" %>
<%@ page import="Student.Student" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G018学生情報画面</title>
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
		</p>
		</div>
	<div id="box1">



		<p>名前: ${sessionScope.student.name}</p>
		<p>ユーザー名: ${sessionScope.student.userName}</p>
		<p>学籍番号:  ${sessionScope.student.studentID}</p>
		<p>所属学科: ${sessionScope.student.subject}</p>
		<p>パスワード: ${sessionScope.student.password}</p>
		<p>メールアドレス: ${sessionScope.student.mailAddress}</p>
		<p>クレジット情報: ${sessionScope.student.creditCard} </p>



	<p hidden>
		識別追加<input type="hidden" name="suinf" value="suinf" />
	</p>
	<form action="Login" method="post">
	<button type="submit" name="check" value="send">変更</button>
	</form>
	</div>
</body>
</html>