package Utilities;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.jetty.html.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;







import Utilities.ReadFileData;

import CommonControls.DeviceController;
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
import static org.junit.Assert.*;




public class AppiumSetup {

	private static Properties properties = ReadFileData.readDataFile();

	
	public AppiumSetup()  {
		// TODO Auto-generated constructor stub
	
	}



	
	
	
	
	public AppiumDriver<AndroidElement> AppiumSetupD(AppiumDriver<AndroidElement> driver, WaitHandler wait, DesiredCapabilities capabilities) throws Exception {
		// TODO Auto-generated constructor stub
		
		
		//Starting Appium Server
		AppiumServer server = new AppiumServer();
		server.start();
		
	
		//Launching emulator
		AndroidLauncher launcher = new AndroidLauncher();
		launcher.launchEmulator(properties.getProperty("deviceName"));
		
		
		
		//Configuring device with apk
		capabilities.setCapability("platformVersion", properties.getProperty("AndroidVersion")) ;
	    capabilities.setCapability("app", properties.getProperty("appPath"));
	    capabilities.setCapability("deviceName", properties.getProperty("deviceName")); // avd -> android virtual device, Nexus_5X_API_23	
	       
	    driver = new AndroidDriver<>(new URL(properties.getProperty("host")), capabilities);
	    wait = new WaitHandler(driver);
	        
		//Performing the login click       
	    driver.findElement(By.id(LoginPage.performLogin)).click();
	        
	      //Return the driver to be used in SampleTest	
	        	return driver;
	        	
	        	
	}




	
	

	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
