<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>User Form</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<meta charset="UTF-8">
		<style>
			.for_contianer{
				max-width: 500px;azimuth;
				margin: 50px auto;
				padding: 20px; 
			}
			h2{
				text-align: center; 
			}
		</style>
	</head>
	
	<body style="padding: 20px">
		<div class="form-container">
			<h2>使用者資料表單</h2>
			<form class = "pure-form pure-form-stacked" method = "POST" action="http://localhost:8080/JavaWeb/guestbook">
				<fieldset>
					<legend>User Form</legend>
					<label>留言：</label>
					<textarea name="message" rows="5" cols="33"></textarea>
					<!-- <input name = "message" type = "text" required /> -->
					
					<button type ="submit" class="pure-form pure-form-primary">送出</button>
					
				</fieldset>
			</form>
		</div>
	</body>
</html>