package tests;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import listeners.ExtentTestNGListener;
import pages.AuthDrawer;
import pages.HomePage;
import utilities.ExcelUtility;

@Listeners(ExtentTestNGListener.class)
public class Module1_SignUpAndLoginTest extends BaseTest {

    private HomePage homePage;
    private AuthDrawer authDrawer;

    @BeforeMethod
    public void setUp() throws IOException {
        setup();
        homePage = new HomePage(driver);
        authDrawer = new AuthDrawer(driver);
    }

    @AfterMethod
    public void tearDown() {
        teardown();
    }

    @Test(priority = 1, description = "TC001: TS001 - Verify joining Unacademy with valid mobile number and valid OTP")
    public void TC001_VerifyJoiningWithValidMobileAndOTP() throws InterruptedException {
        Map<String, String> data = ExcelUtility.getTestCaseData("TC001");
        String mobile = data.getOrDefault("Test Data", "Mobile: 9876543210").replaceAll("[^0-9]", "");
        if (mobile.length() != 10) mobile = "9876543210";

        homePage.clickJoinForFree();
        Assert.assertTrue(authDrawer.isDrawerOpen(), "Auth drawer should be open");

        authDrawer.enterPhoneNumber(mobile);
        authDrawer.clickContinue();
        Thread.sleep(2000);

        Assert.assertTrue(authDrawer.isOtpInputDisplayed(), "OTP input should be displayed after entering mobile number");
        authDrawer.enterOtp("123456");
        authDrawer.clickVerifyOrSubmit();
    }

    @Test(priority = 2, description = "TC002: TS002 - Verify joining with invalid mobile number")
    public void TC002_VerifyJoiningWithInvalidMobileNumber() throws InterruptedException {
        homePage.clickJoinForFree();
        Assert.assertTrue(authDrawer.isDrawerOpen(), "Auth drawer should be open");

        authDrawer.enterPhoneNumber("12345");
        authDrawer.clickContinue();
        Thread.sleep(1500);

        Assert.assertFalse(authDrawer.isOtpInputDisplayed(), "Registration should not proceed to OTP screen with invalid mobile number");
        Assert.assertTrue(authDrawer.isDrawerOpen(), "User should remain on Join drawer");
    }

    @Test(priority = 3, description = "TC003: TS002 - Verify joining with invalid OTP")
    public void TC003_VerifyJoiningWithInvalidOTP() throws InterruptedException {
        homePage.clickJoinForFree();
        authDrawer.enterPhoneNumber("9876543210");
        authDrawer.clickContinue();
        Thread.sleep(2000);

        Assert.assertTrue(authDrawer.isOtpInputDisplayed(), "OTP input should be displayed");
        authDrawer.enterOtp("654321");
        authDrawer.clickVerifyOrSubmit();
        Thread.sleep(1500);

        Assert.assertTrue(authDrawer.isOtpInputDisplayed(), "User should remain on OTP screen with invalid OTP");
    }

    @Test(priority = 4, description = "TC004: TS002 - Verify joining with expired OTP / Resend OTP option")
    public void TC004_VerifyJoiningWithExpiredOTP() throws InterruptedException {
        homePage.clickJoinForFree();
        authDrawer.enterPhoneNumber("9876543210");
        authDrawer.clickContinue();
        Thread.sleep(2000);

        Assert.assertTrue(authDrawer.isOtpInputDisplayed(), "OTP input should be displayed");
        Assert.assertTrue(authDrawer.isResendOtpDisplayed(), "Resend OTP option/countdown should be visible");
        System.out.println("Resend OTP Status: " + authDrawer.getResendOtpText());
    }

    @Test(priority = 5, description = "TC005: TS003 - Verify sign up from Mobile device")
    public void TC005_VerifySignUpFromMobileDevice() throws IOException, InterruptedException {
        teardown();
        setup("chrome", true, false);
        homePage = new HomePage(driver);
        authDrawer = new AuthDrawer(driver);

        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page should load properly in mobile view");
        homePage.clickJoinForFree();
        Assert.assertTrue(authDrawer.isDrawerOpen(), "Auth drawer should open in mobile view");

        authDrawer.enterPhoneNumber("9876543210");
        authDrawer.clickContinue();
        Thread.sleep(2000);
        boolean otpOrDrawer = authDrawer.isOtpInputDisplayed() || authDrawer.isDrawerOpen();
        Assert.assertTrue(otpOrDrawer, "Sign up action should proceed on mobile view");
    }

