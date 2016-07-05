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
 * 导出所有问卷excel工具类
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
		cell.setCellValue("姓名");
		cell = row0.createCell(1);
		cell.setCellValue("电话");
		cell = row0.createCell(2);
		cell.setCellValue("家庭电话");
		cell = row0.createCell(3);
		cell.setCellValue("区/县");
		cell = row0.createCell(4);
		cell.setCellValue("乡/街道");
		cell = row0.createCell(5);
		cell.setCellValue("村");
		cell = row0.createCell(6);
		cell.setCellValue("组/队");
		cell = row0.createCell(7);
		cell.setCellValue("号");
		for(int i=0;i<len*2;i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i+8);
			cell.setCellValue("第"+pid+"题答案");
			cell = row0.createCell(i+1+8);
			cell.setCellValue("第"+pid+"题备注");
		}
		
		for (int i = 0; i < results.size(); i++) {
			HSSFRow row = sheet.createRow(i+1);
			//将用户的身份信息写入前8个单元格中
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
			
			//对试题答案的导入(需要考虑如果是复选框的话，需要把所有答案全导入进去)
			for(int j=0;j<results.get(i).size()*2;){
				int temp = results.get(i).get(j/2).getProblem().getId();
				int id = (temp-13)*2+8;//因为problem表中ID是从13开始取值的，13-8=5
				cell = row.createCell(id);
				if (results.get(i).get(j/2).getProblem().getType()==2) {
					StringBuilder builder = new StringBuilder();
					StringBuilder remark = new StringBuilder();
					int tempPid = results.get(i).get(j/2).getProblem().getId();
					int tempPid2 = results.get(i).get(j/2).getProblem().getId();
					while (j<results.get(i).size()*2&&results.get(i).get(j/2).getProblem().getType()==2 && tempPid==tempPid2 ) {//复选框
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
					//对复选框的备注的导入需要注意，因为之前已经+2了，所以现在需要-2
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
//	exportPath = util.Util.RootPath+util.Util.ResultsOfAll+"所有问卷结果统计.xls";
	try {
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("application/x-download");
//		String filedisplay = "所有问卷结果统计.xls";
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
		cell.setCellValue("问卷编号");
		cell = row0.createCell(1);
		cell.setCellValue("问卷时间");
		cell = row0.createCell(2);
		cell.setCellValue("姓名");
		cell = row0.createCell(3);
		cell.setCellValue("电话");
		cell = row0.createCell(4);
		cell.setCellValue("家庭电话");
		cell = row0.createCell(5);
		cell.setCellValue("区/县");
		cell = row0.createCell(6);
		cell.setCellValue("乡/街道");
		cell = row0.createCell(7);
		cell.setCellValue("村");
		cell = row0.createCell(8);
		cell.setCellValue("组/队");
		cell = row0.createCell(9);
		cell.setCellValue("号");
		for(int i=0;i<len*2;i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i+10);
			cell.setCellValue("第"+pid+"题答案");
			cell = row0.createCell(i+1+10);
			cell.setCellValue("第"+pid+"题备注");
		}
		
		for (int i = 0; i < results.size(); i++) {
			HSSFRow row = sheet.createRow(i+1);
			//将问卷信息写入前两行
			cell = row.createCell(0);
			cell.setCellValue(surveys.get(i).getId());
			cell = row.createCell(1);
			cell.setCellValue(surveys.get(i).getTime());
			//将用户的身份信息写入前8个单元格中
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
			
			//对试题答案的导入(需要考虑如果是复选框的话，需要把所有答案全导入进去)
			for(int j=0;j<results.get(i).size()*2;){
				int temp = results.get(i).get(j/2).getProblem().getId();
				int id = (temp-13)*2+10;//因为problem表中ID是从13开始取值的，13-8=5
				cell = row.createCell(id);
				if (results.get(i).get(j/2).getProblem().getType()==2) {
					StringBuilder builder = new StringBuilder();
					StringBuilder remark = new StringBuilder();
					int tempPid = results.get(i).get(j/2).getProblem().getId();
					int tempPid2 = results.get(i).get(j/2).getProblem().getId();
					while (j<results.get(i).size()*2&&results.get(i).get(j/2).getProblem().getType()==2 && tempPid==tempPid2 ) {//复选框
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
					//对复选框的备注的导入需要注意，因为之前已经+2了，所以现在需要-2
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
//	exportPath = util.Util.RootPath+util.Util.ResultsOfAll+"所有问卷结果统计.xls";
	try {
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("application/x-download");
//		String filedisplay = "所有问卷结果统计.xls";
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
