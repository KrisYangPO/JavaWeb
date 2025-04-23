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
			<fieldset>
				<legend>訂單歷史資料</legend>
				<table class="pure-table pure-table-bordered">
					<thead>
						<tr>
							<th>Index</th><th>Item</th><th>Delete</th><th>update</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach varStatus = "row" var = "dto" items = "${orderDTOs}">
							<tr>
								<td>${row.index }</td>
								<td>${dto.message }</td>
								<!-- 新增刪除鍵，用另外一個 servlet 做到 -->
								<td><a = href = "/JavaWebOrder/order/delete?index=${row.index }" class="button-error pure-button">X</a>
								<td><a = href = "/JavaWebOrder/order/update?index=${row.index }" class="button-success pure-button">@</a>
							</tr>
							
						</c:forEach>
					</tbody>
					<tfoot>
						<tr style = "background-color: #DDDDDD">
							<td colspan = "2" style="text-align: right;">總金額 <fmt:formatNumber value="${ totalprice }" type="currency" maxFractionDigits = "0"/></td>
							<td colspan = "2"></td>
						</tr>
					</tfoot>	
				</table>
				<p/>
				<a href= "/JavaWebOrder/index.jsp" class = "pure-button pure-botton-primary">回首頁</a>
			</fieldset>
		</div>
		
	</body>
</html>