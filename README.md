Unacademy Module 3: Start Learning & Course Selection
 
Project Overview
 
This project automates key user journeys for Module 3 — Start Learning and Course Selection of the Unacademy platform using Java, Selenium WebDriver, TestNG, Maven, and ExtentReports.
 
The framework follows the Page Object Model (POM) to keep test cases organized, reusable, and maintainable.
 
Technologies Used
 
- Java
- Selenium WebDriver
- TestNG
- Maven
- ExtentReports
- WebDriverManager
- Git and GitHub
 
Module 3 Test Scenarios
 
The project includes the following automated test cases:
 
1. Course Exploration — verifies navigation and course exploration.
2. Category Filter — verifies filtering courses by category, including History.
3. Add to Goal — verifies selecting a course and adding it to a learning goal.
4. Goal Persistence — checks whether the selected goal persists after refreshing the page.
 
Project Structure
 
Unacademy_Module3/
├── src/
│   ├── main/java/
│   │   ├── base/
│   │   │   └── BaseClass.java
│   │   ├── pages/
│   │   │   ├── CoursePage.java
│   │   │   ├── ExplorePage.java
│   │   │   └── GoalPage.java
│   │   └── utilities/
│   │       └── ExtentReportManager.java
│   └── test/java/
│       ├── base/
│       │   └── BaseTest.java
│       ├── listeners/
│       │   └── ExtentTestNGListener.java
│       ├── testcases/
│       │   ├── Module3TestListener.java
│       │   ├── TC_Module3_AddToGoal.java
│       │   ├── TC_Module3_CategoryFilter.java
│       │   ├── TC_Module3_CourseExploration.java
│       │   └── TC_Module3_GoalPersistence.java
│       └── utilities/
│           ├── ExcelUtility.java
│           ├── ExtentManager.java
│           └── ScreenshotUtility.java
├── Reports/
├── Screenshots/
├── pom.xml
└── testng.xml
 
Prerequisites
 
- Java JDK compatible with the project configuration
- Maven
- Google Chrome
- Eclipse IDE or another Java IDE
 
How to Run
 
1. Clone or download this repository.
2. Import the project into Eclipse as a Maven project.
3. Allow Maven to download the required dependencies.
4. Run "testng.xml" as a TestNG suite, or execute the tests using Maven.
 
mvn clean test
 
Reports and Screenshots
 
- Reports/ contains the Module 3 automation report.
- Screenshots/ contains screenshots captured during Module 3 test execution.
 
Open "Reports/Module3AutomationReport.html" in a browser to view the saved report.
 
Author
 
Anshika Sharma
 
Module: Module 3 — Start Learning & Course Selection
 
This repository branch contains the Module 3 automation work and its supporting framework files.
 
## 🔗 Repository
* GitHub Remote: [https://github.com/AishMishra001/Unacademy_Jira](https://github.com/AishMishra001/Unacademy_Jira)
