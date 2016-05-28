package ErrorObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SnackBar {
	
	public static String snackBarID = "snackbar_text";
	
	public static Boolean confirmSnackBar(WebDriver driver)
	{
		Boolean isVisible = driver.findElement(By.id(snackBarID)).isDisplayed();
		return isVisible;
	}

}
