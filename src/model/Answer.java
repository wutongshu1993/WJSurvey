package model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Answer {
@Id
@GeneratedValue
public int id;
@ManyToOne
@JoinColumn(name="wjid")
public Survey survey;
@OneToOne
@JoinColumn(name="pid")
public Problem problem;
@OneToOne
@JoinColumn(name="answer")
public Options options;
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

public Options getOptions() {
	return options;
}
public void setOptions(Options options) {
	this.options = options;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}

}
