package model;

import java.util.List;

/**
 * һ����Ŀ��������ɺ͸����Ӧ�ļ���ѡ��
 * @author Administrator
 *
 */
public class Item {
private Problem problem;
private List<Options> options;
public Problem getProblem() {
	return problem;
}
public void setProblem(Problem problem) {
	this.problem = problem;
}
public List<Options> getOptions() {
	return options;
}
public void setOptions(List<Options> options) {
	this.options = options;
}

}
