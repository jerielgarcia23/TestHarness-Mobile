package ErrorObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ErrorWithNoButtons implements ErrorMessage 
{
	public static String classFrame = "android.widget.FrameLayout";
	public static String textID = "shomi_notification_dialog_fragment_message_text";
	
	public static void handleError(WebDriver driver)
	{
		driver.findElement(By.id(textID)).click();
	}
}
