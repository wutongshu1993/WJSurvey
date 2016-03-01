package test;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;


import model.Survey;

public class SurveyManager {
public static void main(String[] args) throws Exception{
	
	Survey s = new Survey();
	Date date = new Date(System.currentTimeMillis());
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	String time = format.format(date).toString();
	s.setTime(time);
	
	
	SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	Session session = sessionFactory.getCurrentSession();
	session.beginTransaction();
	session.save(s);
	session.getTransaction().commit();
}
}
