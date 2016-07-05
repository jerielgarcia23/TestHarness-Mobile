package SampleUIAndConfig;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
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
import org.testng.annotations.Listeners;
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
import PageObjects.CarouselPage;
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
import Utilities.TestReporter;
import Utilities.WaitHandler;

@Listeners(TestReporter.class)
public class SampleTest {

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

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void sample() throws Exception {
		System.out.println("Driver settings : " + driver.getSettings());
		Assert.assertNotNull(driver.getSettings());
	}

	// @Test
	public void sampleUIManipulation() {
		driver.findElement(By.id("home_screen_activity_example_request_button"))
				.click();
	}

	// @Test
	public void sampleDBConnectionTest() throws Exception {
		DBReader dbReader = new DBReader("172.19.3.42:1433", "DBS_Savvis",
				"DBreader", "Cb7Qz[6F50R[%jG2");
		dbReader.query("select top 10 * from MediaQAs");
	}

	// @Test
	public void sampleADBUninstallThenInstall() throws Exception {
		AndroidPackageManipulator apm = new AndroidPackageManipulator(
				"C:\\sampleAPK\\app-Dev-debug.apk");
		apm.cleanBuild();
	}

	// @Test
	public void sampleLogging() throws Exception {
		Logger logger = new Logger();
		logger.write("Sample String");
	}

	// @Test
	public void handleErrorMessages() throws Exception {
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

		// snackbar
		driver.findElement(By.id(ErrorActivity.snackbar)).click();
		SnackBar.confirmSnackBar(driver);
		driver.navigate().back();
	}

	@Test
	public void handleListUIElement() {
		// Navigate to list page
		driver.findElement(By.id(LandingPage.examplList)).click();
		// wait.waitForPageLoad();

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		String tempID = "pagination_example_activity_list";
		MobileElement sampleElement = driver.findElement(By.id(tempID));
		List<MobileElement> targetElements = driver.findElements(By
				.className("LinearLayout"));

		ScrollingElement scroller = new ScrollingElement();
		scroller.scrollLeftUntilElementIsVisible(driver, sampleElement,
				"Bugged");
		driver.navigate().back();
	}

	// @Test
	public void handleGridUIElement() {
		// Navigate to list page
		driver.findElement(By.id(LandingPage.examplList)).click();
		// wait.waitForPageLoad();
		String tempID = "pagination_example_activity_grid";
		MobileElement sampleElement = driver.findElement(By.id(tempID));
		driver.swipe(sampleElement.getCenter().getX(), sampleElement
				.getCenter().getY(), sampleElement.getCenter().getX(),
				sampleElement.getCenter().getY() + 100, 500);
		driver.navigate().back();
	}

	// @Test
	public void validateMoviePDPElemnts() {
		// Navigate to Underworld pdp
		List<String> errors = new ArrayList<String>();

		// Go to pdp page
		driver.findElement(By.id(LandingPage.pdpExamples)).click();

		// Select underworld for validation
		driver.findElement(By.id(PDPExamples.moviePDPUnderworld)).click();

		// validating that ui components exist
		MobileElement element = null;

		element = driver.findElement(By.id(MoviePDP.actionBar));
		ScrollingElement.scrollToElement(driver, element);
		if (element == null)
			errors.add("Action Bar is missing");

		element = driver.findElement(By.id(MoviePDP.heroImage));
		ScrollingElement.scrollToElement(driver, element);
		if (element == null)
			errors.add("Movie PDP Hero Image is missing");

		element = driver.findElement(By.id(MoviePDP.playButton));
		ScrollingElement.scrollToElement(driver, element);
		if (element == null)
			errors.add("Hero Image play button is missing");

		element = driver.findElement(By.id(MoviePDP.addShomiLaterButton));
		ScrollingElement.scrollToElement(driver, element);
		if (element == null)
			errors.add("Add to Shomi Later button is missing");

		element = driver.findElement(By.id(MoviePDP.title));
		ScrollingElement.scrollToElement(driver, element);
		if (element == null)
			errors.add("Title is missing");

		element = driver.findElement(By.id(MoviePDP.director));
		ScrollingElement.scrollToElement(driver, element);
		if (element == null)
			errors.add("Director is missing");

		element = driver.findElement(By.id(MoviePDP.genre));
		ScrollingElement.scrollToElement(driver, element);
		if (element == null)
			errors.add("Genre is missing");

		element = driver.findElement(By.id(MoviePDP.year));
		ScrollingElement.scrollToElement(driver, element);
		if (element == null)
			errors.add("Year is missing");

		element = driver.findElement(By.id(MoviePDP.maturityRating));
		ScrollingElement.scrollToElement(driver, element);
		if (element == null)
			errors.add("Maturity Rating is missing");

		element = driver.findElement(By.id(MoviePDP.starRating));
		ScrollingElement.scrollToElement(driver, element);
		if (element == null)
			errors.add("Star Rating is missing");

		element = driver.findElement(By.id(MoviePDP.duration));
		ScrollingElement.scrollToElement(driver, element);
		if (element == null)
			errors.add("Duration is missing");

		element = driver.findElement(By.id(MoviePDP.description));
		// ScrollingElement.scrollToElement(driver,element);
		if (element == null)
			errors.add("Description is missing");

		element = driver.findElement(By.id(MoviePDP.actors));
		ScrollingElement.scrollToElement(driver, element);
		if (element == null)
			errors.add("Actors are missing");

		if (deviceName.contains("Tablet")) {
			element = driver.findElement(By.id(MoviePDP.tabletOnlyBoxArt));
			ScrollingElement.scrollToElement(driver, element);
			if (!element.isEnabled())
				errors.add("Box art is not found for tablet in PDP Page");
		}

		// setting the default test location to be the landing page.
		driver.navigate().back();
		driver.navigate().back();

		// Validation of errors
		if (errors.size() > 0)
			Assert.fail(errors.toString());

	}

