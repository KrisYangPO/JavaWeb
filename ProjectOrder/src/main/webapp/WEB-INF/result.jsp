<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/JavaWebOrder/css/buttons.css">
	</head>
	
	<body style = "padding: 20px">
		<div class = "pure-form">
			<legend>定單結果</legend>
			${ orderDTO.message }
			<p/>
			<a href= "/JavaWebOrder/index.jsp" class = "pure-button pure-botton-primary">回首頁</a>
		</div>
		
	</body>
</html>