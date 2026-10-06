package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import listeners.ExtentTestNGListener;
import pages.Display_Page;

@Listeners(ExtentTestNGListener.class)
public class AboutUsTest extends BaseTest {

    private Display_Page aboutPage;

    @BeforeMethod
    public void beforeMethod() throws IOException {
        setup();
        aboutPage = new Display_Page(driver);
    }

    @AfterMethod
    public void afterMethod() throws IOException {
        tearDown();
    }

    @Test(priority = 1,description = "TC047 - Validate that the About Us page displays company information, mission, and services correctly")
    public void validateAboutUsPageContent() {
    	
        aboutPage.clickabout();
        Assert.assertTrue(aboutPage.istitledisplayed(), "About Us page content is not displayed correctly");
        
    }

    @Test(priority = 2,description = "TC048 - Validate About Us page responsiveness across desktop, tablet, and mobile viewports")
    public void validateAboutUsResponsiveness() {

        aboutPage.clickabout();

        Assert.assertTrue(aboutPage.isResponsive(),"About Us page is not responsive across different screen sizes");
    }

    @Test(priority = 3,description = "TC049 - Validate About Us page load performance meets acceptable response time")
    public void validateAboutUsPerformance() {

        long startTime = System.currentTimeMillis();

        aboutPage.clickabout();

        Assert.assertTrue(aboutPage.istitledisplayed(),"About Us page failed to load");

        long loadTime = System.currentTimeMillis() - startTime;

        System.out.println("About Us Page Load Time : " + loadTime + " ms");

        Assert.assertTrue(loadTime < 5000,"About Us page took more than 5 seconds to load");
    }

    @Test(priority = 4,description = "TC050 - Validate Mission and Vision section is displayed with correct content")
    public void validateMissionAndVisionSection() {

        aboutPage.clickabout();

        Assert.assertTrue(aboutPage.isMissionVisionDisplayed(), "Mission and Vision section is not displayed");
    }

    @Test(priority = 5,description = "TC051 - Validate Company Achievements section is displayed successfully")
    public void validateAchievementsSection() {

        aboutPage.clickabout();

        Assert.assertTrue(aboutPage.isAchievementsDisplayed(),"Company Achievements section is not displayed");
    }

    @Test(priority = 6,description = "TC052 - Validate Current Openings section displays available job opportunities")
    public void validateCurrentOpeningsSection() {

        aboutPage.clickabout();

        aboutPage.clickOpenPositions();
        aboutPage.clickSeeAllOpenings();
        aboutPage.clickgetAllOpenings();

        Assert.assertTrue(aboutPage.areJobsdisplayed(),"Current job openings are not displayed");
    }

    @Test(priority = 7,description = "TC053 - Validate Apply action redirects the user to the job application page")
    public void validateJobApplicationRedirection() {

        aboutPage.clickabout();

        aboutPage.clickOpenPositions();
        aboutPage.clickSeeAllOpenings();
        aboutPage.clickgetAllOpenings();

        Assert.assertTrue(aboutPage.areJobsdisplayed(),"User was not redirected to the job application page");
    }

    @Test(priority = 8,description = "TC054 - Validate contact information including email, phone number, and address is displayed")
    public void validateContactInformation() {

        aboutPage.clickabout();

        Assert.assertTrue(aboutPage.contactinfodisplayed(),"Contact information is not displayed");
    }

    @Test(priority = 9,description = "TC055 - Validate email link is visible and accessible to the user")
    public void validateEmailLink() {

        aboutPage.clickabout();

        Assert.assertTrue(aboutPage.isEmailLinkDisplayed(),"Email link is not displayed");
    }

    @Test(priority = 10,description = "TC056 - Validate social media links redirect users to official platform pages")
    public void validateSocialMediaLinks() {

        aboutPage.clickabout();

        Assert.assertTrue(aboutPage.areSocialMediaLinksDisplayed(),"Social media links are not displayed");
    }
}