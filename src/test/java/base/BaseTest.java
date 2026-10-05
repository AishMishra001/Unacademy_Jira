package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;
    protected Properties prop;

    public void setup() throws IOException {
        setup(null, false, false);
    }

    public void setup(String browserParam) throws IOException {
        setup(browserParam, false, false);
    }

    public void setup(String browserParam, boolean emulateMobile, boolean isLaptop) throws IOException {
        prop = new Properties();
        String configPath = System.getProperty("user.dir")
                + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "config.properties";

        File configFile = new File(configPath);
        if (configFile.exists()) {
            FileInputStream fis = new FileInputStream(configFile);
            prop.load(fis);
            fis.close();
        }

        String browser = (browserParam != null && !browserParam.isEmpty())
                ? browserParam
                : System.getProperty("browser", prop.getProperty("browser", "chrome"));

        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", prop.getProperty("headless", "true")));

        String url = System.getProperty("url", prop.getProperty("url", "https://unacademy.com"));

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ffOptions = new FirefoxOptions();
                if (headless) {
                    ffOptions.addArguments("-headless");
                }
                driver = new FirefoxDriver(ffOptions);
                break;

            case "edge":
                try {
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (headless) {
                        edgeOptions.addArguments("--headless=new");
                    }
                    driver = new EdgeDriver(edgeOptions);
                } catch (Exception e) {
                    System.out.println("EdgeDriver setup issue, falling back to Chrome: " + e.getMessage());
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions fallbackOptions = new ChromeOptions();
                    if (headless) fallbackOptions.addArguments("--headless=new");
                    driver = new ChromeDriver(fallbackOptions);
                }
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");

                if (emulateMobile) {
                    Map<String, String> mobileEmulation = new HashMap<>();
                    mobileEmulation.put("deviceName", "iPhone 12 Pro");
                    chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                }

                driver = new ChromeDriver(chromeOptions);
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        if (emulateMobile) {
            driver.manage().window().setSize(new Dimension(390, 844));
        } else if (isLaptop) {
            driver.manage().window().setSize(new Dimension(1440, 900));
        } else {
            driver.manage().window().maximize();
        }

        driver.get(url);
    }

    public void teardown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Teardown exception: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
