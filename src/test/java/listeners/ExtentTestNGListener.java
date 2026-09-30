package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilities.ExtentManager;
import utilities.ScreenshotUtility;

public class ExtentTestNGListener implements ITestListener{

	WebDriver driver;
	private static ExtentReports extent=ExtentManager.getinstance();
	private static ThreadLocal<ExtentTest> test=new ThreadLocal<>();
	@Override
	public void onTestStart(ITestResult result)
	{
		ExtentTest extentTest=extent.createTest(result.getMethod().getMethodName());
		test.set(test.get().info("Test Started"));
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.get().pass("Test Passed");
		test.remove();
	}
	@Override
	public void onTestFailure(ITestResult result)
	{
		test.get().fail("Test Failed");
		
		String screenshot = null;
		try {
		screenshot=ScreenshotUtility.capture(driver, result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(screenshot!=null)
		{
			test.get().addScreenCaptureFromPath(screenshot);
			
		}
		
	}
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
	{
		
	}
	
}