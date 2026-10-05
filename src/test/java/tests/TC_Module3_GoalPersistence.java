package tests;
 
import org.testng.Assert;
import org.testng.annotations.Test;
 
import base.BaseClass;
import pages.GoalPage;
 
public class TC_Module3_GoalPersistence extends BaseClass {
 
    @Test
    public void verifyGoalPersistenceAfterRefresh() {
 
        GoalPage goalPage = new GoalPage(driver);
 
        // Open Goal page
        goalPage.openGoalPage();
 
        System.out.println(
                "Goal page opened successfully."
        );
 
        // Verify Goal page before refresh
        String headingBeforeRefresh =
                goalPage.getGoalHeading();
 
        System.out.println(
                "Goal before refresh: "
                + headingBeforeRefresh
        );
 
        Assert.assertTrue(
                headingBeforeRefresh.contains("UPSC"),
                "Goal page was not displayed before refresh."
        );
 
        // Check login status
        boolean loginDisplayed =
                goalPage.isLoginDisplayed();
 
        if (loginDisplayed) {
 
            System.out.println(
                    "User is not logged in."
            );
 
            System.out.println(
                    "Saved Goal persistence cannot be verified " +
                    "for a personal account without login."
            );
 
        } else {
 
            System.out.println(
                    "User is logged in."
            );
 
            // Refresh the page
            driver.navigate().refresh();
 
            System.out.println(
                    "Goal page refreshed successfully."
            );
 
            // Verify Goal page after refresh
            String headingAfterRefresh =
                    goalPage.getGoalHeading();
 
            System.out.println(
                    "Goal after refresh: "
                    + headingAfterRefresh
            );
 
            Assert.assertTrue(
                    headingAfterRefresh.contains("UPSC"),
                    "Goal page was not available after refresh."
            );
 
            System.out.println(
                    "Goal page remained available after refresh."
            );
        }
    }
}
 