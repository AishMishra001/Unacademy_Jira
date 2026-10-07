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
    private final By cardNumberInput = By.xpath("//input[contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'card number') or contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'enter card number') or contains(@name, 'cardNumber') or contains(@autocomplete, 'cc-number')] | //input[@inputmode='numeric' and contains(@class, 'card')] ");
    private final By cardNameInput = By.xpath("//input[contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'name on card') or contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'enter name') or contains(@name, 'cardName') or contains(@autocomplete, 'cc-name')] ");
    private final By expiryInput = By.xpath("//input[contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'mm/yy') or contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'valid through') or contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'expiry') or contains(@name, 'expiry')] ");
    private final By cvvInput = By.xpath("//input[contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'cvv') or contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'cvc') or contains(@name, 'cvv')] ");
    private final By paymentError = By.xpath("//*[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'invalid') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'error') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'declined') or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'valid card')]");
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

    public boolean isPlanPricingVisible() {
        return driver.findElements(By.xpath(
                "//*[contains(normalize-space(.), '₹') and contains(normalize-space(.), '/mo')]"))
                .stream().anyMatch(WebElement::isDisplayed);
    }

    public boolean isDurationAndBenefitsVisible() {
        return driver.findElements(By.xpath(
                "//*[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'months')"
                        + " and (contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'save')"
                        + " or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'extra'))]"))
                .stream().anyMatch(WebElement::isDisplayed);
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
            String value = method == null || method.trim().isEmpty() ? "card" : method.trim().toLowerCase();
            String optionText = value.contains("upi") ? "upi"
                    : value.contains("net") ? "netbanking"
                    : "debit / credit card";
            By methodLocator = By.xpath(
                    "//*[translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='"
                            + optionText + "' and not(.//*[normalize-space(.)!=''])]");
            if (driver.findElements(methodLocator).isEmpty()) {
                methodLocator = By.xpath(
                        "//*[translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='"
                                + optionText + "' and not(.//*[translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='"
                                + optionText + "'])]");
            }
            List<WebElement> options = driver.findElements(methodLocator);
            for (WebElement option : options) {
                if (option.isDisplayed()) {
                    scrollIntoView(option);
                    clickElement(option);
                    return isPaymentMethodActive(value);
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isPaymentMethodActive(String method) {
        String value = method.contains("upi") ? "upi"
                : method.contains("net") ? "netbank"
                : "debit";
        if ("debit".equals(value)) {
            By cardContent = By.xpath(
                    "//input[contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'card number')"
                            + " or contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'valid through')"
                            + " or contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'enter cvv')]");
            if (driver.findElements(cardContent).stream().anyMatch(WebElement::isDisplayed)) {
                return true;
            }
            if (hasCardFieldsInPaymentFrames()) {
                return true;
            }
        } else if ("netbank".equals(value)) {
            By netBankContent = By.xpath(
                    "//*[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'select your bank')"
                            + " or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'choose your bank')"
                            + " or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'netbanking')]");
            if (driver.findElements(netBankContent).stream().anyMatch(WebElement::isDisplayed)) {
                return true;
            }
        }
        if ("upi".equals(value)) {
            By upiContent = By.xpath(
                    "//*[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'pay via upi qr')"
                            + " or contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'generate a new qr')]");
            if (driver.findElements(upiContent).stream().anyMatch(WebElement::isDisplayed)) {
                return true;
            }
        }
        By activeMethod = By.xpath("//*[(@aria-selected='true' or contains(@class, 'active') or contains(@class, 'selected'))"
                + " and contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
                + value + "')]");
        return driver.findElements(activeMethod).stream().anyMatch(WebElement::isDisplayed);
    }

    public boolean enterCardDetails(String cardNumber, String cardHolderName, String expiry, String cvv) {
        boolean fieldEntered = false;
        List<WebElement> paymentInputs = visiblePaymentInputs();
        List<WebElement> cardInputs = driver.findElements(cardNumberInput);
        if (cardInputs.isEmpty() && !paymentInputs.isEmpty()) cardInputs = paymentInputs.subList(0, 1);
        if (!cardInputs.isEmpty()) fieldEntered |= fillField(cardInputs.get(0), cardNumber);

        List<WebElement> nameInputs = driver.findElements(cardNameInput);
        if (nameInputs.isEmpty() && paymentInputs.size() >= 4) nameInputs = paymentInputs.subList(3, 4);
        if (!nameInputs.isEmpty()) fieldEntered |= fillField(nameInputs.get(0), cardHolderName);

        List<WebElement> expiryInputs = driver.findElements(expiryInput);
        if (expiryInputs.isEmpty() && paymentInputs.size() >= 2) expiryInputs = paymentInputs.subList(1, 2);
        if (!expiryInputs.isEmpty()) fieldEntered |= fillField(expiryInputs.get(0), expiry);

        List<WebElement> cvvInputs = driver.findElements(cvvInput);
        if (cvvInputs.isEmpty() && paymentInputs.size() >= 3) cvvInputs = paymentInputs.subList(2, 3);
        if (!cvvInputs.isEmpty()) fieldEntered |= fillField(cvvInputs.get(0), cvv);
        return fieldEntered;
    }

    private boolean fillField(WebElement field, String value) {
        try {
            field.clear();
            field.sendKeys(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private List<WebElement> visiblePaymentInputs() {
        By inputLocator = By.xpath("//input[not(contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'coupon'))"
                + " and not(contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'referral'))"
                + " and not(contains(translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'coupon'))]");
        List<WebElement> inputs = driver.findElements(inputLocator);
        inputs.removeIf(input -> !input.isDisplayed() || !input.isEnabled());
        return inputs;
    }

    public boolean enterGeneratedCardDetailsAndSubmit() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(d -> hasCardFieldsInCurrentDocument()
                            || hasCardFieldsInPaymentFrames());
        } catch (Exception e) {
            return false;
        }

        if (!enterCardDetails("123456789", "TEMP", "01/99", "123")) {
            List<WebElement> frames = driver.findElements(By.tagName("iframe"));
            for (WebElement frame : frames) {
                try {
                    driver.switchTo().frame(frame);
                    if (enterCardDetails("123456789", "TEMP", "01/99", "123")) {
                        pressEnterOnCvv();
                        driver.switchTo().defaultContent();
                        return true;
                    }
                    driver.switchTo().defaultContent();
                } catch (Exception e) {
                    driver.switchTo().defaultContent();
                }
            }
            return false;
        }
        pressEnterOnCvv();
        return true;
    }

    private boolean hasCardFieldsInCurrentDocument() {
        return !driver.findElements(cardNumberInput).isEmpty()
                || !driver.findElements(expiryInput).isEmpty()
                || !driver.findElements(cvvInput).isEmpty()
                || !visiblePaymentInputs().isEmpty();
    }

    private boolean hasCardFieldsInPaymentFrames() {
        for (WebElement frame : driver.findElements(By.tagName("iframe"))) {
            try {
                driver.switchTo().frame(frame);
                boolean found = hasCardFieldsInCurrentDocument();
                driver.switchTo().defaultContent();
                if (found) {
                    return true;
                }
            } catch (Exception e) {
                driver.switchTo().defaultContent();
            }
        }
        return false;
    }

    private void pressEnterOnCvv() {
        List<WebElement> cvvFields = driver.findElements(cvvInput);
        if (!cvvFields.isEmpty() && cvvFields.get(0).isDisplayed()) {
            cvvFields.get(0).sendKeys(Keys.ENTER);
        }
    }

    public boolean isPaymentErrorDisplayed() {
        return driver.findElements(paymentError).stream().anyMatch(WebElement::isDisplayed);
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
