package Myhibernate.crm.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Myhibernate.crm.dao.CustomerDao;
import Myhibernate.crm.dao.linkDao;
import Myhibernate.crm.domain.Customer;
import Myhibernate.crm.domain.LinkMan;
import Myhibernate.crm.hutils.HbnUtils;

public class LinkService {
	// ������ϵ�������Ϣ
	public void addsubmit(LinkMan linkMan) {
		linkDao ldao = new linkDao();		
		CustomerDao cDao = new CustomerDao();
		Session session = HbnUtils.currentsession();
		Transaction tx = session.beginTransaction();
		
			//1 ���ݿͻ�id��ÿͻ�����
			Customer customer = cDao.updateByid(linkMan.getCust_id());
			//2 ���ͻ�����LinkMan�б���ϵ
			linkMan.setCustomer(customer);
			try {
			//3 ����LinkMan
			ldao.addsubmit(linkMan);
		} catch (Exception e) {
			System.out.println("�����ύʧ��");
			tx.rollback();
		}		
		tx.commit();

	}

	// �@ȡ��ϵ���б�
	public List<LinkMan> getlingkservicelist() {
		linkDao link = new linkDao();
		Session session = HbnUtils.currentsession();
		// ��������
		Transaction tx = session.beginTransaction();
		List<LinkMan> LinkMans = null;
		try {
			LinkMans = link.getlingkservicelist();
		} catch (Exception e) {
			tx.rollback();
		}
		tx.commit();
		return LinkMans;
	}

	public void deletelinkman(String id) {
		linkDao link = new linkDao();
		Session session = HbnUtils.currentsession();
		// ��������
		Transaction tx = session.beginTransaction();
		try {
			 LinkMan linkMan = link.getlkByid(id);
				link.deletelinkman(linkMan);
		} catch (Exception e) {
			tx.rollback();
		}	   
		tx.commit();
	}

	public void editsubmit(LinkMan linkMan) {
		Session session = HbnUtils.currentsession();
		Transaction tx = session.beginTransaction();
		linkDao ldao = new linkDao();
		ldao.editsubmit(linkMan);
		tx.commit();

	}

	public LinkMan getlinkmanByid(String id) {
		Session session = HbnUtils.currentsession();
		Transaction tx = session.beginTransaction();
		linkDao ldao = new linkDao();
		return ldao.getlkByid(id);
	}

}
