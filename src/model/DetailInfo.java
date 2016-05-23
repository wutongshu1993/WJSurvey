package model;
/**
 * 与detail的关系是，这个类包括调查者的个人信息
 * @author lh
 *
 */
public class DetailInfo {
//	public int pid;//题号
//	public String title;//题目
//	public String answer;//答案(将对应optionId的内容转换过来)
//	public String remark;//备注
//	public User user;//用户的详细信息
//	public int getPid() {
//		return pid;
//	}
//	public void setPid(int pid) {
//		this.pid = pid;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getAnswer() {
//		return answer;
//	}
//	public void setAnswer(String answer) {
//		this.answer = answer;
//	}
//	public String getRemark() {
//		return remark;
//	}
//	public void setRemark(String remark) {
//		this.remark = remark;
//	}
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//	
Answer answer;
User user;
public Answer getAnswer() {
	return answer;
}
public void setAnswer(Answer answer) {
	this.answer = answer;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public DetailInfo(Answer answer, User user) {
	super();
	this.answer = answer;
	this.user = user;
}

}
