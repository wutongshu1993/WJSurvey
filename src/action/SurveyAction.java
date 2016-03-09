package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.sun.net.httpserver.Authenticator.Success;

import dao.ItemDao;
import dao.ProblemDao;
import impl.ItemDaoImpl;
import impl.ProblemDaoImpl;
import impl.SurveyDaoImpl;
import jdk.nashorn.internal.ir.WhileNode;
import model.Answer;
import model.Bulk;
import model.Item;
import model.Options;
import model.Problem;
import model.Survey;
import model.Util;

public class SurveyAction {
	public Problem problem;
	public Options options;
	public List<Item> items;
	public List<Bulk> bulks;
	public int optionId;
	public boolean checked;
	private String status;
	private String surveyId;
	private Survey survey;
	

	public String execute() throws Exception{
		ProblemDao pDao = new ProblemDaoImpl();
		ItemDao iDao = new ItemDaoImpl();
		List<Problem> problems = pDao.getAllProblems();
		items=new ArrayList<Item>();
		Item item1,item2;
		List<Item> items2 = new ArrayList<>();//同一道题的不同小问所构成的集合
		bulks = new ArrayList<>();
		int start=0;
		int end =0;
		int flag=0;//
		
		for (Problem problem : problems) {
			Item item = iDao.getItem(problem);
			items.add(item);
		}
		while(end<items.size()){
			while (end<items.size()&&items.get(start).getProblem().getPid() == items.get(end).getProblem().getPid()) {
				items2.add(items.get(end));
				end++;
				flag = 1;
			}
//			else { 
				
				List<Item> temp = new ArrayList<>();
				for (Item item : items2) {
					temp.add(item);
				}
				Bulk bulk = new Bulk();
				bulk.setPid(items.get(start).getProblem().pid);
				bulk.setItems(temp);
				bulks.add(bulk);
				start = end;
				items2.clear();
				flag=0;
				
				
//			}
			
		}
//		Map session = ActionContext.getContext().getSession();
//		session.put("items", items);
		surveyId = String.valueOf(System.currentTimeMillis());
		new SurveyDaoImpl().insert(surveyId);
		Map session = ActionContext.getContext().getSession();
		session.put("surveyId", surveyId);
		return "success";
	}
	//还差一个对remark的插入和判断	
	public String checkChangeForR() throws Exception{
		System.out.println(optionId);
		System.out.println(checked);
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		Options op;
		Answer a;
		Problem p;
		//不同的人用的是不同的浏览器，所以session应该也是不同的
		surveyId = ActionContext.getContext().getSession().get("surveyId").toString();
		Criteria c = session.createCriteria(Options.class).
				add(Restrictions.eq("id", optionId));
		op = (Options)c.list().get(0);//将该选项号对应的选项取出来
		
		Criteria c1 = session.createCriteria(Problem.class).
				add(Restrictions.eq("id", op.problem.id));
		p = (Problem)c1.list().get(0);
		
		//此处应该再加一个条件对survey.id的判断 
		Criteria c2 = session.createCriteria(Answer.class)
				.add(Restrictions.eq("problem.id", op.problem.id))
				.add(Restrictions.eq("survey.id", surveyId));
		
		Criteria c3 = session.createCriteria(Survey.class)
				.add(Restrictions.eq("id", surveyId));
		survey = (Survey)c3.list().get(0);
		
		List l = c2.list(); 
		if (!l.isEmpty()) {
			a = (Answer)c2.list().get(0);	//error???
		}
		else {
			a=null;
		}

		if (checked) {
			if (a==null) {//还没有选过这个答案
				a=new Answer();
				a.setSurvey(survey);
				a.setProblem(p);
				a.setOptions(op);
				session.save(a);
				this.status="添加选项成功";
			}
			else {//这个题已经做过一次了，需要更新答案(直接更新，不用判断；或者先删除，再插入)
					session.delete(a);
					a=new Answer();
					a.setSurvey(survey);
					a.setProblem(p);
					a.setOptions(op);
					session.save(a);
					this.status="添加选项成功";
				
			}
		}
			System.out.println("True");
			
		session.getTransaction().commit();
		session.close();
		return "success";
	}
	
	public String checkChangeForC() throws Exception{
		System.out.println(optionId);
		System.out.println(checked);
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		Options op;
		Answer a;
		Problem p;
		//不同的人用的是不同的浏览器，所以session应该也是不同的
		surveyId = ActionContext.getContext().getSession().get("surveyId").toString();
		Criteria c = session.createCriteria(Options.class).
				add(Restrictions.eq("id", optionId));
		op = (Options)c.list().get(0);//将该选项号对应的选项取出来
		
		Criteria c1 = session.createCriteria(Problem.class).
				add(Restrictions.eq("id", op.problem.id));
		p = (Problem)c1.list().get(0);
		
		//此处应该再加一个条件对survey.id的判断 
		Criteria c2 = session.createCriteria(Answer.class)
				.add(Restrictions.eq("problem.id", op.problem.id))
				.add(Restrictions.eq("survey.id", surveyId))
				.add(Restrictions.eq("options.id", optionId));
		
		Criteria c3 = session.createCriteria(Survey.class)
				.add(Restrictions.eq("id", surveyId));
		survey = (Survey)c3.list().get(0);
		if (!c2.list().isEmpty()) {
			a = (Answer)c2.list().get(0);	
		}
		else {
			a=null;
		}
		if (checked == false) {
			System.out.println("False");
			if (a==null) {
				this.status="这个选项之前没选过";
				return "success";
			}
			//之前选了，后来又删除了，此时应该把对应answer删除
			this.status="删除成功";
			session.delete(a);//这里没有指定answer，能够删除成功吗？
			
		}
		else {
			System.out.println("True");
			if (a!=null) {
				this.status="这个选项已经选过了";
				return "success";
			}
			//新答案，插入
			a=new Answer();
			a.setProblem(p);
			a.setOptions(op);
			a.setSurvey(survey);
			session.save(a);
			this.status="添加选项成功";
//			a.setSurvey(s);
		}
		session.getTransaction().commit();
		session.close();
		return "success";
	}

	public String submit() throws Exception{
		return "success";
	}
	
	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Bulk> getBulks() {
		return bulks;
	}

	public void setBulks(List<Bulk> bulks) {
		this.bulks = bulks;
	}
	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	public Survey getSurvey() {
		return survey;
	}
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	
	

}
