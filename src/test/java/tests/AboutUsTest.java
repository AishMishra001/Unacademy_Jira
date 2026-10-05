package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.Display_Page;

public class AboutUsTest extends BaseTest {

    @BeforeMethod
    public void beforeMethod() throws IOException {
        setup();
    }

    @AfterMethod
    public void afterMethod() throws IOException {
        tearDown();
    }

    @Test
    public void verifyAboutUsPage() {

        Display_Page obj = new Display_Page(driver);

        obj.clickabout();

        Assert.assertTrue(obj.istitledisplayed());
    }
    
    @Test
    public void TC048_verifyAboutUsResponsiveness() {

        Display_Page obj = new Display_Page(driver);

        obj.clickabout();

        Assert.assertTrue(obj.isResponsive());
    }

    @Test
    public void verifyAboutUsPerformance() {

        long start = System.currentTimeMillis();

        Display_Page obj = new Display_Page(driver);

        obj.clickabout();

        Assert.assertTrue(obj.istitledisplayed());

        long end = System.currentTimeMillis();

        long loadTime = end - start;

        System.out.println("Load Time = " + loadTime);
        
        Assert.assertTrue(loadTime < 10000);
    }

    @Test
    public void verifyMissionVision() throws InterruptedException {

        Display_Page obj = new Display_Page(driver);

        obj.clickabout();

        Thread.sleep(1000);

        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        Assert.assertTrue(obj.isMissionVisionDisplayed());
    }

    @Test
    public void verifyAchievements() {

        Display_Page obj = new Display_Page(driver);

        obj.clickabout();

        Assert.assertTrue(obj.isAchievementsDisplayed());
    }

    @Test
    public void verifyCurrentOpenings() {

        Display_Page obj = new Display_Page(driver);

        obj.clickabout();

        obj.clickOpenPositions();
        obj.clickSeeAllOpenings();
        obj.clickgetAllOpenings();

        Assert.assertTrue(obj.areJobsdisplayed());
    }

    @Test
    public void verifyContactInformation() {

        Display_Page obj = new Display_Page(driver);

        obj.clickabout();

        Assert.assertTrue(obj.contactinfodisplayed());
    }

    @Test
    public void verifyEmailLink() {

        Display_Page obj = new Display_Page(driver);

        obj.clickabout();

        Assert.assertTrue(obj.isEmailLinkDisplayed());
    }

    @Test
    public void verifySocialMediaLinks() {

        Display_Page obj = new Display_Page(driver);

        obj.clickabout();

        Assert.assertTrue(obj.areSocialMediaLinksDisplayed());
    }
}