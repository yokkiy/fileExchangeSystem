<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List"%>
<%@ page import="classes.Classes"%>
<%@ page import="files.Files"%>
<%
List<Classes>classlist=(List<Classes>)session.getAttribute("classList");
List<Files>filelist=(List<Files>)session.getAttribute("fileList");
%>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G013ファイル削除画面</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div id="box">
		<p>ようこそ${sessionScope.student.name}管理者さん</p>
		<p2><a href="Logout"> <img src="./img/li2.gif"></a></p2>
		<p>
			<a href="./managerTop.jsp">ホーム</a>
			-
			<a href="./filesManager.jsp">ファイル管理</a>
			-
			<a href="./deleteFiles.jsp">ファイル削除</a>
		</p>
	</div>
	<div id="box1" style="height: 400px; overflow-x: scroll;">

			<form action="FileManager" method="post">
					<p>
						授業検索<select name="classselect" size=1>
							<option value=""></option>
							<%
								for (Classes list : classlist) {
									out.println("<option value=" + list.getId() + ">" + list.getName() + "</option>");
								}
							%>
						</select> キーワード検索<input type="text" name="keyword" />
						<button name="check" value="fileserch" type="submit">検索</button>
					</p>
				</form>
				${sessionScope.test}
				${sessionScope.error}

<form action="Login" method="post">

	<%
	for(Files files:filelist){
		String pathName="./uploadFiles/";
		String classname="";%>
		<%
		for(Classes classes:classlist){
			%>
			<%
			if(files.getClassid().equals(classes.getId())){
				classname=classes.getName();
				pathName+=classes.getName();
			}
			%>
			<%
		}%>
		<%
		pathName+="/"+files.getName();
		out.println("<input type=checkbox");
		out.println("name=alfiles "+"value="+files.getId()+"><a href="+pathName+">"+files.getName()+"</a>"
		+"  :"+files.getDate()+"/"+classname+"/"+files.getStudentid()+"<br>");
	%>
	<%} %>

		<p>
			<button name="check" value="deletefile" style="WIDTH: 100%; HEIGHT: 100px" type="submit">削除</button>
		</p>
	</div>
	</form>


</body>
</html>