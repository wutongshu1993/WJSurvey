package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.httpserver.Authenticator.Success;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import model.Answer;
import model.Detail;
import model.Problem;
import model.Survey;
import model.User;
import util.ExcelUtil;

public class TestAction {
	String exportPath ;	
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
public String excelAll(){
	Session session = model.Util.sessionFactory.openSession();
//	String hql = "select count(*) from Problem";
//	Query query = session.createQuery(hql);
//	List<Object> list = query.list();
//	int len = new Long((Long)query.list().get(0)).intValue();//��ȡһ���ж��ٸ�����
	
	String hql = "from Problem";
	Query query = session.createQuery(hql);
	List<Problem> problems = query.list();
	int len = problems.size();//��ȡһ���ж��ٸ�����
	List<List<Answer>> results = new ArrayList<>();//�����û��Ĵ�
	List<User> users = session.createCriteria(User.class).list();//�����û�
	for(User user : users){
		List<Answer> answers = new ArrayList<>();//һ���û���Ӧ�����д�
		answers = session.createCriteria(Answer.class).add(Restrictions.eq("survey.id", user.survey.getId()))
				.addOrder(Order.asc("problem.id")).list();
//		answers = session.createQuery("from Answer a where a.survey.id="+user.survey.getId()+"order by a.problem.id").list();
		results.add(answers);
	}
	HSSFWorkbook workbook = new HSSFWorkbook();
	HSSFSheet sheet = workbook.createSheet();
	try{
		HSSFRow row0 = sheet.createRow(0);
		HSSFCell cell = row0.createCell(0);
		cell.setCellValue("����");
		cell = row0.createCell(1);
		cell.setCellValue("�绰");
		cell = row0.createCell(2);
		cell.setCellValue("��ͥ�绰");
		cell = row0.createCell(3);
		cell.setCellValue("��/��");
		cell = row0.createCell(4);
		cell.setCellValue("��/�ֵ�");
		cell = row0.createCell(5);
		cell.setCellValue("��");
		cell = row0.createCell(6);
		cell.setCellValue("��/��");
		cell = row0.createCell(7);
		cell.setCellValue("��");
		for(int i=0;i<len*2;i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i+8);
			cell.setCellValue("��"+pid+"���");
			cell = row0.createCell(i+1+8);
			cell.setCellValue("��"+pid+"�ⱸע");
		}
		
		for (int i = 0; i < results.size(); i++) {
			HSSFRow row = sheet.createRow(i+1);
			//���û��������Ϣд��ǰ8����Ԫ����
			cell = row.createCell(0);
			cell.setCellValue(users.get(i).getName());
			cell = row.createCell(1);
			cell.setCellValue(users.get(i).getTel());
			cell = row.createCell(2);
			cell.setCellValue(users.get(i).gethTel());
			cell = row.createCell(3);
			if (users.get(i).getAddress()!=null) {
				cell.setCellValue(users.get(i).getAddress().getQuX());
				cell = row.createCell(4);
				cell.setCellValue(users.get(i).getAddress().getXiangJ());
				cell = row.createCell(5);
				cell.setCellValue(users.get(i).getAddress().getCun());
				cell = row.createCell(6);
				cell.setCellValue(users.get(i).getAddress().getZuD());
				cell = row.createCell(7);
				cell.setCellValue(users.get(i).getAddress().getHao());
			}
			
			//������𰸵ĵ���(��Ҫ��������Ǹ�ѡ��Ļ�����Ҫ�����д�ȫ�����ȥ)
			for(int j=0;j<results.get(i).size()*2;){
				int temp = results.get(i).get(j/2).getProblem().getId();
				int id = (temp-13)*2+8;//��Ϊproblem����ID�Ǵ�13��ʼȡֵ�ģ�13-8=5
				cell = row.createCell(id);
				if (results.get(i).get(j/2).getProblem().getType()==2) {
					StringBuilder builder = new StringBuilder();
					StringBuilder remark = new StringBuilder();
					int tempPid = results.get(i).get(j/2).getProblem().getId();
					int tempPid2 = results.get(i).get(j/2).getProblem().getId();
					while (j<results.get(i).size()*2&&results.get(i).get(j/2).getProblem().getType()==2 && tempPid==tempPid2 ) {//��ѡ��
						builder.append(results.get(i).get(j/2).getOptions().getValue()+" ");
						remark.append(results.get(i).get(j/2).getRemark()==null?"":results.get(i).get(j/2).getRemark()+" ");
						j=j+2;
						if (j<results.get(i).size()*2) {
							tempPid2 = results.get(i).get(j/2).getProblem().getId();
						}
						
					}
					String value = builder.toString()!=" "?builder.toString():results.get(i).get(j/2).getOptions().getValue();
					String remarkValue = remark.toString()!=" "?remark.toString() :" ";
					cell.setCellValue(value);
					cell = row.createCell(id+1);
					//�Ը�ѡ��ı�ע�ĵ�����Ҫע�⣬��Ϊ֮ǰ�Ѿ�+2�ˣ�����������Ҫ-2
//					cell.setCellValue(results.get(i).get((j-2)/2).getRemark());
					cell.setCellValue(remarkValue);
				}
				else {
					if(results.get(i).get(j/2).getOptions()!=null){
						String value = results.get(i).get(j/2).getOptions().getValue();
						cell.setCellValue(value);
						}
					cell = row.createCell(id+1);
					cell.setCellValue(results.get(i).get(j/2).getRemark());
					j=j+2;
				}
				
				/*if(results.get(i).get(j/2).getOptions()!=null){
				String value = builder.toString()!=""?builder.toString():results.get(i).get(j/2).getOptions().getValue();
				cell.setCellValue(value);*/
				
				
			}
			}
		}
	
