package com.discovery.qa.utils;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestUtil {

	public static int PAGE_LOAD_TIMEOUT = 40;
	public static int WAIT_TIME= 40;
	public static final int NumberOfVideosToBeAdded=2; 
	
	public static void MouseHover(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	
	public static void ExecuteScript(WebDriver driver,WebElement element)
	{ 
	  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	 public static boolean IsWebElementVisisble(WebElement element, WebDriver driver)
	 {  
		 WebElement webElement=null;
		 boolean status=false;
		 try
		    {
			 webElement= new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.visibilityOf(element));
			 if(webElement!=null)
			 {
				 status=true;
			 }
		    }
		 catch( java.util.NoSuchElementException noex )
		    {
		    	noex.printStackTrace( ); 
		    }
		    catch( org.openqa.selenium.TimeoutException te )
		    {
		    	System.out.println("element not found");
		     // te.printStackTrace( );
		    }
		    catch( org.openqa.selenium.UnhandledAlertException ue )
		    {
		      ue.printStackTrace( );
		    }
	    return status;
	 }
	
	public static WebElement GetWebElement(WebElement element, WebDriver driver)
	  {
	    WebElement webElement=null;
	    try
	    {
	    	webElement= new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.visibilityOf(element));
	    }
	    catch( java.util.NoSuchElementException noex )
	    {
	    	noex.printStackTrace( ); 
	    }
	    catch( org.openqa.selenium.TimeoutException te )
	    {
	    	System.out.println("element not found");
	     // te.printStackTrace( );
	    }
	    catch( org.openqa.selenium.UnhandledAlertException ue )
	    {
	      ue.printStackTrace( );
	    }
	    
	    return webElement;
	  }
	
	// Get the Video object based on the Text of the video
		public static VideoDetail findVideoDetail(
				  String text, List<VideoDetail> VideoDetails) {
				 
				    for (VideoDetail videoDetail : VideoDetails) {
				        if (videoDetail.Text.equals(text)) {
				            return videoDetail;
				        }
				    }
				    return null;
				}
}
