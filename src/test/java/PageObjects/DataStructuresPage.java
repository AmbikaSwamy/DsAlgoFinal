package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*; //for web element
import org.openqa.selenium.support.FindBy;// for FindBy
import org.openqa.selenium.support.PageFactory;

import Utilities.WebPageConstants;

public class DataStructuresPage
{
	// String strHomePageURL;
	// String strDSIntroPageURL;
	
	@FindBy(xpath="//a[@href='data-structures-introduction']")
	WebElement btnDSGetStarted;
	
	@FindBy(linkText ="Time Complexity")
	WebElement lnkTimeComplexity;

	@FindBy(xpath="//a[@href='/tryEditor']")
	WebElement btnTCTryHere;
	
	@FindBy(xpath="//a[@href='/data-structures-introduction/practice']")
	WebElement lnkPQ;
	
	public WebPageConstants objConstants; 
	
	public DataStructuresPage (WebDriver d)
	{
		PageFactory.initElements(d,this); 
	}
	
	public void DSGetStartedbtn() 
	{	
		btnDSGetStarted.click();
	}
	
	public void ClicklnkTimeComplexity() 
	{	
		lnkTimeComplexity.click();
	}
	
	public void ClickTCtryherebtn() 
	{	
		btnTCTryHere.click();		
	}
	
	public void ClickPQlnk()
	{
		lnkPQ.click();
	}
	
	

}
