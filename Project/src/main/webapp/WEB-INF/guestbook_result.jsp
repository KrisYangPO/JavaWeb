<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>資料表結果</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 20px">
		<div class="pure-form">
			<h2> 使用者結果</h2>
			<fieldset>
				<legend>User result</legend>
				本次留言：${ message }<p />
				所有留言：${ guestbook }<p />
				
				<a href="/JavaWeb/guestbook" class = "pure-button pure-botton-primary">返回</a>
			</fieldset>
		</div>
	</body>
</html>