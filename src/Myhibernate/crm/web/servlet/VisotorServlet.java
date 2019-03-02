package Myhibernate.crm.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Myhibernate.crm.domain.Customer;
import Myhibernate.crm.service.CustomerService;


@WebServlet("/Visotor")
public class VisotorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public VisotorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if("getvisitorlist".equals(methodName)){
			getvisitorlist(request,response);
			request.getRequestDispatcher("/jsp/visitor/list.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void getvisitorlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 CustomerService cService = new CustomerService();
		   List<Customer> customers = cService.getCustomerlist();
		   //将数据存入域中
		   request.setAttribute("customers", customers);
	}

}
