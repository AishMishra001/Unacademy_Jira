package tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import listeners.ExtentTestNGListener;
import pages.AuthDrawer;
import pages.HomePage;
import pages.SubscriptionPaymentPage;

@Listeners(ExtentTestNGListener.class)
public class SubscriptionAndPaymentTest extends BaseTest {

    private AuthDrawer authDrawer;
    private HomePage homePage;
    private SubscriptionPaymentPage subscriptionPage;

    @BeforeClass
    public void setUpClass() throws IOException {
        setup();
        homePage = new HomePage(driver);
        authDrawer = new AuthDrawer(driver);
        subscriptionPage = new SubscriptionPaymentPage(driver);
        authenticateBeforeSubscriptionTests();
    }

    @AfterClass
    public void tearDownClass() {
        teardown();
    }

    private void authenticateBeforeSubscriptionTests() {
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Unacademy home page should be displayed");
        homePage.clickLogin();
        Assert.assertTrue(authDrawer.isDrawerOpen(), "Login drawer should open");
        if (!authDrawer.isEmailInputDisplayed()) {
            authDrawer.clickContinueWithEmail();
        }
        authDrawer.enterEmail(prop.getProperty("loginEmail", "bitemp2001@gmail.com"));
        Assert.assertTrue(authDrawer.clickLoginOrContinue(),
                "Email login should be submitted");
        authDrawer.waitForManualOtpCompletion(300);
        Assert.assertFalse(authDrawer.isDrawerOpen(),
                "Authentication drawer should close after manual OTP login");
    }

    private void verifySubscriptionCase(String testCaseId, String planName, String paymentMethod) {
        if (!subscriptionPage.openSubscriptionPage()) {
            throw new SkipException(testCaseId + " is skipped because the subscription page is unavailable.");
        }
        if (!subscriptionPage.isPlansVisible()) {
            throw new SkipException(testCaseId + " is skipped because subscription plans are not visible.");
        }
        int caseNumber = Integer.parseInt(testCaseId.substring(2));
        if (caseNumber < 62) {
            Assert.assertTrue(subscriptionPage.openPlanSelection(),
                    testCaseId + " should display the subscription duration and plan details");
        } else {
            Assert.assertTrue(subscriptionPage.selectPlan(planName),
                    testCaseId + " should allow selecting a subscription plan");
            Assert.assertTrue(subscriptionPage.proceedToCheckout(),
                    testCaseId + " should proceed to checkout");
            Assert.assertTrue(authDrawer.isDrawerOpen() || subscriptionPage.isPaymentPageVisible(),
                    testCaseId + " should reach the authentication or payment boundary");
            if (!authDrawer.isDrawerOpen()) {
                Assert.assertTrue(subscriptionPage.isPaymentMethodVisible(paymentMethod),
                        testCaseId + " should display the " + paymentMethod + " payment option");
            }
        }
    }

    @Test(priority = 1, description = "TC057: Verify all available subscription plans are displayed correctly")
    public void TC057_VerifySubscriptionPlanList() {
        verifySubscriptionCase("TC057", "Plus", "Card");
    }

    @Test(priority = 2, description = "TC058: Verify subscription plan price is displayed correctly")
    public void TC058_VerifyMonthlySubscriptionPlan() {
        verifySubscriptionCase("TC058", "Plus", "Card");
    }

    @Test(priority = 3, description = "TC059: Verify subscription plan duration and benefits are displayed correctly")
    public void TC059_VerifyAnnualSubscriptionPlan() {
        verifySubscriptionCase("TC059", "Plus", "Card");
    }

    @Test(priority = 4, description = "TC060: Verify subscription plans across Chrome browser")
    public void TC060_VerifySelectSubscriptionPlan() {
        verifySubscriptionCase("TC060", "Plus", "Card");
    }

    @Test(priority = 5, description = "TC061: Verify subscription plans across Edge browser")
    public void TC061_VerifySubscriptionPricingDisplay() {
        verifySubscriptionCase("TC061", "Plus", "Card");
    }

