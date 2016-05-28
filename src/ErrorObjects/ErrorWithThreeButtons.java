package ErrorObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ErrorWithThreeButtons extends ErrorWithTwoButtons
{
	protected static String buttonThree = "shomi_notification_dialog_fragment_button3";
	
	public static void clickButtonThree(WebDriver driver)
	{
		driver.findElement(By.id(buttonThree)).click();
	}
}
