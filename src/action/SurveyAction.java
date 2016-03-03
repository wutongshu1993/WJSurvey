package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import dao.ItemDao;
import dao.ProblemDao;
import impl.ItemDaoImpl;
import impl.ProblemDaoImpl;
import jdk.nashorn.internal.ir.WhileNode;
import model.Bulk;
import model.Item;
import model.Options;
import model.Problem;

public class SurveyAction {
	public Problem problem;
	public Options options;
	public List<Item> items;
	public List<Bulk> bulks;
	
	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Bulk> getBulks() {
		return bulks;
	}

	public void setBulks(List<Bulk> bulks) {
		this.bulks = bulks;
	}

	public String execute() {
		ProblemDao pDao = new ProblemDaoImpl();
		ItemDao iDao = new ItemDaoImpl();
		List<Problem> problems = pDao.getAllProblems();
		items=new ArrayList<Item>();
		Item item1,item2;
		List<Item> items2 = new ArrayList<>();//同一道题的不同小问所构成的集合
		bulks = new ArrayList<>();
		int start=0;
		int end =0;
		int flag=0;//
		for (Problem problem : problems) {
			Item item = iDao.getItem(problem);
			items.add(item);
		}
		while(end<items.size()){
			while (end<items.size()&&items.get(start).getProblem().getPid() == items.get(end).getProblem().getPid()) {
				items2.add(items.get(end));
				end++;
				flag = 1;
			}
//			else { 
				
				List<Item> temp = new ArrayList<>();
				for (Item item : items2) {
					temp.add(item);
				}
				Bulk bulk = new Bulk();
				bulk.setPid(items.get(start).getProblem().pid);
				bulk.setItems(temp);
				bulks.add(bulk);
				start = end;
				items2.clear();
				flag=0;
				
				
//			}
			
		}
//		Map session = ActionContext.getContext().getSession();
//		session.put("items", items);
		return "success";
	}
	
	
}
