# Unacademy Hybrid Test Automation Framework

An end-to-end **Hybrid Test Automation Framework** built with **Java**, **Selenium WebDriver**, **TestNG**, **Apache POI**, and **ExtentReports Spark**.

This project implements and automates the test scenarios for **Module 1 (Epic: Sign up and Login)** of the Unacademy web application, based on the Agile Scrum test case design specifications.

---

## 🎯 Scope of Module 1: Sign up and Login

The framework automates **19 Test Cases (TC001 to TC019)** covering functional, negative, boundary, cross-browser, responsive/device, and security aspects:

| Test Case ID | Test Scenario | Description / Steps | Expected Result |
|---|---|---|---|
| **TC001** | `TS001` | Verify joining Unacademy with valid mobile number & valid OTP | User registers / navigates through OTP verification successfully |
| **TC002** | `TS002` | Verify joining with invalid mobile number | Registration blocked; validation prevents progression to OTP screen |
| **TC003** | `TS002` | Verify joining with invalid OTP | Invalid OTP validation is triggered; user remains on OTP screen |
| **TC004** | `TS002` | Verify joining with expired OTP / Resend OTP option | Resend OTP timer and option are displayed correctly |
| **TC005** | `TS003` | Verify sign up from Mobile device (Emulation) | Mobile viewport rendering is responsive and drawer functions properly |
| **TC006** | `TS003` | Verify sign up from Laptop (Desktop view) | Desktop/Laptop viewport layout renders seamlessly |
| **TC007** | `TS004` | Verify sign up using Google Chrome | Seamless sign up flow on Chrome browser |
| **TC008** | `TS004` | Verify sign up using Mozilla Firefox | Cross-browser compatibility verified on Firefox |
| **TC009** | `TS004` | Verify sign up using Microsoft Edge / Cross-browser | Cross-browser compatibility verified on Edge |
| **TC010** | `TS001` | Verify joining with valid mobile, OTP, name & state | Registration form captures Name and State of residence |
| **TC011** | `TS005` | Verify login using valid mobile number & OTP | Triggers OTP verification screen for registered mobile |
| **TC012** | `TS006` | Verify login using valid email & OTP | Toggles to email login and submits email OTP request |
| **TC013** | `TS007` | Verify login with invalid mobile/email | "Email is not valid" / validation error message displayed |
| **TC014** | `TS007` | Verify login with invalid OTP | OTP failure validation is triggered |
| **TC015** | `TS007` | Verify login with expired OTP / Resend countdown | Resend OTP countdown timer displayed |
| **TC016** | `TS008` | Verify login from Mobile device (Emulation) | Mobile viewport login drawer functions cleanly |
| **TC017** | `TS008` | Verify login from Laptop (Desktop view) | Laptop resolution login drawer functions cleanly |
| **TC018** | `TS009` | Verify login from India geographical location | Default country code prefix (+91) verified |
| **TC019** | `TS010` | Verify successful logout flow and redirection | Logout action redirects to Home/Login portal |

---

## 🏗️ Framework Architecture

```
Unacademy_Jira/
├── pom.xml                               # Maven project dependencies & plugins
├── testng.xml                            # TestNG suite runner with Extent listener
├── README.md                             # Project documentation
├── .gitignore
├── Reports/                              # Generated ExtentReports (HTML)
├── Screenshots/                          # Auto-captured screenshots (PASS & FAIL)
├── src/
│   └── test/
│       ├── java/
│       │   ├── base/
│       │   │   └── BaseTest.java         # Driver setup, config loader, browser options
│       │   ├── listeners/
│       │   │   └── ExtentTestNGListener.java # Captures test results, logs & screenshots
│       │   ├── pages/                    # Page Object Model (POM)
│       │   │   ├── HomePage.java         # Unacademy landing page elements & actions
│       │   │   └── AuthDrawer.java       # Sign Up & Login drawer (phone, email, OTP, state)
│       │   ├── tests/
│       │   │   └── Module1_SignUpAndLoginTest.java # TC001 to TC019 implementations
│       │   └── utilities/
│       │       ├── ConfigReader.java     # Configuration property parser
│       │       ├── ExcelUtility.java     # Apache POI Excel reader with DataFormatter
│       │       ├── ExtentManager.java    # ExtentSparkReporter singleton
│       │       └── ScreenshotUtility.java # Takes and saves timestamped screenshots
│       └── resources/
│           ├── config.properties         # Environment configs (URL, browser, timeouts)
│           └── testdata.xlsx             # Module 1 test cases and data
```

---

## 🚀 How to Run the Tests

### Prerequisites
* **Java JDK 17 or higher**
* **Apache Maven 3.8+**
* **Google Chrome** (Firefox optional)

### Running via Terminal
1. Navigate to the project root:
   ```bash
   cd /Users/aishmishra/Desktop/Unacademy
   ```
2. Run all tests via Maven:
   ```bash
   mvn clean test
   ```
3. Run with custom browser or headful mode:
   ```bash
   mvn test -Dbrowser=chrome -Dheadless=false
   ```

---

## 📊 Reports & Screenshots

* **Extent Reports:** Generated in the `Reports/` directory after each test run:
  ```
  Reports/DD-MM-YYYY_AutomationReport.html
  ```
  Open directly in browser:
  ```bash
  open Reports/*_AutomationReport.html
  ```
* **Screenshots:** Automatically captured on both passed steps and failure conditions into:
  ```
  Screenshots/
  ```

---

## 🔗 Repository
* GitHub Remote: [https://github.com/AishMishra001/Unacademy_Jira](https://github.com/AishMishra001/Unacademy_Jira)
