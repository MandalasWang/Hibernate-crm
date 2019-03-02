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
	// 保存联系人相关信息
	public void addsubmit(LinkMan linkMan) {
		linkDao ldao = new linkDao();		
		CustomerDao cDao = new CustomerDao();
		Session session = HbnUtils.currentsession();
		Transaction tx = session.beginTransaction();
		
			//1 根据客户id获得客户对象
			Customer customer = cDao.updateByid(linkMan.getCust_id());
			//2 将客户放入LinkMan中表达关系
			linkMan.setCustomer(customer);
			try {
			//3 保存LinkMan
			ldao.addsubmit(linkMan);
		} catch (Exception e) {
			System.out.println("事务提交失败");
			tx.rollback();
		}		
		tx.commit();

	}

	// 獲取联系人列表
	public List<LinkMan> getlingkservicelist() {
		linkDao link = new linkDao();
		Session session = HbnUtils.currentsession();
		// 开启事务
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
		// 开启事务
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
