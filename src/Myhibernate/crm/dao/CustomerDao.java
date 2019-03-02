package Myhibernate.crm.dao;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import Myhibernate.crm.domain.Customer;
import Myhibernate.crm.domain.User;
import Myhibernate.crm.hutils.HbnUtils;

public class CustomerDao {
	// 将customer对象数据存入表中
	public void addsubmit(Customer customer) throws SecurityException, HeuristicMixedException,
			HeuristicRollbackException, RollbackException, SystemException {
		// 調用utils工具中的opensession方法获取会话对象
		Session session = HbnUtils.currentsession();
		// 利用session对象操作数据库保存customer对象
		session.save(customer);

	}

	// 获取全部的customer对象
	public List<Customer> getCustomerlist() {
		// 調用utils工具中的opensession方法获取会话对象
		Session session = HbnUtils.currentsession();
		// 2 创建Criteria对象
		Criteria c = session.createCriteria(Customer.class);
		return c.list();

	}

	// 根据ID获取需要修改信息的客户信息
	public Customer updateByid(Long id) {
		// 調用utils工具中的opensession方法获取会话对象
		Session session = HbnUtils.currentsession();
		Customer customer = session.get(Customer.class, id);
		return customer;
	}

	// 根据id获取需要删除信息的客户
	public Customer deleteByid(Customer customer) {
		// 調用utils工具中的opensession方法获取会话对象
		Session session = HbnUtils.currentsession();
		session.delete(customer);
		return customer;
	}

	// 获取edit页面数据并保存
	public void editsubmit(Customer customer) {
		// 調用utils工具中的opensession方法获取会话对象
		Session session = HbnUtils.currentsession();
		// 利用session对象操作数据库保存customer对象
		session.save(customer);

	}

	public User getcustomerByNameandpass(String username, String password) {
		Session session = HbnUtils.currentsession();
		String hql = "from User where user_name =? and user_password=?";
		User user = null;
		Query query = session.createQuery(hql);
		query.setParameter(0, username);
		query.setParameter(1, password);		
		user = (User) query.uniqueResult();
		return user;

	}
	public List<Customer> getVisitorlist(){
		// 調用utils工具中的opensession方法获取会话对象
		Session session = HbnUtils.currentsession();
		// 2 创建Criteria对象
		Criteria c = session.createCriteria(Customer.class);
		return c.list();
	}

	public List<Customer> adminsearchBycondition(String condition) {
		Session session = HbnUtils.currentsession();
		Criteria criteria = session.createCriteria(Customer.class);
		criteria.add(Restrictions.like("cust_name", condition));
		return criteria.list();
	}

	public Long getcountCustcom() {
	Session session = HbnUtils.currentsession();
	String hql = "SELECT COUNT(cust_source) FROM Customer WHERE cust_source LIKE ?";
	Query query = session.createQuery(hql);
	query.setParameter(0, "%net%");
	return (Long) query.uniqueResult();	
	}

	public Long countsource() {
		Session session = HbnUtils.currentsession();
		String hql = "SELECT COUNT(cust_source) FROM Customer";
		Query query = session.createQuery(hql);
		return (Long) query.uniqueResult();
			
	}


}
