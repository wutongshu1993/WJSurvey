package model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {
@Id
@GeneratedValue
public int id;
public String quX;//����
public String xiangJ;//�磬�ֵ�
public String cun;//��
public String zuD;//�� ��
public String hao;//��
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getQuX() {
	return quX;
}
public void setQuX(String quX) {
	this.quX = quX;
}
public String getXiangJ() {
	return xiangJ;
}
public void setXiangJ(String xiangJ) {
	this.xiangJ = xiangJ;
}
public String getCun() {
	return cun;
}
public void setCun(String cun) {
	this.cun = cun;
}
public String getZuD() {
	return zuD;
}
public void setZuD(String zuD) {
	this.zuD = zuD;
}
public String getHao() {
	return hao;
}
public void setHao(String hao) {
	this.hao = hao;
}

}