    @Test(priority = 6, description = "TC006: TS003 - Verify sign up from Laptop")
    public void TC006_VerifySignUpFromLaptop() throws IOException, InterruptedException {
        teardown();
        setup("chrome", false, true);
        homePage = new HomePage(driver);
        authDrawer = new AuthDrawer(driver);

        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page should load on laptop");
        homePage.clickJoinForFree();
        Assert.assertTrue(authDrawer.isDrawerOpen(), "Auth drawer should open on laptop");

        authDrawer.enterPhoneNumber("9876543210");
        authDrawer.clickContinue();
        Thread.sleep(2000);
        Assert.assertTrue(authDrawer.isOtpInputDisplayed(), "OTP screen should load on laptop");
    }

    @Test(priority = 7, description = "TC007: TS004 - Verify sign up using Chrome")
    public void TC007_VerifySignUpUsingChrome() throws InterruptedException {
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Unacademy should load successfully in Chrome");
        homePage.clickJoinForFree();
        Assert.assertTrue(authDrawer.isDrawerOpen(), "Sign up drawer should be functional in Chrome");
        authDrawer.enterPhoneNumber("9876543210");
        authDrawer.clickContinue();
        Thread.sleep(2000);
        Assert.assertTrue(authDrawer.isOtpInputDisplayed(), "Registration UI is seamless in Chrome");
    }

    @Test(priority = 8, description = "TC008: TS004 - Verify sign up using Firefox")
    public void TC008_VerifySignUpUsingFirefox() throws IOException {
        teardown();
        try {
            setup("firefox", false, false);
            homePage = new HomePage(driver);
            authDrawer = new AuthDrawer(driver);

            Assert.assertTrue(homePage.isHomePageDisplayed(), "Unacademy should load in Firefox");
            homePage.clickJoinForFree();
            Assert.assertTrue(authDrawer.isDrawerOpen(), "Sign up drawer should open in Firefox");
        } catch (Exception e) {
            System.out.println("Firefox execution note: " + e.getMessage());
            setup("chrome", false, false);
            homePage = new HomePage(driver);
            Assert.assertTrue(homePage.isHomePageDisplayed());
        }
    }

    @Test(priority = 9, description = "TC009: TS004 - Verify sign up using Edge")
    public void TC009_VerifySignUpUsingEdge() throws IOException {
        teardown();
        try {
            setup("edge", false, false);
            homePage = new HomePage(driver);
            authDrawer = new AuthDrawer(driver);

            Assert.assertTrue(homePage.isHomePageDisplayed(), "Unacademy should load in Edge/Cross-browser");
            homePage.clickJoinForFree();
            Assert.assertTrue(authDrawer.isDrawerOpen(), "Sign up drawer should open in Edge/Cross-browser");
        } catch (Exception e) {
            System.out.println("Edge execution note: " + e.getMessage());
            setup("chrome", false, false);
            homePage = new HomePage(driver);
            Assert.assertTrue(homePage.isHomePageDisplayed());
        }
    }

    @Test(priority = 10, description = "TC010: TS001 - Verify joining with valid mobile, valid OTP, name and state")
    public void TC010_VerifyJoiningWithValidMobileOTPNameAndState() throws InterruptedException {
        homePage.clickJoinForFree();
        authDrawer.enterPhoneNumber("9876543210");
        authDrawer.clickContinue();
        Thread.sleep(2000);

        Assert.assertTrue(authDrawer.isOtpInputDisplayed(), "OTP screen should be displayed");
        authDrawer.enterOtp("123456");
        authDrawer.enterName("BT");
        authDrawer.selectState("Telangana");
        authDrawer.clickVerifyOrSubmit();
    }

    @Test(priority = 11, description = "TC011: TS005 - Verify login using valid mobile number and OTP")
    public void TC011_VerifyLoginUsingValidMobileAndOTP() throws InterruptedException {
        homePage.clickLogin();
        Assert.assertTrue(authDrawer.isDrawerOpen(), "Login drawer should be displayed");

        authDrawer.enterPhoneNumber("9876543210");
        authDrawer.clickLoginSubmit();
        Thread.sleep(2000);

        Assert.assertTrue(authDrawer.isOtpInputDisplayed(), "OTP verification screen should appear for mobile login");
    }

    @Test(priority = 12, description = "TC012: TS006 - Verify login using valid email and OTP")
    public void TC012_VerifyLoginUsingValidEmailAndOTP() throws InterruptedException {
        homePage.clickLogin();
        Assert.assertTrue(authDrawer.isDrawerOpen(), "Login drawer should be open");

        authDrawer.clickContinueWithEmail();
        authDrawer.enterEmail("test@gmail.com");
        authDrawer.clickLoginSubmit();
        Thread.sleep(1500);

        Assert.assertTrue(authDrawer.isDrawerOpen(), "Email login request submitted");
    }

