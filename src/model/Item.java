package model;

import java.util.List;

/**
 * 一道题目，包括题干和该题对应的几个选项
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
