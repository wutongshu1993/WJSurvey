package impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import dao.ProblemDao;
import model.Problem;

public class ProblemDaoImpl implements ProblemDao {

	@Override
	public List<Problem> getAllProblems() {
		// TODO Auto-generated method stub
		Session session = model.Util.sessionFactory.openSession();
//		Criteria q = session.createCriteria(Problem.class);
		String hql = "from Problem  p order by p.pid asc";
		Query q = session.createQuery(hql);
		List<Problem> list = (List<Problem>)q.list();
		session.close();
		return list;
	}

}