	// @Test
	public void validateMoviePDPContent() {
		MobileElement element = null;
		boolean snackbar;
		List<String> errors = new ArrayList<String>();

		// Go to pdp page
		driver.findElement(By.id(LandingPage.pdpExamples)).click();

		// Select underworld for validation
		driver.findElement(By.id(PDPExamples.moviePDPUnderworld)).click();

		// driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);

		// validating that ui components exist

		// Clicking play -> expecting snackbar
		driver.findElement(By.id(MoviePDP.playButton)).click();
		snackbar = SnackBar.confirmSnackBar(driver);
		if (!snackbar)
			errors.add("Snackbar not displayed on clicking PLAY");

		// Clicking "+" -> expecting snackbar
		driver.findElement(By.id(MoviePDP.addShomiLaterButton)).click();
		snackbar = SnackBar.confirmSnackBar(driver);
		if (!snackbar)
			errors.add("Snackbar not displayed on clicking shomi later");

		// Hard coded values for verification at this point.
		String title = "Underworld: Evolution";
		String director = "Len Wiseman";
		String genre = "Horror, Action & Adventure";
		String year = "2006";
		String maturityRating = "25";
		String starRating = "3.0";
		String duration = "106 min";
		String description = "As the war between the Death Dealers and the Lycans rages on, Selene, the vampire warrior, and Michael, the werewolf hybrid, work together in an effort to unlock the secrets of their respective bloodlines.";
		String actors = "Kate Beckinsale, Scott Speedman, Tony Curran";
		//

		// validating pdp string content
		element = driver.findElement(By.id(MoviePDP.title));
		if (!element.getText().equals(title))
			errors.add("Title does not match expected results");

		element = driver.findElement(By.id(MoviePDP.director));
		if (!element.getText().equals(director))
			errors.add("Director does not match expected results");

		element = driver.findElement(By.id(MoviePDP.genre));
		if (!element.getText().equals(genre))
			errors.add("Genre does not match expected results");

		element = driver.findElement(By.id(MoviePDP.year));
		if (!element.getText().equals(year))
			errors.add("Year does not match expected results");

		element = driver.findElement(By.id(MoviePDP.maturityRating));
		if (!element.getText().equals(maturityRating))
			errors.add("Maturity Rating does not match expected results");

		element = driver.findElement(By.id(MoviePDP.starRating));
		if (!element.getText().equals(starRating))
			errors.add("Star Rating does not match expected results");

		element = driver.findElement(By.id(MoviePDP.duration));
		if (!element.getText().equals(duration))
			errors.add("Duration does not match expected results");

		element = driver.findElement(By.id(MoviePDP.description));
		ScrollingElement.swipeDownToElement(driver, element);
		if (!element.getText().equals(description))
			errors.add("Description does not match expected results");

		element = driver.findElement(By.id(MoviePDP.actors));
		ScrollingElement.swipeDownToElement(driver, element);
		if (!element.getText().equals(actors))
			errors.add("Actors do not match expected results");

		// setting the default test location to be the landing page.
		driver.navigate().back();
		driver.navigate().back();

		if (errors.size() > 0)
			Assert.fail(errors.toString());
	}

	
}
