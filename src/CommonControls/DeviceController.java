package CommonControls;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ScreenOrientation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;

public class DeviceController {

	public static void rotateDeviceLandscape(AppiumDriver driver)
	{
		
		driver.rotate(ScreenOrientation.LANDSCAPE);
	}
	
	public static void rotateDevicePortrait(AppiumDriver driver)
	{
		
		driver.rotate(ScreenOrientation.PORTRAIT);
	}
	
	public static void enableCommsWifi(AppiumDriver driver) // only for android
	{
		
		((AndroidDriver)driver).setNetworkConnection(new NetworkConnectionSetting(false, true, false)); // The params are (boolean airplaneMode, boolean wifi, boolean data)
	}
	
	public static void enableCommsData(AppiumDriver driver) // only for android
	{
		((AndroidDriver)driver).setNetworkConnection(new NetworkConnectionSetting(false, true, true)); // The params are (boolean airplaneMode, boolean wifi, boolean data)
	}
	
	public static void enableCommsAirplaneMode(AppiumDriver driver) // only for android
	{
		
		((AndroidDriver)driver).setNetworkConnection(new NetworkConnectionSetting(true, false, false)); // The params are (boolean airplaneMode, boolean wifi, boolean data)
	}
	
	public static void enableDefaultSettings(AppiumDriver driver)
	{
		((AndroidDriver)driver).setNetworkConnection(new NetworkConnectionSetting(false, true, true)); // The params are (boolean airplaneMode, boolean wifi, boolean data)
	}
	
	public static void allFalse(AppiumDriver driver)
	{
		((AndroidDriver)driver).setNetworkConnection(new NetworkConnectionSetting(false, false, false)); // The params are (boolean airplaneMode, boolean wifi, boolean data)
	}
	
	
}
