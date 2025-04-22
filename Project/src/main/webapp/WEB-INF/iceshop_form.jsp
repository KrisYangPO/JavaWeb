<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>冰果店</title>
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
			<h2>冰果訂購單</h2>
			<form class = "pure-form pure-form-stacked" method = "POST" action="http://localhost:8080/JavaWeb/iceshop">
				<fieldset>
					<legend>選擇主餐</legend>
					<!-- 單選，Servlet 後台會使用 getParameter("mainDish") -->
					<input type ="radio" name ="mainDish" value="Ice" checked>挫冰 (50元) <p />
					<input type ="radio" name ="mainDish" value="Douhua" checked>豆花 (40元) <p />
				</fieldset>
				<fieldset>
					<!-- 多選，後台 Servlet 需要用 req.getParamterValues("topping") 會得到 String 字串陣列 -->
					<legend>選擇加料</legend>
					<input type ="checkbox" name ="topping" value="花生" checked>花生 (10元) <p />
					<input type ="checkbox" name ="topping" value="綠豆" checked>綠豆 (10元) <p />
					<input type ="checkbox" name ="topping" value="紅豆" checked>綠豆 (10元) <p />
					<input type ="checkbox" name ="topping" value="芋圓" checked>綠豆 (10元) <p />
					<input type ="checkbox" name ="topping" value="粉圓" checked>綠豆 (10元) <p />
					<input type ="checkbox" name ="topping" value="煉乳" checked>綠豆 (10元) <p />
				</fieldset>
				<button type = "submit" class ="pure-button pure-button-primary">結帳 </button>
			</form>
		</div>
	</body>
</html>