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
<title>G014ファイル追加画面</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<% if(session.getAttribute("fileAddError")!=
	null&&(Boolean)session.getAttribute("fileAddError")==true){out.println("<p>ファイル名がかぶっています</p>"); } %>
	<div id="box">
		<p>ようこそ${sessionScope.student.name}管理者さん</p>
		<p2> <a href="Logout"> <img src="./img/li2.gif"></a></p2>
		<p>
			<a href="./managerTop.jsp">ホーム</a> - <a href="./filesManager.jsp">ファイル管理</a>
			- <a href="./addFiles.jsp">ファイル追加</a>
		</p>
	</div>
	<div id="box1">

		<form action="FileManager" method="post" enctype="multipart/form-data">
			<div id="box2">

				<p>
					授業選択<select name="classselect" size=1
					style="WIDTH:200px; HEIGHT:30px"> <% for(Classes
						list:classlist){ out.println("<option value="+list.getId()+">"+list.getName()+"</option>"); } %>
					</select>
				</p>
				<p>
					ファイルを追加してください<input type="file" name="file"
					style="WIDTH:200px; HEIGHT:30px"/>
				</p>
				<p hidden>
					識別追加<input type="hidden" name="check" value="addfile" />
				</p>
				<p>
					${requestScope.e}
				</p>

				<p>
					<button name="追加" style="WIDTH: 100%; HEIGHT: 100px">追加</button>
				</p>
			</div>
		</form>
	</div>
</body>
</html>