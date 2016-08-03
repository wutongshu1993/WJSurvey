package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
//		HSSFRow row0 = sheet.createRow(0);
//		HSSFCell cell = row0.createCell(0);
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

/**
 * 将多选题每一项单独一列，每个多选题的选项默认都有备注
 * @param title
 * @param problems：所有的问题
 * @param users：所有的调查者
 * @param surveys：针对所有调查者的，所有的调查（每个被调查者的answer都是按照problem的id进行排序的）
 * @param results：所有用户的所有答案
 * @param out
 */
public static void exportExcel3 (String title,List<Problem> problems,List<User> users,List<Survey> surveys,List<List<Answer>> results, OutputStream out){
	int len = problems.size();
//  HSSFWorkbook workbook = new HSSFWorkbook();
//	HSSFSheet sheet = workbook.createSheet();	
	XSSFWorkbook workbook = new XSSFWorkbook();
	XSSFSheet sheet = workbook.createSheet();
	try{
//		HSSFRow row0 = sheet.createRow(0);
//		HSSFCell cell = row0.createCell(0);
		XSSFRow row0  = sheet.createRow(0);
		XSSFCell cell = row0.createCell(0);
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
		for(int i=0;i<32*2;i=i+2){//1-18题（第1~32小题）
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i+10);
			cell.setCellValue("第"+pid+"题答案");
			cell = row0.createCell(i+1+10);
			cell.setCellValue("第"+pid+"题备注");
		}
		//19题多选题 5个选项（ 74~83）
		for(int i=74;i<84;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("第19题-选项"+((i-73+1)/2));
			cell = row0.createCell(i+1);
			cell.setCellValue("第19题-备注"+((i-73+1)/2));
		}
		
		//20-22题，20是第33小题（从0开始计数）（20-22共有7个题） 最后一题的备注为第84~97个单元格
		for(int i=33*2;i<40*2;i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i-33*2+84);
			cell.setCellValue("第"+pid+"题答案");
			cell = row0.createCell(i-33*2+84+1);
			cell.setCellValue("第"+pid+"题备注");
		}
		//23题多选题，19个选项 （第40小题）98~135
		for(int i=98;i<136;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("第"+23+"题-选项"+((i-97+1)/2));
			cell = row0.createCell(i+1);
			cell.setCellValue("第"+23+"题-备注"+((i-97+1)/2));
		}
		
		
		//24题多选题 有5个选项，都有备注(从136~145个单元格，第41小题)
		for(int i=136;i<146;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("第"+24+"题-选项"+((i-135+1)/2));
			cell = row0.createCell(i+1);
			cell.setCellValue("第"+24+"题-备注"+((i-135+1)/2));
		}
		
		//25题 多选题 7个选项（从146~159，第42小题）
		for(int i=146;i<160;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("第"+25+"题-选项"+((i-145+1)/2));
			cell = row0.createCell(i+1);
			cell.setCellValue("第"+25+"题-备注"+((i-145+1)/2));
		}
		
		//26 单选题 2个小题（第43~44小题）
		for(int i=160;i<164;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("第26题答案");
			cell = row0.createCell(i+1);
			cell.setCellValue("第26题备注");
		}
		
		//27题前3个单选小题（45~47 从164个单元格开始， 169结束 ）
		for(int i=45*2;i<48*2;i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i-45*2+164);
			cell.setCellValue("第"+pid+"题答案");
			cell = row0.createCell(i-45*2+164+1);
			cell.setCellValue("第"+pid+"题备注");
		}
		//27题第4个小题是多选  8个选项（第48小题，从170~185单元格开始）
		for(int i=170;i<186;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("第"+27+"题-选项"+((i-169+1)/2));
			cell = row0.createCell(i+1);
			cell.setCellValue("第"+27+"题-备注"+((i-169+1)/2));
		}
		
		//////////
		//第27题第5小问开始，至44结束（第49小题到79小题,从第186个单元格开始 ，247结束）
		for(int i=49*2;i<80*2; i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i-49*2+186);
			cell.setCellValue("第"+pid+"题答案");
			cell = row0.createCell(i-49*2+186+1);
			cell.setCellValue("第"+pid+"题备注");
		}
		//第45题 5个选项，都没有备注
		for(int i=248;i<258;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("第"+45+"题-选项"+((i-247+1)/2));
			cell = row0.createCell(i+1);
			cell.setCellValue("第"+45+"题-备注"+((i-247+1)/2));
		}
		//第46~85题  81小题到120小题 从258~337开始
		for(int i=81*2;i<121*2; i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i-81*2+258);
			cell.setCellValue("第"+pid+"题答案");
			cell = row0.createCell(i-81*2+258+1);
			cell.setCellValue("第"+pid+"题备注");
		}
		//下面开始写入被调查者的问卷信息
		for (int i = 0; i < results.size(); i++) {
			XSSFRow row = sheet.createRow(i+1);
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
			
			//对试题答案的导入(需要考虑如果是复选框的话，需要依次把每个选项导入到对应的列)
			for(int j=0;j<338;){//因为一共有337列T T
				if(j<results.get(i).size()*2){
				Answer a = results.get(i).get(j/2);
				Problem p = a.getProblem();
				int id = p.getId();
				int pid = p.getPid();
				//1-18题答案的写入
				if(id>=13 && id<45){
					cell = row.createCell((id-13)*2+10);
					if(results.get(i).get(j/2).getOptions()!=null){
						String value = results.get(i).get(j/2).getOptions().getValue();
						cell.setCellValue(value);
						}
					cell = row.createCell((id-13)*2+11);
					cell.setCellValue(results.get(i).get(j/2).getRemark());
					j=j+2;
				}
				//19题
				else if(id==45){
					if (a.getOptions()!=null) {
						String ans = a.getOptions().getValue();
						cell = row.createCell(a.getOptions().getId()-41+(a.getOptions().getId()-115));
						cell.setCellValue(ans);
						
					}
					j=j+2;
					
				}
				//20~22
				else if(id>=46&&id<53){
					cell = row.createCell((id-46)*2+84);
					if(results.get(i).get(j/2).getOptions()!=null){
						String value = results.get(i).get(j/2).getOptions().getValue();
						cell.setCellValue(value);
						}
					cell = row.createCell((id-46)*2+84+1);
					cell.setCellValue(results.get(i).get(j/2).getRemark());
					j=j+2;
				}
//				//23////////
				else if(id==53){
					if (a.getOptions()!=null) {
						String ans = a.getOptions().getValue();
						cell = row.createCell(a.getOptions().getId()-41+(a.getOptions().getId()-139));
						cell.setCellValue(ans);
						cell = row.createCell(a.getOptions().getId()-41+1+(a.getOptions().getId()-139));
						cell.setCellValue(a.getRemark());
							
						
					}
					j=j+2;
				}
//				//24  每个选项都有备注
				else if(id==54){
					if (a.getOptions()!=null) {
						String ans = a.getOptions().getValue();
						cell = row.createCell(a.getOptions().getId()-22+(a.getOptions().getId()-158));
						cell.setCellValue(ans);
						cell = row.createCell(a.getOptions().getId()-22+1+(a.getOptions().getId()-158));
						cell.setCellValue(a.getRemark());
					}
					j=j+2;
				}
//				//25 多选 最后一个选项有备注
				else if(id==55){
					if (a.getOptions()!=null) {
						String ans = a.getOptions().getValue();
						cell = row.createCell(a.getOptions().getId()-17+(a.getOptions().getId()-163));
						cell.setCellValue(ans);
						cell = row.createCell(a.getOptions().getId()-17+1+(a.getOptions().getId()-163));
						cell.setCellValue(a.getRemark());
						
						
					}
					j=j+2;
				}
//				//26 和27的前3个 单选
				else if(id>=56 && id<=60){
					cell = row.createCell((id-56)*2+160);
					if(results.get(i).get(j/2).getOptions()!=null){
						String value = results.get(i).get(j/2).getOptions().getValue();
						cell.setCellValue(value);
						}
					cell = row.createCell((id-56)*2+160+1);
					cell.setCellValue(results.get(i).get(j/2).getRemark());
					j=j+2;
				}
//				//27第4个 多选，最后一个选项有备注
				else if(id==61){
					if (a.getOptions()!=null) {
						String ans = a.getOptions().getValue();
						cell = row.createCell(a.getOptions().getId()-25+(a.getOptions().getId()-195));
						cell.setCellValue(ans);
						cell = row.createCell(a.getOptions().getId()-25+1+(a.getOptions().getId()-195));
						cell.setCellValue(a.getRemark());
						
					}
					j=j+2;
				}
//				//27第5问至44 单选
				else if(id>=62 && id<=92){
					cell = row.createCell((id-62)*2+186);
					if(results.get(i).get(j/2).getOptions()!=null){
						String value = results.get(i).get(j/2).getOptions().getValue();
						cell.setCellValue(value);
						}
					cell = row.createCell((id-62)*2+186+1);
					cell.setCellValue(results.get(i).get(j/2).getRemark());
					j=j+2;
				}
//				//45 多选  ，没有备注
				else if(id == 93){
					if (a.getOptions()!=null) {
						String ans = a.getOptions().getValue();
						cell = row.createCell(a.getOptions().getId()-47+(a.getOptions().getId()-295));
						cell.setCellValue(ans);
					}
					j=j+2;
				}
//				//46~88题 单选
				else if(id >=94 && id<=133){
					cell = row.createCell((id-94)*2+258);
					if(results.get(i).get(j/2).getOptions()!=null){
						String value = results.get(i).get(j/2).getOptions().getValue();
						cell.setCellValue(value);
						}
					cell = row.createCell((id-94)*2+258+1);
					cell.setCellValue(results.get(i).get(j/2).getRemark());
					j=j+2;
				}
				else {
					j=j+2;
				}
				
			}
				else {
					break;
				}
			}
		}
		}
	
	catch(Exception e){
		e.printStackTrace();
	}
	try {
		workbook.write(out);
		workbook.close();
		out.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void exportExcel4 (String title,List<Problem> problems,List<User> users,List<Survey> surveys,List<List<Answer>> results, OutputStream out){
	int len = problems.size();
//  HSSFWorkbook workbook = new HSSFWorkbook();
//	HSSFSheet sheet = workbook.createSheet();	
	XSSFWorkbook workbook = new XSSFWorkbook();
	XSSFSheet sheet = workbook.createSheet();
	try{
//		HSSFRow row0 = sheet.createRow(0);
//		HSSFCell cell = row0.createCell(0);
		XSSFRow row0  = sheet.createRow(0);
		XSSFCell cell = row0.createCell(0);
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
		for(int i=0;i<32*2;i=i+2){//1-18题（第1~32小题）
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i+10);
			cell.setCellValue("第"+pid+"题答案");
			cell = row0.createCell(i+1+10);
			cell.setCellValue("第"+pid+"题备注");
		}
		//19题多选题 5个选项（没有备注 74开始）
		for(int i=74;i<79;i++){
			cell = row0.createCell(i);
			cell.setCellValue("第19题-选项"+(i-73));
		}
		
		//20-22题，20是第33小题（从0开始计数）（20-22共有7个题） 最后一题的备注为第92个单元格
		for(int i=33*2;i<40*2;i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i-33*2+79);
			cell.setCellValue("第"+pid+"题答案");
			cell = row0.createCell(i-33*2+79+1);
			cell.setCellValue("第"+pid+"题备注");
		}
		//23题多选题，19个选项 最后一个有备注（第40小题）
		for(int i=93;i<112;i++){
			cell = row0.createCell(i);
			cell.setCellValue("第"+23+"题-选项"+(i-92));
		}
		cell = row0.createCell(112);
		cell.setCellValue("第"+23+"题-备注");
		
		//24题多选题 有5个选项，都有备注(从113个单元格开始，第41小题)
		for(int i=113;i<123;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("第"+24+"题-选项"+((i-112+1)/2));
			cell = row0.createCell(i+1);
			cell.setCellValue("第"+24+"题-备注"+((i-112+1))/2);
		}
		
		//25题 多选题 7个选项，最后一个有备注（从123开始，第42小题）
		for(int i=123;i<130;i++){
			cell = row0.createCell(i);
			cell.setCellValue("第"+25+"题-选项"+(i-122));
		}
		cell = row0.createCell(130);
		cell.setCellValue("第"+25+"题-备注");
		
		//26 单选题 2个小题（第43~44小题）
		for(int i=131;i<135;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("第26题答案");
			cell = row0.createCell(i+1);
			cell.setCellValue("第26题备注");
		}
		
		//27题前3个单选小题（45~47 从135个单元格开始， 140结束 ）
		for(int i=45*2;i<48*2;i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i-45*2+135);
			cell.setCellValue("第"+pid+"题答案");
			cell = row0.createCell(i-45*2+135+1);
			cell.setCellValue("第"+pid+"题备注");
		}
		//27题第4个小题是多选  8个选项 最后一个有备注（第48小题，从141个单元格开始）
		for(int i=141;i<149;i++){
			cell = row0.createCell(i);
			cell.setCellValue("第"+27+"题-选项"+(i-140));
		}
		cell = row0.createCell(149);
		cell.setCellValue("第"+27+"题-备注");
		//////////
		//第27题第5小问开始，至44结束（第49小题到79小题,从第150个单元格开始 ，211结束）
		for(int i=49*2;i<80*2; i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i-49*2+150);
			cell.setCellValue("第"+pid+"题答案");
			cell = row0.createCell(i-49*2+150+1);
			cell.setCellValue("第"+pid+"题备注");
		}
		//第45题 5个选项，都没有备注
		for(int i=212;i<217;i++){
			cell = row0.createCell(i);
			cell.setCellValue("第"+45+"题-选项"+(i-211));
		}
		//第46~85题  81小题到120小题 从217开始
		for(int i=81*2;i<121*2; i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i-81*2+217);
			cell.setCellValue("第"+pid+"题答案");
			cell = row0.createCell(i-81*2+217+1);
			cell.setCellValue("第"+pid+"题备注");
		}
		//下面开始写入被调查者的问卷信息
		for (int i = 0; i < results.size(); i++) {
			XSSFRow row = sheet.createRow(i+1);
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
			
			//对试题答案的导入(需要考虑如果是复选框的话，需要依次把每个选项导入到对应的列)
			for(int j=0;j<301;){//因为一共有300列T T
				if(j<results.get(i).size()*2){
				Answer a = results.get(i).get(j/2);
				Problem p = a.getProblem();
				int id = p.getId();
				int pid = p.getPid();
				//1-18题答案的写入
				if(id>=13 && id<45){
					cell = row.createCell((id-13)*2+10);
					if(results.get(i).get(j/2).getOptions()!=null){
						String value = results.get(i).get(j/2).getOptions().getValue();
						cell.setCellValue(value);
						}
					cell = row.createCell((id-13)*2+11);
					cell.setCellValue(results.get(i).get(j/2).getRemark());
					j=j+2;
				}
				//19题
				else if(id==45){
					if (a.getOptions()!=null) {
						String ans = a.getOptions().getValue();
						cell = row.createCell(a.getOptions().getId()-41);
						cell.setCellValue(ans);
						j=j+1;
					}
					
				}
				//20~22
				else if(id>=46&&id<53){
					cell = row.createCell((id-46)*2+79);
					if(results.get(i).get(j/2).getOptions()!=null){
						String value = results.get(i).get(j/2).getOptions().getValue();
						cell.setCellValue(value);
						}
					cell = row.createCell((id-13)*2+11);
					cell.setCellValue(results.get(i).get(j/2).getRemark());
					j=j+2;
				}
				//23////////
				else if(id==53){
					if (a.getOptions()!=null) {
						String ans = a.getOptions().getValue();
						cell = row.createCell(a.getOptions().getId()-46);
						cell.setCellValue(ans);
						j=j+1;
						if(a.getRemark()!=null){
							cell = row.createCell(a.getOptions().getId()-46+1);
							cell.setCellValue(a.getRemark());
							j=j+1;
						}
					}
				}
				//24  每个选项都有备注
				else if(id==54){
					if (a.getOptions()!=null) {
						String ans = a.getOptions().getValue();
						cell = row.createCell(a.getOptions().getId()-46);
						cell.setCellValue(ans);
						cell = row.createCell(a.getOptions().getId()-46+1);
						cell.setCellValue(a.getRemark());
						j=j+2;
					}
				}
				//25 多选 最后一个选项有备注
				else if(id==55){
					if (a.getOptions()!=null) {
						String ans = a.getOptions().getValue();
						cell = row.createCell(a.getOptions().getId()-40);
						cell.setCellValue(ans);
						j=j+1;
						if(a.getRemark()!=null){
							cell = row.createCell(a.getOptions().getId()-40+1);
							cell.setCellValue(a.getRemark());
							j=j+1;
						}
					}
				}
				//26 和27的前3个 单选
				else if(id>=56 && id<=60){
					cell = row.createCell((id-56)*2+131);
					if(results.get(i).get(j/2).getOptions()!=null){
						String value = results.get(i).get(j/2).getOptions().getValue();
						cell.setCellValue(value);
						}
					cell = row.createCell((id-56)*2+131+1);
					cell.setCellValue(results.get(i).get(j/2).getRemark());
					j=j+2;
				}
				//27第4个 多选，最后一个选项有备注
				else if(id==61){
					if (a.getOptions()!=null) {
						String ans = a.getOptions().getValue();
						cell = row.createCell(a.getOptions().getId()-54);
						cell.setCellValue(ans);
						j=j+1;
						if(a.getRemark()!=null){
							cell = row.createCell(a.getOptions().getId()-54+1);
							cell.setCellValue(a.getRemark());
							j=j+1;
						}
					}
				}
				//27第5问至44 单选
				else if(id>=62 && id<=92){
					cell = row.createCell((id-62)*2+150);
					if(results.get(i).get(j/2).getOptions()!=null){
						String value = results.get(i).get(j/2).getOptions().getValue();
						cell.setCellValue(value);
						}
					cell = row.createCell((id-62)*2+150+1);
					cell.setCellValue(results.get(i).get(j/2).getRemark());
					j=j+2;
				}
				//45 多选  ，没有备注
				else if(id == 93){
					if (a.getOptions()!=null) {
						String ans = a.getOptions().getValue();
						cell = row.createCell(a.getOptions().getId()-83);
						cell.setCellValue(ans);
						j=j+1;
						
					}
				}
				//46~88题 单选
				else if(id >=94 && id<=133){
					cell = row.createCell((id-94)*2+217);
					if(results.get(i).get(j/2).getOptions()!=null){
						String value = results.get(i).get(j/2).getOptions().getValue();
						cell.setCellValue(value);
						}
					cell = row.createCell((id-94)*2+217+1);
					cell.setCellValue(results.get(i).get(j/2).getRemark());
					j=j+2;
				}
				else {
					j=j+2;
				}
//				cell = row.createCell(id);
				/*if (results.get(i).get(j/2).getProblem().getType()==2) {
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
				}*/
				
				/*if(results.get(i).get(j/2).getOptions()!=null){
				String value = builder.toString()!=""?builder.toString():results.get(i).get(j/2).getOptions().getValue();
				cell.setCellValue(value);*/
				
				
			}
				else {
					break;
				}
			}
		}
		}
	
	catch(Exception e){
		e.printStackTrace();
	}
	try {
		workbook.write(out);
		workbook.close();
		out.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
/**
 * 
 * @param title
 * @param problems 所有问题
 * @param users 所有用户
 * @param surveys 所有问卷
 * @param results 所有用户的所有答案
 * @param out
 */
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