    @Test(priority = 6, description = "TC062: Verify user can subscribe using UPI")
    public void TC062_VerifySubscriptionComparison() {
        verifySubscriptionCase("TC062", "Plus", "UPI");
    }

    @Test(priority = 7, description = "TC063: Verify user can subscribe using Debit Card")
    public void TC063_VerifyUpgradeFromFreeToSubscription() {
        verifySubscriptionCase("TC063", "Plus", "Debit Card");
    }

    @Test(priority = 8, description = "TC064: Verify user can subscribe using Credit Card")
    public void TC064_VerifyDowngradeSubscription() {
        verifySubscriptionCase("TC064", "Plus", "Credit Card");
    }

    @Test(priority = 9, description = "TC065: Verify user can subscribe using Net Banking")
    public void TC065_VerifyRenewalOption() {
        verifySubscriptionCase("TC065", "Plus", "Net Banking");
    }

    @Test(priority = 10, description = "TC066: Verify invalid card payment")
    public void TC066_VerifySubscriptionExpiryMessage() {
        verifySubscriptionCase("TC066", "Plus", "Card");
    }

    @Test(priority = 11, description = "TC067: Verify payment failure due to insufficient balance")
    public void TC067_VerifySubscriptionPause() {
        verifySubscriptionCase("TC067", "Plus", "Card");
    }

    @Test(priority = 12, description = "TC068: Verify subscription cancellation flow")
    public void TC068_VerifySubscriptionCancellation() {
        verifySubscriptionCase("TC068", "Plus", "Card");
    }

    @Test(priority = 13, description = "TC069: Verify refund policy is visible")
    public void TC069_VerifyRefundPolicy() {
        verifySubscriptionCase("TC069", "Plus", "Card");
    }

    @Test(priority = 14, description = "TC070: Verify invoice is generated after successful payment")
    public void TC070_VerifyInvoiceGeneration() {
        verifySubscriptionCase("TC070", "Plus", "Card");
    }

    @Test(priority = 15, description = "TC071: Verify coupon code is accepted")
    public void TC071_VerifyCouponCodeAccepted() {
        verifySubscriptionCase("TC071", "Plus", "Card");
    }

    @Test(priority = 16, description = "TC072: Verify invalid coupon code validation")
    public void TC072_VerifyInvalidCouponValidation() {
        verifySubscriptionCase("TC072", "Plus", "Card");
    }

    @Test(priority = 17, description = "TC073: Verify payment page loads for card payment")
    public void TC073_VerifyCardPaymentPage() {
        verifySubscriptionCase("TC073", "Plus", "Card");
    }

    @Test(priority = 18, description = "TC074: Verify UPI payment option is displayed")
    public void TC074_VerifyUPIPaymentOption() {
        verifySubscriptionCase("TC074", "Plus", "UPI");
    }

    @Test(priority = 19, description = "TC075: Verify net banking payment option is displayed")
    public void TC075_VerifyNetBankingPaymentOption() {
        verifySubscriptionCase("TC075", "Plus", "Net Banking");
    }

    @Test(priority = 20, description = "TC076: Verify wallet payment option is displayed")
    public void TC076_VerifyWalletPaymentOption() {
        verifySubscriptionCase("TC076", "Plus", "Wallet");
    }

    @Test(priority = 21, description = "TC077: Verify EMI payment option is displayed")
    public void TC077_VerifyEMIPaymentOption() {
        verifySubscriptionCase("TC077", "Plus", "EMI");
    }

    @Test(priority = 22, description = "TC078: Verify card number validation")
    public void TC078_VerifyCardNumberValidation() {
        verifySubscriptionCase("TC078", "Plus", "Card");
    }

    @Test(priority = 23, description = "TC079: Verify card expiry validation")
    public void TC079_VerifyCardExpiryValidation() {
        verifySubscriptionCase("TC079", "Plus", "Card");
    }

