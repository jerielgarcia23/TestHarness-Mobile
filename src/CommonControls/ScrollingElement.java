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
	
	public void scrollLeft(AppiumDriver driver, MobileElement scrollableElement)
	{
		driver.swipe(scrollableElement.getCenter().getX(), scrollableElement.getCenter().getY(), scrollableElement.getCenter().getX() - 200, scrollableElement.getCenter().getY(), 500);
	}
	
	public void scrollRight(AppiumDriver driver, MobileElement scrollableElement)
	{
		driver.swipe(scrollableElement.getCenter().getX(), scrollableElement.getCenter().getY(), scrollableElement.getCenter().getX() + 200, scrollableElement.getCenter().getY(), 500);
	}

	public void scrollLeftUntilElementIsVisible(AppiumDriver driver, MobileElement scrollableElement, String targetText)
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
	
	public void scrollRightUntilElementIsVisible(AppiumDriver driver, MobileElement scrollableElement, String targetText)
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
	
	public void scrollDown(AppiumDriver driver, MobileElement scrollableElement)
	{
		driver.swipe(scrollableElement.getCenter().getX(), scrollableElement.getCenter().getY(), scrollableElement.getCenter().getX(), scrollableElement.getCenter().getY() + 200, 500);
	}
	
	public void scrollUp(AppiumDriver driver, MobileElement scrollableElement)
	{
		driver.swipe(scrollableElement.getCenter().getX(), scrollableElement.getCenter().getY(), scrollableElement.getCenter().getX(), scrollableElement.getCenter().getY() - 200, 500);
	}
	
	public void scrollDownUntilElementIsVisible(AppiumDriver driver, MobileElement scrollableElement, String targetText)
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
	
	public void scrollUpUntilElementIsVisible(AppiumDriver driver, MobileElement scrollableElement, String targetText)
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
}
