package SampleUIAndConfig;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonControls.CarouselController;
import CommonControls.Login;
import CommonControls.ScrollingElement;
import PageObjects.CarouselPage;
import PageObjects.LandingPage;
import Utilities.AndroidLauncher;
import Utilities.WaitHandler;

public class CarouselTesting {

	AppiumDriver<MobileElement> driver;
	AndroidLauncher launcher;
	WaitHandler wait;
	AppiumDriverLocalService localService;
	private String appPath = "C:/sampleAPK/app-Stage-debug.apk";
	private String deviceName = "Android_Tablet"; // Test Android_Tablet

	@BeforeClass
	public void Setup() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// capabilities.setCapability("deviceName","Android");
		capabilities.setCapability("platformVersion", "4.4.2");
		capabilities.setCapability("app", appPath);
		capabilities.setCapability("deviceName", deviceName); // avd -> android
																// virtual
																// device,
																// Nexus_5X_API_23
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
		wait = new WaitHandler(driver);

		Login.login(driver, "test");
	}

	@AfterClass()
	public void tearDown() {
		//driver.quit();
	}
	

	@Test
	public void validateCarouselContents() {
		
		driver.findElement(By.id(LandingPage.carouselExample)).click();
		
		List<String> errors = new ArrayList<String>();
		MobileElement element = null;

		// validate elements have loaded
		
		element = driver.findElement(By.id(CarouselPage.carouselImage));
		if (element == null)
			errors.add("Carousel Image is missing");

		element = driver.findElement(By.id(CarouselPage.carouselTitle));
		if (element == null)
			errors.add("Carousel Title is missing");

		// validate description
		element = driver.findElement(By.id(CarouselPage.carouselDescription));
		if (element == null)
			errors.add("Carousel Description is missing");

		driver.navigate().back();
		
		if (errors.size() > 0) {
			String errorString = "";
			for (String error : errors) {
				errorString += error + " ";
			}
			Assert.fail(errorString);
		}
	}

	@Test
	public void adjustLeft() {
		
		driver.findElement(By.id(LandingPage.carouselExample)).click();
		
		List<String> errors = new ArrayList<String>();
		MobileElement element = null;

		// validate elements have loaded
		
		element = driver.findElement(By.id(CarouselPage.carouselImage));

		String titleText = driver
				.findElement(By.id(CarouselPage.carouselTitle)).getText();
		
		CarouselController.waitUntilIndexIsSelected(driver,3);
		
		int beforeIndex = CarouselController.getCurrentIndex(driver);
		
		CarouselController.carouselSwipeRight(driver, element);
		
		int afterIndex = CarouselController.getCurrentIndex(driver);
		String newTitleText = driver.findElement(
				By.id(CarouselPage.carouselTitle)).getText();

		if (titleText.equals(newTitleText)) {
			errors.add("Swipe has landed on the same object");
		}

		if (beforeIndex == afterIndex) {
			errors.add("Index remains unchanged after swipe");
		}

		driver.navigate().back();
		
		if (errors.size() > 0) {
			String errorString = "";
			for (String error : errors) {
				errorString += error + " ";
			}
			Assert.fail(errorString);
		}
	}

	@Test
	public void adjustRight() {
		
		driver.findElement(By.id(LandingPage.carouselExample)).click();
		
		List<String> errors = new ArrayList<String>();
		MobileElement element = null;

		// validate elements have loaded
		
		element = driver.findElement(By.id(CarouselPage.carouselImage));

		String titleText = driver
				.findElement(By.id(CarouselPage.carouselTitle)).getText();
		int beforeIndex = CarouselController.getCurrentIndex(driver);
		
		CarouselController.carouselSwipeLeft(driver, element);
		
		int afterIndex = CarouselController.getCurrentIndex(driver);
		String newTitleText = driver.findElement(
				By.id(CarouselPage.carouselTitle)).getText();

		if (titleText.equals(newTitleText)) {
			errors.add("Swipe has landed on the same object");
		}

		if (beforeIndex == afterIndex) {
			errors.add("Index remains unchanged after swipe");
		}
		
		driver.navigate().back();

		if (errors.size() > 0) {
			String errorString = "";
			for (String error : errors) {
				errorString += error + " ";
			}
			Assert.fail(errorString);
		}
	}

	@Test
	public void touchAndHold() {
		
		driver.findElement(By.id(LandingPage.carouselExample)).click();
		
		List<String> errors = new ArrayList<String>();
		MobileElement element = null;

		// validate elements have loaded
		
		element = driver.findElement(By.id(CarouselPage.carouselImage));

		String titleText = driver
				.findElement(By.id(CarouselPage.carouselTitle)).getText();
		int beforeIndex = CarouselController.getCurrentIndex(driver);
		CarouselController.touchAndHold(driver, element);
		int afterIndex = CarouselController.getCurrentIndex(driver);
		String newTitleText = driver.findElement(
				By.id(CarouselPage.carouselTitle)).getText();

		if (!titleText.equals(newTitleText)) {
			errors.add("Touching the carousel does not prevent it from scolling on it's own");
		}

		if (beforeIndex != afterIndex) {
			errors.add("Touch and hold has not prevented the Index from changing ");
		}
		
		driver.navigate().back();

		if (errors.size() > 0) {
			String errorString = "";
			for (String error : errors) {
				errorString += error + " ";
			}
			Assert.fail(errorString);
		}
	}
	
	@Test
	public void loopToEnd() {
		
		driver.findElement(By.id(LandingPage.carouselExample)).click();
		
		List<String> errors = new ArrayList<String>();
		MobileElement element = null;

		// validate elements have loaded
		
		element = driver.findElement(By.id(CarouselPage.carouselImage));
		System.out.println("");
		System.out.println("");
		System.out.println("");
		CarouselController.waitUntilIndexIsSelected(driver,2);
		CarouselController.goToFirstIndex(driver, element);
		int size = CarouselController.getIndexMaxSize(driver) - 1;
		
		CarouselController.carouselSwipeRightUntilIndexed(driver, size-1);
		int current = CarouselController.getCurrentIndex(driver);
		
		
		if (size != current)
			errors.add("Swiping left from the first position does not bring you to the last index");
		
		driver.navigate().back();
		
		if (errors.size() > 0) {
			String errorString = "";
			for (String error : errors) {
				errorString += error + " ";
			}
			Assert.fail(errorString);
		}
	}
	
	@Test
	public void loopToStart() {
		
		driver.findElement(By.id(LandingPage.carouselExample)).click();
		
		List<String> errors = new ArrayList<String>();
		MobileElement element = null;

		// validate elements have loaded
		
		element = driver.findElement(By.id(CarouselPage.carouselImage));
		System.out.println("");
		System.out.println("");
		System.out.println("");
		CarouselController.waitUntilIndexIsSelected(driver,2);
		CarouselController.goToFirstIndex(driver, element);
		int size = 0;
		
		CarouselController.carouselSwipeLeftUntilIndexed(driver, 0);
		int current = CarouselController.getCurrentIndex(driver);
		
		
		if (size != current)
			errors.add("Swiping left from the first position does not bring you to the last index");
		
		driver.navigate().back();
		
		if (errors.size() > 0) {
			String errorString = "";
			for (String error : errors) {
				errorString += error + " ";
			}
			Assert.fail(errorString);
		}
	}
}
