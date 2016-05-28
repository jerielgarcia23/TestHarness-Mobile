package ErrorObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ErrorWithTwoButtons extends ErrorWithOneButton 
{
	protected static String buttonNo = "shomi_notification_dialog_fragment_button2";
	
	public static void clickButtonOne(WebDriver driver) // Default value is: yes
	{
		driver.findElement(By.id(buttonYes)).click();
	}
	
	public static void clickButtonTwo(WebDriver driver)
	{
		driver.findElement(By.id(buttonNo)).click(); // Default value is: no
	}
}
