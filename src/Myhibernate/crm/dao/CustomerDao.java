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
	// ��customer�������ݴ������
	public void addsubmit(Customer customer) throws SecurityException, HeuristicMixedException,
			HeuristicRollbackException, RollbackException, SystemException {
		// �{��utils�����е�opensession������ȡ�Ự����
		Session session = HbnUtils.currentsession();
		// ����session����������ݿⱣ��customer����
		session.save(customer);

	}

	// ��ȡȫ����customer����
	public List<Customer> getCustomerlist() {
		// �{��utils�����е�opensession������ȡ�Ự����
		Session session = HbnUtils.currentsession();
		// 2 ����Criteria����
		Criteria c = session.createCriteria(Customer.class);
		return c.list();

	}

	// ����ID��ȡ��Ҫ�޸���Ϣ�Ŀͻ���Ϣ
	public Customer updateByid(Long id) {
		// �{��utils�����е�opensession������ȡ�Ự����
		Session session = HbnUtils.currentsession();
		Customer customer = session.get(Customer.class, id);
		return customer;
	}

	// ����id��ȡ��Ҫɾ����Ϣ�Ŀͻ�
	public Customer deleteByid(Customer customer) {
		// �{��utils�����е�opensession������ȡ�Ự����
		Session session = HbnUtils.currentsession();
		session.delete(customer);
		return customer;
	}

	// ��ȡeditҳ�����ݲ�����
	public void editsubmit(Customer customer) {
		// �{��utils�����е�opensession������ȡ�Ự����
		Session session = HbnUtils.currentsession();
		// ����session����������ݿⱣ��customer����
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
		// �{��utils�����е�opensession������ȡ�Ự����
		Session session = HbnUtils.currentsession();
		// 2 ����Criteria����
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
