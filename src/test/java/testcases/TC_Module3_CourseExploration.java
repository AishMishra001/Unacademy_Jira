 
package testcases;
 
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
 
import base.BaseClass;
import pages.ExplorePage;
 
@Listeners(Module3TestListener.class)
public class TC_Module3_CourseExploration extends BaseClass {
 
    @Test
    public void verifyCourseExploration() {
 
        Module3TestListener.logStep(
                "Step 1: Started Course Exploration test."
        );
 
        // Create Explore Page object
        ExplorePage explorePage = new ExplorePage(driver);
 
        Module3TestListener.logStep(
                "Step 2: Created ExplorePage object."
        );
 
        // Verify Explore page is opened
        String title = explorePage.getPageTitle();
 
        Module3TestListener.logStep(
                "Step 3: Retrieved page title: " + title
        );
 
        System.out.println("Page Title: " + title);
 
        Assert.assertTrue(
                title.toLowerCase().contains("unacademy"),
                "Unacademy Explore page was not opened successfully."
        );
 
        Module3TestListener.logStep(
                "Step 4: Verified Unacademy page title successfully."
        );
 
        // Search for UPSC CSE
        explorePage.searchGoal("UPSC CSE");
 
        Module3TestListener.logStep(
                "Step 5: Entered UPSC CSE in the search box."
        );
 
        System.out.println("UPSC CSE entered in search box.");
 
        // Verify search text
        String searchValue = explorePage.getSearchBox()
                .getAttribute("value");
 
        Module3TestListener.logStep(
                "Step 6: Read search box value: " + searchValue
        );
 
        Assert.assertTrue(
                searchValue != null
                        && searchValue.contains("UPSC CSE"),
                "UPSC CSE was not entered in the search box."
        );
 
        Module3TestListener.logStep(
                "Step 7: Verified search text successfully."
        );
 
        // Select UPSC CSE - GS
        explorePage.clickUPSCGSCourse();
 
        Module3TestListener.logStep(
                "Step 8: Selected UPSC CSE - GS."
        );
 
        // Verify History filter is available
        Assert.assertTrue(
                explorePage.getHistoryFilter().isDisplayed(),
                "History subject filter is not displayed."
        );
 
        Module3TestListener.logStep(
                "Step 9: Verified History subject filter is displayed."
        );
 
        // Apply History subject filter
        explorePage.clickHistoryFilter();
 
        Module3TestListener.logStep(
                "Step 10: Clicked the History subject filter."
        );
 
        System.out.println("History subject filter selected.");
 
        Module3TestListener.logStep(
                "Step 11: Course Exploration test completed successfully."
        );
    }
}
 