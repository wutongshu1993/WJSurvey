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
public int id;//小问题编号
public int pid;//大问题编号
public String title;
public int type;//题目类型（单选多选，1为单选，2为多选，3为填空）
//public int kind;//题目属于哪部分；（1为基本情况，2为地震相关情况，3为地震受伤情况，4为地震受灾情况，5为救治情况，6为抗震减灾认知调查）
public String img;//题目对应图片的地址
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
//public int getKind() {
//	return kind;
//}
//public void setKind(int kind) {
//	this.kind = kind;
//}
public String getImg() {
	return img;
}
public void setImg(String img) {
	this.img = img;
}


}
