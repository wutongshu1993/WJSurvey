package model;

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Util {

	public static final SessionFactory sessionFactory;  
//	public static Session  session;
//	public static Transaction transaction;
//	public static Session session;	
    static 
    {  
        try 
        {  
                  

        	Configuration cfg = new Configuration().configure();
        	StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        	StandardServiceRegistry sr = srb.build();
        	sessionFactory = cfg.buildSessionFactory(sr);        	
//        	session = model.Util.sessionFactory.openSession();
//        	transaction = session.getTransaction();
        	
//        	transaction = session.beginTransaction();

  
        } 
        catch (Throwable e) 
        {  
            throw new ExceptionInInitializerError(e);  
        }  
    }  
  
    public static Session openSession() {
        return sessionFactory.openSession();
    }
    public static void executeUpdate(String hql, String... params) {

        Session session = openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);

            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    query.setParameter(i, params[i]);
                    // System.out.println("query influenced: "+params[i]);
                }
            }
            System.out.println("query influenced: " + query.getQueryString());
            int n = query.executeUpdate();
            System.out.println("query influence: " + n);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    	
}
