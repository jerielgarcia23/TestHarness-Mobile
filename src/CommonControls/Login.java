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
