<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
</head>
	<body>
			<div class="pure-form">
			<h2> 使用者結果</h2>
			<fieldset>
				<legend>User result</legend>
				咖啡比例：${ makecoffee.ratio}<p />
				咖啡種類：${ makecoffee.kind }<p />
				牛奶毫升數：${ makecoffee.milk }<p />
				咖啡毫升數：${ makecoffee.coffee }<p />
				描述：${ makecoffee.desc }<p />
				
				<a href="/JavaWeb/makeCoffee" class = "pure-button pure-botton-primary">返回</a>
			</fieldset>
		</div>
	</body>
</html>