package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Options {
@Id
@GeneratedValue
public int id;
@ManyToOne
@JoinColumn(name="pid")
public Problem problem;
public String value;
public boolean edit;//该选项是否可以编辑，true为可以
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
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
public boolean isEdit() {
	return edit;
}
public void setEdit(boolean edit) {
	this.edit = edit;
}

}
