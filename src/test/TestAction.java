package test;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import model.Detail;

public class TestAction {
public String excel(){
	HttpServletResponse response = ServletActionContext.getResponse();
	String filename = "a";
	String sheetName = "sheet1";
	List<Detail> details = new ArrayList<>();
	Detail detail1 = new Detail();
	detail1.setPid(1);
	detail1.setAnswer("��������1");
	detail1.setTitle("11");
	detail1.setRemark("���1");
	Detail detail2 = new Detail();
	detail2.setPid(1);
	detail2.setAnswer("��������2");
	detail2.setTitle("22");
	detail2.setRemark("���2");
	details.add(detail1);
	details.add(detail2);
	List<String> columns = new ArrayList<>();
	columns.add("���");
	columns.add("���");
	columns.add("��");
	columns.add("��ע");
	JxlExcelUtils.exportexcle(response, filename, details, sheetName, columns);
	return "success";
}
}
