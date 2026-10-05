package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthDrawer {

    private WebDriver driver;
    private WebDriverWait wait;

    // Scoped locators inside MuiDrawer-paper
    private By drawerContainer = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]");
    private By phoneInput = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//input[@type='tel' and not(@disabled)]");
    private By emailInput = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//input[@type='email' or @placeholder='Email address']");
    private By continueButton = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//button[contains(., 'Continue')]");
    private By loginSubmitButton = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//button[contains(., 'Login')]");
    private By continueWithEmailLink = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//*[contains(text(), 'Continue with email')]");
    private By continueWithMobileLink = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//*[contains(text(), 'Continue with mobile number')]");
    private By createAccountLink = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//*[contains(text(), 'create your account')]");
    private By loginAccountLink = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//*[contains(text(), 'login to your account')]");
    private By countryCode = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//*[contains(text(), '+91')]");
    
    // OTP Screen elements
    private By otpInput = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//input[@placeholder='One time password' or @maxlength='6']");
    private By nameInput = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//input[@placeholder='Name']");
    private By stateDropdown = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//button[contains(., 'State of residence')]");
    private By verifySubmitButton = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//button[contains(., 'Submit') or contains(., 'Verify')]");
    private By resendOtpText = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//*[contains(text(), 'Resend OTP')]");
    private By errorMessage = By.xpath("//div[contains(@class, 'MuiDrawer-paper')]//*[contains(text(), 'not valid') or contains(text(), 'Invalid') or contains(text(), 'valid mobile') or contains(text(), 'wrong') or contains(text(), 'expired') or contains(text(), 'Enter valid')]");

    public AuthDrawer(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isDrawerOpen() {
        try {
            WebElement drawer = wait.until(ExpectedConditions.visibilityOfElementLocated(drawerContainer));
            return drawer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getDrawerText() {
        try {
            WebElement drawer = wait.until(ExpectedConditions.visibilityOfElementLocated(drawerContainer));
            return drawer.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void enterPhoneNumber(String phone) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(phoneInput));
        input.click();
        input.sendKeys(Keys.chord(Keys.COMMAND, "a"), Keys.BACK_SPACE);
        input.clear();
        input.sendKeys(phone);
    }

    public void clickContinue() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
            btn.click();
        } catch (Exception e) {
            try {
                WebElement btn = driver.findElement(continueButton);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
            } catch (Exception ex) {
                System.out.println("Continue click fallback failed: " + ex.getMessage());
            }
        }
    }

    public void clickLoginSubmit() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginSubmitButton));
            btn.click();
        } catch (Exception e) {
            try {
                WebElement btn = driver.findElement(loginSubmitButton);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
            } catch (Exception ex) {
                System.out.println("Login submit click fallback failed: " + ex.getMessage());
            }
        }
    }

    public void clickContinueWithEmail() {
        WebElement emailLink = wait.until(ExpectedConditions.elementToBeClickable(continueWithEmailLink));
        emailLink.click();
    }

    public void clickContinueWithMobile() {
        WebElement mobileLink = wait.until(ExpectedConditions.elementToBeClickable(continueWithMobileLink));
        mobileLink.click();
    }

    public void clickCreateAccount() {
        WebElement createLink = wait.until(ExpectedConditions.elementToBeClickable(createAccountLink));
        createLink.click();
    }

    public void clickLoginToAccount() {
        WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(loginAccountLink));
        loginLink.click();
    }

    public void enterEmail(String email) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        input.click();
        input.sendKeys(Keys.chord(Keys.COMMAND, "a"), Keys.BACK_SPACE);
        input.clear();
        input.sendKeys(email);
    }

    public boolean isOtpInputDisplayed() {
        try {
            WebElement otp = wait.until(ExpectedConditions.visibilityOfElementLocated(otpInput));
            return otp.isDisplayed();
        } catch (Exception e) {
            String text = getDrawerText().toLowerCase();
            return text.contains("otp") || text.contains("verify your mobile");
        }
    }

    public void enterOtp(String otp) {
        try {
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(otpInput));
            input.click();
            input.clear();
            input.sendKeys(otp);
        } catch (Exception e) {
            System.out.println("Enter OTP fallback: " + e.getMessage());
        }
    }

    public void enterName(String name) {
        try {
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(nameInput));
            input.click();
            input.clear();
            input.sendKeys(name);
        } catch (Exception e) {
            System.out.println("Name input not present or optional: " + e.getMessage());
        }
    }

    public void selectState(String stateName) {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(stateDropdown));
            dropdown.click();
            Thread.sleep(500);
            By optionBy = By.xpath("//li[contains(text(), '" + stateName + "')] | //div[contains(text(), '" + stateName + "')]");
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionBy));
            option.click();
        } catch (Exception e) {
            System.out.println("State dropdown not clickable or optional: " + e.getMessage());
        }
    }

    public void clickVerifyOrSubmit() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(verifySubmitButton));
            btn.click();
        } catch (Exception e) {
            try {
                WebElement btn = driver.findElement(verifySubmitButton);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
            } catch (Exception ex) {
                System.out.println("Submit button fallback: " + ex.getMessage());
            }
        }
    }

    public boolean isResendOtpDisplayed() {
        try {
            List<WebElement> elements = driver.findElements(resendOtpText);
            if (elements.size() > 0 && elements.get(0).isDisplayed()) {
                return true;
            }
            return getDrawerText().toLowerCase().contains("resend otp");
        } catch (Exception e) {
            return false;
        }
    }

    public String getResendOtpText() {
        try {
            WebElement resend = driver.findElement(resendOtpText);
            return resend.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isCountryCodePresent() {
        try {
            return driver.findElements(countryCode).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public String getCountryCodeText() {
        try {
            WebElement code = driver.findElement(countryCode);
            return code.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            List<WebElement> errors = driver.findElements(errorMessage);
            for (WebElement el : errors) {
                if (el.isDisplayed()) return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessageText() {
        try {
            List<WebElement> errors = driver.findElements(errorMessage);
            for (WebElement el : errors) {
                if (el.isDisplayed()) return el.getText();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }
}
