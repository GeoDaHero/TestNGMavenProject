package tests;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AmazonPage;
import utils.Driver;

public class AmazonSearch {
	
	@BeforeMethod
	public void setup() {
		Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void quitDriver() {
		Driver.getDriver();
	}
	
	
  @Test (dataProvider = "searchItems")
  public void AmazonSearchTest(String item) {
	  AmazonPage amazonPage = new AmazonPage();
	  Driver.getDriver().get("https://amazon.com");
	  amazonPage.searchBox.sendKeys(item);
	  amazonPage.searchBtn.click();
	  String actualText = amazonPage.searchItemText.getText().substring(1, item.length()+1);
	  System.out.println(actualText + "" + item.length());
	  Assert.assertEquals(actualText, item);
	  
	  //"coffee mug"    coffee mug
  }
  
  @DataProvider
  public String[] searchItems() {
	  String[] items = new String[6];
	  items[0] = "coffee mug";
	  items[1] = "pretty coffee mug";
	  items[2] = "ugly coffee mug";
	  items[3] = "funny coffee mug";
	  items[4] = "peru coffee mug";
	  items[5] = "el salvador coffee mug";
	  
	  return items;
	  
  }
  
  
}
