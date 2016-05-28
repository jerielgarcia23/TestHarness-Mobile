package ErrorObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ErrorWithOneButton extends ErrorWithNoButtons
{
	protected static String buttonYes = "shomi_notification_dialog_fragment_button1";
	
	public static void handleError(WebDriver driver)
	{
		driver.findElement(By.id(buttonYes)).click();
	}
}
