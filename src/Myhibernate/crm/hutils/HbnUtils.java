package Myhibernate.crm.hutils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HbnUtils {
	private static SessionFactory sFactory;
     static{
        //创建调用空参的构造方法
    	 Configuration conf = new Configuration().configure();
    	 //根据配置信息创建factory对象
    	 sFactory = conf.buildSessionFactory();
    	 
       }
     //获取一个新的会话对象 
     public static Session opensession(){
    	 Session session = sFactory.openSession();
    	 return session;
     }
     //获取当前进程的会话对象
     public static Session currentsession(){
    	 Session session = sFactory.getCurrentSession();
    	 return session;
    	  
     }
     public static void close(){
    	 sFactory.close();
     }
    
}
