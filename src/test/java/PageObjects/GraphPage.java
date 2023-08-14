package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*; //for web element
import org.openqa.selenium.support.FindBy;// for FindBy
import org.openqa.selenium.support.PageFactory;

import Utilities.WebPageConstants;

public class GraphPage
{
	String strHomePageURL;
	String strGraphPageURL;
		
	/*@FindBy(xpath= "//a[@href='graph']")
	WebElement lnkGraph;*/
	
	@FindBy(linkText = "Graph")
	WebElement lnkGraph;
	
	@FindBy(linkText="Graph Representations")
	WebElement lnkGraphRepresentations;	
		
	@FindBy(linkText="Practice Questions")
	WebElement lnkPQ;
	
	@FindBy(xpath="//a[@href='/tryEditor']")
	WebElement btnGraphTryHere;
	
	public GraphPage (WebDriver d)
	{
		PageFactory.initElements(d,this); 
	}
	
	/*public void GraphGetStartedbtn() 
	{	
		btnGraphGetStarted.click();
	}*/
	
	public void ClickGraphlink()
	{	
		lnkGraph.click();
	}
	
	public void Clicktryherebtn() 
	{	
		btnGraphTryHere.click();		
	}
	
	public void ClickGraphRepresentationslink()
	{
		lnkGraphRepresentations.click();
	}
	
	public void ClickPQlnk()
	{
		lnkPQ.click();
	}
}
