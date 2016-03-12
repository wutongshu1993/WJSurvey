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
public int id;//С������
public int pid;//��������
public String title;
public int type;//��Ŀ���ͣ���ѡ��ѡ��1Ϊ��ѡ��2Ϊ��ѡ��3Ϊ��գ�
//public int kind;//��Ŀ�����Ĳ��֣���1Ϊ���������2Ϊ������������3Ϊ�������������4Ϊ�������������5Ϊ���������6Ϊ���������֪���飩
public String img;//��Ŀ��ӦͼƬ�ĵ�ַ
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
