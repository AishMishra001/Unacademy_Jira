package base;
 
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
 
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class BaseClass {
 
    protected WebDriver driver;
 
    @BeforeMethod
    public void setUp() {
 
        WebDriverManager.chromedriver().setup();
 
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
 
        driver = new ChromeDriver(options);
 
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
 
        driver.get("https://unacademy.com/explore");
    }
 
    @AfterMethod
    public void tearDown(ITestResult result) {
 
        if (driver != null) {
 
            try {
                File screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);
 
                File destination = new File(
                        "Screenshots/" +
                        result.getName() + "_PASS_" +
                        System.currentTimeMillis() + ".png"
                );
 
                Files.copy(
                        screenshot.toPath(),
                        destination.toPath(),
                        StandardCopyOption.REPLACE_EXISTING
                );
 
                System.out.println("Screenshot captured at: "
                        + destination.getAbsolutePath());
 
            } catch (IOException e) {
                System.out.println("Screenshot capture failed: "
                        + e.getMessage());
            }
 
            driver.quit();
        }
    }
}
 