	catch(Exception e){
		e.printStackTrace();
	}
	
   exportPath = util.Util.RootPath+util.Util.ResultsOfAll+"�����ʾ���ͳ��.xls";
	try {
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("application/x-download");
//		String filedisplay = "�����ʾ���ͳ��.xls";
//		filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
//		response.addHeader("Content-Disposition", "attachment;filename="+ filedisplay);
//		OutputStream out = response.getOutputStream();
//		OutputStream out = new FileOutputStream(util.Util.RootPath+exportPath);
		OutputStream out = new FileOutputStream(exportPath);
		workbook.write(out);
		workbook.close();
		out.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ActionSupport.SUCCESS;
}
/**
 * �����ʾ��ʱ��������ʱ����ʾ���
 * @return
 */
public String excelAll2(){
	Session session = model.Util.sessionFactory.openSession();
	String hql = "from Problem";
	Query query = session.createQuery(hql);
	List<Problem> problems = query.list();
	int len = problems.size();//��ȡһ���ж��ٸ�����
	List<List<Answer>> results = new ArrayList<>();//�����û��Ĵ�
	List<User> users = session.createCriteria(User.class).list();//�����û�
	List<Survey> surveys = new ArrayList<>();//�����û����ʾ���Ϣ
	for(User user : users){
		List<Answer> answers = new ArrayList<>();//һ���û���Ӧ�����д�
		answers = session.createCriteria(Answer.class).add(Restrictions.eq("survey.id", user.survey.getId()))
				.addOrder(Order.asc("problem.id")).list();
//		answers = session.createQuery("from Answer a where a.survey.id="+user.survey.getId()+"order by a.problem.id").list();
		results.add(answers);
		
		Survey survey = (Survey)session.createCriteria(Survey.class).add(Restrictions.eq("id", user.survey.getId())).list().get(0);
		surveys.add(survey);
	}
	String fileName = "���е����ʾ���.xls";
	HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response =ServletActionContext.getResponse();
	try{
		OutputStream os = response.getOutputStream();
		 //���öԻ���
        response.setHeader("Content-disposition", "attachment;filename="+ new String(fileName.getBytes("GB2312"),"ISO-8859-1"));
        //���� MIME(Excel)
        response.setContentType("application/vnd.ms-excel");
        //���ñ���
        response.setCharacterEncoding("UTF-8");
        ExcelUtil.exportExcel2("sheet1", problems,  users, surveys,results, os);
        os.flush();
        os.close();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
	return ActionSupport.SUCCESS;
}
public static void main(String[] args){
	 new TestAction().excelAll2();
}
public String getExportPath() {
	return exportPath;
}
public void setExportPath(String exportPath) {
	this.exportPath = exportPath;
}

}
