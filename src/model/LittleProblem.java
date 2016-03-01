package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LittleProblem {
@Id
@GeneratedValue
public int id;
public Problem problem;
public String title;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public Problem getProblem() {
	return problem;
}
public void setProblem(Problem problem) {
	this.problem = problem;
}

public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}

}
