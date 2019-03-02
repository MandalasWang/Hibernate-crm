package Myhibernate.crm.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import Myhibernate.crm.domain.Customer;
import Myhibernate.crm.domain.User;
import Myhibernate.crm.service.CustomerService;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

@WebServlet("/Customer")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static CustomerService cService = new CustomerService();

	public CustomerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String methodName = request.getParameter("method");
		if ("addsubmit".equals(methodName)) {
			addsubmit(request, response);
			getCustomerlist(request, response);
			response.getWriter().write("Save Data Successful!!");
			// request.getRequestDispatcher("/jsp/customer/list.jsp").forward(request,
			// response);
		} else if ("getCustomerlist".equals(methodName)) {
			getCustomerlist(request, response);
			request.getRequestDispatcher("/jsp/customer/list.jsp").forward(request, response);
		} else if ("updateByid".equals(methodName)) {
			String sid = request.getParameter("cid");
			int id = Integer.valueOf(sid);
			updateByid(id, request, response);
			request.getRequestDispatcher("/jsp/customer/edit.jsp").forward(request, response);
		} else if ("deleteByid".equals(methodName)) {
			String sid = request.getParameter("cid");
			System.out.println(sid);
			int id = Integer.valueOf(sid);
			deleteByid(id, request, response);
			getCustomerlist(request, response);
			request.getRequestDispatcher("/jsp/customer/list.jsp").forward(request, response);
		} else if ("editsubmit".equals(methodName)) {
			editsubmit(request, response);
			getCustomerlist(request, response);
			request.getRequestDispatcher("/jsp/customer/list.jsp").forward(request, response);
		} else if ("login".equals(methodName)) {
			User user = login(request, response);
			if (user != null && Integer.valueOf(user.getUser_state().toString()) == 1) {
				request.getRequestDispatcher("index.htm").forward(request, response);
			} else {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} else if ("adminsearch".equals(methodName)) {
			List<Customer> customers = adminsearch(request, response);
			request.setAttribute("customers", customers);
			request.getRequestDispatcher("/jsp/customer/list.jsp").forward(request, response);
		}else if("countCustcom".equals(methodName)){
			countCustcom(request,response);
			request.getRequestDispatcher("/jsp/analysis/list.jsp").forward(request, response);
			
		}else if("countsource".equals(methodName)){
			countsource(request,response);
			request.getRequestDispatcher("/jsp/analysis/list2.jsp").forward(request, response);
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public static void addsubmit(HttpServletRequest request, HttpServletResponse response) {

		Customer customer = new Customer();
		Map<String, String[]> properties = request.getParameterMap();
		try {
			BeanUtils.populate(customer, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		cService.addsubmit(customer);

	}

	// ��ȡȫ�����û���Ϣ
	public static void getCustomerlist(HttpServletRequest request, HttpServletResponse response) {
		List<Customer> customers = cService.getCustomerlist();
		// �����ݴ�����
		request.setAttribute("customers", customers);
	}

	// ����id�@ȡ��Ҫ�޸ĵĿ͑���Ϣ
	public static void updateByid(long id, HttpServletRequest request, HttpServletResponse response) {
		Customer customer = cService.updateByid(id);
		// �����ݸ�ҳ��
		request.setAttribute("customer", customer);
	}

	// ����id��ȡ��Ҫɾ����Ϣ�ͻ�����Ϣ
	private static void deleteByid(int id, HttpServletRequest request, HttpServletResponse response) {
		Customer customer = cService.updateByid(id);
		cService.deleteByid(customer);
	}

	// ��ȡeditҳ���ύ�����ݲ�����
	private static void editsubmit(HttpServletRequest request, HttpServletResponse response) {
		Customer customer = new Customer();
		Map<String, String[]> properties = request.getParameterMap();
		try {
			BeanUtils.populate(customer, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		cService.editsubmit(customer);
	}

	// �����¼ҳ�洫������Ϣ
	public static User login(HttpServletRequest request, HttpServletResponse response) {

		String password = request.getParameter("user_password");
		String username = request.getParameter("user_name");
		User user = cService.getcustomerByNameandpass(username, password);
		request.getSession().setAttribute("user", user);
		return user;
	}

	private static List<Customer> adminsearch(HttpServletRequest request, HttpServletResponse response) {
		String condition = request.getParameter("custName").trim().toString();
		return cService.adminsearchBycondition(condition);

	}

	private void countCustcom(HttpServletRequest request, HttpServletResponse response) {
		Long totalcount = cService.getcountCustcom(); 
		request.setAttribute("total", totalcount);
	}

	private void countsource(HttpServletRequest request, HttpServletResponse response) {
		Long totalcount = cService.countsource(); 
		request.setAttribute("total", totalcount);
		
	}
}
