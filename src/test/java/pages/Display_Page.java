package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Display_Page {

	WebDriver driver;
	
	By aboutbutton=By.xpath("//a[text()='About Us']");
	By title=By.tagName("h1");
	By missionvision = By.cssSelector(".epbj6e82");
	By achievements = By.xpath("//*[contains(text(),'journey')]");
	By openpositions = By.linkText("See open positions");
	By seeopenings = By.linkText("View all job openings");
	By getopenings = By.xpath("//a[@class = 'db-btn style-primary open-jobs-btn']");
	By apply = By.xpath("//span[text()= 'Open jobs available']");
	By contact = By.xpath("//*[contains(text(),'Contact')]");
	By email = By.xpath("//a[contains(@href,'mailto')]");
	By facebook = By.xpath("//a[contains(@href,'facebook.com')]");
	By youtube = By.xpath("//a[contains(@href,'youtube.com')]");
	By linkedin = By.xpath("//a[contains(@href,'linkedin.com')]");
	
	public Display_Page(WebDriver driver2) {
		// TODO Auto-generated constructor stub
		this.driver=driver2;
	}
	
	public void clickabout()
	{
		driver.findElement(aboutbutton).click();
	}
	
	public boolean istitledisplayed()
	{
		return driver.findElement(title).isDisplayed();
	}
	
	public boolean isMissionVisionDisplayed()
	{
	    return driver.findElement(missionvision).isDisplayed();
	}
	
	public boolean isAchievementsDisplayed()
	{
	    return driver.findElement(achievements).isDisplayed();
	}
	
	public void clickOpenPositions()
	{
		driver.findElement(openpositions).click();
	}
	
	public void clickSeeAllOpenings()
	{
		driver.findElement(seeopenings).click();
	}
	
	public void clickgetAllOpenings()
	{
		driver.findElement(getopenings).click();
	}
	
	public boolean areJobsdisplayed()
	{
		return driver.findElement(apply).isDisplayed();
	}
	
	public boolean contactinfodisplayed()
	{
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
}