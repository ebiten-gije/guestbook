package himedia.controller;

import java.io.IOException;
import java.util.List;
import himedia.dao.GuestbookDao;
import himedia.dao.GuestbookDaoOI;
import himedia.dao.GuestbookVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Guestbook", urlPatterns = "/el")
public class GuestbookServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionName = req.getParameter("a");
		if("deleteform".equals(actionName)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
			rd.forward(req, resp);
			
		} else if ("passconfirm".equals(actionName)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/passconfirm.jsp");
			rd.forward(req, resp);
			
		} else {
			GuestbookDao dao = new GuestbookDaoOI(dbuser, dbpass);
			List<GuestbookVo> list = dao.getList();
			
			req.setAttribute("list", list);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
			rd.forward(req, resp);
		}
//		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionName = req.getParameter("a");
		
		if("add".equals(actionName)) {
			String name = req.getParameter("name");
			String password = req.getParameter("pass");
			String content = req.getParameter("content");

			GuestbookVo vo = new GuestbookVo(name, password, content);

			GuestbookDao dao = new GuestbookDaoOI(dbuser, dbpass);

			boolean success = dao.add(vo);
			
			if(success){
				resp.sendRedirect(req.getContextPath() + "/el");
			}
		
		} else if ("delete".equals(actionName)) {
			String no = req.getParameter("id");
			Long num = Long.parseLong(no);	


			String password = req.getParameter("password");

			GuestbookDao dao = new GuestbookDaoOI(dbuser, dbpass);

			boolean success = dao.del(num, password);

			if(success){
				resp.sendRedirect(req.getContextPath() + "/el");
			} else {
				resp.sendRedirect(req.getContextPath() + "/cannot.jsp");
			}
			
		} else if ("edit".equals(actionName)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/guestbook/edit.jsp");
			rd.forward(req, resp);
			
		}else if ("update".equals(actionName)) {
			String no = req.getParameter("no");
			Long num = Long.parseLong(no);	

			String content = req.getParameter("content");

			GuestbookDao dao = new GuestbookDaoOI(dbuser, dbpass);

			boolean success = dao.update(num, content);

			if(success){
				resp.sendRedirect(req.getContextPath() + "/el");
			} else {
				resp.sendRedirect(req.getContextPath() + "/cannot.jsp");
			}
			
		} else {
			super.doPost(req, resp);
		}
		
	}

}
