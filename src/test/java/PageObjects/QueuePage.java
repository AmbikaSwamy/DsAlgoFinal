package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BaseClassUtils;
import Utilities.LoggerLoad;
import Utilities.WebPageConstants;

import org.junit.Assert;

public class QueuePage
{
	public BaseClassUtils objBaseClass;
	public WebPageConstants objPageConst;
	
	WebDriver localDriver;
	public String sClickedQueueLinkName = "";
	
	@FindBy(linkText = "Implementation of Queue in Python")WebElement lnkQueueInPython;
	@FindBy(linkText = "Implementation using collections.deque")WebElement lnkImplmntUsingCollection;
	@FindBy(linkText = "Implementation using array")WebElement lnkImplmntUsingArray;
	@FindBy(linkText = "Queue Operations")WebElement lnkQueueOperations;
	@FindBy(linkText = "Practice Questions")WebElement lnkQueuePracticeQuestion;
	@FindBy (xpath="//a[@href='/tryEditor']")WebElement lnkTryHereQueue;	
	
	public QueuePage(WebDriver remoteDriver)
	{
		localDriver = remoteDriver;
		PageFactory.initElements(localDriver, this);
	}
	
	public void clickQueueInPython()
	{
		sClickedQueueLinkName = lnkQueueInPython.getText();
		lnkQueueInPython.click();
	}
	
	public String getQueuePageTitle() 
	{
		String title = localDriver.getTitle();
		return title;
	}
	
	public void clickImplementationUsingCollection()
	{
		sClickedQueueLinkName = lnkImplmntUsingCollection.getText();
		lnkImplmntUsingCollection.click();
	}
	
	public void clickImplementationUsingArray()
	{
		sClickedQueueLinkName = lnkImplmntUsingArray.getText();
		lnkImplmntUsingArray.click();
	}
	
	public void clickQueueOperations()
	{
		sClickedQueueLinkName = lnkQueueOperations.getText();
		lnkQueueOperations.click();
	}
	
	public void clickQueuePracticeQues()
	{
		lnkQueuePracticeQuestion.click();
	}
	
	public void clickTryHereQueue()
	{
		lnkTryHereQueue.click();
	}
}