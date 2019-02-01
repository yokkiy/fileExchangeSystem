<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List"%> <%@ page import="files.Files"%> <%@
page import="classes.Classes"%> <% List
<Files>filelist=(List<Files>)session.getAttribute("fileList");
Classes nowclass=(Classes)session.getAttribute("classes"); %>


<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G005ダウンロード画面</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div id="box">
		<p>ようこそ${sessionScope.student.name}さん</p>
		<p2> <a href="Logout"> <img src="./img/li2.gif"></a></p2>
		<p>
			<a href="./studentTop.jsp">ホーム</a> - <a href="chooseClass.jsp">授業選択</a>
			- <a href="downloadFiles.jsp">ファイルダウンロード</a>
		</p>
	</div>
	<div id="box1">授業：${sessionScope.classes.name}のダウンロードリスト</div>

	<div id="box1" style="height: 400px; overflow-x: scroll;">
		<p><% String pathName="./uploadFiles/"+nowclass.getName()+"/";
			for(Files file:filelist){ out.println("<p>"+file.getDate()+":<input type=hidden>"+
		"<ahref="+pathName+file.getName()+">"+file.getName()+"</a>"+"</p>"); } %>

		</p>
		<p hidden>
			識別追加<input type="hidden" name="check" value="download" />
		</p>
		<button onclick="location.href='./uploadFiles'" type="submit"
			name="action" value="send">ダウンロード</button>
		</p2>
	</div>
</body>
</html>