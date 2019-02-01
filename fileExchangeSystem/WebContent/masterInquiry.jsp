<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List" %> <%@ page import=
"java.util.ArrayList" %> <%@page import="Inquiry.Inquiry" %>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G???問い合わせ管理画面</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div id="box">
		<p>ようこそ${sessionScope.student.name}管理者さん</p>
		<p2>
		<a href="Logout"> <img src="./img/li2.gif"></a></p2>
		<p>
			<a href="./managerTop.jsp">ホーム</a> - <a>問い合わせ管理</a>
			<!-- href="./masterInquiry.jsp"としたら何故かエラー -->
		</p>
	</div>
	<div id="box1">
		<form action="Login" method="post">
			<select name="iii" size=10 style="WIDTH: 100%; HEIGHT: 250px">
				<%List<Inquiry> inquiryList=(List<Inquiry>)request.getAttribute("inquiryList");
				for(Inquiry i:inquiryList){ out.print("<option value="+i.getInquiryID()+">"+
				i.getInquiryID()+""+i.getStudent()+" "+i.getDate()+"</option>"); } %>
			</select>
			<button type="submit" name="check" value="ddd">削除</button>
			<button type="submit" name="check" value="sss">参照</button>
		</form>
	</div>
</body>
</html>