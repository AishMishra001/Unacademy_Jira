package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.Login_POM;

public class AboutUsTest extends BaseTest {
	
	@BeforeMethod
	public void beforeMethod() throws IOException
	{
		setup();
		
	}
	
	@AfterMethod
	public void afterMethod() throws IOException
	{
		tearDown();
		
	}
    
    @Test
    public void orangeHRMlogintest(String username, String password)
    {
    	Login_POM obj=new Login_POM(driver);
    	
    	obj.enterusername(username);
    	obj.enterpassword(password);
    	obj.clicklogin();
    	Assert.assertTrue(obj.isdashboarddisplayed());
    }
	
}