package utilities;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getinstance() {
        if (extent == null) {
            String projectPath = System.getProperty("user.dir");
            String reportFolder = projectPath + File.separator + "Reports";

            File folder = new File(reportFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String reportPath = reportFolder + File.separator + date + "_AutomationReport.html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("Unacademy Hybrid Automation Framework - Module 1: Sign up and Login");
            reporter.config().setDocumentTitle("Unacademy Automation Test Report");
            reporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("Application", "Unacademy Platform");
            extent.setSystemInfo("Module", "Module 1 - Sign Up and Login");
            extent.setSystemInfo("Author", "Aish Mishra");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }

        return extent;
    }
}
