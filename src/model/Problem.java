package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Problem {
@Id
@GeneratedValue
public int id;
public int pid;
public String title;
public int type;//��Ŀ���ͣ���ѡ��ѡ��0Ϊ��ѡ��1Ϊ��ѡ��
@OneToMany
@JoinColumn(name="ltId")
public LittleProblem littleProblem;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public LittleProblem getLittleProblem() {
	return littleProblem;
}
public void setLittleProblem(LittleProblem littleProblem) {
	this.littleProblem = littleProblem;
}
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}


}
