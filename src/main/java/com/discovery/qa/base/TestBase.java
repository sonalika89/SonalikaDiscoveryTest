package com.discovery.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.nio.file.Paths;
import com.discovery.qa.utils.TestUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	static String path = Paths.get("").toAbsolutePath().toString();
	Logger log=Logger.getLogger(TestBase.class);
	
	public TestBase() throws IOException
	{
		try{
		prop= new Properties();
		
		FileInputStream fs= new FileInputStream(path+"/src/main/java/com/discovery/qa/config/config.properties");
		prop.load(fs);
			
		  } catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void initialization()
	{
		String browsername=prop.getProperty("browser");
		
		if(browsername.equals("chrome")){
			System.setProperty("webdriver.chrome.driver",path+"/Library/chromedriver.exe");
			driver =new ChromeDriver();
		}
		else if(browsername.equals("firefox")){
			System.setProperty("webdriver.gecko.driver",path+"/Library/geckodriver.exe");
			driver =new FirefoxDriver();
		}
		
		 log.info("Launching browser !!");
		 driver.get(prop.getProperty("url"));
		 log.info("URL:"+prop.getProperty("url"));
		 driver.manage().window().maximize();
         driver.manage().deleteAllCookies();
         driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
         driver.manage().timeouts().implicitlyWait(TestUtil.WAIT_TIME,TimeUnit.SECONDS);
       
       	}
       public void failed()
       {
    	   File srcfile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	   try
    	   {   
    		log.warn("Test case failed.Capturing Failure Test cases !!");
    		FileUtils.copyFile(srcfile, new File(path+"/screenshots/testFailure.jpg"));
    	   }catch(IOException e)
   		{
   			e.printStackTrace();
   		}
       }
}
