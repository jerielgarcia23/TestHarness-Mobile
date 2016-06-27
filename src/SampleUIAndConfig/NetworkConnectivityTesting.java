package SampleUIAndConfig;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.HasNetworkConnection;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.jetty.html.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import CommonControls.DeviceController;
import CommonControls.Login;
import CommonControls.ScrollingElement;
import ErrorObjects.Alert;
import ErrorObjects.ErrorWithFourButtons;
import ErrorObjects.ErrorWithNoButtons;
import ErrorObjects.ErrorWithOneButton;
import ErrorObjects.ErrorWithThreeButtons;
import ErrorObjects.ErrorWithTwoButtons;
import ErrorObjects.SnackBar;
import PageObjects.ErrorActivity;
import PageObjects.LandingPage;
import PageObjects.LoginPage;
import PageObjects.MoviePDP;
import PageObjects.PDPExamples;
import Utilities.AndroidLauncher;
import Utilities.AndroidPackageManipulator;
import Utilities.AppiumServer;
import Utilities.DBReader;
import Utilities.LatencyChecker;
import Utilities.Logger;
import Utilities.WaitHandler;

public class NetworkConnectivityTesting 
{
	AppiumDriver<MobileElement> driver;
	AndroidLauncher launcher;
	WaitHandler wait;
	AppiumDriverLocalService localService;
	private String appPath = "C:/sampleAPK/app-Stage-debug.apk";
	private String deviceName = "Android_Tablet"; //Test Android_Tablet
	
	
	@BeforeClass
	public void Setup() throws Exception
	{
		
		AndroidPackageManipulator apm = new AndroidPackageManipulator(appPath);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability("deviceName","Android");
        capabilities.setCapability("platformVersion", "4.4.2");
        capabilities.setCapability("app", appPath);
        capabilities.setCapability("deviceName", deviceName); // avd -> android virtual device, Nexus_5X_API_23	
        //capabilities.setCapability(capabilityName, value);
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        //DeviceController.enableCommsData(driver);
        wait = new WaitHandler(driver);

        Login.login(driver, "test");
	}
	
	@AfterClass
	public void TearDown() throws Exception
	{
		DeviceController.enableCommsData(driver);
	}
	
	@Test
	public void airplaneProducesConnectivityError()
	{
		DeviceController.enableCommsAirplaneMode(driver);
		//DeviceController.allFalse(driver);
		driver.findElement(By.id(LandingPage.pdpExamples)).click();
		
		try
		{
			driver.findElement(By.id(PDPExamples.moviePDPUnderworld)).click();
			driver.findElement(By.id("fragment_shomi_notification_dialog_button1")).click();
		}
		catch (Exception e)
		{
			Assert.fail("Error message not displayed");
		}
	}
	
	//@Test
	public void lackOfConnectivityProducesError()
	{
		
	}
	
	
}
