<%@page import="himedia.dao.GuestbookVo"%>
<%@page import="java.util.List"%>
<%@page import="himedia.dao.GuestbookDaoOI"%>
<%@page import="himedia.dao.GuestbookDao"%>
<%@ page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%
List <GuestbookVo> list = null;
if(request.getAttribute("list") instanceof List){
	list = (List<GuestbookVo>)request.getAttribute("list");	
}

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/el" method="POST">
	<input type="hidden" name="a" value="add"/>
	<table border=1 width=500>
		<tr>
			<td>이름</td><td><input type="text" name="name"></td>
			<td>비밀번호</td><td><input type="password" name="pass"></td>
		</tr>
		<tr>
			<td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
		</tr>
		<tr>
			<td colspan=4 align=right><input type="submit" VALUE="확인"></td>
		</tr>
	</table>
	</form>
	<br/>

<%
	for (GuestbookVo vo : list){
%>
	<table width=510 border=1>
		<tr>
			<td><%=vo.getNo()%></td>
			<td><%=vo.getName()%></td>
			<td><%=vo.getDate()%></td>
			<td><a href="<%=request.getContextPath()%>/el?a=passconfirm&no=<%=vo.getNo()%>">수정</a></td>
			<td><a href="<%=request.getContextPath()%>/el?a=deleteform&no=<%=vo.getNo()%>">삭제</a></td>
		</tr>
		<tr>
			<td colspan=5><%=vo.getContent()%></td>
		</tr>
	</table>
        <br/>
<%
	}
%>        
</body>
</html>