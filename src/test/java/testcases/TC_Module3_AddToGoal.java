 
package testcases;
 
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
 
import base.BaseClass;
import pages.GoalPage;
 
@Listeners(Module3TestListener.class)
public class TC_Module3_AddToGoal extends BaseClass {
 
    @Test
    public void verifySelectedCourseForGoal() {
 
        Module3TestListener.logStep(
                "Step 1: Started Add to Goal test."
        );
 
        // Step 2: Create Goal Page object
        GoalPage goalPage = new GoalPage(driver);
 
        Module3TestListener.logStep(
                "Step 2: Created GoalPage object."
        );
 
        // Step 3: Open UPSC CSE goal page
        goalPage.openGoalPage();
 
        Module3TestListener.logStep(
                "Step 3: Opened the UPSC CSE - GS goal page."
        );
 
        // Step 4: Get and verify the goal heading
        String heading = goalPage.getGoalHeading();
 
        Module3TestListener.logStep(
                "Step 4: Retrieved goal heading: " + heading
        );
 
        System.out.println("Goal Page Heading: " + heading);
 
        Assert.assertTrue(
                heading != null && heading.contains("UPSC"),
                "UPSC Goal page was not displayed."
        );
 
        Module3TestListener.logStep(
                "Step 5: Verified the UPSC goal page successfully."
        );
 
        // Step 6: Check whether the user is logged in
        boolean loginDisplayed = goalPage.isLoginDisplayed();
 
        if (loginDisplayed) {
 
            Module3TestListener.logStep(
                    "Step 6: Login option is displayed."
            );
 
            Module3TestListener.logStep(
                    "Step 7: User is logged out; "
                    + "personal Goal saving requires login."
            );
 
            System.out.println("User is not logged in.");
 
            System.out.println(
                    "Personal Goal saving cannot be verified "
                    + "without login."
            );
 
        } else {
 
            Module3TestListener.logStep(
                    "Step 6: Login option is not displayed."
            );
 
            // Step 7: Attempt the Goal action
            boolean goalActionAvailable = goalPage.addGoal();
 
            Assert.assertTrue(
                    goalActionAvailable,
                    "Goal action could not be located or performed."
            );
 
            Module3TestListener.logStep(
                    "Step 7: Goal action was located and clicked."
            );
        }
 
        Module3TestListener.logStep(
                "Step 8: Add to Goal test completed."
        );
    }
}
 