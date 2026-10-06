package utilities;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;

    public static synchronized ExtentReports getinstance() {

        if (extent == null) {

            String reportFolder =
                    System.getProperty("user.dir")
                    + File.separator
                    + "Reports";

            new File(reportFolder).mkdirs();

            String timestamp =
                    LocalDateTime.now()
                    .format(
                        DateTimeFormatter.ofPattern(
                            "dd-MM-yyyy_HH-mm-ss"));

            String reportPath =
                    reportFolder
                    + File.separator
                    + "Report_"
                    + timestamp
                    + ".html";

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(
                            reportPath);

            reporter.config().setTheme(
                    Theme.DARK);

            reporter.config().setDocumentTitle(
                    "Automation Report");

            reporter.config().setReportName(
                    "Unacademy About Us Automation");

            extent = new ExtentReports();

            extent.attachReporter(reporter);

            extent.setSystemInfo(
                    "Tester",
                    "Ajeet Singh");

            extent.setSystemInfo(
                    "Environment",
                    "QA");

            extent.setSystemInfo(
                    "OS",
                    System.getProperty("os.name"));

            extent.setSystemInfo(
                    "Java",
                    System.getProperty("java.version"));

            System.out.println(
                    "Report Created : "
                    + reportPath);
        }

        return extent;
    }
}