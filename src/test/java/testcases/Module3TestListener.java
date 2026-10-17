package testcases;
 
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
 
import base.BaseClass;
import utilities.ExtentReportManager;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
 
public class Module3TestListener implements ITestListener {
 
    private static ExtentReports extent =
            ExtentReportManager.getReportInstance();
 
    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<ExtentTest>();
 
    // Creates a report entry for each test
    @Override
    public void onTestStart(ITestResult result) {
 
        ExtentTest extentTest = extent.createTest(
                result.getMethod().getMethodName());
 
        test.set(extentTest);
 
        test.get().info("Test started: "
                + result.getMethod().getMethodName());
    }
 
    // Logs successful test results
    @Override
    public void onTestSuccess(ITestResult result) {
 
        test.get().pass("Test passed successfully.");
    }
 
    // Logs failed tests and captures screenshots
    @Override
    public void onTestFailure(ITestResult result) {
 
        test.get().fail("Test failed: "
                + result.getThrowable());
 
        Object instance = result.getInstance();
 
        if (instance instanceof BaseClass) {
 
            WebDriver driver =
                    ((BaseClass) instance).getDriver();
 
            if (driver != null) {
 
                try {
                    File folder = new File("Screenshots");
 
                    if (!folder.exists()) {
                        folder.mkdirs();
                    }
 
                    String timestamp =
                            new SimpleDateFormat("yyyyMMdd_HHmmss")
                                    .format(new Date());
 
                    String screenshotName =
                            result.getMethod().getMethodName()
                                    + "_" + timestamp + ".png";
 
                    File source = ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);
 
                    File destination =
                            new File(folder, screenshotName);
 
                    FileHandler.copy(source, destination);
 
                    test.get().fail(
                            "Failure screenshot",
                            MediaEntityBuilder
                                    .createScreenCaptureFromPath(
                                            destination.getAbsolutePath())
                                    .build());
 
                    System.out.println(
                            "Failure screenshot saved: "
                                    + destination.getAbsolutePath());
 
                } catch (IOException e) {
 
                    test.get().warning(
                            "Could not save screenshot: "
                                    + e.getMessage());
                }
            }
        }
    }
 
    // Logs skipped tests
    @Override
    public void onTestSkipped(ITestResult result) {
 
        test.get().skip("Test skipped: "
                + result.getMethod().getMethodName());
    }
 
    // Logs additional test events
    @Override
    public void onTestFailedButWithinSuccessPercentage(
            ITestResult result) {
 
        test.get().warning(
                "Test failed within success percentage: "
                        + result.getMethod().getMethodName());
    }
 
    @Override
    public void onStart(ITestContext context) {
 
        System.out.println(
                "Starting TestNG suite: "
                        + context.getName());
    }
 
    // Writes and saves the final HTML report
    @Override
    public void onFinish(ITestContext context) {
 
        extent.flush();
 
        System.out.println(
                "Extent HTML report generated in the Reports folder.");
    }
 
    // Call this method from test classes to log each execution step
    public static void logStep(String message) {
 
        ExtentTest currentTest = test.get();
 
        if (currentTest != null) {
            currentTest.info(message);
        }
    }
}
 