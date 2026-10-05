package pages;
 
import java.time.Duration;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class CoursePage {
 
    private WebDriver driver;
    private WebDriverWait wait;
 
    public CoursePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
 
    // Verify that the course page has opened
 
    public boolean isCoursePageOpened() {
     
        try {
     
            wait.until(
                    ExpectedConditions.or(
                            ExpectedConditions.urlContains("/course/"),
                            ExpectedConditions.urlContains("/class/")
                    )
            );
     
            String currentUrl = driver.getCurrentUrl();
     
            return currentUrl.contains("/course/")
                    || currentUrl.contains("/class/");
     
        } catch (Exception e) {
     
            return false;
        }
    }
     
    // Get current course page URL
    public String getCoursePageUrl() {
 
        return driver.getCurrentUrl();
    }
 
    // Check whether Login button is displayed
    public boolean isLoginDisplayed() {
 
        try {
 
            WebElement login = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(
                                    "//*[self::button or self::a]" +
                                    "[normalize-space()='Log in']"
                            )
                    )
            );
 
            return login.isDisplayed();
 
        } catch (Exception e) {
 
            return false;
        }
    }
 
    // Check whether Goal/Save action is displayed
    public boolean isGoalOrSaveDisplayed() {
 
        try {
 
            WebElement goalAction = driver.findElement(
                    By.xpath(
                            "//*[self::button or self::a or @role='button']" +
                            "[" +
                            "contains(translate(normalize-space(.)," +
                            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                            "'abcdefghijklmnopqrstuvwxyz')," +
                            "'add to goal')" +
                            " or " +
                            "contains(translate(normalize-space(.)," +
                            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                            "'abcdefghijklmnopqrstuvwxyz')," +
                            "'add to your goal')" +
                            " or " +
                            "contains(translate(normalize-space(.)," +
                            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                            "'abcdefghijklmnopqrstuvwxyz')," +
                            "'save')" +
                            "]"
                    )
            );
 
            return goalAction.isDisplayed();
 
        } catch (Exception e) {
 
            return false;
        }
    }
}
