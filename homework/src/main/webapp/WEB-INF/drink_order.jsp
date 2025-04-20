<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<div>
			<fieldset>
				<legend>Drink Order</legend>
				飲料：${ drinkorder.item }<br />
				容量：${ drinkorder.size }<br />
				含糖：${ drinkorder.AddIce }<br />
				${ drinkorder.info }<br />
			</fieldset>
		</div>
	</body>
</html>