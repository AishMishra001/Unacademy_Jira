package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import utilities.ExtentManager;
import utilities.ScreenshotUtility;

public class ExtentTestNGListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getinstance();

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());

        test.set(extentTest);

        test.get().info("Test Started");

        System.out.println("Extent Test Started: "
                + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed");

        System.out.println("Test Passed: "
                + result.getMethod().getMethodName());

        test.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail("Test Failed");

        System.out.println("Test Failed: "
                + result.getMethod().getMethodName());

        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();

        if (driver != null) {

            try {

                String screenshot =
                        ScreenshotUtility.capture(
                                driver,
                                result.getMethod().getMethodName());

                test.get().addScreenCaptureFromPath(screenshot);

            } catch (IOException e) {

                test.get().fail(
                        "Unable to capture screenshot: "
                                + e.getMessage());
            }
        }

        test.remove();
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        System.out.println("Extent Report Generated");
    }
}