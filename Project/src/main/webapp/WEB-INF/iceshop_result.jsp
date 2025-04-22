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
				<p />
				<h2> 點餐紀錄 </h2>
<%-- 					<ul>
						<c:forEach var="ics" items = "${iceorderlist}">
							<li>${ ics.main }</li>
								<ol>
									<c:forEach var="dresses" items = "${ics.dressing}">
										<li>${ dresses }</li>
									</c:forEach>
								</ol>
							總金額：${ ics.totalPrice }
						</c:forEach>
					</ul> --%>
				<p />
				目前留言筆數：${fn:length(iceorderlist)}
				<table class = "pure-table pure-table-bordered">
					<thead>
						<tr>
							<th>No</th>
							<th>主餐</th>
							<th>點綴</th>
							<th>總金額</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach varStatus="i" var="ics" items ="${iceorderlist}">
							<tr>
								<td>${i.index + 1 }</td>
								<td>${ics.main }</td>
								<td>
									<ol>
										<c:forEach var="dresses" items = "${ics.dressing}">
											<li>${ dresses }</li>
										</c:forEach>
									</ol>
								</td>
								<td>${ics.totalPrice}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<a href="/JavaWeb/iceshop" class = "pure-button pure-botton-primary">返回</a>
			</fieldset>
		</div>
	</body>
</html>