 
package testcases;
 
import java.time.Duration;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
 
import base.BaseClass;
import pages.GoalPage;
 
public class TC_Module3_CategoryFilter extends BaseClass {
 
    @Test
    public void verifyHistoryCategoryFilter() {
 
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));
 
        GoalPage goalPage = new GoalPage(driver);
 
        // Step 1: Open the UPSC CSE goal page
        goalPage.openGoalPage();
 
        Module3TestListener.logStep(
                "Step 1: Opened the UPSC CSE goal page");
 
        System.out.println("UPSC CSE - GS goal page opened.");
 
        // Step 2: Locate the History category
        By historyFilter =
                By.xpath("//div[@aria-label='History']");
 
        WebElement history = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        historyFilter));
 
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                history);
 
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        historyFilter));
 
        Module3TestListener.logStep(
                "Step 2: Located the History category");
 
        // Step 3: Click the History category
        history.click();
 
        Module3TestListener.logStep(
                "Step 3: Clicked the History category");
 
        System.out.println(
                "History category selected successfully.");
 
        // Step 4: Verify a History course appears
        By historyCourse = By.xpath(
                "//h5[contains(normalize-space(),"
                + "'Important Questions')]");
 
        WebElement course = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        historyCourse));
 
        Assert.assertTrue(
                course.isDisplayed(),
                "History course was not displayed.");
 
        Module3TestListener.logStep(
                "Step 4: Verified the History course is displayed");
 
        Module3TestListener.logStep(
                "Displayed course: " + course.getText());
 
        System.out.println(
                "History category filter verified successfully.");
 
        System.out.println(
                "Displayed course: " + course.getText());
    }
}
//Module3 test