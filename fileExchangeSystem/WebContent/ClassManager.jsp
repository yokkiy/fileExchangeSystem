<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List"%> <%@ page import="classes.Classes"%>
<% List
<Classes>classlist=(List<Classes>)session.getAttribute("classList");
%>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G???授業管理画面</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div id="box">
		<p>ようこそ${sessionScope.student.name}管理者さん</p>
		<p2>
		<a href="Logout"> <img src="./img/li2.gif"></a></p2>
		<p>
			<a href="./managerTop.jsp">ホーム</a> - <a href="./ClassManager.jsp">授業管理</a>
		</p>
	</div>
	<form action="Login" method="post">
		<div id="box1" style="height: 400px; overflow-x: scroll;">
			<p>
				<% for(Classes classes:classlist){ out.println("<input type=checkbox name=classlist value="+
			classes.getName()+">"+classes.getName()+"</option><br>"); %> <%} %>
			</p>

		<p>
			新規追加授業名<input type=text name=newclassname>
		</p>
		<p>
			<button name="check" value="addClass"
				style="WIDTH: 100%; HEIGHT: 100px" type="submit">追加</button>
		</p>
		<p>
			<button name="check" value="deleteClass"
				style="WIDTH: 100%; HEIGHT: 100px" type="submit">削除</button>
		</p>
	</div>
	</form>

</body>
</html>