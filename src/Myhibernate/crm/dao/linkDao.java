package Myhibernate.crm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import Myhibernate.crm.domain.LinkMan;
import Myhibernate.crm.hutils.HbnUtils;

public class linkDao {

	public void addsubmit(LinkMan linkMan) {
		Session session = HbnUtils.currentsession();
		session.save(linkMan);

	}

	public List<LinkMan> getlingkservicelist() {
		Session session = HbnUtils.currentsession();
		Criteria criteria = session.createCriteria(LinkMan.class);
		List<LinkMan> linkMans = criteria.list();
		return linkMans;
	}


	public void deletelinkman(LinkMan linkMan) {
		Session session = HbnUtils.currentsession();
		session.delete(linkMan);

	}

	public void editsubmit(LinkMan linkMan) {
		Session session = HbnUtils.currentsession();
		session.update(linkMan);

	}

	public LinkMan getlkByid(String id) {
		Session session = HbnUtils.currentsession();
		return session.get(LinkMan.class, Long.valueOf(id));
		
	}

}
