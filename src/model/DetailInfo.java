package model;
/**
 * ��detail�Ĺ�ϵ�ǣ��������������ߵĸ�����Ϣ
 * @author lh
 *
 */
public class DetailInfo {
//	public int pid;//���
//	public String title;//��Ŀ
//	public String answer;//��(����ӦoptionId������ת������)
//	public String remark;//��ע
//	public User user;//�û�����ϸ��Ϣ
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
