<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G004アップロード画面</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div id="box">
		<p>ようこそ${sessionScope.student.name}さん</p>
		<p2><a href="Logout"> <img src="./img/li2.gif"></a></p2>
		<p>
			<a href="studentTop.jsp">ホーム</a>
			-
			<a href="chooseClass.jsp">授業選択</a>
			-
			<a href="uploadFiles.jsp">ファイルアップロード</a>
		</p>
		</div>
		<p>授業：＜${sessionScope.nclasses.name}＞へのアップロード</p>
	<form action="FileManager" method="post" enctype="multipart/form-data">
	<div id="box1">
		<p>
			ファイル名<input type="file" name="filename" />
		</p>
		<p>
		${requestScope.fileAddError}
		</p>
	</div>
	<p hidden>
		識別追加<input type="hidden" name="check" value="uploadbyst" />
	</p>
	<button type="submit" name="upp" value="uploadbyst">アップロード</button>
	</p2>
	</form>
	</div>
</body>
</html>