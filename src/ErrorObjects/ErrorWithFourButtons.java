package ErrorObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ErrorWithFourButtons extends ErrorWithThreeButtons 
{
		protected static String buttonFour = "shomi_notification_dialog_fragment_button4";
		
		public static void clickButtonFour(WebDriver driver)
		{
			driver.findElement(By.id(buttonFour)).click();
		}
}
