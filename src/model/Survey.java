package model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Survey {
@Id
public String id;

public String time;



@Column(length = 100)
public String getTime() {
	return time;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public void setTime(String time) {
	this.time = time;
}



}
