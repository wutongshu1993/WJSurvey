package model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Survey {

public int id;

public String time;
@Id
@GeneratedValue
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
@Column(length = 100)
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}



}
