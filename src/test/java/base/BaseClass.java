package base;
 
import java.time.Duration;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class BaseClass {
 
    protected WebDriver driver;
 
    @BeforeMethod
    public void setUp() {
 
        WebDriverManager.chromedriver().setup();
 
        driver = new ChromeDriver();

        driver.manage().window().maximize();
 
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
 
        driver.get("https://unacademy.com/explore");
    }
 
    @AfterMethod
    public void tearDown() {
     
        if (driver != null) {
            driver.quit();
        }
    }
}