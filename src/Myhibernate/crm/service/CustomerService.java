package Myhibernate.crm.service;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Myhibernate.crm.dao.CustomerDao;
import Myhibernate.crm.domain.Customer;
import Myhibernate.crm.domain.User;
import Myhibernate.crm.hutils.HbnUtils;

public class CustomerService {
private CustomerDao cDao = new CustomerDao();
	public void addsubmit(Customer customer) {
		Session session = HbnUtils.currentsession();
		//打开事物
		Transaction tx = session.beginTransaction();
		try {
			cDao.addsubmit(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tx.rollback();
		}
		tx.commit();
	}
	//获取全部的用户信息
	public List<Customer> getCustomerlist() {
		Session session = HbnUtils.currentsession();
		//开启事务
		Transaction tx = session.beginTransaction();
	    List<Customer> customers = cDao.getCustomerlist();
	    tx.commit();
		return customers;
		
	}
	//根据ID获取需要修改信息的客户信息
	public Customer updateByid(long id) {
		Session session = HbnUtils.currentsession();
		//开启事务
		Transaction tx = session.beginTransaction();
		Customer customer =cDao.updateByid(id);
		tx.commit();
		return customer;
	}
	//根据id获取需要删除信息的客户
	public Customer deleteByid(Customer customer) {
		Session session = HbnUtils.currentsession();
		//开启事务
		Transaction tx = session.beginTransaction();
		cDao.deleteByid(customer);
		tx.commit();
		return customer;
	}
	//获取edit页面数据并保存
	public void editsubmit(Customer customer) {
		Session session = HbnUtils.currentsession();
		//打开事物
		Transaction tx = session.beginTransaction();
		try {
			cDao.editsubmit(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tx.rollback();
		}
		tx.commit();
	}
	//根据登录页面传来的信息查询相关信息
	public User getcustomerByNameandpass(String username, String password) {
		Session session = HbnUtils.currentsession();
		//开启事务
		Transaction tx = session.beginTransaction();
		User user = null;
		try {
			user = cDao.getcustomerByNameandpass(username,password);
		}catch (Exception e) {
			tx.rollback();
		}
		tx.commit();
		return user;
	}
	public List<Customer> getVisitorlist(){
		Session session = HbnUtils.currentsession();
		//开启事务
		Transaction tx = session.beginTransaction();
	    List<Customer> customers = cDao.getVisitorlist();
	    tx.commit();
		return customers;
	}
	public List<Customer> adminsearchBycondition(String condition) {
		Session session = HbnUtils.currentsession();
		Transaction tx = session.beginTransaction();
		List<Customer> customers = cDao.adminsearchBycondition(condition);
		tx.commit();
		return customers;
	}
	public Long getcountCustcom() {
		Session session = HbnUtils.currentsession();
		Transaction tx = session.beginTransaction();
		Long count = (long) cDao.getcountCustcom();
		return count;
	}
	public Long countsource() {
		Session session = HbnUtils.currentsession();
		Transaction tx = session.beginTransaction();
		Long count = (long) cDao.countsource();
		return count;
	}
	

     
}
