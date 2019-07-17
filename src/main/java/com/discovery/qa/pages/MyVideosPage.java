package com.discovery.qa.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.discovery.qa.base.TestBase;
import com.discovery.qa.utils.TestUtil;
import com.discovery.qa.utils.VideoDetail;

public class MyVideosPage extends TestBase{
	
	Logger log=Logger.getLogger(MyVideosPage.class);

	@FindBy(xpath="//*[@class='dscHeaderMain__iconMenu']")
	WebElement menuIcon;
	
	@FindBy(xpath="//span[contains(text(),'My Videos')]")
	WebElement myVideosLink;
	
	@FindBy(xpath="//h2[contains(text(),'Favorite Shows')]")
	WebElement favoriteView ;
	
	@FindBys({@FindBy(xpath="//h3[@class='showTileSquare__title']")})
	List<WebElement> videoTexts;
	
	@FindBy(xpath="//h3[@class='showTileSquare__title']")
	WebElement videoText;

	@FindBys({@FindBy(xpath="//div[@class='showTileSquare__description']")})
	List<WebElement> descriptions;
   
	@FindBys({@FindBy(xpath ="//h2[contains(text(),'Favorite Shows')]/following-sibling::div//div[1]/div/div/div")})
	List<WebElement> favoritevideosList;
	
	public MyVideosPage() throws IOException  {
		PageFactory.initElements(driver, this);
	}
	
	 public List<VideoDetail> getMyFavoriteVideos() throws InterruptedException
		{
		    log.info("Getting my favourite Videos !!!");
		 
		    menuIcon.click();
		    driver.manage().timeouts().implicitlyWait(TestUtil.WAIT_TIME,TimeUnit.SECONDS);
			myVideosLink.click();
			driver.manage().timeouts().implicitlyWait(TestUtil.WAIT_TIME,TimeUnit.SECONDS);
			List<VideoDetail> myFavoriteVideos=new ArrayList<VideoDetail>();
			WebElement element = favoriteView;
		    TestUtil.ExecuteScript(driver, element);
		    driver.manage().timeouts().implicitlyWait(TestUtil.WAIT_TIME,TimeUnit.SECONDS); 
		    List<WebElement> lst=favoritevideosList;
		    for(int i =0;i<lst.size();i++)
		     {
			   WebElement webElement=lst.get(i);
			   if(webElement!=null)
				{
					TestUtil.MouseHover(driver, webElement);
					driver.manage().timeouts().implicitlyWait(TestUtil.WAIT_TIME,TimeUnit.SECONDS);
					Boolean IsElementVisible =TestUtil.IsWebElementVisisble(videoText, driver);
					String text=null;
					if(IsElementVisible)
					{
						text=videoTexts.get(i).getText();
					}
					else
					{
						log.warn("FavouriteVideos not Found");
					}
					
					String description=descriptions.get(i).getText();
					VideoDetail detail=new VideoDetail();
					detail.Text=text;
					detail.Description=description;
					myFavoriteVideos.add(detail);
					log.info("Video Text :"+text);
					log.info("Video Description :"+description);
					driver.manage().timeouts().implicitlyWait(TestUtil.WAIT_TIME,TimeUnit.SECONDS); 		
				}
		  	}
		    
		    log.info("My favourite Videos Added !!!");
		   return myFavoriteVideos;
		}
		
	
	}	
	
