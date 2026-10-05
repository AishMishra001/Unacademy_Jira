package pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 
public class ExplorePage {
 
    WebDriver driver;
 
    public ExplorePage(WebDriver driver) {
        this.driver = driver;
    }
 
    // Get page title
    public String getPageTitle() {
 
        return driver.getTitle();
    }
 
    // Search box
    public WebElement getSearchBox() {
 
        return driver.findElement(
                By.xpath("//input[@placeholder='Search for your goal']")
        );
    }
 
    // Search for a goal
    public void searchGoal(String goal) {
 
        getSearchBox().sendKeys(goal);
    }
 
    // UPSC CSE - GS search result
    public WebElement getUPSCGSCourse() {
 
        return driver.findElement(
                By.xpath(
                    "//div[contains(@class,'DdItemInfoTitle')" +
                    "][.//span[normalize-space()='UPSC CSE']" +
                    " and .//span[normalize-space()='- GS']]"
                )
        );
    }
 
    // Click UPSC CSE - GS
    public void clickUPSCGSCourse() {
 
        getUPSCGSCourse().click();
    }
 
    // History subject filter
    public WebElement getHistoryFilter() {
 
        return driver.findElement(
                By.xpath("//div[@aria-label='History']")
        );
    }
 
    // Click History subject filter
    public void clickHistoryFilter() {
 
        getHistoryFilter().click();
    }
}
 