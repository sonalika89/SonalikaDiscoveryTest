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

public class LandingPage extends TestBase{
	
	Logger log=Logger.getLogger(LandingPage.class);
	
	@FindBys({@FindBy(xpath="//h3[@class='showTileSquare__title']")})
	List<WebElement> videoTexts;
	
	@FindBy(xpath="//h3[@class='showTileSquare__title']")
	WebElement videoText ;

	@FindBys({@FindBy(xpath="//div[@class='showTileSquare__description']")})
	List<WebElement> descriptions;
	
	@FindBys({@FindBy(xpath="//*[@class='my-favorites-button-container']/span/i")})
	List<WebElement> addToFavoriteLinks;
		
	@FindBy(xpath="//div[contains(text(),'Recommended')]")
	WebElement recommendedVideosView ;
	
	@FindBys({@FindBy(xpath="//div[contains(text(),'Recommended')]/parent::h2/following-sibling::div//div[1]/div/div/div")})
	List<WebElement> recommondedvideosList;
	
	public LandingPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	public List<VideoDetail> getFavouriteVideosAddedFromRecomendedVideos() throws InterruptedException
	{	
		log.info("Getting Favourite Videos !!!");
		List<VideoDetail> favouriteVideos=new ArrayList<VideoDetail>();
		
		WebElement element =TestUtil.GetWebElement(recommendedVideosView, driver); 
		
		TestUtil.ExecuteScript(driver, element);
		driver.manage().timeouts().implicitlyWait(TestUtil.WAIT_TIME,TimeUnit.SECONDS); 
		List<WebElement> lst=recommondedvideosList;
		
		for(int i =0;i<TestUtil.NumberOfVideosToBeAdded;i++)
		{
			WebElement webElement=lst.get(i);
			if(webElement!=null)
			{
				TestUtil.MouseHover(driver, webElement);
				
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
				driver.manage().timeouts().implicitlyWait(TestUtil.WAIT_TIME,TimeUnit.SECONDS);
				addToFavoriteLinks.get(i).click();
				VideoDetail detail=new VideoDetail();
				detail.Text=text;
				detail.Description=description;
				favouriteVideos.add(detail);
				log.info("Video Text :"+text);
				log.info("Video Description :"+description);
				
				driver.manage().timeouts().implicitlyWait(TestUtil.WAIT_TIME,TimeUnit.SECONDS);
			}
						
		}
		
		log.info("Favourite Videos Added ..!!!");
		return favouriteVideos;
	}
}
