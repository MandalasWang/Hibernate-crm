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
		//������
		Transaction tx = session.beginTransaction();
		try {
			cDao.addsubmit(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tx.rollback();
		}
		tx.commit();
	}
	//��ȡȫ�����û���Ϣ
	public List<Customer> getCustomerlist() {
		Session session = HbnUtils.currentsession();
		//��������
		Transaction tx = session.beginTransaction();
	    List<Customer> customers = cDao.getCustomerlist();
	    tx.commit();
		return customers;
		
	}
	//����ID��ȡ��Ҫ�޸���Ϣ�Ŀͻ���Ϣ
	public Customer updateByid(long id) {
		Session session = HbnUtils.currentsession();
		//��������
		Transaction tx = session.beginTransaction();
		Customer customer =cDao.updateByid(id);
		tx.commit();
		return customer;
	}
	//����id��ȡ��Ҫɾ����Ϣ�Ŀͻ�
	public Customer deleteByid(Customer customer) {
		Session session = HbnUtils.currentsession();
		//��������
		Transaction tx = session.beginTransaction();
		cDao.deleteByid(customer);
		tx.commit();
		return customer;
	}
	//��ȡeditҳ�����ݲ�����
	public void editsubmit(Customer customer) {
		Session session = HbnUtils.currentsession();
		//������
		Transaction tx = session.beginTransaction();
		try {
			cDao.editsubmit(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tx.rollback();
		}
		tx.commit();
	}
	//���ݵ�¼ҳ�洫������Ϣ��ѯ�����Ϣ
	public User getcustomerByNameandpass(String username, String password) {
		Session session = HbnUtils.currentsession();
		//��������
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
		//��������
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
