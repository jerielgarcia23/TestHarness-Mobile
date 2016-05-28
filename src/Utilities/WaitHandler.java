package Utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHandler 
{
	WebDriverWait wait;
	WebDriver driver;
	public WaitHandler(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10000);
	}
	
	public void waitForPageLoad()
	{
		try 
		{
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}
		catch (Exception e)
		{
			this.waitForPageLoad();
		}
	}
}
