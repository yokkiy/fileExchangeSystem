<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>G020登録確認画面</title>
<link rel="stylesheet" href="./css/normalize.css">
<link rel="stylesheet" href="./css/main.css">
</head>
<body>
	<div id="box">
		<p>
			<a href="./studentTop.jsp">ホーム</a>
		</p>
	</div>
	<div id="box1">
		<p>
			ID<input type="text" name="user" />
		</p>
		<p>パスワード<input type="text" name="pass" /></p>
		<p>メールアドレス<input type="text" name="mail" /></p>
		<p>クレジット情報<input type="text" name="credit" /><p>

	<p hidden>
		識別追加<input type="hidden" name="idc" value="idc" />
	</p>
	<button type="submit" name="action" value="send">登録</button>
</div>
</body>
</html>