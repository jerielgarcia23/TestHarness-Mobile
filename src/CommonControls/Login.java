package CommonControls;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import PageObjects.LoginPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


public class Login {

	public static void login(AppiumDriver driver, String credentials)
	{
		
		try
		{
			driver.findElement(By.id("fragment_shomi_notification_dialog_button1")).click();
			//Alert alert = driver.switchTo().alert();
		    //alert.getText();
		    //alert.accept();
		    DeviceController.enableCommsData(driver);
		        //Intent intent = new Intent("com.twidroid.SendTweet");
		}
		catch(Exception e){}
		driver.findElement(By.id(LoginPage.performLogin)).click();
		List<MobileElement> elements = driver.findElements(By.className("android.widget.RadioButton"));
		for (MobileElement element : elements)
		{
			if (element.getAttribute("text").equals(credentials))
				element.click();
		}
		driver.findElement(By.id(LoginPage.okayButton)).click();
	}
}
