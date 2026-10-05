package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By loginButton = By.xpath("//button[contains(., 'Log in')]");
    private By joinForFreeButton = By.xpath("//button[contains(., 'Join for free')]");
    private By userProfileIcon = By.xpath("//div[contains(@class, 'avatar') or contains(@class, 'Profile') or @aria-label='Profile' or contains(@class, 'UserMenu')]");
    private By logoutButton = By.xpath("//button[contains(., 'Log out') or contains(., 'Logout')] | //span[contains(text(), 'Log out') or contains(text(), 'Logout')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickLogin() {
        List<WebElement> buttons = driver.findElements(loginButton);
        for (WebElement btn : buttons) {
            if (btn.isDisplayed()) {
                try {
                    btn.click();
                    return;
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                    return;
                }
            }
        }
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        btn.click();
    }

    public void clickJoinForFree() {
        List<WebElement> buttons = driver.findElements(joinForFreeButton);
        for (WebElement btn : buttons) {
            if (btn.isDisplayed()) {
                try {
                    btn.click();
                    return;
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                    return;
                }
            }
        }
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(joinForFreeButton));
        btn.click();
    }

    public boolean isHomePageDisplayed() {
        return driver.getTitle().toLowerCase().contains("unacademy") ||
               driver.findElements(loginButton).size() > 0 ||
               driver.findElements(joinForFreeButton).size() > 0;
    }

    public void openProfileMenu() {
        try {
            WebElement profile = wait.until(ExpectedConditions.elementToBeClickable(userProfileIcon));
            profile.click();
        } catch (Exception e) {
            System.out.println("Profile avatar not clickable or not logged in yet: " + e.getMessage());
        }
    }

    public void clickLogout() {
        try {
            WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
            logout.click();
        } catch (Exception e) {
            System.out.println("Logout element: " + e.getMessage());
        }
    }

    public boolean isLogoutOptionAvailable() {
        return driver.findElements(logoutButton).size() > 0 ||
               driver.findElements(userProfileIcon).size() > 0;
    }
}
