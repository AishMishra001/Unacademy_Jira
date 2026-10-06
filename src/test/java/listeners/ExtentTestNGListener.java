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

    private static final ExtentReports extent =
            ExtentManager.getinstance();

    private final ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    public ExtentTest getCurrentTest() {
        return test.get();
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(
                        result.getMethod().getMethodName(),
                        result.getMethod().getDescription());

        extentTest.assignAuthor("Ajeet Singh");
        extentTest.assignCategory("About Us Module");

        test.set(extentTest);

        log(Status.INFO,
                "Execution Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        log(Status.PASS,
                "Test Passed Successfully");

        attachScreenshot(result, "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        log(Status.FAIL,
                "Test Failed");

        if (result.getThrowable() != null) {

            test.get().fail(result.getThrowable());
        }

        attachScreenshot(result, "FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        log(Status.SKIP,
                "Test Skipped");

        if (result.getThrowable() != null) {

            test.get().skip(result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        System.out.println(
                "Extent Report Generated Successfully");
    }

    private void log(Status status,
                     String message) {

        if (test.get() != null) {

            test.get().log(status, message);
        }
    }

    private void attachScreenshot(
            ITestResult result,
            String status) {

        try {

            WebDriver driver =
                    getDriver(result);

            if (driver == null) {

                return;
            }

            String path =
                    ScreenshotUtility.capture(
                            driver,
                            result.getMethod()
                            .getMethodName()
                            + "_"
                            + status);

            test.get()
                .addScreenCaptureFromPath(
                        path,
                        status + " Screenshot");

        } catch (IOException e) {

            test.get()
                .warning(e.getMessage());
        }
    }

    private WebDriver getDriver(
            ITestResult result) {

        Object current =
                result.getInstance();

        if (current instanceof BaseTest) {

            return ((BaseTest) current)
                    .getDriver();
        }

        return null;
    }
}