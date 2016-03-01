package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Problem {
@Id
@GeneratedValue
public int id;
public String  title;
public boolean hasLittle;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public boolean isHasLittle() {
	return hasLittle;
}
public void setHasLittle(boolean hasLittle) {
	this.hasLittle = hasLittle;
}

}
