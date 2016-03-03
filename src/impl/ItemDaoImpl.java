package impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dao.ItemDao;
import model.Item;
import model.Options;
import model.Problem;

public class ItemDaoImpl implements ItemDao {

	@Override
	public Item getItem(Problem problem) {
		// TODO Auto-generated method stub
		Session session = model.Util.sessionFactory.openSession();
		Criteria q = session.createCriteria(Options.class).add(Restrictions.eq("problem.id", problem.id));
		List list = q.list();
		Item item = new Item();
		item.setProblem(problem);
		item.setOptions(list);
		session.close();
		return item;
	}

}
