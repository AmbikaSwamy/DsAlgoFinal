package PageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BaseClassUtils;
import Utilities.LoggerLoad;
import Utilities.WebPageConstants;

import org.junit.Assert;

public class HomePage 
{
	public BaseClassUtils objBaseClass;
	
	public WebDriver localDriver;
	
	@FindBy(className = "btn")
	WebElement weBtnGetStarted;			// App URL and get into the home page by clicking this button.
	
	@FindBy(linkText = "NumpyNinja")
	WebElement weLinkNumpyNinja;
	
	@FindBy(xpath = "//a[@class='nav-link dropdown-toggle']")
	WebElement weLinkTopicDropDown;		// Data Structures Dropdown
	
	@FindBy(linkText = "Register")
	WebElement weLinkRegisterUser;
	
	@FindBy(linkText = "Sign in")
	WebElement weLinkSignIn;
	
	@FindBy(linkText = "Sign out")
	WebElement weLinkSignOut;
	
	@FindBy(xpath = "//a[@href='data-structures-introduction']")
	WebElement weBtnDSIntro;		// Data Structures Intro
	
	@FindBy(xpath = "//a[@href='array']")
	WebElement weBtnArray;			// Array
	
	@FindBy(xpath = "//a[@href='linked-list']")
	WebElement weBtnLinkedList;		// Linked List
	
	@FindBy(xpath = "//a[@href='stack']")
	WebElement weBtnStack;			// Stack
	
	@FindBy(xpath = "//a[@href='queue']")
	WebElement weBtnQueue;			// Queue
	
	@FindBy(xpath = "//a[@href='tree']")
	WebElement weBtnTree;			// Tree
	
	@FindBy(xpath = "//a[@href='graph']")
	WebElement weBtnGraph;			// Graph
	
	By weListTopicItems = By.xpath("//a[@class='dropdown-item']");		// Arrays, Linked List, Stack, Queue, Tree, Graph
	
	public HomePage(WebDriver remoteDriver) 
	{
		localDriver = remoteDriver;
		PageFactory.initElements(localDriver, this);
		objBaseClass  = new BaseClassUtils();
	}
	
	public void GetStarted()
	{
		if(weBtnGetStarted.isDisplayed())
			if(weBtnGetStarted.isEnabled())
				weBtnGetStarted.click();
			else
				LoggerLoad.ErrorMsg("Get Started Button is not enabled !");		
		else
			LoggerLoad.ErrorMsg("Get Started Button is not visible !");
	}
	
	// User clicks on the Page  Title -> "Numpy Ninja" and should stay in the same home page
	// https://dsportalapp.herokuapp.com/home
	
	public void clickPageTitle()
	{
		weLinkNumpyNinja.click();
	}
	
	public void clickDSMenuOptions() throws IOException
	{
		if(weLinkTopicDropDown.isDisplayed())
			if(weLinkTopicDropDown.isEnabled())
				weLinkTopicDropDown.click();
			else
				LoggerLoad.ErrorMsg("Data Structures Dropdown is not enabled !");
		else
			LoggerLoad.ErrorMsg("Data Structures Dropdown is not enabled !");
		
		
		String sMethodName = new Throwable().getStackTrace()[0].getMethodName();
		objBaseClass.Capture_ScreeShots(localDriver, "Failed", sMethodName);
	}
	
	public int getDSMenuItemCount()
	{
		int intMenuItemCount = 0;
		
		List<WebElement> lstDSMenuOptions;

		lstDSMenuOptions = localDriver.findElements(weListTopicItems);

		for (WebElement i : lstDSMenuOptions) 
		{              
			// System.out.println(i.getText());
			intMenuItemCount++;
		}
		
		return intMenuItemCount;
	}
	
	public void DSIntro_Btn_Click()
	{
		if(weBtnDSIntro.isDisplayed())
			if(weBtnDSIntro.isEnabled())
			{
				weBtnDSIntro.click();
				
				/*
				if(weLinkSignIn.isDisplayed())	// User not logged in yet and so stay in the current page.
				{
					Assert.assertEquals("User not logged in. So stay in home page !", objBaseClass.Home_Page_URL, localDriver.getCurrentUrl());
				}
				else		// User logged in and so navigate to the designated page.
				{
					Assert.assertEquals("User logged in. So navigate to designated Page !", objBaseClass.DSIntroPage_URL, localDriver.getCurrentUrl());
				}*/
			}
			// else
				// LoggerLoad.ErrorMsg("Data Structures Intro Button is not enabled !");
		// else
			// LoggerLoad.ErrorMsg("Data Structures Intro Button is not visible !");
	}
	
	public void Array_Btn_Click()
	{
		if(weBtnArray.isDisplayed())
			if(weBtnArray.isEnabled())
				weBtnArray.click();
			else
				LoggerLoad.ErrorMsg("Array Button is not enabled !");
		else
			LoggerLoad.ErrorMsg("Array Button is not visible !");
	}
	
	public void LinkedList_Btn_Click()
	{
		if(weBtnLinkedList.isDisplayed())
			if(weBtnLinkedList.isEnabled())
				weBtnLinkedList.click();
			else
				LoggerLoad.ErrorMsg("Linked List Button is not enabled !");
		else
			LoggerLoad.ErrorMsg("Linked List Button is not visible !");
	}
	
	public void Stack_Btn_Click()
	{
		if(weBtnStack.isDisplayed())
			if(weBtnStack.isEnabled())
				weBtnStack.click();
			else
				LoggerLoad.ErrorMsg("Stack Button is not enabled !");
		else
			LoggerLoad.ErrorMsg("Stack Button is not visible !");
	}
	
	public void Queue_Btn_Click()
	{
		if(weBtnQueue.isDisplayed())
			if(weBtnQueue.isEnabled())
				weBtnQueue.click();
			else
				LoggerLoad.ErrorMsg("Queue Button is not enabled !");
		else
			LoggerLoad.ErrorMsg("Queue Button is not visible !");
	}
	
	public void Tree_Btn_Click()
	{		
		if(weBtnTree.isDisplayed())
			if(weBtnTree.isEnabled())
				weBtnTree.click();
			else
				LoggerLoad.ErrorMsg("Tree Button is not enabled !");
		else
			LoggerLoad.ErrorMsg("Tree Button is not visible !");
	}
	
	public void Graph_Btn_Click()
	{
		if(weBtnGraph.isDisplayed())
			if(weBtnGraph.isEnabled())
				weBtnGraph.click();
			else
				LoggerLoad.ErrorMsg("Graph Button is not enabled !");
		else
			LoggerLoad.ErrorMsg("Graph Button is not visible !");
	}
	
	public void RegisterUser_Click()
	{
		if(weLinkRegisterUser.isDisplayed())
			if(weLinkRegisterUser.isEnabled())
			{
				weLinkRegisterUser.click();
				Assert.assertEquals("Register User Click Success !", objBaseClass.RegisterUserPage_URL, localDriver.getCurrentUrl());
			}
			else
				LoggerLoad.ErrorMsg("User Registration Link is not enabled !");
		else
			LoggerLoad.ErrorMsg("User Registration Link is not visible !");
	}
	
	public void SignIn_Click()
	{
		if(weLinkSignIn.isDisplayed())
			if(weLinkSignIn.isEnabled())
			{
				weLinkSignIn.click();
				Assert.assertEquals("Sign-In Click Success !", objBaseClass.SignInPage_URL, localDriver.getCurrentUrl());
			}
			else
				LoggerLoad.ErrorMsg("Sign-In Link is not enabled !");
		else
			LoggerLoad.ErrorMsg("Sign-In Link is not visible !");
	}
}
 