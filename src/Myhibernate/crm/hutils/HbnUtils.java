package Myhibernate.crm.hutils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HbnUtils {
	private static SessionFactory sFactory;
     static{
        //�������ÿղεĹ��췽��
    	 Configuration conf = new Configuration().configure();
    	 //����������Ϣ����factory����
    	 sFactory = conf.buildSessionFactory();
    	 
       }
     //��ȡһ���µĻỰ���� 
     public static Session opensession(){
    	 Session session = sFactory.openSession();
    	 return session;
     }
     //��ȡ��ǰ���̵ĻỰ����
     public static Session currentsession(){
    	 Session session = sFactory.getCurrentSession();
    	 return session;
    	  
     }
     public static void close(){
    	 sFactory.close();
     }
    
}
