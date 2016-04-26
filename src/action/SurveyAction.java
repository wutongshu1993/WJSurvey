package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Delayed;

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
import model.Address;
import model.Answer;
import model.Bulk;
import model.Item;
import model.Options;
import model.Problem;
import model.Survey;
import model.User;
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
	private String remark;
	public String name;
	public String hTel;
	public String tel;
	public String quX;//����
	public String xiangJ;//�磬�ֵ�
	public String cun;//��
	public String zuD;//�� ��
	public String hao;//��
	

	public String execute() throws Exception{
		ProblemDao pDao = new ProblemDaoImpl();
		ItemDao iDao = new ItemDaoImpl();
		List<Problem> problems = pDao.getAllProblems();
		items=new ArrayList<Item>();
		Item item1,item2;
		List<Item> items2 = new ArrayList<>();//ͬһ����Ĳ�ͬС�������ɵļ���
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
	public String saveRemarkForE() throws Exception{
		System.out.println(optionId);
		System.out.println(remark);
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		Options op;
		Answer a;
		Problem p;
		//��ͬ�����õ��ǲ�ͬ�������������sessionӦ��Ҳ�ǲ�ͬ��
		surveyId = ActionContext.getContext().getSession().get("surveyId").toString();
		Criteria c = session.createCriteria(Options.class).
				add(Restrictions.eq("id", optionId));
		op = (Options)c.list().get(0);//����ѡ��Ŷ�Ӧ��ѡ��ȡ����
		
		Criteria c1 = session.createCriteria(Problem.class).
				add(Restrictions.eq("id", op.problem.id));
		p = (Problem)c1.list().get(0);
		
		//�˴�Ӧ���ټ�һ��������survey.id���ж� 
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

		if (remark!=null) {//��������˱�ע
			if (a==null) {//��û��ѡ�������
				a=new Answer();
				a.setSurvey(survey);
				a.setProblem(p);
				a.setOptions(op);
				a.setRemark(remark);
				session.save(a);
				this.status="���ѡ��ɹ�";
			}
			else {//������Ѿ�����һ���ˣ���Ҫ���´�(ֱ�Ӹ��£������жϣ�������ɾ�����ٲ���)
					session.delete(a);
					a=new Answer();
					a.setSurvey(survey);
					a.setProblem(p);
					a.setOptions(op);
					a.setRemark(remark);
					session.save(a);
					this.status="���ѡ��ɹ�";
				
			}
		}
			System.out.println("True");
			
		session.getTransaction().commit();
		session.close();
		return "success";
}
	//����һ����remark�Ĳ�����ж�	
	public String checkChangeForR() throws Exception{
		System.out.println(checked);
		System.out.println(remark);
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		Options op;
		Answer a;
		Problem p;
		//��ͬ�����õ��ǲ�ͬ�������������sessionӦ��Ҳ�ǲ�ͬ��
		surveyId = ActionContext.getContext().getSession().get("surveyId").toString();
		Criteria c = session.createCriteria(Options.class).
				add(Restrictions.eq("id", optionId));
		op = (Options)c.list().get(0);//����ѡ��Ŷ�Ӧ��ѡ��ȡ����
		
		Criteria c1 = session.createCriteria(Problem.class).
				add(Restrictions.eq("id", op.problem.id));
		p = (Problem)c1.list().get(0);
		
		//�˴�Ӧ���ټ�һ��������survey.id���ж� 
		Criteria c2 = session.createCriteria(Answer.class)
				.add(Restrictions.eq("problem.id", op.problem.id))
				.add(Restrictions.eq("survey.id", surveyId));
		
		Criteria c3 = session.createCriteria(Survey.class)
				.add(Restrictions.eq("id", surveyId));
		survey = (Survey)c3.list().get(0);
		
		List l = c2.list(); 
		if (!l.isEmpty()) {
			a = (Answer)c2.list().get(0);	
			//remark = a.getRemark();
			//error???
		}
		else {
			a=null;
		}

		if (checked) {
			if (a==null) {//��û��ѡ�������
				a=new Answer();
				a.setSurvey(survey);
				a.setProblem(p);
				a.setOptions(op);
				session.save(a);
				this.status="���ѡ��ɹ�";
			}
			else {//������Ѿ�����һ���ˣ���Ҫ���´�(ֱ�Ӹ��£������жϣ�������ɾ�����ٲ���)
					session.delete(a);
					a=new Answer();
					a.setSurvey(survey);
					a.setProblem(p);
					a.setOptions(op);
					if (remark!=null) {
						a.setRemark(remark);
					}
					session.save(a);
					this.status="���ѡ��ɹ�";
				
			}
		}
		else {
			if (a!=null) {
				session.delete(a);
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
		//��ͬ�����õ��ǲ�ͬ�������������sessionӦ��Ҳ�ǲ�ͬ��
		surveyId = ActionContext.getContext().getSession().get("surveyId").toString();
		Criteria c = session.createCriteria(Options.class).
				add(Restrictions.eq("id", optionId));
		op = (Options)c.list().get(0);//����ѡ��Ŷ�Ӧ��ѡ��ȡ����
		
		Criteria c1 = session.createCriteria(Problem.class).
				add(Restrictions.eq("id", op.problem.id));
		p = (Problem)c1.list().get(0);
		
		//�˴�Ӧ���ټ�һ��������survey.id���ж� 
		Criteria c2 = session.createCriteria(Answer.class)
				.add(Restrictions.eq("problem.id", op.problem.id))
				.add(Restrictions.eq("survey.id", surveyId))
				.add(Restrictions.eq("options.id", optionId));
		
		Criteria c3 = session.createCriteria(Survey.class)
				.add(Restrictions.eq("id", surveyId));
		survey = (Survey)c3.list().get(0);
		if (!c2.list().isEmpty()) {
			a = (Answer)c2.list().get(0);
			remark = a.getRemark();
		}
		else {
			a=null;
		}
		if (checked == false) {
			System.out.println("False");
			if (a==null) {
				this.status="���ѡ��֮ǰûѡ��";
				return "success";
			}
			//֮ǰѡ�ˣ�������ɾ���ˣ���ʱӦ�ðѶ�Ӧanswerɾ��
			this.status="ɾ���ɹ�";
			session.delete(a);//����û��ָ��answer���ܹ�ɾ���ɹ���
			
		}
		else {
			System.out.println("True");
			if (a!=null) {
				this.status="���ѡ���Ѿ�ѡ����";
				return "success";
			}
			//�´𰸣�����
			a=new Answer();
			a.setProblem(p);
			a.setOptions(op);
			a.setSurvey(survey);
			a.setRemark(remark);
			session.save(a);
			this.status="���ѡ��ɹ�";
//			a.setSurvey(s);
		}
		session.getTransaction().commit();
		session.close();
		return "success";
	}
public String saveRemarkForR() throws Exception{
	System.out.println(optionId);
	System.out.println(remark);
	Session session = model.Util.sessionFactory.openSession();
	session.beginTransaction();
	Options op;
	Answer a;
	Problem p;
	//��ͬ�����õ��ǲ�ͬ�������������sessionӦ��Ҳ�ǲ�ͬ��
	surveyId = ActionContext.getContext().getSession().get("surveyId").toString();
	Criteria c = session.createCriteria(Options.class).
			add(Restrictions.eq("id", optionId));
	op = (Options)c.list().get(0);//����ѡ��Ŷ�Ӧ��ѡ��ȡ����
	
	Criteria c1 = session.createCriteria(Problem.class).
			add(Restrictions.eq("id", op.problem.id));
	p = (Problem)c1.list().get(0);
	
	//�˴�Ӧ���ټ�һ��������survey.id���ж� 
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

	if (remark!=null) {//��������˱�ע
		if (a==null) {//��û��ѡ�������
			a=new Answer();
			a.setSurvey(survey);
			a.setProblem(p);
			a.setOptions(op);
			a.setRemark(remark);
			session.save(a);
			this.status="���ѡ��ɹ�";
		}
		else {//������Ѿ�����һ���ˣ���Ҫ���´�(ֱ�Ӹ��£������жϣ�������ɾ�����ٲ���)
				session.delete(a);
				a=new Answer();
				a.setSurvey(survey);
				a.setProblem(p);
				a.setOptions(op);
				a.setRemark(remark);
				session.save(a);
				this.status="���ѡ��ɹ�";
			
		}
	}
		System.out.println("True");
		
	session.getTransaction().commit();
	session.close();
	return "success";
}

public String saveRemarkForC() throws Exception{
	System.out.println(optionId);
	System.out.println(remark);
	Session session = model.Util.sessionFactory.openSession();
	session.beginTransaction();
	Options op;
	Answer a;
	Problem p;
	//��ͬ�����õ��ǲ�ͬ�������������sessionӦ��Ҳ�ǲ�ͬ��
	surveyId = ActionContext.getContext().getSession().get("surveyId").toString();
	Criteria c = session.createCriteria(Options.class).
			add(Restrictions.eq("id", optionId));
	op = (Options)c.list().get(0);//����ѡ��Ŷ�Ӧ��ѡ��ȡ����
	
	Criteria c1 = session.createCriteria(Problem.class).
			add(Restrictions.eq("id", op.problem.id));
	p = (Problem)c1.list().get(0);
	
	//�˴�Ӧ���ټ�һ��������survey.id���ж� 
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
	if (remark != null) {
//		if (a==null) {
//			this.status="���ѡ��֮ǰûѡ��";
//			return "success";
//		}
//		//֮ǰѡ�ˣ�������ɾ���ˣ���ʱӦ�ðѶ�Ӧanswerɾ��
//		this.status="ɾ���ɹ�";
//		session.delete(a);//����û��ָ��answer���ܹ�ɾ���ɹ���
//		
//	}
//	else {
		System.out.println("True");
		if (a==null) {//���ѡ�ûѡ��������ֱ��������remark��ֱ�Ӳ��룻
			a=new Answer();
			a.setProblem(p);
			a.setOptions(op);
			a.setSurvey(survey);
			a.setRemark(remark);
			session.save(a);
			this.status="���ѡ��ɹ�";
		}
		else {
			//
			session.delete(a);
			a=new Answer();
			a.setProblem(p);
			a.setOptions(op);
			a.setSurvey(survey);
			a.setRemark(remark);
			session.save(a);
			this.status="���ѡ��ɹ�";
		}
		
	}
	session.getTransaction().commit();
	session.close();
	return "success";
}
	public String submit() throws Exception{
		System.out.println(name+hTel+tel+quX+xiangJ+cun+zuD+hao);
		Session session = model.Util.sessionFactory.openSession();
		session.beginTransaction();
		surveyId = ActionContext.getContext().getSession().get("surveyId").toString();
		Address address = new Address();
		address.setCun(cun);
		address.setHao(hao);
		address.setQuX(quX);
		address.setXiangJ(xiangJ);
		address.setZuD(zuD);
		session.save(address);//��Ҫ�Ȱѵ�ַ����
		Criteria c = session.createCriteria(Survey.class).add(Restrictions.eq("id", surveyId));
		survey = (Survey)c.uniqueResult();
		User user = new User();
		user.setAddress(address);//
		user.sethTel(hTel);
		user.setName(name);
		user.setSurvey(survey);//
		user.setTel(tel);
		session.save(user);
		session.getTransaction().commit();
		session.close();
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String gethTel() {
		return hTel;
	}
	public void sethTel(String hTel) {
		this.hTel = hTel;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getQuX() {
		return quX;
	}
	public void setQuX(String quX) {
		this.quX = quX;
	}
	public String getXiangJ() {
		return xiangJ;
	}
	public void setXiangJ(String xiangJ) {
		this.xiangJ = xiangJ;
	}
	public String getCun() {
		return cun;
	}
	public void setCun(String cun) {
		this.cun = cun;
	}
	public String getZuD() {
		return zuD;
	}
	public void setZuD(String zuD) {
		this.zuD = zuD;
	}
	public String getHao() {
		return hao;
	}
	public void setHao(String hao) {
		this.hao = hao;
	}
	
	

}
