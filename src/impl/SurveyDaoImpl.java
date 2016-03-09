package impl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.hibernate.Session;

import dao.SurveyDao;
import model.Survey;

public class SurveyDaoImpl implements SurveyDao {

	@Override
	public void insert(String surveyId) {
		// TODO Auto-generated method stub
		Survey survey = new Survey();
		survey.setId(surveyId);
		Date date = new Date(System.currentTimeMillis());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = format.format(date).toString();
		survey.setTime(time);
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		session.save(survey);
		session.getTransaction().commit();
		session.close();
	}

}
