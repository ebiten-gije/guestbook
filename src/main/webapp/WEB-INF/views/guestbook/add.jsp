<%@page import="himedia.dao.GuestbookDaoOI"%>
<%@page import="himedia.dao.GuestbookDao"%>
<%@page import="himedia.dao.GuestbookVo"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
ServletContext cont = getServletContext();

String dbuser = cont.getInitParameter("dbuser");
String dbpass = cont.getInitParameter("dbpass");

String name = request.getParameter("name");
String password = request.getParameter("pass");
String content = request.getParameter("content");

GuestbookVo vo = new GuestbookVo(name, password, content);

GuestbookDao dao = new GuestbookDaoOI(dbuser, dbpass);

boolean success = dao.add(vo);

if(success){
	response.sendRedirect(request.getContextPath());
} else {
	%>
	
	<h1>잘못되었음..</h1>
	<p>뭔가 문제가 있는데?</p>
	
	<%
}
%>    
