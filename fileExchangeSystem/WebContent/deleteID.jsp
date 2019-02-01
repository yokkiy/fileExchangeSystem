<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List" %> <%@ page import=
"java.util.ArrayList" %> <%@ page import="Student.Student" %>

<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G013ID削除</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div id="box">
		<p>ようこそ${sessionScope.student.name}管理者さん</p>
		<p2>
		<a href="Logout"><img src="./img/li2.gif"></a></p2>
		<p>
			<a href="./managerTop.jsp">ホーム</a> - <a href="./IDManager.jsp">ID管理</a>
			- <a href="./deleteID.jsp">ID削除</a>
		</p>
	</div>
	<div id="box1">
		<form action="Login" method="post">
			<p>
				キーワード検索<input type="text" name="key" placeholder="キーワード入力" />
				<button name="check" value="keyword" type="submit">検索</button>
			</p>


			<div style="height: 400px; overflow-x: scroll;">

				<p>管理者</p>

				<% List
				<Student> masterList=(List<Student>)request.getAttribute("masterList");


				for(Student m:masterList){ out.println("<input type="+"checkbox"+" name="+" d"+
				" value="+m.getStudentID()+">"+m.getStudentID()+"<br>"); } %>

				<p>学生</p>
				<% List<Student> studentList=(List<Student>)request.getAttribute("studentList");
				for(Student s:studentList){ out.println("<input type="+"checkbox"+" name="+" r"+
				" value="+s.getStudentID()+">"+s.getStudentID()+"<br>"); } %>
			</div>





			<p>
				<button name="check" value="deleteID"
					style="WIDTH: 100%; HEIGHT: 100px" type="submit">削除</button>
			</p>

		</form>

		<p hidden>
			識別追加<input type="hidden" name="c" value="fileSearch" />
		</p>

	</div>




</body>
</html>