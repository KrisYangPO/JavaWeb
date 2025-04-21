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
				姓名：${ u.userName }<p />
				性別：${ u.gender }<p />
				年齡：${ u.age }<p />
				身高 (cm)：${ u.height }<p />
				體重 (kg)：${ u.weight }<p />
				BMI值：${ u.bmiValue }<p />
				BMI判斷：${ u.evaluateBMI }<p />
				
				<a href="/JavaWeb/user" class = "pure-button pure-botton-primary">返回</a>
			</fieldset>
		</div>
	</body>
</html>