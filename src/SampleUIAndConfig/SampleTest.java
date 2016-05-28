package SampleUIAndConfig;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import CommonControls.ScrollingElement;
import ErrorObjects.ErrorWithFourButtons;
import ErrorObjects.ErrorWithNoButtons;
import ErrorObjects.ErrorWithOneButton;
import ErrorObjects.ErrorWithThreeButtons;
import ErrorObjects.ErrorWithTwoButtons;
import ErrorObjects.SnackBar;
import PageObjects.ErrorActivity;
import PageObjects.LandingPage;
import Utilities.AndroidPackageManipulator;
import Utilities.AppiumServer;
import Utilities.DBReader;
import Utilities.LatencyChecker;
import Utilities.Logger;
import Utilities.WaitHandler;

public class SampleTest {
	
	AppiumDriver<AndroidElement> driver;
	Util util;
	WaitHandler wait;
	AppiumDriverLocalService localService;
	
	// Required Capabilities
	@BeforeClass
	public void Setup() throws Exception
	{
	
		
		//AppiumServerManipulator appiumServer = new AppiumServerManipulator();
		//appiumServer.startServer();
		
		AppiumServer server = new AppiumServer();
		server.start();
		
		AndroidPackageManipulator apm = new AndroidPackageManipulator("C:/sampleAPK/app-debug-Fedir.apk");
        //apm.launchAppiumServer(localService);
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability("deviceName","Android");
        capabilities.setCapability("platformVersion", "4.4.2");
        capabilities.setCapability("app", "C:/sampleAPK/app-debug-Fedir.apk");
        capabilities.setCapability("deviceName", "Test"); // avd -> android virtual device, Nexus_5X_API_23
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        //driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities); // started through console
        util = new Util();
        wait = new WaitHandler(driver);
        
        /*try {
			apm.cleanBuild();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	
  //@Test
  public void sample() throws Exception 
  {
	  System.out.println("Driver settings : " + driver.getSettings());
	  Assert.assertNotNull(driver.getSettings());
  }
  
  //@Test
  public void sampleUIManipulation()
  {
	 driver.findElement(By.id("home_screen_activity_example_request_button")).click();
  }
  
  //@Test 
  public void sampleDBConnectionTest() throws Exception 
  {
	  DBReader dbReader = new DBReader("172.19.3.42:1433", "DBS_Savvis", "DBreader", "Cb7Qz[6F50R[%jG2");
	  dbReader.query("select top 10 * from MediaQAs");
  }
  
  //@Test
  public void sampleADBUninstallThenInstall() throws Exception
  {
	  AndroidPackageManipulator apm = new AndroidPackageManipulator("C:\\sampleAPK\\app-debug.apk");
	  apm.cleanBuild();
  }
  
  //@Test
  public void sampleLogging() throws Exception
  {
	  Logger logger = new Logger();
	  logger.write("Sample String");
  }
  
  //@Test
  public void sampleReflectionTEst() throws Exception
  {
	  LatencyChecker checker = new LatencyChecker(50,500);
	  Method functRef = Util.class.getMethod("clickElement");
	  checker.getLatency(functRef, util,"home_screen_activity_example_request_button");
  }
  
  //@Test
  public void handleErrorMessages() throws Exception
  {
	  driver.findElement(By.id(LandingPage.showErrorActivity)).click();
	  
	  // activate error display with no buttons
	  driver.findElement(By.id(ErrorActivity.errorWithNoButtons)).click();
	  ErrorWithNoButtons.handleError(driver);
	  
	  // activate error display with one buttons
	  driver.findElement(By.id(ErrorActivity.errorWithOneButton)).click();
	  ErrorWithOneButton.handleError(driver);
	  
	  // activate error display with two buttons
	  driver.findElement(By.id(ErrorActivity.errorWithTwoButtons)).click();
	  ErrorWithTwoButtons.handleError(driver);
	  
	  // activate error display with three buttons
	  driver.findElement(By.id(ErrorActivity.errorWithThreeButtons)).click();
	  ErrorWithThreeButtons.handleError(driver);
	  
	  // activate error display with four buttons
	  driver.findElement(By.id(ErrorActivity.errorWithFourButtons)).click();
	  ErrorWithFourButtons.handleError(driver);
	  
	  //snackbar
	  driver.findElement(By.id(ErrorActivity.snackbar)).click();
	  SnackBar.confirmSnackBar(driver);
  }
  
  @Test
  public void handleListUIElement()
  {
	  // Navigate to list page
	  driver.findElement(By.id(LandingPage.examplList)).click();
	  // wait.waitForPageLoad();
	  
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  
	  String tempID = "pagination_example_activity_list";
	  MobileElement sampleElement = driver.findElement(By.id(tempID));
	  List<AndroidElement> targetElements = driver.findElements(By.className("LinearLayout"));
	  
	  ScrollingElement scroller = new ScrollingElement();
	  
	  System.out.println("Before swipe");
	  //scroller.scrollLeft(driver, sampleElement);// targetElements.get(8));
	  scroller.scrollLeftUntilElementIsVisible(driver, sampleElement, "Bean");
	  System.out.println("After swipe");
  }
  
  //@Test
  public void handleGridUIElement()
  {
	  // Navigate to list page
	  driver.findElement(By.id(LandingPage.examplList)).click();
	  // wait.waitForPageLoad();
	  String tempID = "pagination_example_activity_grid";
	  MobileElement sampleElement = driver.findElement(By.id(tempID));
	  System.out.println("Before swipe");
	  
	  driver.swipe(sampleElement.getCenter().getX(), sampleElement.getCenter().getY(), sampleElement.getCenter().getX()  , sampleElement.getCenter().getY()  + 100, 500);

	  System.out.println("After swipe");
  }
  
  
  public class Util{
	  public Util(){
		  
	  }
	  
	  public void clickElement(String str)
	  {
		driver.findElement(By.id(str)).click();
	  }
  }
}
 