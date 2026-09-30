package utilities;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	private static ExtentReports extent;
	static String d1=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	
	
	public static ExtentReports getinstance()
	{
		if(extent==null)
		{
			String reportfolder=System.getProperty("user.dir"+"\\Reports");
			
			File folder=new File(reportfolder);
			
			if(!folder.exists())
			{
				folder.mkdir();
			}
			
			String reportpath=reportfolder+d1+"AutomationReport.html";
			  ExtentSparkReporter reporter=new ExtentSparkReporter(reportpath);
			  reporter.config().setReportName("Selenium Hybrid Framework");
			  reporter.config().setDocumentTitle("Automation Test Report");
			  extent=new ExtentReports();
			  
			  extent.attachReporter(reporter);
			  
			  extent.setSystemInfo("Tester", "Harini");
			  extent.setSystemInfo("Environment", "QA");
			  
			
		}
		return extent;
	}


	

}