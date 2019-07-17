package com.discovery.qa.test;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.discovery.qa.base.CustumListner;
import com.discovery.qa.base.TestBase;
import com.discovery.qa.pages.LandingPage;
import com.discovery.qa.pages.MyVideosPage;
import com.discovery.qa.utils.TestUtil;
import com.discovery.qa.utils.VideoDetail;

@Listeners(CustumListner.class)
public class HomePage extends TestBase {
	
	Logger log=Logger.getLogger(HomePage.class);
	LandingPage landingPage=null;
	MyVideosPage myVideoPage=null;
	public HomePage() throws IOException{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		initialization();
		try {
			landingPage=new LandingPage();
			myVideoPage=new MyVideosPage();
		} catch (IOException e) {
			e.printStackTrace();
		}
     }
	
	@Test
	public void VerifyAddedFavouriteVideosWithMyFavouriteShows() throws InterruptedException
	{	
		SoftAssert softAssert= new SoftAssert();		
		List<VideoDetail> addedFavouriteVideos=landingPage.getFavouriteVideosAddedFromRecomendedVideos();
		List<VideoDetail> myvideos=myVideoPage.getMyFavoriteVideos();
		Assert.assertEquals(addedFavouriteVideos.size(), myvideos.size());
		for (VideoDetail videoDetail : myvideos) 
		{
			VideoDetail video=TestUtil.findVideoDetail(videoDetail.Text,addedFavouriteVideos);
			String recoVidepDesc=video.Description.substring(0, video.Description.length()-3);
			log.info("Checking the Text and Description of the videos !!");
			softAssert.assertTrue(videoDetail.Description.contains(recoVidepDesc));
			softAssert.assertEquals(video.Text, videoDetail.Text);
		}
		
		log.info("Added Favourite Videos and Myvideos are successfully verified !!");
		softAssert.assertAll();
	}
	
	@AfterClass
	public void teardown()
	{
		driver.close();
	}
}



