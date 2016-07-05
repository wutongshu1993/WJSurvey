package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;

import model.Answer;
import model.Detail;
import model.DetailInfo;
import model.Options;
import model.Survey;
import model.User;
import test.JxlExcelUtils;

public class ResultAction {
public String username;
public String password;
public List<User> users;
public String surveyId;
public String time;
public List<Detail> details = new ArrayList<>();
public int currentPage;
public int totalPage;
public int pageSize;
public String excute() {
	
	return "success";
}
//默认的用户名是admin 密码123456
public String login() {
	System.out.println(username+password);
	if (username.equals("admin") && password.equals("123456")) {
		Map session = ActionContext.getContext().getSession();
		session.put("user", username);
		return "success";
	}
	return "error";
}
public String result(){
	Session session = model.Util.sessionFactory.openSession();
	session.beginTransaction();
	Criteria c = session.createCriteria(User.class);
	users = c.list();
	return "success";
}

public String result_list() {
	ActionContext ctx = ActionContext.getContext();       
	HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);   
	String current = request.getParameter("currentPage");
	if (current!=null) {
		currentPage = Integer.parseInt(current);
	}
	pageSize = 20;//20
	Session session = model.Util.openSession();
	session.beginTransaction();
	Query q =session.createQuery("from User u order by u.survey.time desc");
	totalPage = q.list().size()%pageSize==0?q.list().size()/pageSize :q.list().size()/pageSize+1;//总页数
	if (totalPage<1) {
		totalPage=1;
	}
	if(currentPage>totalPage){
		currentPage = totalPage;
	}
	if (currentPage<1) {
		currentPage = 1;
	}
	q.setMaxResults(pageSize);//20
	q.setFirstResult((currentPage-1)*pageSize);//(currentPage-1)*pageSize
	users = q.list();
	return "success";
}
/**
 * 显示某个用户的问卷信息
 * @return
 */
public String detail() throws Exception{
	System.out.println(surveyId);
	Session session = model.Util.sessionFactory.openSession();
	session.beginTransaction();
	Criteria c = session.createCriteria(Survey.class).add(Restrictions.eq("id", surveyId));
	Survey survey = (Survey)c.list().get(0);
	time = survey.time;
	Criteria c2 = session.createCriteria(Answer.class).add(Restrictions.eq("survey.id", surveyId))
			.addOrder(Order.asc("problem.id"));
	List<Answer> answers = c2.list();
	for (Answer answer : answers) {
		Detail detail = new Detail();
		detail.setTitle(answer.getProblem().title);
		detail.setRemark(answer.getRemark());
		detail.setPid(answer.getProblem().pid);
		if (answer.getOptions()!=null) {
			detail.setAnswer(answer.getOptions().getValue());
		}
		
		details.add(detail);
	}
	return "success";
}
/**
 * 将所有信息导出到一张表中，包括用户信息。(但是不是一行一个用户)
 * 
 * @return
 */
public String excelDetail(){
	//System.out.println(surveyId);
	Session session = model.Util.sessionFactory.openSession();
	session.beginTransaction();
	HttpServletResponse response = ServletActionContext.getResponse();
	String filename = "excel";
	String sheetName = "sheet1";
//	String sql = "from User user left join Answer answer with user.survey.id=answer.survey.id";
//	String sql = "select user,answer from User user ,Answer answer where user.survey.id=answer.survey.id";
	String sql = "select new model.DetailInfo(answer,user)from User user ,Answer answer where user.survey.id=answer.survey.id";
	Query query = session.createQuery(sql);

//	List<DetailInfo> list = new ArrayList<>();
	List<DetailInfo> detailInfos = query.list();
//	for(DetailInfo info : detailInfos){
//		list.add(info);
//	}
	
	List<String> columns = new ArrayList<>();
	columns.add("姓名");
	columns.add("电话");
	columns.add("家庭电话");
	columns.add("区/县");
	columns.add("乡/街道");
	columns.add("村");
	columns.add("组/队");
	columns.add("号");
	columns.add("题号");
	columns.add("题干");
	columns.add("答案");
	columns.add("备注");
	JxlExcelUtils.exportexcle2(response, filename, detailInfos, sheetName, columns);
	return "success";
}
/**
 * 把单张表导出到表格(包括用户信息)
 * @return
 */
public String excel(){

	System.out.println(surveyId);
	Session session = model.Util.sessionFactory.openSession();
	session.beginTransaction();
	HttpServletResponse response = ServletActionContext.getResponse();
	String filename = "excel";
	String sheetName = "sheet1";
//	String sql = "from User user left join Answer answer with user.survey.id=answer.survey.id";
//	String sql = "select user,answer from User user ,Answer answer where user.survey.id=answer.survey.id";
	String sql = "select new model.DetailInfo(answer,user)from User user ,Answer answer where user.survey.id="+surveyId+"and user.survey.id=answer.survey.id ";
	Query query = session.createQuery(sql);

//	List<DetailInfo> list = new ArrayList<>();
	List<DetailInfo> detailInfos = query.list();
//	for(DetailInfo info : detailInfos){
//		list.add(info);
//	}
	
	List<String> columns = new ArrayList<>();
	columns.add("姓名");
	columns.add("电话");
	columns.add("家庭电话");
	columns.add("区/县");
	columns.add("乡/街道");
	columns.add("村");
	columns.add("组/队");
	columns.add("号");
	columns.add("题号");
	columns.add("题干");
	columns.add("答案");
	columns.add("备注");
	JxlExcelUtils.exportexcle2(response, filename, detailInfos, sheetName, columns);
	return "success";

}
/**
 * excel的表格导出来（不包括调查者的个人信息）
 * @return
 */
public String excelUserInfo(){

	System.out.println(surveyId);
	Session session = model.Util.sessionFactory.openSession();
	session.beginTransaction();
	HttpServletResponse response = ServletActionContext.getResponse();
	String filename = "excel";
	String sheetName = "sheet1";
	Criteria c2 = session.createCriteria(Answer.class).add(Restrictions.eq("survey.id", surveyId));
	List<Answer> answers = c2.list();
	for (Answer answer : answers) {
		Detail detail = new Detail();
		detail.setTitle(answer.getProblem().title);
		detail.setRemark(answer.getRemark());
		detail.setPid(answer.getProblem().pid);
		if (answer.getOptions()!=null) {
			detail.setAnswer(answer.getOptions().getValue());
		}
		
		details.add(detail);
	}
	List<String> columns = new ArrayList<>();
	columns.add("题号");
	columns.add("题干");
	columns.add("答案");
	columns.add("备注");
	JxlExcelUtils.exportexcle(response, filename, details, sheetName, columns);
	return "success";

}

public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public List<User> getUsers() {
	return users;
}
public void setUsers(List<User> users) {
	this.users = users;
}

public String getSurveyId() {
	return surveyId;
}
public void setSurveyId(String surveyId) {
	this.surveyId = surveyId;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public List<Detail> getDetails() {
	return details;
}
public void setDetails(List<Detail> details) {
	this.details = details;
}
public int getCurrentPage() {
	return currentPage;
}
public void setCurrentPage(int currentPage) {
	this.currentPage = currentPage;
}
public int getTotalPage() {
	return totalPage;
}
public void setTotalPage(int totalPage) {
	this.totalPage = totalPage;
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}

}
