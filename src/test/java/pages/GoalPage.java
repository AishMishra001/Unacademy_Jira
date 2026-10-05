package pages;
 
import java.time.Duration;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class GoalPage {
 
    private WebDriver driver;
    private WebDriverWait wait;
 
    private String goalUrl =
            "https://unacademy.com/goal/upsc-civil-services-examination-ias-preparation/KSCGY";
 
    public GoalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
 
    // Open UPSC CSE Goal page
    public void openGoalPage() {
 
        driver.get(goalUrl);
    }
 
    // Get Goal page heading
    public String getGoalHeading() {
 
        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[contains(.,'UPSC')]")
                )
        );
 
        return heading.getText();
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
 
    // Check whether Add Goal option is displayed
    public boolean isAddGoalDisplayed() {
 
        try {
 
            WebElement addGoal = driver.findElement(
                    By.xpath(
                            "//*[self::button or self::a or @role='button']" +
                            "[" +
                            "contains(translate(normalize-space(.)," +
                            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                            "'abcdefghijklmnopqrstuvwxyz')," +
                            "'add it to your goals')" +
                            " or " +
                            "contains(translate(normalize-space(.)," +
                            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                            "'abcdefghijklmnopqrstuvwxyz')," +
                            "'add to your goals')" +
                            " or " +
                            "contains(translate(normalize-space(.)," +
                            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                            "'abcdefghijklmnopqrstuvwxyz')," +
                            "'add goal')" +
                            "]"
                    )
            );
 
            return addGoal.isDisplayed();
 
        } catch (Exception e) {
 
            return false;
        }
    }
 
    // Get History category filter
    public WebElement getHistoryFilter() {
 
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[@aria-label='History']")
                )
        );
    }
 
    // Click History category
    public void clickHistoryFilter() {
 
        WebElement history = getHistoryFilter();
 
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                history
        );
 
        wait.until(
                ExpectedConditions.elementToBeClickable(history)
        );
 
        history.click();
    }
 
    // Get History course
    public WebElement getHistoryCourse() {
 
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//h5[contains(normalize-space()," +
                                "'Important Questions')]"
                        )
                )
        );
    }
 
    // Click selected History course
    public void clickHistoryCourse() {
 
        WebElement course = getHistoryCourse();
 
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                course
        );
 
        wait.until(
                ExpectedConditions.elementToBeClickable(course)
        );
 
        try {
 
            course.click();
 
        } catch (Exception e) {
 
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    course
            );
        }
    }
}
 