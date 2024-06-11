<%@page import="himedia.dao.GuestbookDaoOI"%>
<%@page import="himedia.dao.GuestbookDao"%>
<%@page import="himedia.dao.GuestbookVo"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
ServletContext cont = getServletContext();

String dbuser = cont.getInitParameter("dbuser");
String dbpass = cont.getInitParameter("dbpass");

String no = request.getParameter("no");
Long num = Long.parseLong(no);	

String content = request.getParameter("content");

GuestbookDao dao = new GuestbookDaoOI(dbuser, dbpass);

boolean success = dao.update(num, content);

if(success){
	response.sendRedirect(request.getContextPath());
} else {
	%>
	
	<h1>잘못되었음..</h1>
	<p>뭔가 이상함</p>
	<p><a href ="<%=request.getContextPath()%>">돌아가기..</a></p>
	
	<%
}
%>    