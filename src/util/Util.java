package util;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

public class Util {
	public static final String RootPath;
	public static final String FileUploadPath = "/FileUpload/"; //œ‡∂‘”⁄Rootpath
	public static final String ResultsOfAll = FileUploadPath+"resultsOfAll/";
	public static final String Result = FileUploadPath+"result/";
	public static void makeDir(String path)
	{
		File file = new File(path);
		if(!file.exists())
		{
			makeDir(file.getParent().toString());
			file.mkdir();
		}
		
	}
	static 
	{	
		ServletContext application = ServletActionContext.getServletContext();
		RootPath = application.getRealPath("");
		makeDir(RootPath+ResultsOfAll);
		makeDir(RootPath+Result);
	}
}
