package model;
//每条答案对应的一个完整的信息
public class Detail {
public int pid;//题号
public String title;//题目
public String answer;//答案(将对应optionId的内容转换过来)
public String remark;//备注
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
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}

}
