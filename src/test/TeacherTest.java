package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import model.Teacher;

public class TeacherTest {
	public static void main(String[] args) {
		Teacher t = new Teacher();
		
		t.setName("t1");
		t.setTitle("middle");
		
		Session session = model.Util.sessionFactory.openSession();
		
//		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
		session.close();
		
		
	}
}
