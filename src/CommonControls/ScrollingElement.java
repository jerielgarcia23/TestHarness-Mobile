package CommonControls;

import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ScrollingElement 
{
	private static String topFrame = "action_bar";
	private static String bottomFrame = "navigationBarBackground";
	private static int spacer = 50;
	
	public static boolean isElementOnScreen(AppiumDriver driver, MobileElement element)
	{
		boolean withinFrame = true;
		WebElement top = driver.findElement(By.id(topFrame));
		WebElement bottom = driver.findElement(By.id(bottomFrame));
		
		if ((bottom.getLocation().y - spacer) < element.getLocation().y || (top.getLocation().y + top.getSize().getHeight() + spacer) > element.getLocation().y )
		{
			withinFrame = false;
		}

		return withinFrame;
	}
	
	public static void scrollLeft(AppiumDriver driver, MobileElement scrollableElement)
	{
		int width, height;
		height = driver.manage().window().getSize().getHeight() / 2; // center height
		width = driver.manage().window().getSize().getWidth() / 2; // center width
		
		int swipeDistance = width - 1;
		int offSet = 0;
		swipeDistance += offSet;
		
		driver.swipe(scrollableElement.getCenter().getX() + offSet, scrollableElement.getCenter().getY(), scrollableElement.getCenter().getX() - swipeDistance, scrollableElement.getCenter().getY(), 500);
	}
	
	public static void scrollRight(AppiumDriver driver, MobileElement scrollableElement)
	{
		int width, height;
		height = driver.manage().window().getSize().getHeight() / 2; // center height
		width = driver.manage().window().getSize().getWidth() / 2; // center width
		
		int swipeDistance = width - 1;
		int offSet = 0;
		swipeDistance += offSet;
		
		driver.swipe(scrollableElement.getCenter().getX() - offSet, scrollableElement.getCenter().getY(), scrollableElement.getCenter().getX() + swipeDistance, scrollableElement.getCenter().getY(), 500);
	}

	public static void scrollLeftUntilElementIsVisible(AppiumDriver driver, MobileElement scrollableElement, String targetText)
	{
		WebElement element = null; // it's loaded, but isn't being displayed
		boolean gate = false;	
		while(!gate)
		{
			try{
				element = driver.findElement(By.xpath("//android.widget.TextView[contains(TextView," + targetText + ")]"));
				System.out.println("Element is found : '" + element.getLocation() +"'");
			}
			catch (Exception e){
				
			}
			
			if (element!=null && element.isDisplayed())
			{
				element = driver.findElement(By.id("pagination_example_activity_list"));
				List<WebElement> elements = element.findElements(By.className("android.widget.ImageView"));//was TextView
				int counter = 0;
				for (counter = 0; counter < elements.size(); counter++)
				{
					WebElement tempElement = elements.get(counter);
					if (tempElement.getAttribute("name").equals(targetText))
					{
						gate = true;
						break;
					}
				}	
				scrollLeft(driver, scrollableElement);				
			}
		}
	}
	
	public static void scrollRightUntilElementIsVisible(AppiumDriver driver, MobileElement scrollableElement, String targetText)
	{
		WebElement element = null; // it's loaded, but isn't being displayed
		boolean gate = false;	
		while(!gate)
		{
			try{
				element = driver.findElement(By.xpath("//android.widget.TextView[contains(TextView," + targetText + ")]"));
				System.out.println("Element is found : '" + element.getLocation() +"'");
			}
			catch (Exception e){
				
			}
			
			if (element!=null && element.isDisplayed())
			{
				element = driver.findElement(By.id("pagination_example_activity_list"));
				List<WebElement> elements = element.findElements(By.className("android.widget.ImageView"));//was TextView
				int counter = 0;
				for (counter = 0; counter < elements.size(); counter++)
				{
					WebElement tempElement = elements.get(counter);
					if (tempElement.getAttribute("name").equals(targetText))
					{
						gate = true;
						break;
					}
				}	
				scrollRight(driver, scrollableElement);				
			}
		}
	}
	
	public static void scrollDown(AppiumDriver driver, MobileElement scrollableElement)
	{
		int width, height;
		height = driver.manage().window().getSize().getHeight() / 2; // center height
		width = driver.manage().window().getSize().getWidth() / 2; // center width
		
		int swipeDistance = height;
		int offSet = 0;
		swipeDistance += offSet;
		
		
		driver.swipe(scrollableElement.getCenter().getX(), scrollableElement.getCenter().getY() - offSet, scrollableElement.getCenter().getX(), scrollableElement.getCenter().getY() + swipeDistance, 500);
	}
	
	public static void scrollUp(AppiumDriver driver, MobileElement scrollableElement)
	{
		int width, height;
		height = driver.manage().window().getSize().getHeight() / 2; // center height
		width = driver.manage().window().getSize().getWidth() / 2; // center width
		
		int swipeDistance = height;
		int offSet = 0;
		swipeDistance += offSet;
		
		
		
		
		driver.swipe(scrollableElement.getCenter().getX(), scrollableElement.getCenter().getY() + offSet, scrollableElement.getCenter().getX(), scrollableElement.getCenter().getY() - swipeDistance, 500);
	}
	
	public static void scrollDownUntilElementIsVisible(AppiumDriver driver, MobileElement scrollableElement, String targetText)
	{
		WebElement element = null; // it's loaded, but isn't being displayed
		boolean gate = false;	
		while(!gate)
		{
			try{
				element = driver.findElement(By.xpath("//android.widget.TextView[contains(TextView," + targetText + ")]"));
				System.out.println("Element is found : '" + element.getLocation() +"'");
			}
			catch (Exception e){
				
			}
			
			if (element!=null && element.isDisplayed())
			{
				element = driver.findElement(By.id("pagination_example_activity_list"));
				List<WebElement> elements = element.findElements(By.className("android.widget.ImageView"));//was TextView
				int counter = 0;
				for (counter = 0; counter < elements.size(); counter++)
				{
					WebElement tempElement = elements.get(counter);
					if (tempElement.getAttribute("name").equals(targetText))
					{
						gate = true;
						break;
					}
				}	
				scrollDown(driver, scrollableElement);				
			}
		}
	}
	
	public static void scrollUpUntilElementIsVisible(AppiumDriver driver, MobileElement scrollableElement, String targetText)
	{
		WebElement element = null; // it's loaded, but isn't being displayed
		boolean gate = false;	
		while(!gate)
		{
			try{
				element = driver.findElement(By.xpath("//android.widget.TextView[contains(TextView," + targetText + ")]"));
				System.out.println("Element is found : '" + element.getLocation() +"'");
			}
			catch (Exception e){
				
			}
			
			if (element!=null && element.isDisplayed())
			{
				element = driver.findElement(By.id("pagination_example_activity_list"));
				List<WebElement> elements = element.findElements(By.className("android.widget.ImageView"));//was TextView
				int counter = 0;
				for (counter = 0; counter < elements.size(); counter++)
				{
					WebElement tempElement = elements.get(counter);
					if (tempElement.getAttribute("name").equals(targetText))
					{
						gate = true;
						break;
					}
				}	
				scrollUp(driver, scrollableElement);				
			}
		}
	}
	
	public static void scrollToElement(AppiumDriver driver, MobileElement element)
	{
		if (!element.isDisplayed())
		{
			driver.scrollTo(element.getText());
		}
	}
	
	public static void swipeUpToElement(AppiumDriver driver, MobileElement element)
	{
		int width, height;
		height = driver.manage().window().getSize().getHeight() / 2; // center height
		width = driver.manage().window().getSize().getWidth() / 2; // center width
		
		int swipeDistance = height;
		int offSet = 0;
		swipeDistance += offSet;
		
		
		driver.swipe(width, height - offSet, width, height + swipeDistance, 500);
		
		while (!element.isDisplayed())
		{
			driver.swipe(width, height, width, height  + swipeDistance, 500);
		}
		
	}
	
	public static void swipeDownToElement(AppiumDriver driver, MobileElement element)
	{
		int width, height;
		height = driver.manage().window().getSize().getHeight() / 2; // center height
		width = driver.manage().window().getSize().getWidth() / 2; // center width
		
		int swipeDistance = height;
		int offSet = 0;
		swipeDistance += offSet;
		
		driver.swipe(width, height + offSet, width, height - swipeDistance, 500);
		
		while (!element.isDisplayed())
		{
			driver.swipe(width, height, width, height - swipeDistance, 500);
		}
	}
	
	public static void swipeRightToElement(AppiumDriver driver, MobileElement element)
	{
		int width, height;
		height = driver.manage().window().getSize().getHeight() / 2; // center height
		width = driver.manage().window().getSize().getWidth() / 2; // center width
		
		int swipeDistance = width - 1;
		int offSet = 0;
		
		driver.swipe(width - offSet, height, width + swipeDistance, height, 500);
		
		while (!element.isDisplayed())
		{
			driver.swipe(width, height, width + swipeDistance, height, 500);
		}
	}
	
	public static void swipeLeftToElement(AppiumDriver driver, MobileElement element)
	{
		int width, height;
		
		height = driver.manage().window().getSize().getHeight() / 2; // center height
		width = driver.manage().window().getSize().getWidth() / 2; // center width
		
		int swipeDistance = width - 1;
		int offSet = 0;
		swipeDistance += offSet;
		
		driver.swipe(width + offSet, height, width - swipeDistance, height, 500);
		
		while (!element.isDisplayed())
		{
			driver.swipe(width, height, width - swipeDistance, height, 500);
		}
	}
}
