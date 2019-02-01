<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List" %>
<%@ page import= "java.util.ArrayList" %>
<%@page import="Inquiry.Inquiry" %>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>問い合わせ内容</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">

</head>
<body>
	<div id="box">

		<p>
			<a href="./managerTop.jsp">ホーム</a>
		</p>
	</div>
	<div id="box1">
	<form action="Login" method="post">
	<div style="height:400px; overflow-x:scroll;">

	<%List<Inquiry> contentsList=(List<Inquiry>)request.getAttribute("contentsList") ;
	for(Inquiry i:contentsList){
		out.println("<p>件名"+"　　　　　　　　　　"+"問い合わせ者学籍番号"+i.getStudent()+"</p>");
		out.println("<p>"+i.getSubjectOfQuestion()+"</p>");
		out.println("<p>　　　</p>");
		out.println("<p>内容</p>");
		out.println("<p>"+i.getQuestionContent()+"</p>");
	}

	%>

	</div>

		</form>

	</div>
</body>
</html>