 
package base;
 
import java.time.Duration;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class BaseClass {
 
    protected WebDriver driver;
 
    @BeforeMethod
    public void setUp() {
 
        // Set up ChromeDriver
        WebDriverManager.chromedriver().setup();
 
        // Configure Chrome browser
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920,1080");
 
        // Launch Chrome
        driver = new ChromeDriver(options);
 
        // Set implicit wait
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
 
        // Open Unacademy
        driver.get("https://unacademy.com/explore");
 
        System.out.println("Chrome browser opened successfully.");
        System.out.println("Unacademy website opened successfully.");
    }
 
    @AfterMethod
    public void tearDown() {
 
        // Close browser after each test
        if (driver != null) {
            driver.quit();
            System.out.println("Chrome browser closed.");
        }
    }
 
    // Used by test classes and listener
    public WebDriver getDriver() {
        return driver;
    }
}
 