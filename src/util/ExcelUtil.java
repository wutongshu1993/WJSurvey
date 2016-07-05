package util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import model.Answer;
import model.Problem;
import model.Survey;
import model.User;
/**
 * ���������ʾ�excel������
 * @author lh
 *
 */
public class ExcelUtil {
public static void exportExcel (String title,List<Problem> problems,List<User> users,List<List<Answer>> results, OutputStream out){
	int len = problems.size();
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
//	exportPath = util.Util.RootPath+util.Util.ResultsOfAll+"�����ʾ���ͳ��.xls";
	try {
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("application/x-download");
//		String filedisplay = "�����ʾ���ͳ��.xls";
//		filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
//		response.addHeader("Content-Disposition", "attachment;filename="+ filedisplay);
//		OutputStream out = response.getOutputStream();
//		OutputStream out = new FileOutputStream(util.Util.RootPath+exportPath);
	
		
//		OutputStream out = new FileOutputStream(exportPath);
		workbook.write(out);
		workbook.close();
		out.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static void exportExcel2 (String title,List<Problem> problems,List<User> users,List<Survey> surveys,List<List<Answer>> results, OutputStream out){
	int len = problems.size();
	HSSFWorkbook workbook = new HSSFWorkbook();
	HSSFSheet sheet = workbook.createSheet();	
	
	try{
		HSSFRow row0 = sheet.createRow(0);
		HSSFCell cell = row0.createCell(0);
		cell.setCellValue("�ʾ���");
		cell = row0.createCell(1);
		cell.setCellValue("�ʾ�ʱ��");
		cell = row0.createCell(2);
		cell.setCellValue("����");
		cell = row0.createCell(3);
		cell.setCellValue("�绰");
		cell = row0.createCell(4);
		cell.setCellValue("��ͥ�绰");
		cell = row0.createCell(5);
		cell.setCellValue("��/��");
		cell = row0.createCell(6);
		cell.setCellValue("��/�ֵ�");
		cell = row0.createCell(7);
		cell.setCellValue("��");
		cell = row0.createCell(8);
		cell.setCellValue("��/��");
		cell = row0.createCell(9);
		cell.setCellValue("��");
		for(int i=0;i<len*2;i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i+10);
			cell.setCellValue("��"+pid+"���");
			cell = row0.createCell(i+1+10);
			cell.setCellValue("��"+pid+"�ⱸע");
		}
		
		for (int i = 0; i < results.size(); i++) {
			HSSFRow row = sheet.createRow(i+1);
			//���ʾ���Ϣд��ǰ����
			cell = row.createCell(0);
			cell.setCellValue(surveys.get(i).getId());
			cell = row.createCell(1);
			cell.setCellValue(surveys.get(i).getTime());
			//���û��������Ϣд��ǰ8����Ԫ����
			cell = row.createCell(2);
			cell.setCellValue(users.get(i).getName());
			cell = row.createCell(3);
			cell.setCellValue(users.get(i).getTel());
			cell = row.createCell(4);
			cell.setCellValue(users.get(i).gethTel());
			cell = row.createCell(5);
			if (users.get(i).getAddress()!=null) {
				cell.setCellValue(users.get(i).getAddress().getQuX());
				cell = row.createCell(6);
				cell.setCellValue(users.get(i).getAddress().getXiangJ());
				cell = row.createCell(7);
				cell.setCellValue(users.get(i).getAddress().getCun());
				cell = row.createCell(8);
				cell.setCellValue(users.get(i).getAddress().getZuD());
				cell = row.createCell(9);
				cell.setCellValue(users.get(i).getAddress().getHao());
			}
			
			//������𰸵ĵ���(��Ҫ��������Ǹ�ѡ��Ļ�����Ҫ�����д�ȫ�����ȥ)
			for(int j=0;j<results.get(i).size()*2;){
				int temp = results.get(i).get(j/2).getProblem().getId();
				int id = (temp-13)*2+10;//��Ϊproblem����ID�Ǵ�13��ʼȡֵ�ģ�13-8=5
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
//	exportPath = util.Util.RootPath+util.Util.ResultsOfAll+"�����ʾ���ͳ��.xls";
	try {
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("application/x-download");
//		String filedisplay = "�����ʾ���ͳ��.xls";
//		filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
//		response.addHeader("Content-Disposition", "attachment;filename="+ filedisplay);
//		OutputStream out = response.getOutputStream();
//		OutputStream out = new FileOutputStream(util.Util.RootPath+exportPath);
	
		
//		OutputStream out = new FileOutputStream(exportPath);
		workbook.write(out);
		workbook.close();
		out.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
