package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.Display_Page;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

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
    public void verifyAboutUsPage() throws InterruptedException
    {
    	Display_Page obj=new Display_Page(driver);
    	obj.clickabout();
    	
    	Assert.assertTrue(obj.istitledisplayed());
    }
    
    
    @Test
    public void verifyAboutUsPerformance() throws InterruptedException
    {
        long start = System.currentTimeMillis();
        
        Display_Page obj = new Display_Page(driver);
        obj.clickabout();
        
        Assert.assertTrue(obj.istitledisplayed());

        long end = System.currentTimeMillis();

        long loadTime = end - start;

        System.out.println("Load Time = " + loadTime);

        Assert.assertTrue(loadTime < 3000);
    }
    
    
    @Test
    public void verifyMissionVision() throws InterruptedException
    {
        Display_Page obj = new Display_Page(driver);
        
        obj.clickabout();
        
        Thread.sleep(5000);

        System.out.println(
        	    driver.findElements(
        	        By.xpath("//*[contains(.,'OUR MISSION & IMPACT')]")
        	    ).size()
        	);
        
        Assert.assertTrue(obj.isMissionVisionDisplayed());
    }
    
    
    @Test
    public void verifyAchievements() throws InterruptedException
    {
        Display_Page obj = new Display_Page(driver);
        obj.clickabout();
        

        Assert.assertTrue(obj.isAchievementsDisplayed());
    }

	
    @Test
    public void verifyCurrentOpenings() throws InterruptedException
    {
        Display_Page obj = new Display_Page(driver);
        obj.clickabout();
        
        obj.clickOpenPositions();
        obj.clickSeeAllOpenings();
        obj.clickgetAllOpenings();
        
        Assert.assertTrue(obj.areJobsdisplayed());
    }
    
    
    @Test
    public void verifyContactInformation() throws InterruptedException
    {
        Display_Page obj = new Display_Page(driver);
        obj.clickabout();
        

        Assert.assertTrue(obj.contactinfodisplayed());
    }
    
    
    @Test
    public void verifyEmailLink() throws InterruptedException
    {
        Display_Page obj = new Display_Page(driver);
        obj.clickabout();
        

        Assert.assertTrue(obj.isEmailLinkDisplayed());
    }
    
    
    @Test
    public void verifySocialMediaLinks() throws InterruptedException
    {
        Display_Page obj = new Display_Page(driver);
        obj.clickabout();
        

        Assert.assertTrue(obj.areSocialMediaLinksDisplayed());
    }
}