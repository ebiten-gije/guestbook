<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
</head>
<body>
	<form method="post" action="<%=request.getContextPath()%>/el">
	<input type='hidden' name="a" value="edit">
	<input type='hidden' name="id" value=<%=request.getParameter("no")%>>
	<table>
		<tr>
			<td>수정하기</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="password"></td>
			<td><input type="submit" value="확인"></td>
			<td><a href="<%=request.getContextPath()%>/el">메인으로 돌아가기</a></td>
		</tr>
	</table>
	</form>
	
</body>
</html>