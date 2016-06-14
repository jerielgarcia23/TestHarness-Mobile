package ErrorObjects;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class Alert 
{
	public static boolean isAlertPresent(WebDriver driver) 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	}   // isAlertPresent()
}
