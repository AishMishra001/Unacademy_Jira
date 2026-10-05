package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Dimension;

public class Display_Page {

    WebDriver driver;

    By aboutbutton = By.linkText("About Us");

    By title = By.tagName("h1");

    By missionvision = By.xpath("//*[contains(.,'OUR MISSION')]");

    By achievements = By.xpath("//*[contains(.,'journey')]");

    By openpositions = By.linkText("See open positions");

    By seeopenings = By.linkText("View all job openings");

    By getopenings = By.xpath("//a[contains(@class,'open-jobs-btn')]");

    By apply = By.xpath("//*[contains(.,'Open jobs available')]");

    By contact = By.xpath("//*[contains(.,'Contact')]");

    By email = By.xpath("//a[contains(@href,'mailto:')]");

    By facebook = By.xpath("//a[contains(@href,'facebook')]");

    By youtube = By.xpath("//a[contains(@href,'youtube')]");

    By linkedin = By.xpath("//a[contains(@href,'linkedin')]");

    public Display_Page(WebDriver driver) {
        this.driver = driver;
    }

    public void clickabout() {

        String parent = driver.getWindowHandle();

        driver.findElement(aboutbutton).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (String handle : driver.getWindowHandles()) {

            if (!handle.equals(parent)) {

                driver.switchTo().window(handle);

                break;
            }
        }
    }

    public boolean istitledisplayed() {
        return driver.findElement(title).isDisplayed();
    }

    public boolean isMissionVisionDisplayed() {
        return driver.findElement(missionvision).isDisplayed();
    }

    public boolean isAchievementsDisplayed() {
        return driver.findElement(achievements).isDisplayed();
    }

    public void clickOpenPositions() {
        driver.findElement(openpositions).click();
    }

    public void clickSeeAllOpenings() {
        driver.findElement(seeopenings).click();
    }

    public void clickgetAllOpenings() {

        WebElement element = driver.findElement(getopenings);

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        element);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    public boolean areJobsdisplayed() {
        return driver.findElement(apply).isDisplayed();
    }

    public boolean contactinfodisplayed() {
        return driver.findElement(contact).isDisplayed();
    }

    public boolean isEmailLinkDisplayed() {
        return driver.findElement(email).isDisplayed();
    }

    public boolean areSocialMediaLinksDisplayed() {

        return driver.findElement(facebook).isDisplayed()
                && driver.findElement(linkedin).isDisplayed()
                && driver.findElement(youtube).isDisplayed();
    }
    
    public boolean isResponsive() {

        driver.manage().window().setSize(new Dimension(1920, 1080));
        boolean desktop = driver.findElement(title).isDisplayed();

        driver.manage().window().setSize(new Dimension(768, 1024));
        boolean tablet = driver.findElement(title).isDisplayed();

        driver.manage().window().setSize(new Dimension(375, 812));
        boolean mobile = driver.findElement(title).isDisplayed();

        return desktop && tablet && mobile;
    }
}