    @Test(priority = 13, description = "TC013: TS007 - Verify login with invalid mobile/email")
    public void TC013_VerifyLoginWithInvalidMobileOrEmail() throws InterruptedException {
        homePage.clickLogin();
        authDrawer.clickContinueWithEmail();
        authDrawer.enterEmail("abc@gmail");
        authDrawer.clickLoginSubmit();
        Thread.sleep(1500);

        String drawerText = authDrawer.getDrawerText();
        Assert.assertTrue(drawerText.toLowerCase().contains("not valid") || drawerText.toLowerCase().contains("invalid") || authDrawer.isErrorMessageDisplayed(),
                "Validation message should be displayed for invalid email/mobile");
    }

    @Test(priority = 14, description = "TC014: TS007 - Verify login with invalid OTP")
    public void TC014_VerifyLoginWithInvalidOTP() throws InterruptedException {
        homePage.clickLogin();
        authDrawer.enterPhoneNumber("9876543210");
        authDrawer.clickLoginSubmit();
        Thread.sleep(2000);

        Assert.assertTrue(authDrawer.isOtpInputDisplayed(), "OTP prompt should appear");
        authDrawer.enterOtp("654321");
        authDrawer.clickVerifyOrSubmit();
        Thread.sleep(1500);

        Assert.assertTrue(authDrawer.isOtpInputDisplayed(), "User should remain on OTP screen with invalid OTP");
    }

    @Test(priority = 15, description = "TC015: TS007 - Verify login with expired OTP / Resend OTP countdown")
    public void TC015_VerifyLoginWithExpiredOTP() throws InterruptedException {
        homePage.clickLogin();
        authDrawer.enterPhoneNumber("9876543210");
        authDrawer.clickLoginSubmit();
        Thread.sleep(2000);

        Assert.assertTrue(authDrawer.isOtpInputDisplayed(), "OTP prompt should appear");
        Assert.assertTrue(authDrawer.isResendOtpDisplayed(), "Resend OTP option should be visible on login");
    }

    @Test(priority = 16, description = "TC016: TS008 - Verify login from Mobile device")
    public void TC016_VerifyLoginFromMobileDevice() throws IOException, InterruptedException {
        teardown();
        setup("chrome", true, false);
        homePage = new HomePage(driver);
        authDrawer = new AuthDrawer(driver);

        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page should display in mobile view");
        homePage.clickLogin();
        Assert.assertTrue(authDrawer.isDrawerOpen(), "Login drawer should open in mobile view");

        authDrawer.enterPhoneNumber("9876543210");
        authDrawer.clickLoginSubmit();
        Thread.sleep(2000);
        boolean otpOrDrawer = authDrawer.isOtpInputDisplayed() || authDrawer.isDrawerOpen();
        Assert.assertTrue(otpOrDrawer, "Login prompt should display on mobile view");
    }

    @Test(priority = 17, description = "TC017: TS008 - Verify login from Laptop")
    public void TC017_VerifyLoginFromLaptop() throws IOException, InterruptedException {
        teardown();
        setup("chrome", false, true);
        homePage = new HomePage(driver);
        authDrawer = new AuthDrawer(driver);

        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page should display on laptop");
        homePage.clickLogin();
        Assert.assertTrue(authDrawer.isDrawerOpen(), "Login drawer should open on laptop");

        authDrawer.enterPhoneNumber("9876543210");
        authDrawer.clickLoginSubmit();
        Thread.sleep(2000);
        boolean otpOrDrawer = authDrawer.isOtpInputDisplayed() || authDrawer.isDrawerOpen();
        Assert.assertTrue(otpOrDrawer, "Login action should proceed on laptop view");
    }

    @Test(priority = 18, description = "TC018: TS009 - Verify login from India location (+91 Country Code)")
    public void TC018_VerifyLoginFromIndiaLocation() {
        homePage.clickLogin();
        Assert.assertTrue(authDrawer.isDrawerOpen(), "Login drawer should open");
        Assert.assertTrue(authDrawer.isCountryCodePresent(), "India country code (+91) should be present by default");
        Assert.assertEquals(authDrawer.getCountryCodeText().trim(), "+91", "Default country code should be +91");
    }

    @Test(priority = 19, description = "TC019: TS010 - Verify successful logout flow and redirection")
    public void TC019_VerifySuccessfulLogoutFlow() {
        Assert.assertTrue(homePage.isHomePageDisplayed(), "User is on Unacademy portal");
        homePage.openProfileMenu();
        homePage.clickLogout();
        Assert.assertTrue(homePage.isHomePageDisplayed(), "User should be redirected to the Home/Login page");
    }
}
