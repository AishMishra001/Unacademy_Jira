package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login_POM {

	WebDriver driver;
	
	
	By uname=By.name("username");
	By pword=By.name("password");
	By loginbutton=By.xpath("//button[@type='submit']");
	By dashboard=By.xpath("///h6[text()='Dashboard']");
	
	public Login_POM(WebDriver driver2) {
		// TODO Auto-generated constructor stub
		this.driver=driver2;
	}

	public void enterusername(String username)
	{
		driver.findElement(uname).sendKeys(username);
	}
	
	public void enterpassword(String password)
	{
		driver.findElement(pword).sendKeys(password);
	}
	
	public void clicklogin()
	{
		driver.findElement(loginbutton).click();
	}
	
	public boolean isdashboarddisplayed()
	{
		return driver.findElement(dashboard).isDisplayed();
		
	}
	
}