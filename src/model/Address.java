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
public String quX;//区县
public String xiangJ;//乡，街道
public String cun;//村
public String zuD;//组 队
public String hao;//号
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
