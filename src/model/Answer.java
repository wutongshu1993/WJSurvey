package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Answer {
@Id
public int id;
@OneToOne
@JoinColumn(name="wjid")
public Survey survey;
@OneToOne
@JoinColumn(name="pid")
public Problem problem;
public String answer;
public String remark;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Survey getSurvey() {
	return survey;
}
public void setSurvey(Survey survey) {
	this.survey = survey;
}

public Problem getProblem() {
	return problem;
}
public void setProblem(Problem problem) {
	this.problem = problem;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}

}
