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
<title>G003授業選択画面</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div id="box">
		<p>ようこそ${sessionScope.student.name}さん</p>
		<p2> <a href="Logout"> <img src="./img/li2.gif"></a></p2>
		<p>
			<a href="studentTop.jsp">ホーム</a> - <a href="chooseClass.jsp">授業選択</a>
		</p>
	</div>
	<div id="box1">
		<form action="Login" method="post">
				<p>授業選択</p>
				<select name="classes" size=10 style="WIDTH: 100%; HEIGHT: 250px">
					<%
					boolean sel=true;
						for (Classes classes: classlist) {
							if(sel){
								out.println("<option value=" + classes.getId() + " selected>" + classes.getName() + "</option>");
							}else{
							out.println("<option value=" + classes.getId() + ">" + classes.getName() + "</option>");
							}
					%>
					<%
					sel=false;
						}
					%>
				</select>

				<p>
					<button type="submit" name="check" value="uploadFiles"
						style="WIDTH: 100%; HEIGHT: 100px">アップロード</button>
				</p>
				<p>
					<button type="submit" name="check" value="downloadFiles"
						style="WIDTH: 100%; HEIGHT: 100px">ダウンロード</button>
				</p>
				<p>
					<button type="submit" name="check" value="studentChatroom"
						style="WIDTH: 100%; HEIGHT: 100px">チャット</button>
				</p>
		</form>
	</div>
</body>
</html>