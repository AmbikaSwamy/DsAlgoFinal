package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import Utilities.BaseClassUtils;
import Utilities.LoggerLoad;
import Utilities.WebPageConstants;

import org.junit.Assert;

public class ArrayPage
{
	public BaseClassUtils objBaseClass;
	public WebPageConstants objPageConst;
	
	WebDriver localDriver;
	public String sClickedArrayLinkName = "";
	
	/*@FindBy(xpath="//a[@href='array']")
	WebElement btnGetStartedArray;*/
	
	//@FindBy (xpath="//a[@href='arrays-in-python']")WebElement lnkArraysInPython;
	
	@FindBy (linkText = "Arrays in Python")
	WebElement lnkArraysInPython;
	
	@FindBy (linkText = "Arrays Using List")
	WebElement lnkArraysUsingList;
	
	@FindBy (linkText = "Basic Operations in Lists")
	WebElement lnkBasicOperationsinLists;
	
	@FindBy (linkText = "Applications of Array")
	WebElement lnkApplicationsOfArray;
	
	@FindBy (linkText ="Practice Questions")
	WebElement lnkArrayPracticeQuestion;	
	
	@FindBy (linkText ="Search the array")
	WebElement lnkSearchArray;
	
	@FindBy (linkText="Max Consecutive Ones")
	WebElement lnkConsecutiveOnes;
	
	@FindBy (linkText="Find Numbers with Even Number of Digits")
	WebElement lnkEvenDigits;
	
	@FindBy (linkText="Squares of a Sorted Array")
	WebElement lnkArraySquares;
	
	@FindBy(xpath = "//a[@href = '/tryEditor']")WebElement lnkTryHereArray;
	
	@FindBy (xpath="//*[@id='answer_form']")WebElement answerform;
	@FindBy (xpath="//textarea[@tabindex='0']")WebElement editorInput;
	@FindBy (xpath="//*[@id='answer_form']/button")WebElement btnRun;
	@FindBy (xpath="//*[@class='button']")WebElement btnSubmit;
	@FindBy (id="output")WebElement outputform; 
	
	public ArrayPage(WebDriver remoteDriver)
	{
		localDriver = remoteDriver;
		PageFactory.initElements(localDriver, this);
		
		objBaseClass = new BaseClassUtils();
	}
	
	public void clickArrayInPython()
	{
		lnkArraysInPython.click();
		sClickedArrayLinkName = lnkArraysInPython.getText();
	}
	
	public String getArrayPageTitle() 
	{
		String title = localDriver.getTitle();
		return title;
	}
	
	public void clickArrayUsingList()
	{
		lnkArraysUsingList.click();
		sClickedArrayLinkName = lnkArraysUsingList.getText();		
	}
	
	public void clickBasicOperationsInLists()
	{
		lnkBasicOperationsinLists.click();
		sClickedArrayLinkName = lnkBasicOperationsinLists.getText();
	}
	
	public void clickApplicationOfArray()
	{
		lnkApplicationsOfArray.click();
		sClickedArrayLinkName = lnkApplicationsOfArray.getText();
	}	
	
	public void clickArrayPracticeQues()
	{
		lnkArrayPracticeQuestion.click();
	}
	
	public void clickArraySearch()
	{
		lnkSearchArray.click();
	}
	
	public void clickMaxConsecutiveOnes()
	{
		lnkConsecutiveOnes.click();
	}
	
	public void clickEvenNumberOfDigits()
	{
		lnkEvenDigits.click();
	}
	
	public void clickSquaresOfArray()
	{
		lnkArraySquares.click();
	}
	
	public void clickTryHereArray()
	{
		lnkTryHereArray.click();
	}
	
	public void clickSubmitButton()
	{
		btnSubmit.click();
	}
	
	public String getOutput() 
	{
		waitForElement(outputform);
		return outputform.getText();
	}
	
	public void waitForElement(WebElement element) 
	{
		new WebDriverWait(localDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
	}
}