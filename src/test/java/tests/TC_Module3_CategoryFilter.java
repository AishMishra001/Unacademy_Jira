package tests;
 
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
 
        // Create Goal Page object
        GoalPage goalPage = new GoalPage(driver);
 
        // Open UPSC CSE - GS goal page
        goalPage.openGoalPage();
 
        System.out.println(
                "UPSC CSE - GS goal page opened."
        );
 
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));
 
        // History category locator
        By historyFilter =
                By.xpath("//div[@aria-label='History']");
 
        // History course locator
        By historyCourse =
                By.xpath(
                    "//h5[contains(normalize-space()," +
                    "'Important Questions')]"
                );
 
        // Scroll until History filter is visible
        WebElement history = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        historyFilter
                )
        );
 
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                history
        );
 
        // Wait until clickable
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        historyFilter
                )
        );
 
        // Click History
        history.click();
 
        System.out.println(
                "History category selected successfully."
        );
 
        // Wait for History course to appear
        WebElement course = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        historyCourse
                )
        );
 
        // Verify course is displayed
        Assert.assertTrue(
                course.isDisplayed(),
                "History course was not displayed."
        );
 
        System.out.println(
                "History category filter verified successfully."
        );
 
        System.out.println(
                "Displayed course: " + course.getText()
        );
    }
}
 