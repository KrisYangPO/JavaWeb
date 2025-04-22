<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>冰果店點餐整理</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 20px">
		<div class="pure-form">
			<h2> 點餐結果 </h2>
			<fieldset>
				<legend>點餐項目與金額：</legend>
				主餐：${ iceshop.main }<p />
				<ol>
					<c:forEach var="dress" items = "${iceshop.dressing}">
						<li>${ dress }</li>
					</c:forEach>
				</ol>
				總金額：${ iceshop.totalPrice }<p />
				<a href="/JavaWeb/iceshop" class = "pure-button pure-botton-primary">返回</a>
			</fieldset>
		</div>
	</body>
</html>