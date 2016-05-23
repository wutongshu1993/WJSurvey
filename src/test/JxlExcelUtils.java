package test;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import jxl.SheetSettings;  
import jxl.Workbook;  
import jxl.format.Alignment;  
import jxl.format.Colour;  
import jxl.format.VerticalAlignment;  
import jxl.write.Label;  
import jxl.write.WritableCellFormat;  
import jxl.write.WritableFont;  
import jxl.write.WritableSheet;  
import jxl.write.WritableWorkbook; 
import model.Detail;
import model.DetailInfo;

public class JxlExcelUtils {
	
	/**
	 * 
	 * @param response
	 * @param objData
	 * @param sheetName
	 * @param columns
	 * @return
	 */
public static int exportToExcel(HttpServletResponse response,List<Detail> objData,String sheetName,List<String> columns){
	int flag = 0;
	WritableWorkbook wwb;
	try {  
        //���ݴ�������file���󴴽���д���Excel������  
        OutputStream os = response.getOutputStream();  
          
        wwb = Workbook.createWorkbook(os);  

        /* 
         * ����һ��������sheetNameΪ����������ơ�"0"Ϊ��һ�������� 
         * ��Excel��ʱ��ῴ�����½�Ĭ����3��sheet��"sheet1��sheet2��sheet3"���� 
         * �����е�"0"����sheet1��������һһ��Ӧ�� 
         * createSheet(sheetName, 0)һ���ǹ���������ƣ���һ���ǹ������ڹ������е�λ�� 
         */  
        WritableSheet ws = wwb.createSheet(sheetName, 0);  
          
//        SheetSettings ss = ws.getSettings();  
//        ss.setVerticalFreeze(1);//�����ͷ  
//          
        WritableFont font1 =new WritableFont(WritableFont.createFont("΢���ź�"), 10 ,WritableFont.BOLD);  
//        WritableFont font2 =new WritableFont(WritableFont.createFont("΢���ź�"), 9 ,WritableFont.NO_BOLD);  
        WritableCellFormat wcf = new WritableCellFormat(font1);  
//        WritableCellFormat wcf2 = new WritableCellFormat(font2);  
//        WritableCellFormat wcf3 = new WritableCellFormat(font2);//������ʽ������  
//
        //������Ԫ����ʽ  
        //WritableCellFormat wcf = new WritableCellFormat();  
//
//        //������ɫ  
//        wcf.setBackground(jxl.format.Colour.YELLOW);  
//        wcf.setAlignment(Alignment.CENTRE);  //ƽ�о���  
//        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);  //��ֱ����  
//        wcf3.setAlignment(Alignment.CENTRE);  //ƽ�о���  
//        wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);  //��ֱ����  
//        wcf3.setBackground(Colour.LIGHT_ORANGE);  
//        wcf2.setAlignment(Alignment.CENTRE);  //ƽ�о���  
//        wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);  //��ֱ����  
//
//        /* 
//         * ����ǵ�Ԫ�����ݾ�����ʾ 
//         * ���кܶ�ܶ���ʽ 
//         */  
//        wcf.setAlignment(Alignment.CENTRE);  

        //�ж�һ�±�ͷ�����Ƿ�������  
        if (columns != null && columns.size() > 0) {  

            //ѭ��д���ͷ  
            for (int i = 0; i < columns.size(); i++) {  

                /* 
                 * ��ӵ�Ԫ��(Cell)����addCell() 
                 * ���Label����Label() 
                 * ���ݵ������кܶ��֡�����������Ҫʲô���;͵���ʲô���� 
                 * �磺jxl.write.DateTime ��jxl.write.Number��jxl.write.Label 
                 * Label(i, 0, columns[i], wcf) 
                 * ����iΪ�С�0Ϊ�С�columns[i]Ϊ���ݡ�wcfΪ��ʽ 
                 * ����������˵��columns[i]��ӵ���һ��(�С����±궼�Ǵ�0��ʼ)��i�С���ʽΪʲô"ɫ"���ݾ��� 
                 */  
                ws.addCell(new Label(i, 0, columns.get(i), wcf));  
            }  

            //�жϱ����Ƿ�������  
            if (objData != null && objData.size() > 0) {  
                //ѭ��д���������  
                for (int i = 0; i < objData.size(); i++) {  

                    //ת����map����{activyName:���Թ���,count:2}  
//                    Map<String, Object> map = (Map<String, Object>)objData.get(i);  
                	Detail detail = objData.get(i);
                    //ѭ�����map�е��Ӽ�������ֵ  
//                    int j=0;  
//                    for(Object o:map.keySet()){  
//                        //ps����ΪҪ����ͨ�á����������ܣ���������ѭ����ʱ����get("Name"),����ͨ��map.get(o)  
//                        ws.addCell(new Label(j,i+1,String.valueOf(map.get(o))));  
//                        j++;  
//                    }  
                	ws.addCell(new Label(0, i+1, detail.getPid()+""));
            		ws.addCell(new Label(1, i+1, detail.getTitle()));
            		ws.addCell(new Label(2, i+1, detail.getAnswer()));
            		ws.addCell(new Label(3, i+1, detail.getRemark()));
//                	for(int j=0;j<columns.size();j++){
//                		ws.addCell(new Label(j, i+1, detail.getPid()+""));
//                		ws.addCell(new Label(j, i+1, detail.getTitle()));
//                		ws.addCell(new Label(j, i+1, detail.getAnswer()));
//                		ws.addCell(new Label(j, i+1, detail.getRemark()));
//                	}
                }  
            }else{  
                flag = -1;  
            }  

            //д��Exel������  
            wwb.write();  

            //�ر�Excel����������   
            wwb.close();  
              
            //�ر���  
            os.flush();  
            os.close();  
              
            os =null;  
        }  
    }catch (IllegalStateException e) {  
        System.err.println(e.getMessage());  
    }  
    catch (Exception ex) {  
        flag = 0;  
        ex.printStackTrace();  
    }  

    return flag;  
}

public static int exportToExcel2(HttpServletResponse response,List<DetailInfo> objData,String sheetName,List<String> columns){
	int flag = 0;
	WritableWorkbook wwb;
	try {  
        //���ݴ�������file���󴴽���д���Excel������  
        OutputStream os = response.getOutputStream();  
          
        wwb = Workbook.createWorkbook(os);  

        /* 
         * ����һ��������sheetNameΪ����������ơ�"0"Ϊ��һ�������� 
         * ��Excel��ʱ��ῴ�����½�Ĭ����3��sheet��"sheet1��sheet2��sheet3"���� 
         * �����е�"0"����sheet1��������һһ��Ӧ�� 
         * createSheet(sheetName, 0)һ���ǹ���������ƣ���һ���ǹ������ڹ������е�λ�� 
         */  
        WritableSheet ws = wwb.createSheet(sheetName, 0);  
          
        WritableFont font1 =new WritableFont(WritableFont.createFont("΢���ź�"), 10 ,WritableFont.BOLD);  
        WritableCellFormat wcf = new WritableCellFormat(font1);  

        //�ж�һ�±�ͷ�����Ƿ�������  
        if (columns != null && columns.size() > 0) {  

            //ѭ��д���ͷ  
            for (int i = 0; i < columns.size(); i++) {  

                /* 
                 * ��ӵ�Ԫ��(Cell)����addCell() 
                 * ���Label����Label() 
                 * ���ݵ������кܶ��֡�����������Ҫʲô���;͵���ʲô���� 
                 * �磺jxl.write.DateTime ��jxl.write.Number��jxl.write.Label 
                 * Label(i, 0, columns[i], wcf) 
                 * ����iΪ�С�0Ϊ�С�columns[i]Ϊ���ݡ�wcfΪ��ʽ 
                 * ����������˵��columns[i]��ӵ���һ��(�С����±궼�Ǵ�0��ʼ)��i�С���ʽΪʲô"ɫ"���ݾ��� 
                 */  
                ws.addCell(new Label(i, 0, columns.get(i), wcf));  
            }  

            //�жϱ����Ƿ�������  
            if (objData != null && objData.size() > 0) {  
                //ѭ��д���������  
                for (int i = 0; i < objData.size(); i++) {  

                    //ת����map����{activyName:���Թ���,count:2}  
//                    Map<String, Object> map = (Map<String, Object>)objData.get(i);  
//                	String name = objData.get(i).getUser().getName();
//                	System.out.println("###########"+name);
                	DetailInfo detail = objData.get(i);
                    //ѭ�����map�е��Ӽ�������ֵ  
//                    int j=0;  
//                    for(Object o:map.keySet()){  
//                        //ps����ΪҪ����ͨ�á����������ܣ���������ѭ����ʱ����get("Name"),����ͨ��map.get(o)  
//                        ws.addCell(new Label(j,i+1,String.valueOf(map.get(o))));  
//                        j++;  
//                    }  
                	ws.addCell(new Label(0, i+1, detail.getUser().getName()));
            		ws.addCell(new Label(1, i+1, detail.getUser().getTel()));
            		ws.addCell(new Label(2, i+1, detail.getUser().gethTel()));
            		ws.addCell(new Label(3, i+1, detail.getUser().getAddress().getQuX()));
            		ws.addCell(new Label(4, i+1, detail.getUser().getAddress().getXiangJ()));
            		ws.addCell(new Label(5, i+1, detail.getUser().getAddress().getCun()));
            		ws.addCell(new Label(6, i+1, detail.getUser().getAddress().getZuD()));
            		ws.addCell(new Label(7, i+1, detail.getUser().getAddress().getHao()));
                	ws.addCell(new Label(8, i+1, detail.getAnswer().getProblem().getPid()+""));
            		ws.addCell(new Label(9, i+1, detail.getAnswer().getProblem().getTitle()));
            		ws.addCell(new Label(10, i+1, detail.getAnswer().getOptions().getValue()));
            		ws.addCell(new Label(11, i+1, detail.getAnswer().getRemark()));
                }  
            }else{  
                flag = -1;  
            }  

            //д��Exel������  
            wwb.write();  

            //�ر�Excel����������   
            wwb.close();  
              
            //�ر���  
            os.flush();  
            os.close();  
              
            os =null;  
        }  
    }catch (IllegalStateException e) {  
        System.err.println(e.getMessage());  
    }  
    catch (Exception ex) {  
        flag = 0;  
        ex.printStackTrace();  
    }  

    return flag;  
}
/** 
 * ����excel 
 * @author  
 * @param response 
 * @param filename �ļ��� ,��:20110808.xls 
 * @param listData ����Դ 
 * @param sheetName ��ͷ���� 
 * @param columns �����Ƽ���,�磺{��Ʒ���ƣ�����������} 
 */  
public static void exportexcle(HttpServletResponse response,String filename,List<Detail> details,String sheetName,List<String> columns)  
{  
    //��������ķ���������Excel�ļ�  
    response.setContentType("application/vnd.ms-excel");  
    //response.setHeader("Content-Disposition", "attachment;filename="+filename);  
    try {  
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1") + ".xls");  

        exportToExcel(response, details, sheetName, columns);  
    } catch (UnsupportedEncodingException e) {  
        e.printStackTrace();  
    }   


} 
public static void exportexcle2(HttpServletResponse response,String filename,List<DetailInfo> details,String sheetName,List<String> columns)  
{  
    //��������ķ���������Excel�ļ�  
    response.setContentType("application/vnd.ms-excel");  
    //response.setHeader("Content-Disposition", "attachment;filename="+filename);  
    try {  
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1") + ".xls");  

        exportToExcel2(response, details, sheetName, columns);  
    } catch (UnsupportedEncodingException e) {  
        e.printStackTrace();  
    }   


} 
//public static void main(String[] args){
//	HttpServletResponse response = ServletActionContext.getResponse();
//	String filename = "a.xls";
//	String sheetName = "sheet1";
//	List<Detail> details = new ArrayList<>();
//	Detail detail1 = new Detail();
//	detail1.setPid(1);
//	detail1.setAnswer("��������");
//	detail1.setTitle("22");
//	detail1.setRemark("���");
//	Detail detail2 = new Detail();
//	detail2.setPid(1);
//	detail2.setAnswer("��������");
//	detail2.setTitle("22");
//	detail2.setRemark("���");
//	details.add(detail1);
//	details.add(detail2);
//	List<String> columns = new ArrayList<>();
//	columns.add("���");
//	columns.add("���");
//	columns.add("��");
//	columns.add("��ע");
//	new JxlExcelUtils().exportexcle(response, filename, details, sheetName, columns);
//}
}  

