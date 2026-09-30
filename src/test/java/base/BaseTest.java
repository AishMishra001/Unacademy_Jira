package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	String projectpath;
	protected WebDriver driver;
	
	public void setup() throws IOException {
		
		Properties prop = new Properties();

        FileInputStream fis = new FileInputStream("C:\\Users\\ajeet.4.singh\\OneDrive - Coforge Limited\\Desktop\\Automation_Selenium\\src\\test\\resources\\Confiuration\\config.properties");
        prop.load(fis);

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(prop.getProperty("url"));
        
	}
	
	public void tearDown() {
		driver.quit();
	}
}
