<!-- isErrorPage: 
早期 HTML 只支援 GET & POST，所以 JSP 也只支援 GET, POST，
如果加上 isErrorPage:可能會發生錯誤訊息，就不只會有 GET, POST，會有 PUT, DELETE
因此只有 error.jsp 會支援所有傳遞形式的請求。

這個時候，在這個 update.jsp isErrorPage，就可以讓 update.jsp 
就可以接收來自Controller 所傳遞的 delete, put 的請求。 -->

<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Spring Form 表單標籤 -->
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Room Update</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/css/buttons.css">
	</head>
	<body>
		<!-- menu bar include -->
		<%@ include file="/WEB-INF/view/menu.jspf" %>
		
		<!-- body content -->
		<div style="padding: 15px">
			
			<table>
				<tr>
					<!-- Room 表單修改 -->
					<td valign="top">
						<sp:form class="pure-form" method="post" modelAttribute="roomDTO" action="/room/update/${ roomDTO.roomId }" >
							<fieldset>
								<legend>Room 表單修改</legend>
								<input type = "hidden" name="_method" value="PUT"/>
								Room 房號: <sp:input type="number" path="roomId" readonly="true" />
								<sp:errors path="roomId" style="color: red" />
								<p />
								Room 名稱: <sp:input type="text" path="roomName" />
								<sp:errors path="roomName" style="color: red" />
								<p />
								Room 人數: <sp:input type="number" path="roomSize" />
								<sp:errors path="roomSize" style="color: red" />
								<p />
								<button type="submit" class="pure-button pure-button-primary">修改</button>
							</fieldset>
							
							<%-- 可以不用再每個 input 下寫每個錯誤訊息：，將所有錯誤訊息寫在下面。
							<hr />
							<sp:errors path"*" style = "color: red" /> --%>
							
							
						</sp:form>
						
					</td>
				</tr>
			</table>
			
		</div>
	</body>
</html>