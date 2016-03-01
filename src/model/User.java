package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class User {
@Id
@GeneratedValue
public int id;
public String name;
public String hTel;
public String tel;
public String address;
@OneToOne
@JoinColumn(name="testId")
public Survey survey;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Survey getSurvey() {
	return survey;
}
public void setSurvey(Survey survey) {
	this.survey = survey;
}


}
