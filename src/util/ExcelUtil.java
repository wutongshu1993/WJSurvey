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
//		HSSFRow row0 = sheet.createRow(0);
//		HSSFCell cell = row0.createCell(0);
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

/**
 * ����ѡ��ÿһ���һ�У�ÿ����ѡ���ѡ��Ĭ�϶��б�ע
 * @param title
 * @param problems�����е�����
 * @param users�����еĵ�����
 * @param surveys��������е����ߵģ����еĵ��飨ÿ���������ߵ�answer���ǰ���problem��id��������ģ�
 * @param results�������û������д�
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
		for(int i=0;i<32*2;i=i+2){//1-18�⣨��1~32С�⣩
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i+10);
			cell.setCellValue("��"+pid+"���");
			cell = row0.createCell(i+1+10);
			cell.setCellValue("��"+pid+"�ⱸע");
		}
		//19���ѡ�� 5��ѡ� 74~83��
		for(int i=74;i<84;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("��19��-ѡ��"+((i-73+1)/2));
			cell = row0.createCell(i+1);
			cell.setCellValue("��19��-��ע"+((i-73+1)/2));
		}
		
		//20-22�⣬20�ǵ�33С�⣨��0��ʼ��������20-22����7���⣩ ���һ��ı�עΪ��84~97����Ԫ��
		for(int i=33*2;i<40*2;i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i-33*2+84);
			cell.setCellValue("��"+pid+"���");
			cell = row0.createCell(i-33*2+84+1);
			cell.setCellValue("��"+pid+"�ⱸע");
		}
		//23���ѡ�⣬19��ѡ�� ����40С�⣩98~135
		for(int i=98;i<136;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("��"+23+"��-ѡ��"+((i-97+1)/2));
			cell = row0.createCell(i+1);
			cell.setCellValue("��"+23+"��-��ע"+((i-97+1)/2));
		}
		
		
		//24���ѡ�� ��5��ѡ����б�ע(��136~145����Ԫ�񣬵�41С��)
		for(int i=136;i<146;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("��"+24+"��-ѡ��"+((i-135+1)/2));
			cell = row0.createCell(i+1);
			cell.setCellValue("��"+24+"��-��ע"+((i-135+1)/2));
		}
		
		//25�� ��ѡ�� 7��ѡ���146~159����42С�⣩
		for(int i=146;i<160;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("��"+25+"��-ѡ��"+((i-145+1)/2));
			cell = row0.createCell(i+1);
			cell.setCellValue("��"+25+"��-��ע"+((i-145+1)/2));
		}
		
		//26 ��ѡ�� 2��С�⣨��43~44С�⣩
		for(int i=160;i<164;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("��26���");
			cell = row0.createCell(i+1);
			cell.setCellValue("��26�ⱸע");
		}
		
		//27��ǰ3����ѡС�⣨45~47 ��164����Ԫ��ʼ�� 169���� ��
		for(int i=45*2;i<48*2;i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i-45*2+164);
			cell.setCellValue("��"+pid+"���");
			cell = row0.createCell(i-45*2+164+1);
			cell.setCellValue("��"+pid+"�ⱸע");
		}
		//27���4��С���Ƕ�ѡ  8��ѡ���48С�⣬��170~185��Ԫ��ʼ��
		for(int i=170;i<186;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("��"+27+"��-ѡ��"+((i-169+1)/2));
			cell = row0.createCell(i+1);
			cell.setCellValue("��"+27+"��-��ע"+((i-169+1)/2));
		}
		
		//////////
		//��27���5С�ʿ�ʼ����44��������49С�⵽79С��,�ӵ�186����Ԫ��ʼ ��247������
		for(int i=49*2;i<80*2; i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i-49*2+186);
			cell.setCellValue("��"+pid+"���");
			cell = row0.createCell(i-49*2+186+1);
			cell.setCellValue("��"+pid+"�ⱸע");
		}
		//��45�� 5��ѡ���û�б�ע
		for(int i=248;i<258;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("��"+45+"��-ѡ��"+((i-247+1)/2));
			cell = row0.createCell(i+1);
			cell.setCellValue("��"+45+"��-��ע"+((i-247+1)/2));
		}
		//��46~85��  81С�⵽120С�� ��258~337��ʼ
		for(int i=81*2;i<121*2; i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i-81*2+258);
			cell.setCellValue("��"+pid+"���");
			cell = row0.createCell(i-81*2+258+1);
			cell.setCellValue("��"+pid+"�ⱸע");
		}
		//���濪ʼд�뱻�����ߵ��ʾ���Ϣ
		for (int i = 0; i < results.size(); i++) {
			XSSFRow row = sheet.createRow(i+1);
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
			
			//������𰸵ĵ���(��Ҫ��������Ǹ�ѡ��Ļ�����Ҫ���ΰ�ÿ��ѡ��뵽��Ӧ����)
			for(int j=0;j<338;){//��Ϊһ����337��T T
				if(j<results.get(i).size()*2){
				Answer a = results.get(i).get(j/2);
				Problem p = a.getProblem();
				int id = p.getId();
				int pid = p.getPid();
				//1-18��𰸵�д��
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
				//19��
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
//				//24  ÿ��ѡ��б�ע
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
//				//25 ��ѡ ���һ��ѡ���б�ע
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
//				//26 ��27��ǰ3�� ��ѡ
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
//				//27��4�� ��ѡ�����һ��ѡ���б�ע
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
//				//27��5����44 ��ѡ
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
//				//45 ��ѡ  ��û�б�ע
				else if(id == 93){
					if (a.getOptions()!=null) {
						String ans = a.getOptions().getValue();
						cell = row.createCell(a.getOptions().getId()-47+(a.getOptions().getId()-295));
						cell.setCellValue(ans);
					}
					j=j+2;
				}
//				//46~88�� ��ѡ
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
		for(int i=0;i<32*2;i=i+2){//1-18�⣨��1~32С�⣩
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i+10);
			cell.setCellValue("��"+pid+"���");
			cell = row0.createCell(i+1+10);
			cell.setCellValue("��"+pid+"�ⱸע");
		}
		//19���ѡ�� 5��ѡ�û�б�ע 74��ʼ��
		for(int i=74;i<79;i++){
			cell = row0.createCell(i);
			cell.setCellValue("��19��-ѡ��"+(i-73));
		}
		
		//20-22�⣬20�ǵ�33С�⣨��0��ʼ��������20-22����7���⣩ ���һ��ı�עΪ��92����Ԫ��
		for(int i=33*2;i<40*2;i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i-33*2+79);
			cell.setCellValue("��"+pid+"���");
			cell = row0.createCell(i-33*2+79+1);
			cell.setCellValue("��"+pid+"�ⱸע");
		}
		//23���ѡ�⣬19��ѡ�� ���һ���б�ע����40С�⣩
		for(int i=93;i<112;i++){
			cell = row0.createCell(i);
			cell.setCellValue("��"+23+"��-ѡ��"+(i-92));
		}
		cell = row0.createCell(112);
		cell.setCellValue("��"+23+"��-��ע");
		
		//24���ѡ�� ��5��ѡ����б�ע(��113����Ԫ��ʼ����41С��)
		for(int i=113;i<123;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("��"+24+"��-ѡ��"+((i-112+1)/2));
			cell = row0.createCell(i+1);
			cell.setCellValue("��"+24+"��-��ע"+((i-112+1))/2);
		}
		
		//25�� ��ѡ�� 7��ѡ����һ���б�ע����123��ʼ����42С�⣩
		for(int i=123;i<130;i++){
			cell = row0.createCell(i);
			cell.setCellValue("��"+25+"��-ѡ��"+(i-122));
		}
		cell = row0.createCell(130);
		cell.setCellValue("��"+25+"��-��ע");
		
		//26 ��ѡ�� 2��С�⣨��43~44С�⣩
		for(int i=131;i<135;i=i+2){
			cell = row0.createCell(i);
			cell.setCellValue("��26���");
			cell = row0.createCell(i+1);
			cell.setCellValue("��26�ⱸע");
		}
		
		//27��ǰ3����ѡС�⣨45~47 ��135����Ԫ��ʼ�� 140���� ��
		for(int i=45*2;i<48*2;i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i-45*2+135);
			cell.setCellValue("��"+pid+"���");
			cell = row0.createCell(i-45*2+135+1);
			cell.setCellValue("��"+pid+"�ⱸע");
		}
		//27���4��С���Ƕ�ѡ  8��ѡ�� ���һ���б�ע����48С�⣬��141����Ԫ��ʼ��
		for(int i=141;i<149;i++){
			cell = row0.createCell(i);
			cell.setCellValue("��"+27+"��-ѡ��"+(i-140));
		}
		cell = row0.createCell(149);
		cell.setCellValue("��"+27+"��-��ע");
		//////////
		//��27���5С�ʿ�ʼ����44��������49С�⵽79С��,�ӵ�150����Ԫ��ʼ ��211������
		for(int i=49*2;i<80*2; i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i-49*2+150);
			cell.setCellValue("��"+pid+"���");
			cell = row0.createCell(i-49*2+150+1);
			cell.setCellValue("��"+pid+"�ⱸע");
		}
		//��45�� 5��ѡ���û�б�ע
		for(int i=212;i<217;i++){
			cell = row0.createCell(i);
			cell.setCellValue("��"+45+"��-ѡ��"+(i-211));
		}
		//��46~85��  81С�⵽120С�� ��217��ʼ
		for(int i=81*2;i<121*2; i=i+2){
			int pid = problems.get(i/2).getPid();
			cell = row0.createCell(i-81*2+217);
			cell.setCellValue("��"+pid+"���");
			cell = row0.createCell(i-81*2+217+1);
			cell.setCellValue("��"+pid+"�ⱸע");
		}
		//���濪ʼд�뱻�����ߵ��ʾ���Ϣ
		for (int i = 0; i < results.size(); i++) {
			XSSFRow row = sheet.createRow(i+1);
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
			
			//������𰸵ĵ���(��Ҫ��������Ǹ�ѡ��Ļ�����Ҫ���ΰ�ÿ��ѡ��뵽��Ӧ����)
			for(int j=0;j<301;){//��Ϊһ����300��T T
				if(j<results.get(i).size()*2){
				Answer a = results.get(i).get(j/2);
				Problem p = a.getProblem();
				int id = p.getId();
				int pid = p.getPid();
				//1-18��𰸵�д��
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
				//19��
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
				//24  ÿ��ѡ��б�ע
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
				//25 ��ѡ ���һ��ѡ���б�ע
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
				//26 ��27��ǰ3�� ��ѡ
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
				//27��4�� ��ѡ�����һ��ѡ���б�ע
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
				//27��5����44 ��ѡ
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
				//45 ��ѡ  ��û�б�ע
				else if(id == 93){
					if (a.getOptions()!=null) {
						String ans = a.getOptions().getValue();
						cell = row.createCell(a.getOptions().getId()-83);
						cell.setCellValue(ans);
						j=j+1;
						
					}
				}
				//46~88�� ��ѡ
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
 * @param problems ��������
 * @param users �����û�
 * @param surveys �����ʾ�
 * @param results �����û������д�
 * @param out
 */
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
