package CommonControls;

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
		((AndroidDriver)driver).setNetworkConnection(new NetworkConnectionSetting(false, true, false)); // The params are data/wifi/airplane mode	
	}
	
	public static void enableCommsData(AppiumDriver driver) // only for android
	{
		((AndroidDriver)driver).setNetworkConnection(new NetworkConnectionSetting(false, true, false)); // The params are data/wifi/airplane mode	
	}
	
	public static void enableCommsAirplaneMode(AppiumDriver driver) // only for android
	{
		((AndroidDriver)driver).setNetworkConnection(new NetworkConnectionSetting(true, true, true)); // The params are data/wifi/airplane mode
	}
}
