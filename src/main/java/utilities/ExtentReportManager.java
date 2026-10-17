
package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

   private static ExtentReports extent;

   public static ExtentReports getReportInstance() {

       if (extent == null) {

           ExtentSparkReporter reporter =
                   new ExtentSparkReporter(
                           "Reports/Module3AutomationReport.html");

           reporter.config().setDocumentTitle(
                   "Unacademy Module 3 Test Report");

           reporter.config().setReportName(
                   "Unacademy Module 3 Automation");

           extent = new ExtentReports();
           extent.attachReporter(reporter);

           extent.setSystemInfo("Project", "Unacademy Module 3");
           extent.setSystemInfo("Framework", "Selenium + TestNG");
       }

       return extent;
   }
}
