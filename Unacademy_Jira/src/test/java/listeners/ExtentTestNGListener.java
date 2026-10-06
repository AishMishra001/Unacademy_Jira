package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.BaseTest;
import utilities.ExtentManager;
import utilities.ScreenshotUtility;

public class ExtentTestNGListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getinstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return test.get();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        if (description == null || description.isEmpty()) {
            description = testName;
        }

        ExtentTest extentTest = extent.createTest(testName, description);
        test.set(extentTest);
        test.get().log(Status.INFO, "Test Execution Started: " + testName);
        System.out.println(">>> Extent Test Started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        test.get().log(Status.PASS, "Test Passed Successfully: " + testName);
        System.out.println(">>> Test Passed: " + testName);

        Object currentClass = result.getInstance();
        if (currentClass instanceof BaseTest) {
            WebDriver driver = ((BaseTest) currentClass).getDriver();
            if (driver != null) {
                try {
                    String screenshot = ScreenshotUtility.capture(driver, testName + "_PASS");
                    if (screenshot != null) {
                        test.get().addScreenCaptureFromPath(screenshot, "Passed Screenshot");
                    }
                } catch (IOException e) {
                    test.get().log(Status.WARNING, "Failed to capture pass screenshot: " + e.getMessage());
                }
            }
        }
        test.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        test.get().log(Status.FAIL, "Test Failed: " + testName);
        if (result.getThrowable() != null) {
            test.get().log(Status.FAIL, result.getThrowable());
        }
        System.out.println(">>> Test Failed: " + testName);

        Object currentClass = result.getInstance();
        if (currentClass instanceof BaseTest) {
            WebDriver driver = ((BaseTest) currentClass).getDriver();
            if (driver != null) {
                try {
                    String screenshot = ScreenshotUtility.capture(driver, testName + "_FAILED");
                    if (screenshot != null) {
                        test.get().addScreenCaptureFromPath(screenshot, "Failure Screenshot");
                    }
                } catch (IOException e) {
                    test.get().log(Status.WARNING, "Unable to capture failure screenshot: " + e.getMessage());
                }
            }
        }
        test.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        if (test.get() != null) {
            test.get().log(Status.SKIP, "Test Skipped: " + testName);
            if (result.getThrowable() != null) {
                test.get().log(Status.SKIP, result.getThrowable());
            }
            test.remove();
        }
        System.out.println(">>> Test Skipped: " + testName);
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
            System.out.println(">>> Extent Report Generated Successfully");
        }
    }
}
