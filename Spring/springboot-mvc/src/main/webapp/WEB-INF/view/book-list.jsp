<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Book List</title>
	</head>
	<body>
		<%@ include file="include/menu.jsp" %>
		<div>
			<form method ="post" action = "/ssr/book/add">
				書名：<input type = "text" name = "name" required /><p/>
				價格：<input type = "number" name = "price" step = "0.1" required /><p/>
				數量：<input type = "number" name = "amount" required /><p/>
				出刊：<input type = "checkbox" name = "pub" required/><p/>
				<button type = "submit"> 送出 </button>
			</form>
		</div>
		<div>
			<table border="1">
				<thead>
					<tr>
						<th>ID</th><th>書名</th><th>價格</th><th>數量</th><th>出刊</th><th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${ books }">
						<tr>
							<td>${ book.id }</td>
							<td>${ book.name }</td>
							<td>${ book.price }</td>
							<td>${ book.amount }</td>
							<td>${ book.pub }</td>
							<td>
							
								<a href = "/ssr/book/edit/${book.id}"> 修改</a>
								&nbsp; | &nbsp;
								<!-- &nbsp; 是產生空白的意思 -->
								<!-- 原本的刪除法： -->
								<%-- <a href = "/ssr/book/delete/${book.id}">刪除</a> --%>
								
								<!-- 利用 DELETE RestController 寫法： -->
								<form style = "dsiplay: inline" method ="post" action = "/ssr/book/delete/${book.id}">
									<input type="hidden" name="_method" value="DELETE"/>
									<button type = "submit"> 刪除 </button>
								</form>
								
								<!-- 用 JavaScript 寫法： -->
								&nbsp; | &nbsp;
								<a href="#" onclick="deleteBook(${book.id})"> 刪除 </a>
								
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<script>
			asyn function deleteBook(id){
				if(confirm('確定要刪除嗎？')){
					const response = await fetch(`/ssr/book/delete/` + id, {method: 'DELETE'});
					if(response.redirected){
						window.location.href = response.url;
					}else{
						alert('刪除失敗');
					}
					
				}
			}
		</script>
	</body>
</html>