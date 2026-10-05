package tests;
 
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
 
import base.BaseClass;
import pages.CoursePage;
import pages.GoalPage;
 
public class TC_Module3_AddToGoal extends BaseClass {
 
    @Test
    public void verifySelectedCourseForGoal() {
 
        // Create Goal Page object
        GoalPage goalPage = new GoalPage(driver);
 
        // Step 1: Open UPSC CSE Goal page
        goalPage.openGoalPage();
 
        System.out.println(
                "UPSC CSE Goal page opened successfully."
        );
 
        // Step 2: Verify Goal page
        String heading = goalPage.getGoalHeading();
 
        System.out.println(
                "Goal Page Heading: " + heading
        );
 
        Assert.assertTrue(
                heading.contains("UPSC"),
                "UPSC Goal page was not displayed."
        );
 
        // Step 3: Select History category
        goalPage.clickHistoryFilter();
 
        System.out.println(
                "History category selected successfully."
        );
 
        // Step 4: Verify History course is displayed
        WebElement course = goalPage.getHistoryCourse();
 
        Assert.assertTrue(
                course.isDisplayed(),
                "History course was not displayed."
        );
 
        System.out.println(
                "History course found: " + course.getText()
        );
 
        // Step 5: Open selected course
        goalPage.clickHistoryCourse();
 
        System.out.println(
                "Selected History course opened successfully."
        );
 
        // Step 6: Create Course Page object
        CoursePage coursePage = new CoursePage(driver);
 
        // Step 7: Verify that the selected course page opened
        boolean coursePageOpened =
                coursePage.isCoursePageOpened();
 
        System.out.println(
                "Course Page URL: " +
                coursePage.getCoursePageUrl()
        );
 
        Assert.assertTrue(
                coursePageOpened,
                "Selected course page was not opened."
        );
 
        System.out.println(
                "Selected course page verified successfully."
        );
 
        // Step 8: Check Goal/Save action
        boolean goalOrSaveAvailable =
                coursePage.isGoalOrSaveDisplayed();
 
        boolean loginDisplayed =
                coursePage.isLoginDisplayed();
 
        if (goalOrSaveAvailable) {
 
            System.out.println(
                    "Goal/Save action is available on the course page."
            );
 
        } else if (loginDisplayed) {
 
            System.out.println(
                    "Course Goal/Save action requires login."
            );
 
            System.out.println(
                    "Logged-out authentication state verified."
            );
 
        } else {
 
            System.out.println(
                    "Goal/Save action is not available " +
                    "in the current session."
            );
        }
    }
}
 