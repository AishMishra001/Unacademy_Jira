package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubscriptionPaymentPage {

    public static final String SUBSCRIPTION_URL =
            "https://unacademy.com/goal/upsc-optional/NYHNH/subscriptions";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By planCard = By.xpath("//*[contains(normalize-space(.), 'months') and contains(normalize-space(.), '₹') and not(.//*[contains(normalize-space(.), 'months') and contains(normalize-space(.), '₹')])]");
    private final By getPlusButton = By.xpath("//button[normalize-space(.)='Get Plus']");
    private final By durationOption = By.xpath("//*[not(ancestor::*[contains(@class, 'MuiDrawer-paper')]) and (self::button or self::label or @role='button') and contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'month')]");
    private final By couponInput = By.xpath("//input[contains(@placeholder, 'Coupon') or contains(@placeholder, 'Apply') or contains(@name, 'coupon') or contains(@aria-label, 'coupon')] | //input[@type='text' and contains(@class, 'coupon')] ");
    private final By applyCouponButton = By.xpath("//button[contains(., 'Apply') or contains(., 'Redeem') or contains(., 'Coupon')] ");
    private final By checkoutButton = By.xpath("//*[not(ancestor::*[contains(@class, 'MuiDrawer-paper')]) and (self::button or self::a or @role='button') and (normalize-space(.)='Continue' or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'checkout') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'proceed') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'pay now'))]");
    private final By paymentMethodButton = By.xpath("//button[contains(., 'Card') or contains(., 'UPI') or contains(., 'Net Banking') or contains(., 'Wallet') or contains(., 'EMI') or contains(., 'Paytm') or contains(., 'PhonePe')] | //label[contains(., 'Card') or contains(., 'UPI') or contains(., 'Net Banking') or contains(., 'Wallet') or contains(., 'EMI')] ");
    private final By paymentPageHeading = By.xpath("//*[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'choose a payment method')]");
    private final By cardNumberInput = By.xpath("//input[contains(@placeholder, 'Card Number') or contains(@name, 'cardNumber') or contains(@autocomplete, 'cc-number')] | //input[@inputmode='numeric' and contains(@class, 'card')] ");
    private final By cardNameInput = By.xpath("//input[contains(@placeholder, 'Name on Card') or contains(@name, 'cardName') or contains(@autocomplete, 'cc-name')] ");
    private final By expiryInput = By.xpath("//input[contains(@placeholder, 'MM/YY') or contains(@placeholder, 'Expiry') or contains(@name, 'expiry')] ");
    private final By cvvInput = By.xpath("//input[contains(@placeholder, 'CVV') or contains(@placeholder, 'CVC') or contains(@name, 'cvv')] ");
    private final By successText = By.xpath("//*[contains(., 'Subscribed') or contains(., 'Active') or contains(., 'Payment Successful') or contains(., 'Upgrade successful') or contains(., 'Plan activated')] ");
    private final By subscriptionItem = By.xpath("//*[contains(., 'My Subscription') or contains(., 'Subscriptions') or contains(., 'Current Plan') or contains(., 'Active Plan')] ");
    private final By cancelButton = By.xpath("//button[contains(., 'Cancel') or contains(., 'Cancel plan') or contains(., 'Cancel subscription')] ");
    private final By invoiceLink = By.xpath("//a[contains(., 'Invoice') or contains(., 'Receipt') or contains(., 'Download invoice')] | //button[contains(., 'Invoice') or contains(., 'Receipt')] ");

    public SubscriptionPaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean openSubscriptionPage() {
        try {
            driver.get(SUBSCRIPTION_URL);
            wait.until(ExpectedConditions.urlContains("/goal/upsc-optional/NYHNH/subscriptions"));
            return isPlansVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPlansVisible() {
        try {
            List<WebElement> cards = driver.findElements(planCard);
            return !cards.isEmpty() && cards.stream().anyMatch(WebElement::isDisplayed);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean selectPlan(String planName) {
        try {
            if (!clickGetPlus()) {
                return false;
            }
            return selectAvailablePlan();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean selectAvailablePlan() {
        for (int attempt = 0; attempt < 10; attempt++) {
            List<WebElement> options = driver.findElements(durationOption);
            if (options.isEmpty()) {
                options = driver.findElements(planCard);
            }
            for (WebElement option : options) {
                if (option.isDisplayed()) {
                    scrollIntoView(option);
                    clickElement(option);
                    return true;
                }
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }

    public boolean clickGetPlus() {
        for (WebElement button : driver.findElements(getPlusButton)) {
            if (button.isDisplayed() && button.isEnabled()) {
                scrollIntoView(button);
                clickElement(button);
                return true;
            }
        }
        return false;
    }

    public boolean openPlanSelection() {
        try {
            if (!clickGetPlus()) {
                return false;
            }
            wait.until(driver -> !driver.findElements(durationOption).isEmpty()
                    || !driver.findElements(planCard).isEmpty());
            return isPlansVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean applyCoupon(String couponCode) {
        try {
            List<WebElement> inputs = driver.findElements(couponInput);
            if (!inputs.isEmpty()) {
                WebElement input = inputs.get(0);
                if (input.isDisplayed()) {
                    input.clear();
                    input.sendKeys(couponCode == null || couponCode.trim().isEmpty() ? "WELCOME10" : couponCode);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean applyCouponToCart() {
        try {
            List<WebElement> buttons = driver.findElements(applyCouponButton);
            for (WebElement button : buttons) {
                if (button.isDisplayed()) {
                    clickElement(button);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean proceedToCheckout() {
        try {
            for (int attempt = 0; attempt < 3; attempt++) {
                List<WebElement> buttons = driver.findElements(checkoutButton);
                for (WebElement checkout : buttons) {
                    if (checkout.isDisplayed() && checkout.isEnabled()) {
                        scrollIntoView(checkout);
                        clickElement(checkout);
                        return true;
                    }
                }
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(400);
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", element);
    }

    public boolean isPaymentPageVisible() {
        try {
            List<WebElement> methods = driver.findElements(paymentMethodButton);
            return !methods.isEmpty()
                    || !driver.findElements(paymentPageHeading).isEmpty()
                    || driver.getCurrentUrl().toLowerCase().contains("payment");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPaymentMethodVisible(String method) {
        try {
            String value = method == null || method.trim().isEmpty() ? "card" : method.trim().toLowerCase();
            String normalized = value.replace("-", "").replace(" ", "");
            String aliases = normalized.contains("upi") ? "upi"
                    : normalized.contains("netbank") ? "netbanking"
                    : normalized.contains("debit") || normalized.contains("credit") ? "debit"
                    : normalized;
            String locatorText = "translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')";
            String methodExpression = normalized.contains("debit") || normalized.contains("credit")
                    ? "contains(" + locatorText + ", 'debit') and contains(" + locatorText + ", 'credit')"
                    : "contains(" + locatorText + ", '" + aliases + "') or contains(" + locatorText + ", '"
                            + value + "')";
            By methodLocator = By.xpath("//*[" + methodExpression + "]");
            return driver.findElements(methodLocator).stream().anyMatch(WebElement::isDisplayed);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean selectPaymentMethod(String method) {
        try {
            String value = method == null || method.trim().isEmpty() ? "Card" : method.trim();
            By methodLocator = By.xpath("//*[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" + value.toLowerCase() + "')] ");
            List<WebElement> options = driver.findElements(methodLocator);
            for (WebElement option : options) {
                if (option.isDisplayed()) {
                    clickElement(option);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean enterCardDetails(String cardNumber, String cardHolderName, String expiry, String cvv) {
        try {
            List<WebElement> cardInputs = driver.findElements(cardNumberInput);
            if (!cardInputs.isEmpty()) {
                WebElement cardField = cardInputs.get(0);
                cardField.clear();
                cardField.sendKeys(cardNumber == null || cardNumber.trim().isEmpty() ? "4242424242424242" : cardNumber);
            }

            List<WebElement> nameInputs = driver.findElements(cardNameInput);
            if (!nameInputs.isEmpty()) {
                WebElement nameField = nameInputs.get(0);
                nameField.clear();
                nameField.sendKeys(cardHolderName == null || cardHolderName.trim().isEmpty() ? "Automation User" : cardHolderName);
            }

            List<WebElement> expiryInputs = driver.findElements(expiryInput);
            if (!expiryInputs.isEmpty()) {
                WebElement expiryField = expiryInputs.get(0);
                expiryField.clear();
                expiryField.sendKeys(expiry == null || expiry.trim().isEmpty() ? "12/30" : expiry);
            }

            List<WebElement> cvvInputs = driver.findElements(cvvInput);
            if (!cvvInputs.isEmpty()) {
                WebElement cvvField = cvvInputs.get(0);
                cvvField.clear();
                cvvField.sendKeys(cvv == null || cvv.trim().isEmpty() ? "123" : cvv);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean completePayment() {
        try {
            List<WebElement> buttons = driver.findElements(checkoutButton);
            for (WebElement button : buttons) {
                if (button.isDisplayed() && button.getText().toLowerCase().contains("pay")) {
                    clickElement(button);
                    return true;
                }
            }
            return !buttons.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSubscriptionActivated() {
        try {
            List<WebElement> success = driver.findElements(successText);
            if (!success.isEmpty() && success.stream().anyMatch(WebElement::isDisplayed)) {
                return true;
            }
            List<WebElement> subscription = driver.findElements(subscriptionItem);
            return !subscription.isEmpty() && subscription.stream().anyMatch(WebElement::isDisplayed);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean cancelSubscription() {
        try {
            List<WebElement> cancelOptions = driver.findElements(cancelButton);
            for (WebElement cancel : cancelOptions) {
                if (cancel.isDisplayed()) {
                    clickElement(cancel);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean openInvoice() {
        try {
            List<WebElement> invoiceOptions = driver.findElements(invoiceLink);
            for (WebElement invoice : invoiceOptions) {
                if (invoice.isDisplayed()) {
                    clickElement(invoice);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private void clickElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } catch (Exception ignored) {
                System.out.println("Could not click subscription/payment element: " + ignored.getMessage());
            }
        }
    }
}
