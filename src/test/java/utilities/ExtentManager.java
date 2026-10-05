package utilities;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getinstance() {

        if (extent == null) {

            String projectPath =
                    System.getProperty("user.dir");

            String reportFolder =
                    projectPath + File.separator + "Reports";

            File folder = new File(reportFolder);

            if (!folder.exists()) {

                folder.mkdirs();
            }

            String date =
                    LocalDate.now()
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            String reportPath =
                    reportFolder
                    + File.separator
                    + date
                    + "_AutomationReport.html";

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(reportPath);

            reporter.config()
                    .setReportName("Selenium Hybrid Framework");

            reporter.config()
                    .setDocumentTitle("Automation Test Report");

            extent = new ExtentReports();

            extent.attachReporter(reporter);

            extent.setSystemInfo("Tester", "Harini");
            extent.setSystemInfo("Environment", "QA");
        }

        return extent;
    }
}