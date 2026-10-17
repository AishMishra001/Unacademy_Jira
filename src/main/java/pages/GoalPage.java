  
package pages;
 
import java.time.Duration;
import java.util.List;
 
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class GoalPage {
 
    private WebDriver driver;
    private WebDriverWait wait;
 
    private final String goalUrl =
        "https://unacademy.com/goal/upsc-civil-services-examination-ias-preparation/KSCGY";
 
    private final By goalHeading =
        By.xpath("//h1[contains(.,'UPSC')]");
 
    private final By historyFilter =
        By.xpath("//div[@aria-label='History']");
 
    private final By historyCourse =
        By.xpath("//h5[contains(normalize-space(),'Important Questions')]");
 
    private final By loginButton =
        By.xpath(
            "//*[self::button or self::a]" +
            "[normalize-space()='Log in']"
        );
 
    public GoalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
            driver, Duration.ofSeconds(15)
        );
    }
 
    // Step 1: Open the UPSC CSE goal page
    public void openGoalPage() {
        driver.get(goalUrl);
        wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.tagName("body")
            )
        );
    }
 
    // Step 2: Read the goal page heading
    public String getGoalHeading() {
        try {
            WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    goalHeading
                )
            );
            return heading.getText();
        } catch (Exception e) {
            return driver.getTitle();
        }
    }
 
    // Step 3: Select the History category
    public void clickHistoryFilter() {
        WebElement history = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                historyFilter
            )
        );
 
        scrollToElement(history);
 
        wait.until(
            ExpectedConditions.elementToBeClickable(
                historyFilter
            )
        );
 
        clickElement(history);
    }
 
    // Step 4: Find a course displayed under History
    public WebElement getHistoryCourse() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                historyCourse
            )
        );
    }
 
    // Step 5: Open the selected History course
    public void clickHistoryCourse() {
        WebElement course = getHistoryCourse();
        scrollToElement(course);
        clickElement(course);
    }
 
    // Step 6: Check whether an Add Goal action is available
    public boolean addGoal() {
        try {
            JavascriptExecutor js =
                (JavascriptExecutor) driver;
 
            js.executeScript(
                "window.scrollTo(0, document.body.scrollHeight);"
            );
 
            By addGoalLocator = By.xpath(
                "//*[self::button or self::a or @role='button']" +
                "[contains(" +
                "translate(normalize-space(.)," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                "'abcdefghijklmnopqrstuvwxyz')," +
                "'add it to your goals') or contains(" +
                "translate(normalize-space(.)," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                "'abcdefghijklmnopqrstuvwxyz')," +
                "'add to your goals') or contains(" +
                "translate(normalize-space(.)," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                "'abcdefghijklmnopqrstuvwxyz')," +
                "'add goal')]"
            );
 
            List<WebElement> buttons =
                driver.findElements(addGoalLocator);
 
            if (buttons.isEmpty()) {
                System.out.println(
                    "Add Goal action is not available in this session."
                );
                return false;
            }
 
            WebElement button = buttons.get(0);
            scrollToElement(button);
 
            wait.until(
                ExpectedConditions.elementToBeClickable(button)
            );
 
            clickElement(button);
 
            System.out.println("Add Goal action clicked.");
            return true;
 
        } catch (Exception e) {
            System.out.println(
                "Could not perform Add Goal: " + e.getMessage()
            );
            return false;
        }
    }
 
    // Step 7: Check whether the login button is visible
    public boolean isLoginDisplayed() {
        try {
            return !driver.findElements(loginButton).isEmpty()
                && driver.findElement(loginButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
 
    // Utility: Scroll an element into view
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            element
        );
    }
 
    // Utility: Click normally, then use JavaScript if intercepted
    private void clickElement(WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
            );
        }
    }
}
 