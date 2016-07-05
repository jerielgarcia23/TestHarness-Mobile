package CommonControls;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CarouselPage;
import PageObjects.LandingPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class CarouselController {
	
	public static void touchAndHold(AppiumDriver driver, MobileElement scrollableElement)
	{
		driver.swipe(scrollableElement.getCenter().getX(), scrollableElement.getCenter().getY(), scrollableElement.getCenter().getX(), scrollableElement.getCenter().getY(), 10000);
	}	
	
	public static void touchAndHold(AppiumDriver driver, MobileElement scrollableElement, int duration)
	{
		System.out.println("--------------------------------------------------------------------------Touch and hold");
		driver.swipe(scrollableElement.getCenter().getX(), scrollableElement.getCenter().getY(), scrollableElement.getCenter().getX(), scrollableElement.getCenter().getY(), duration);
	}
	
	public static int getCurrentIndex(AppiumDriver driver)
	{
		MobileElement parent = (MobileElement) driver.findElement(By.id(CarouselPage.carouselIndex));
		List<MobileElement> indexies = parent.findElements(By.className("android.view.View"));
		
		int activeIndex = 0;
		boolean found = false;
		for (MobileElement index : indexies)
		{
			//System.out.println(index.getAttribute("selected"));
			if (index.getAttribute("selected").equals("true"))
			{
				found = true;
				break;
			}
			
			activeIndex++;
		}
		if (!found)
			activeIndex = getCurrentIndex(driver);
		
		return activeIndex;
	}
	
	public static int getIndexMaxSize(AppiumDriver driver)
	{
		MobileElement parent = (MobileElement) driver.findElement(By.id(CarouselPage.carouselIndex));
		List<MobileElement> indexies = parent.findElements(By.className("android.view.View"));
		return indexies.size();
	}
	
	public static void goToFirstIndex(AppiumDriver driver, MobileElement element)
	{
		MobileElement parent = (MobileElement) driver.findElement(By.id(CarouselPage.carouselIndex));
		List<MobileElement> indexies = parent.findElements(By.className("android.view.View"));
		touchAndHold(driver, element, 500);
		System.out.println("-----------------------------------" + indexies.get(0).getAttribute("selected"));
		
		while (!indexies.get(0).getAttribute("selected").equals("true"))
		{	
			carouselSwipeRight(driver, element);
			System.out.println("Swiped left");
		}
		
	}
	
	public static void goToLastIndex(AppiumDriver driver, MobileElement element)
	{
		MobileElement parent = (MobileElement) driver.findElement(By.id(CarouselPage.carouselIndex));
		List<MobileElement> indexies = parent.findElements(By.className("android.view.View"));
		touchAndHold(driver, element, 500);
		
		while (!indexies.get(indexies.size()-1).getAttribute("selected").equals("true"))
		{
			touchAndHold(driver, element, 500);
			carouselSwipeRight(driver, element);
		}
	}
	
	public static void waitUntilIndexIsSelected(AppiumDriver driver, int index)
	{
		MobileElement parent = (MobileElement) driver.findElement(By.id(CarouselPage.carouselIndex));
		List<MobileElement> indexies = parent.findElements(By.className("android.view.View"));
		
		WebDriverWait wait = new WebDriverWait(driver,200);
		
		wait.until(ExpectedConditions.attributeToBe(indexies.get(index), "selected", "true"));
	}
	
	public static void carouselSwipeLeft(AppiumDriver driver, MobileElement element)
	{

		int width, height;
		height = driver.manage().window().getSize().getHeight(); // center height
		width = driver.manage().window().getSize().getWidth() / 2; // center width
		int startWidth = driver.manage().window().getSize().getWidth() - 10;
		int swipeDistance = width * 5 / 6;
		int offSet = 0;
		swipeDistance += offSet;
		
		driver.swipe(startWidth, element.getCenter().getY(), startWidth - swipeDistance, element.getCenter().getY(), 500);
	}
	
	public static void carouselSwipeLeftUntilIndexed(AppiumDriver driver, int index)
	{
		MobileElement parent = (MobileElement) driver.findElement(By.id(CarouselPage.carouselIndex));
		List<MobileElement> indexies = parent.findElements(By.className("android.view.View"));
		
		while (!indexies.get(index).getAttribute("selected").equals("true"))
		{
			carouselSwipeLeft(driver, (MobileElement)driver.findElement(By.id(CarouselPage.carouselImage)));
		}
	}
	
	public static void carouselSwipeRight(AppiumDriver driver, MobileElement element)
	{
		int width, height;
		height = driver.manage().window().getSize().getHeight(); // center height
		width = driver.manage().window().getSize().getWidth() / 2; // center width
		int startWidth = 10;
		int swipeDistance = width * 5 / 6;
		int offSet = 0;
		swipeDistance += offSet;
		
		driver.swipe(startWidth, element.getCenter().getY(), startWidth + swipeDistance, element.getCenter().getY(), 500);
	}
	
	public static void carouselSwipeRightUntilIndexed(AppiumDriver driver, int index)
	{
		MobileElement parent = (MobileElement) driver.findElement(By.id(CarouselPage.carouselIndex));
		List<MobileElement> indexies = parent.findElements(By.className("android.view.View"));
		
		while (!indexies.get(index).getAttribute("selected").equals("true"))
		{
			carouselSwipeRight(driver, (MobileElement)driver.findElement(By.id(CarouselPage.carouselImage)));
		}
	}
}
