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
			<form class = "pure-form pure-form-stacked" method = "POST" action="http://localhost:8080/JavaWeb/user">
				<fieldset>
					<legend>User Form</legend>
					<label>姓名：</label>
					<input name = "userName" type = "text" required />
					
					<label>性別：</label>
					<label class="pure-radio">
						<input name = "gender" type = "radio" value = "male" checked>&nbsp;男
					</label>
					<label class="pure-radio">
						<input name = "gender" type = "radio" value = "female" checked>&nbsp;女
					</label>
					
					<label>年齡：</label>
					<input name = "age" type = "number" min="1" max="150" required />
					
					<label>身高(cm)：</label>
					<input name = "height" type = "number" min="1" max="300" step="0.1" required />
					
					<label>體重(kg)：</label>
					<input name = "weight" type = "number" min="0.1" max="1000" step="0.1" required />
					
					<button type ="submit" class="pure-form pure-form-primary">送出</button>
					
				</fieldset>
			</form>
		</div>
	</body>
</html>