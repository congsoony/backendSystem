package org.edwith.webbe.guestbook.servlet;

import org.edwith.webbe.guestbook.dao.GuestbookDao;
import org.edwith.webbe.guestbook.dto.Guestbook;
import org.edwith.webbe.guestbook.util.DBUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/guestbooks/write")
public class GuestbookWriteServlet extends HttpServlet {
	private GuestbookDao dao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		dao = new GuestbookDao(DBUtil.getConnection());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8"); //받은 데이터 한글인식
		
		String name = request.getParameter("name");
		
		String content = request.getParameter("content");

		dao.addGuestbook(new Guestbook(name, content));
		
		response.sendRedirect("/guestbook/guestbooks");
	}

}
