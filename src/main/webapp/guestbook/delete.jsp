<%@page import="himedia.dao.GuestbookDaoOI"%>
<%@page import="himedia.dao.GuestbookDao"%>
<%@page import="himedia.dao.GuestbookVo"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
ServletContext cont = getServletContext();

String dbuser = cont.getInitParameter("dbuser");
String dbpass = cont.getInitParameter("dbpass");

String no = request.getParameter("id");
Long num = Long.parseLong(no);	


String password = request.getParameter("password");

GuestbookDao dao = new GuestbookDaoOI(dbuser, dbpass);

boolean success = dao.del(num, password);

if(success){
	response.sendRedirect(request.getContextPath() + "/guestbook/list.jsp");
} else {
	%>
	
	<h1>잘못되었음..</h1>
	<p>비밀번호 틀린 듯??</p>
	<p><a href ="<%=request.getContextPath()%>/guestbook/deleteform.jsp?no=<%=num%>">돌아가기..</a></p>
	
	<%
}
%>    
