 
package testcases;
 
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
 
import base.BaseClass;
import pages.GoalPage;
 
@Listeners(Module3TestListener.class)
public class TC_Module3_GoalPersistence extends BaseClass {
 
    @Test
    public void verifyGoalPersistenceAfterRefresh() {
 
        // Step 1: Create GoalPage object
        GoalPage goalPage = new GoalPage(driver);
 
        Module3TestListener.logStep(
                "Step 1: GoalPage object created."
        );
 
        // Step 2: Open the UPSC goal page
        goalPage.openGoalPage();
 
        Module3TestListener.logStep(
                "Step 2: UPSC goal page opened."
        );
 
        // Step 3: Verify the goal page before refresh
        String headingBeforeRefresh =
                goalPage.getGoalHeading();
 
        System.out.println(
                "Goal heading before refresh: "
                + headingBeforeRefresh
        );
 
        Assert.assertTrue(
                headingBeforeRefresh != null
                        && headingBeforeRefresh.contains("UPSC"),
                "Goal page was not displayed before refresh."
        );
 
        Module3TestListener.logStep(
                "Step 3: Goal page heading verified before refresh."
        );
 
        // Step 4: Refresh the page
        driver.navigate().refresh();
 
        Module3TestListener.logStep(
                "Step 4: Goal page refreshed successfully."
        );
 
        // Step 5: Verify the page after refresh
        String headingAfterRefresh =
                goalPage.getGoalHeading();
 
        System.out.println(
                "Goal heading after refresh: "
                + headingAfterRefresh
        );
 
        Assert.assertTrue(
                headingAfterRefresh != null
                        && headingAfterRefresh.contains("UPSC"),
                "Goal page was not available after refresh."
        );
 
        Module3TestListener.logStep(
                "Step 5: Goal page verified after refresh."
        );
 
        System.out.println(
                "Goal page persistence test completed successfully."
        );
    }
}
 