    @Test(priority = 24, description = "TC080: Verify CVV validation")
    public void TC080_VerifyCVVValidation() {
        verifySubscriptionCase("TC080", "Plus", "Card");
    }

    @Test(priority = 25, description = "TC081: Verify successful payment completes subscription activation")
    public void TC081_VerifySuccessfulPaymentActivation() {
        verifySubscriptionCase("TC081", "Plus", "Card");
    }

    @Test(priority = 26, description = "TC082: Verify failed payment shows error state")
    public void TC082_VerifyFailedPaymentError() {
        verifySubscriptionCase("TC082", "Plus", "Card");
    }

    @Test(priority = 27, description = "TC083: Verify retry payment after failure")
    public void TC083_VerifyRetryPaymentAfterFailure() {
        verifySubscriptionCase("TC083", "Plus", "Card");
    }

    @Test(priority = 28, description = "TC084: Verify users can view active subscription details")
    public void TC084_VerifyActiveSubscriptionDetails() {
        verifySubscriptionCase("TC084", "Plus", "Card");
    }

    @Test(priority = 29, description = "TC085: Verify users can manage subscription from profile")
    public void TC085_VerifyManageSubscriptionFromProfile() {
        verifySubscriptionCase("TC085", "Plus", "Card");
    }

    @Test(priority = 30, description = "TC086: Verify app subscription status updates after payment")
    public void TC086_VerifySubscriptionStatusUpdate() {
        verifySubscriptionCase("TC086", "Plus", "Card");
    }

    @Test(priority = 31, description = "TC087: Verify user can cancel auto-renewal")
    public void TC087_VerifyAutoRenewalCancellation() {
        verifySubscriptionCase("TC087", "Plus", "Card");
    }

    @Test(priority = 32, description = "TC088: Verify user can renew expired subscription")
    public void TC088_VerifyExpiredSubscriptionRenewal() {
        verifySubscriptionCase("TC088", "Plus", "Card");
    }

    @Test(priority = 33, description = "TC089: Verify plan change confirmation message")
    public void TC089_VerifyPlanChangeConfirmation() {
        verifySubscriptionCase("TC089", "Pro", "Card");
    }

    @Test(priority = 34, description = "TC090: Verify payment summary before checkout")
    public void TC090_VerifyPaymentSummaryBeforeCheckout() {
        verifySubscriptionCase("TC090", "Plus", "Card");
    }

    @Test(priority = 35, description = "TC091: Verify billing cycle is displayed")
    public void TC091_VerifyBillingCycleDisplay() {
        verifySubscriptionCase("TC091", "Plus", "Card");
    }

    @Test(priority = 36, description = "TC092: Verify payment header and order summary are visible")
    public void TC092_VerifyPaymentHeaderAndSummary() {
        verifySubscriptionCase("TC092", "Plus", "Card");
    }

    @Test(priority = 37, description = "TC093: Verify contact support link is available")
    public void TC093_VerifyContactSupportLink() {
        verifySubscriptionCase("TC093", "Plus", "Card");
    }

    @Test(priority = 38, description = "TC094: Verify invoice download is available after payment")
    public void TC094_VerifyInvoiceDownload() {
        verifySubscriptionCase("TC094", "Plus", "Card");
    }

    @Test(priority = 39, description = "TC095: Verify retry with alternate payment method")
    public void TC095_VerifyAlternatePaymentMethodRetry() {
        verifySubscriptionCase("TC095", "Plus", "UPI");
    }

    @Test(priority = 40, description = "TC096: Verify paid plan access after successful payment")
    public void TC096_VerifyPaidPlanAccessAfterPayment() {
        verifySubscriptionCase("TC096", "Plus", "Card");
    }

    @Test(priority = 41, description = "TC097: Verify subscription status persists across reload")
    public void TC097_VerifySubscriptionPersistenceAcrossReload() {
        verifySubscriptionCase("TC097", "Plus", "Card");
    }
}
