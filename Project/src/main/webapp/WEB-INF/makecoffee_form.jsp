<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coffee Maker</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
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
<body>
		<div class="form-container">
			<h2>使用者資料表單</h2>
			<form class = "pure-form pure-form-stacked" method = "POST" action="http://localhost:8080/JavaWeb/makeCoffee">
				<fieldset>
					<legend>咖啡配方</legend>
					
					<label>輸入牛奶毫升數：(ml)：</label>
					<input name = "milk" type = "number" min="1" max="1000" step="0.1" required />
					
					<label>輸入咖啡毫升數(ml)：</label>
					<input name = "coffee" type = "number" min="1" max="1000" step="0.1" required />
					
					<button type ="submit" class="pure-form pure-form-primary">送出</button>
					
				</fieldset>
			</form>
		</div>
</body>
</html>