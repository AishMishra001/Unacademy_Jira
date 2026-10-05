package tests;
 
import org.testng.Assert;
import org.testng.annotations.Test;
 
import base.BaseClass;
import pages.ExplorePage;
 
public class TC_Module3_CourseExploration extends BaseClass {
 
    @Test
    public void verifyCourseExploration() {
 
        // Create Explore Page object
        ExplorePage explorePage = new ExplorePage(driver);
 
        // Verify Explore page is opened
        String title = explorePage.getPageTitle();
 
        System.out.println("Page Title: " + title);
 
        Assert.assertTrue(
                title.toLowerCase().contains("unacademy"),
                "Unacademy Explore page was not opened successfully"
        );
 
        // Search for UPSC CSE
        explorePage.searchGoal("UPSC CSE");
 
        System.out.println("UPSC CSE entered in search box.");
 
        // Verify search text
        Assert.assertTrue(
                explorePage.getSearchBox()
                        .getAttribute("value")
                        .contains("UPSC CSE"),
                "UPSC CSE was not entered in the search box"
        );
 
        // Select UPSC CSE - GS
        explorePage.clickUPSCGSCourse();
 
        System.out.println("UPSC CSE - GS selected.");
 
        // Verify History filter is available
        Assert.assertTrue(
                explorePage.getHistoryFilter().isDisplayed(),
                "History subject filter is not displayed"
        );
 
        // Apply History subject filter
        explorePage.clickHistoryFilter();
 
        System.out.println("History subject filter selected.");
 
    }
}
 