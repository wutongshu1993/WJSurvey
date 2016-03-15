package action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.Answer;
import model.Detail;
import model.Options;
import model.Survey;
import model.User;

public class ResultAction {
public String username;
public String password;
public List<User> users;
public String surveyId;
public String time;
public List<Detail> details = new ArrayList<>();
public String excute() {
	return "success";
}
//默认的用户名是admin 密码123456
public String login() {
	System.out.println(username+password);
	if (username.equals("admin") && password.equals("123456")) {
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
	Criteria c2 = session.createCriteria(Answer.class).add(Restrictions.eq("survey.id", surveyId));
	List<Answer> answers = c2.list();
	for (Answer answer : answers) {
		Detail detail = new Detail();
		detail.setTitle(answer.getProblem().title);
		detail.setRemark(answer.getRemark());
		detail.setPid(answer.getProblem().pid);
		detail.setAnswer(answer.getOptions().getValue());
		details.add(detail);
	}
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

}
