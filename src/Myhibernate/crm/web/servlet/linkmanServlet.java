package Myhibernate.crm.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import Myhibernate.crm.domain.Customer;
import Myhibernate.crm.domain.LinkMan;
import Myhibernate.crm.service.CustomerService;
import Myhibernate.crm.service.LinkService;

/**
 * Servlet implementation class linkmanServlet
 */
@WebServlet("/linkman")
public class linkmanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public linkmanServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mathodName = request.getParameter("method");
		if ("addsubmit".equals(mathodName)) {
			addsubmit(request, response);
			response.getWriter().write("date save successful!!");
		} else if ("linkmanlist".equals(mathodName)) {
			linkmanlist(request, response);
			request.getRequestDispatcher("jsp/linkman/list.jsp").forward(request, response);
		} else if ("editlinkman".equals(mathodName)) {
			editlinkman(request, response);
			request.getRequestDispatcher("jsp/linkman/edit.jsp").forward(request, response);
		} else if ("editsubmit".equals(mathodName)) {
			editsubmit(request,response);
			response.getWriter().write("data save successful!!");
		} else if ("deletelinkman".equals(mathodName)) {
			deletelinkman(request, response);
			linkmanlist(request, response);
			request.getRequestDispatcher("jsp/linkman/list.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private static void addsubmit(HttpServletRequest request, HttpServletResponse response) {
		LinkMan linkMan = new LinkMan();
		try {
			BeanUtils.populate(linkMan, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LinkService lingkservice = new LinkService();
		lingkservice.addsubmit(linkMan);
	}

	private static void linkmanlist(HttpServletRequest request, HttpServletResponse response) {
		LinkService lingkservice = new LinkService();
		List<LinkMan> linkMans = lingkservice.getlingkservicelist();
		// 将数据存入域中
		request.setAttribute("linkMans", linkMans);

	}

	private static void editlinkman(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("lkm_id");
		LinkService lingkservice = new LinkService();
		// 根据id查询联系人相关对象并返回
		LinkMan linkMan = lingkservice.getlinkmanByid(id);
		request.setAttribute("linkman", linkMan);
	}

	private static void editsubmit(HttpServletRequest request, HttpServletResponse response){
		LinkMan linkMan = new LinkMan();
		try {
			BeanUtils.populate(linkMan, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LinkService lingkservice = new LinkService();
		lingkservice.editsubmit(linkMan);
	}
	private static void deletelinkman(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("lkm_id");
		LinkService lingkservice = new LinkService();
		// 根据id查询联系人相关对象并返回
		lingkservice.deletelinkman(id);

	}
}
