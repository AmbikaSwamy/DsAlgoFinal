package StepDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;

import Utilities.BaseClassUtils;
import Utilities.DataFileHandling;
import Utilities.LoggerLoad;
import Utilities.WebPageConstants;

import PageObjects.RegisterUserPage;
import PageObjects.SignInPage;
import PageObjects.StackPage;
import PageObjects.TreePage;
import PageObjects.TryEditorPage;
import PageObjects.ArrayPage;
import PageObjects.DataStructuresPage;
import PageObjects.GraphPage;
import PageObjects.HomePage;
import PageObjects.LinkedListPage;
import PageObjects.QueuePage;

public class stepDefn 
{
	public ServiceHooks objServiceHooks;
	
	public BaseClassUtils objBaseClass;
	public WebPageConstants objConstants;
	public DataFileHandling objFileData;

	public HomePage objHomePage;
	public RegisterUserPage objRegUser;
	public SignInPage objSignInPage;

	public DataStructuresPage objDSPage;	
	public GraphPage objGraphPage;
	public ArrayPage objArrayPage;
	public QueuePage objQueuePage;
	public LinkedListPage objLLPage;	
	public StackPage objStackPage;
	public TreePage objTreePage;
	public TryEditorPage objEditorPage;
	
	public WebDriver localDriver;
	
	public String strUsername;
	public String strPass;
	public String strConfirmPass;
	public String strStatusMsg;
	public String strCurrPageName;
	public String sClassName = "";
	public String strMethodName = "";
	public String strErrMsgToLog = "";
	
	public String strDSIntroPageURL;
	
	String strSignInUserName = "";
	String strSignInPassCode = "";

	String strExcelFilePath = "";
	String strLoginDataSheet = "";

	Workbook myXLWorkBook;
	Sheet shtLogin;

	int intRowLoop = 0;
	int intColLoop = 0;

	public boolean bValidCredentials = false;
	public boolean isConstInitiated = false;
	public boolean bValidRegnValues = true;

	@Given("the constants are initialized") // Change it to a more readable statement instead of "constants".
	public void the_constants_are_initialized() throws IOException 
	{
		strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		sClassName = this.getClass().getSimpleName();
				
		try
		{
			if (isConstInitiated == false) 
			{
				objConstants = new WebPageConstants();
	
				objBaseClass = new BaseClassUtils();
				objBaseClass.LaunchBrowser();
				localDriver = objBaseClass.GetDriver();
	
				isConstInitiated = true;
			}
			objBaseClass.LogGeneralMsg("Connection Established !!");;
		}
		catch(Exception E)
		{			
			strErrMsgToLog = "Unable to establish a connection !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Given("user launches the web page")
	public void user_launches_the_web_page() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			localDriver.get(objConstants.APP_URL);
			
			Assert.assertEquals("Web Page Launched successfully !", objConstants.APP_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogGeneralMsg("Web Page Launched !!");
		}
		catch(Exception E)
		{	
			strErrMsgToLog = "Failed to launch the web page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Given("clicks the get_started button")
	public void clicks_the_get_started_button() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objHomePage = new HomePage(localDriver);
			objHomePage.GetStarted();
			
			objBaseClass.LogGeneralMsg("User clicked the Get Started Button !!");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "Failed to click the Get Started Button !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);				
		}
	}

	@Then("go to the home page")
	public void go_to_the_home_page() throws IOException 
	{
		// Redirected to the HomePage automatically upon clicking the "Get Started" Button.
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();

			Assert.assertEquals("User is at Home Page", objConstants.HomePage_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogGeneralMsg("User is at Home Page !!");			
		}
		catch(Exception e)
		{
			strErrMsgToLog = "Failed to land on the home page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);			
		}
	}

	@Given("user clicks the page title")
	public void user_clicks_the_page_title() throws Exception
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			objHomePage.clickPageTitle();
	
			Assert.assertEquals("User clicks Page Title - stays at Home Page", objConstants.HomePage_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogGeneralMsg("User clicks Page Title - stays at Home Page !!");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "Error while checking the page title !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);	
		}
	}

	@Then("user clicks the DS dropdown")
	public void user_clicks_the_ds_dropdown() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			objHomePage.clickDSMenuOptions();
	
			if (objHomePage.getDSMenuItemCount() <= 0) 
				objBaseClass.LogGeneralMsg("Data Structures dropdown is empty !!");
			else
				objBaseClass.LogGeneralMsg("User Clicked Data Structures dropdown !!");
			
			Assert.assertEquals("User clicks Data Structures Dropdown Menu", objConstants.HomePage_URL, localDriver.getCurrentUrl());
			objBaseClass.LogGeneralMsg("User clicks Data Structures Dropdown Menu - stays at Home Page !!");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "Unable to click the DS Dropdown Menu !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("user clicks the DS-IntroButton")
	public void user_clicks_the_ds_intro_button() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			objHomePage.DSIntro_Btn_Click();
			Thread.sleep(1000);
			
			if(bValidCredentials)
			{
				Assert.assertEquals("User clicks DS Intro Button - logged in - moves to DS Intro Page", objConstants.DSIntro_Page_URL, localDriver.getCurrentUrl());
				objBaseClass.LogGeneralMsg("User clicks DS Intro Button - logged in - moves to Intro Page !!");
			}
			else
			{
				Assert.assertEquals("User clicks DS Intro Button - not logged in - stays at Home Page", objConstants.HomePage_URL, localDriver.getCurrentUrl());
				objBaseClass.LogGeneralMsg("User clicks DS Intro Button - not logged in - stays at Home Page !!");
			}
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "Unable to click the Data Structures Intro Button !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("user clicks the ArrayButton")
	public void user_clicks_the_array_button() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			objHomePage.Array_Btn_Click();
			Thread.sleep(1000);
			
			if(bValidCredentials)
			{
				Assert.assertEquals("User clicks Array Button - logged in - moves to Array Page", objConstants.Array_Page_URL, localDriver.getCurrentUrl());
				objBaseClass.LogGeneralMsg("User clicks Array Button - logged in - moves to Array Page !!");
			}
			else
			{
				Assert.assertEquals("User clicks Array Button - not logged in - stays at Home Page", objConstants.HomePage_URL, localDriver.getCurrentUrl());
				objBaseClass.LogGeneralMsg("User clicks Array Button - not logged in - stays at Home Page !!");
			}
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "Unable to click the Array Button !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("user clicks the LinkedListButton")
	public void user_clicks_the_linked_list_button() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			objHomePage.LinkedList_Btn_Click();
			Thread.sleep(1000);
			
			if(bValidCredentials)
			{
				Assert.assertEquals("User clicks Linked List Button - logged in - moves to Linked List Page", objConstants.LL_Page_URL, localDriver.getCurrentUrl());
				objBaseClass.LogGeneralMsg("User clicks Linked List Button - logged in - moves to Linked List Page !!");
			}
			else
			{
				Assert.assertEquals("User clicks Linked List Button - not logged in - stays at Home Page !", objConstants.HomePage_URL, localDriver.getCurrentUrl());
				objBaseClass.LogGeneralMsg("User clicks Linked List Button - not logged in - stays at Home Page !!");
			}
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "Unable to click the Linked List Button !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("user clicks the StackButton")
	public void user_clicks_the_stack_button() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			objHomePage.Stack_Btn_Click();
			Thread.sleep(1000);
			
			if(bValidCredentials)
			{
				Assert.assertEquals("User clicks Stack Button - logged in - moves to Stack Page", objConstants.Stack_Page_URL, localDriver.getCurrentUrl());
				objBaseClass.LogGeneralMsg("User clicks Stack Button - logged in - moves to Stack Page !!");
			}
			else
			{
				Assert.assertEquals("User clicks Stack Button - not logged in - stays at Home Page", objConstants.HomePage_URL, localDriver.getCurrentUrl());
				objBaseClass.LogGeneralMsg("User clicks Stack Button - not logged in - stays at Home Page !!");
			}
		}
		catch(Exception e)
		{			
			strErrMsgToLog = "Unable to click the Stack Button !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("user clicks the QueueButton")
	public void user_clicks_the_queue_button() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			objHomePage.Queue_Btn_Click();
			Thread.sleep(1000);
			
			if(bValidCredentials)
			{
				Assert.assertEquals("User clicks Queue Button - logged in - moves to Queue Page!", objConstants.QPage_URL, localDriver.getCurrentUrl());
				objBaseClass.LogGeneralMsg("User clicks Queue Button - logged in - moves to Queue Page !!");
			}
			else
			{
				Assert.assertEquals("User clicks Queue Button - not logged in - stays at Home Page !", objConstants.HomePage_URL, localDriver.getCurrentUrl());
				objBaseClass.LogGeneralMsg("User clicks Queue Button - not logged in - stays at Home Page !!");
			}
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "Unable to click the Queue Button !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("user clicks the TreeButton")
	public void user_clicks_the_Tree_button() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			objHomePage.Graph_Btn_Click();
			Thread.sleep(1000);
			
			if(bValidCredentials)
			{
				Assert.assertEquals("User clicks Tree Button - logged in - moves to Tree Page !", objConstants.Tree_Page_URL, localDriver.getCurrentUrl());
				objBaseClass.LogGeneralMsg("User clicks Tree Button - logged in - moves to Tree Page !!");
			}
			else
			{
				Assert.assertEquals("User clicks Tree Button - not logged in - stays at Home Page !", objConstants.HomePage_URL, localDriver.getCurrentUrl());
				objBaseClass.LogGeneralMsg("User clicks Tree Button - not logged in - stays at Home Page !!");
			}
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "Unable to click the Tree Button !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@Then("user clicks the GraphButton")
	public void user_clicks_the_graph_button() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			objHomePage.Graph_Btn_Click();
			Thread.sleep(1000);
			
			if(bValidCredentials)
			{
				Assert.assertEquals("User clicks Graph Button - logged in - moves to Graph Page !", objConstants.Graph_Page_URL, localDriver.getCurrentUrl());
				objBaseClass.LogGeneralMsg("User clicks Graph Button - logged in - moves to Graph Page !!");
			}
			else
			{
				Assert.assertEquals("User clicks Graph Button - not logged in - stays at Home Page !", objConstants.HomePage_URL, localDriver.getCurrentUrl());
				objBaseClass.LogGeneralMsg("User clicks Graph Button - not logged in - stays at Home Page !!");
			}
		}
		catch(Exception e)
		{
			strErrMsgToLog = "Unable to click the Graph Button !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("user clicks the RegisterLink")
	public void user_clicks_the_register_link() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			objHomePage.RegisterUser_Click();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicks Register User Link - Moves to User Registration Page", objConstants.User_Regn_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogGeneralMsg("User clicks User Registration Link - Moves to User Registration Page !!");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "Unable to click the User Registration Link !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("user clicks the SignInLink")
	public void user_clicks_the_sign_in_link() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			objHomePage.SignIn_Click();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicks Sign-In User Link - Moves to User Sign-In Page", objConstants.SignIn_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogGeneralMsg("User clicks Sign-In Link - Moves to User Sign-In Page !!");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "Unable to click the Sign-In Link !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	// User Registration Page starts here.
	@Given("user launches the browser")
	public void user_launches_the_browser() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			if (localDriver == null) 
			{
				//Registration Page Driver is null and so re-initialized !
	
				objBaseClass = new BaseClassUtils();
				objBaseClass.LaunchBrowser();
				localDriver = objBaseClass.GetDriver();
			} 
			//else
				objRegUser = new RegisterUserPage(localDriver);
				
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "Launching web page to check the user registration test case !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "Unable to launch the web page to check the user registration test case !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Given("user opens the URL {string}")
	public void user_opens_the_url(String strURL) throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			localDriver.get(objConstants.User_Regn_Page_URL);
			
			objBaseClass.LogGeneralMsg("User landed in User Registration Page !");
			Assert.assertEquals("User clicks User Registration Link - Moves to User Registration Page", objConstants.User_Regn_Page_URL, localDriver.getCurrentUrl());
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "Unable to open the User Registration Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("user enters new {string} and {string} and {string}")
	public void user_enters_new_and_and(String strUname, String P1, String P2) throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			strUsername = strUname;
			strPass = P1;
			strConfirmPass = P2;
	
			objRegUser.setUserName(strUsername);
			objRegUser.setPassword(P1);
			objRegUser.setNewPassword(P2);
			Thread.sleep(1000);			
			
			objBaseClass.LogGeneralMsg("User testing registering User Name (" + strUsername + ") and Password (" + P1 + ") and P2 (" + P2 + ") in User Registration Page !");
			Assert.assertEquals("User testing registration page with different set of values !", objConstants.User_Regn_Page_URL, localDriver.getCurrentUrl());
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "Unable to enter login details in the user registration Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("click on register")
	public void click_on_register() throws InterruptedException, IOException {
		/*
		 * if(strUsername.length()<8) { LoggerLoad.ErrorMsg("Username (" + strUsername +
		 * ") is short !!"); bValidRegnValues = false; Assert.assertFalse(false); } else
		 * if(strUsername.length() > 150) { LoggerLoad.ErrorMsg("Username (" +
		 * strUsername + ") is longer than 150 chars !!"); bValidRegnValues = false;
		 * Assert.assertFalse(false); } else
		 */ 
		String sTestFailMsg = "";
		
		strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
		try
		{			
			if (strUsername.equalsIgnoreCase(strPass)) 
			{
				sTestFailMsg = "Username (" + strUsername + ") is the same as Password!!";
				LoggerLoad.ErrorMsg(sTestFailMsg);
				bValidRegnValues = false;
			}
			
			if (strUsername == "") 
			{
				sTestFailMsg = "Username is empty !!";
				LoggerLoad.ErrorMsg(sTestFailMsg);
				
				objRegUser.ClickRegisterUser();
				if(objRegUser.strUserNameValidatedMsg != "")
					LoggerLoad.SuccessMsg(objRegUser.strUserNameValidatedMsg + " -> system validated msg !");
				else
					LoggerLoad.WarningMsg("System failed to validate the empty user name !");
					
				bValidRegnValues = false;
			}
			
			if (strUsername.equalsIgnoreCase(strPass)) 
			{
				sTestFailMsg = "Username (" + strUsername + ") is the same as Password!!";
				LoggerLoad.ErrorMsg(sTestFailMsg);
				bValidRegnValues = false;
			}
			
			//if (objBaseClass.isAllAlphabets(strUsername)) 
			{
				// Enable these lines of code if "ALL ALPHABETS IN A STRING" is declared invalid
				// under a given condition
				// LoggerLoad.ErrorMsg("Username is all alphabets !!");
				// bValidRegnValues = false;
				// Assert.assertFalse(false);
			} 
			
			if (objBaseClass.isAllNumeric(strUsername)) 
			{
				sTestFailMsg = "Username (" + strUsername + ")  is all numeric !!";
				LoggerLoad.ErrorMsg(sTestFailMsg);
				bValidRegnValues = false;
			}
			
			if (!objBaseClass.IsValidUserName(strUsername)) 
			{
				sTestFailMsg = "Username (" + strUsername + ") does not follow the standards !!";
				LoggerLoad.ErrorMsg(sTestFailMsg + objBaseClass.strValidUserNameStatus);
				bValidRegnValues = false;
			}
			
			if (strPass == "") 
			{
				sTestFailMsg = "Password is empty !!";
				LoggerLoad.ErrorMsg(sTestFailMsg);
				
				objRegUser.ClickRegisterUser();
				if(objRegUser.strPwdValidatedMsg != "")
					LoggerLoad.SuccessMsg(objRegUser.strPwdValidatedMsg + " -> system validated msg !");
				else
					LoggerLoad.WarningMsg("System failed to validate the empty password !");
					
				bValidRegnValues = false;
			}
			
			if (strConfirmPass == "") 
			{
				sTestFailMsg = "Confirm Password is empty !!";
				LoggerLoad.ErrorMsg(sTestFailMsg);
				
				objRegUser.ClickRegisterUser();
				if(objRegUser.strPwd2ValidatedMsg != "")
					LoggerLoad.SuccessMsg(objRegUser.strPwd2ValidatedMsg + " -> system validated msg !");
				else
					LoggerLoad.WarningMsg("System failed to validate the empty password !");
				
				bValidRegnValues = false;
			}
			
			if (strPass != strConfirmPass) 
			{
				sTestFailMsg = "Passwords do not match !!";
				LoggerLoad.ErrorMsg(sTestFailMsg);
				bValidRegnValues = false;
			}
			
			if (bValidRegnValues) 
			{
				objRegUser.ClickRegisterUser();
				Thread.sleep(1000);
				objBaseClass.LogSuccessMsg(sClassName, strMethodName, "New User Registered successfully !");
			} 
			else 
			{
				LoggerLoad.ErrorMsg("Invalid User Registration details supplied !!");
			}
		}
		catch(Exception e)
		{	
			strErrMsgToLog = sTestFailMsg;
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}		
	}

	@When("Page source contains {string}")
	public void page_source_contains(String strSuccess) throws IOException 
	{
		String sUserInfoStatus = "";
		
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			strStatusMsg = objRegUser.LoginSuccessMsg();
			
			if (strStatusMsg.contains("New ")) 
			{
				// Registration successful. Check for any of the following conditions:
				// //div[@class='alert alert-primary'] -> Displays "New Account Created. You are
				// logged in as <username>
				// OR <a href=""> TrialLogin1 </a> WHERE "TrialLogin1" is the new user account
				// created.
	
				// System.out.println("Msg From feature file: " + strSuccess + strUsername);
				// System.out.println("Msg From web page: " + strStatusMsg);
	
				if (bValidRegnValues)
				{
					sUserInfoStatus = "New user registered successfully !";
					objBaseClass.LogSuccessMsg(sClassName, strMethodName, sUserInfoStatus);
				}
			} 
			else
			{
				sUserInfoStatus = "Invalid credentials to register the user !!";
				objBaseClass.LogErrMsg(sClassName, strMethodName, sUserInfoStatus);
			}
			
			if (localDriver.getPageSource().contains("password_mismatch:")) 
			{
				// System.out.println("Password Mismatch !!");
				sUserInfoStatus = "Password Mismatch !!";
				objBaseClass.LogErrMsg(sClassName, strMethodName, sUserInfoStatus);
			}
			
			Assert.assertEquals("User testing registration page with valid set of values !", objConstants.User_Regn_Page_URL, localDriver.getCurrentUrl());
		}
		catch(Exception e)
		{	
			strErrMsgToLog = sUserInfoStatus;
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
		
	@Then("click on sign out")
	public void click_on_sign_out() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			if (strStatusMsg != null) 
			{
				if (strStatusMsg.contains("New ")) 
				{
					objRegUser.ClickLogout();
					Thread.sleep(3000);
					
					Assert.assertEquals("User signing out after registering !", objConstants.User_Regn_Page_URL, localDriver.getCurrentUrl());
					objBaseClass.LogWarningMsg(sClassName, strMethodName, "User signed out !!");
				}
			}
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "Error while User signing out after registering!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("close the browser")
	public void close_the_browser() throws InterruptedException 
	{
		isConstInitiated = false;
		if (localDriver != null) localDriver.quit();
		Thread.sleep(1000);
		objBaseClass.LogGeneralMsg("User closed the browser / driver !");
	}

	// Sign-In Page starts here.
	@Given("Launchh the browser")
	public void launch_the_browser() throws IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			sClassName = this.getClass().getName();
			
			objBaseClass = new BaseClassUtils(); 
			objBaseClass.LaunchBrowser();
	 		localDriver = objBaseClass.GetDriver();
	  
			objFileData = new DataFileHandling(); 
			objConstants = new WebPageConstants();
			
			objBaseClass.LogGeneralMsg("User launched the web page to test Sign-In Module !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to launch the browser -> Sign-In Module !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
  
	@Then("Openn the website")
	public void open_the_website() throws IOException
	{ 
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			localDriver.get(objBaseClass.SignInPage_URL); 
			objSignInPage = new SignInPage(localDriver);
			
			Assert.assertEquals("User in Sign-In Page !", objConstants.SignIn_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogWarningMsg(sClassName, strMethodName, "User in Sign-In Page !!");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to launch the Sign-In Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	  
	@Then("Readd the XL file values")
	public void read_the_xl_file_values() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		  
			objBaseClass = new BaseClassUtils();	  
			objFileData = new DataFileHandling(); 
			// objFileData.ReadConfigFileParams();
		  
			strExcelFilePath = objConstants.ExcelFile_Path; 
			strSignInUserName = objConstants.Username; 
			strSignInPassCode = objConstants.Password;
			strLoginDataSheet = objConstants.SignIn_XL_DataSheet;
			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "Reading XL File and Sheet Names !!");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to read XL File and Sheet Names !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, false);
		}
	}
	  
	@Then("Checkk for valid credentials") 
	public void check_for_valid_credentials() throws IOException 
	{ 
		strMethodName = new Throwable().getStackTrace()[0].getMethodName();
	  
		try 
		{ 
			File myXLFile = new File(System.getProperty("user.dir") + objFileData.getExcelFilePath());
			
			objFileData.setXLWorkBook(myXLFile);
			myXLWorkBook = objFileData.getXLWorkBook();
	  
			shtLogin = objFileData.getXLWorkSheet(myXLWorkBook, objFileData.getLoginSheetName());
			
			Row rowFirstRow = objFileData.setRowStart(shtLogin); 
			int intLastRow =  shtLogin.getLastRowNum(); 
			int intFirstCol = rowFirstRow.getFirstCellNum();
			int intLastCol = rowFirstRow.getLastCellNum();
	  
			DataFormatter formatter = new DataFormatter();
	  
			for(intRowLoop = 1; intRowLoop <= intLastRow; intRowLoop++) 
			{ 
				intColLoop = 0;  
				rowFirstRow.setRowNum(intRowLoop);
	  
				strSignInUserName = formatter.formatCellValue(shtLogin.getRow(intRowLoop).getCell(intColLoop));
				// objSignInPage.setUserName(strSignInUserName);
				objBaseClass.setText(objSignInPage.getUsernameTextBox(), strSignInUserName);
	  
				intColLoop = intColLoop + 1;
	  
				strSignInPassCode = formatter.formatCellValue(shtLogin.getRow(intRowLoop).getCell(intColLoop));
 				// objSignInPage.setUserName(strSignInPassCode); 
				objBaseClass.setText(objSignInPage.getPasswordTextBox(), strSignInPassCode);
	  
				objSignInPage.ClickLogin(); 
				Thread.sleep(1000);
	  
				try 
				{ 
					// bValidCredentials = wDriver.findElement(hrefSignOut).isDisplayed();
					
					if(localDriver.getCurrentUrl().equalsIgnoreCase(objConstants.HomePage_URL))
						bValidCredentials = true;
					else
						bValidCredentials = false;
				
					myXLWorkBook.close(); 
				}	  
				catch(Exception E) 
				{
					System.out.println("Row number " + intRowLoop + "# Failed reading from XL File !");
					objBaseClass.LogErrMsg(sClassName, strMethodName, "Failed reading from XL File !!");
				} 
			} 
		}
	  
		catch(Exception E) 
		{ 	
			strErrMsgToLog = "User unable to read XL File !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, false);
		}
	}
	  
	@Then("Loginn if valid") 
	public void login_if_valid() throws IOException 
	{ 
		try 
		{ 
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			if(bValidCredentials) 
			{ 
				LoggerLoad.SuccessMsg("Class Name: " + this.getClass().getSimpleName() + " -> Method: " + strMethodName + " -> Success Msg : Login Success !!");
				Assert.assertEquals("Signed in with Valid credentials !",  objConstants.HomePage_URL, localDriver.getCurrentUrl());
			} 
			else 
			{
				LoggerLoad.WarningMsg("Class Name: " + this.getClass().getSimpleName() + " -> Method: " + strMethodName + " -> Warning : Login failed !!"); 
			} 
		}
	  
		catch(Exception E) 
		{ 
			if(!bValidCredentials)				
				strErrMsgToLog = "Invalid login credentials !";
				objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, false);
		} 
	} 

	//RAVALI Data Structures Step Def
	@When("User clicks the Getstarted button of Data Structures - Introduction")
	public void user_clicks_the_getstarted_button_of_data_structures_introduction() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	

			objHomePage.DSIntro_Btn_Click();
			Thread.sleep(1000);	
			
			Assert.assertEquals("User clicks the Getstarted button of Data Structures - Introduction -> DS Page !",  objConstants.DSIntro_Page_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicks the Getstarted button of Data Structures - Introduction -> DS Page !");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			strErrMsgToLog = "User unable to click the Getstarted button of Data Structures - Introduction -> DS Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	
	}

	@Then("User is directed to Data Structures -Introduction Page")
	public void user_is_directed_to_data_structures_introduction_page() throws IOException 
	{

		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	

			Assert.assertEquals("https://dsportalapp.herokuapp.com/data-structures-introduction/", localDriver.getCurrentUrl());
			
			objDSPage = new DataStructuresPage(localDriver);
			Assert.assertEquals("Assert - DS Intro Page success", objConstants.DSIntro_Page_URL, localDriver.getCurrentUrl());
			Assert.assertEquals("User is on Data Structures - Introduction Page !",  objConstants.DSIntro_Page_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is on Data Structures - Introduction Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to land on Data Structures - Introduction Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("User clicks on the Time Complexity link")
	public void user_clicks_on_the_time_complexity_link() throws InterruptedException, IOException 
	{	

		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
	
			objDSPage.ClicklnkTimeComplexity();
			Thread.sleep(2000);
			
			Assert.assertEquals("User is able to click Time Complexity Link -> DS Page !",  objConstants.DSTimeComplexity_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to click Time Complexity Link -> DS Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to click Time Complexity Link -> DS Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}		
	}

	@Then("User is directed to Time Complexity Page")
	public void user_is_directed_to_time_complexity_page() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
		
			Assert.assertEquals("User is directed to Time Complexity Page !",  objConstants.DSTimeComplexity_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is directed to Time Complexity Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to land on Time Complexity Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}			
    }
	
	@When("User clicks on DSTry here button")
	public void user_clicks_on_DStry_here_button() throws IOException
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
		
			objDSPage.ClickTCtryherebtn();	
			
			// DisplayEditorCode(strCurrPageName, strCurrPageName);
			
			Assert.assertEquals("User clicks on TryHere button ->DS Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to click Try Here button -> DS Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to click Try Here button -> DS Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@Then("User is directed to DStryEditor Page")
	public void user_is_directed_to_DStry_editor_page() throws IOException 
	{	
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	

			objEditorPage = new TryEditorPage(localDriver);
			String sCodeSnippet;
			
			sCodeSnippet ="print(" + "\"INTRO TO DATA STRUCTURES" + "\");";
			objEditorPage.TryThisCode(sCodeSnippet);	
		
			Assert.assertEquals("User is in TryEditor Page -> DS Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is in TryEditor Page -> DS Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to land on TryEditorPage -> DS Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);		
		}
	}

	@Then("User clicks on DSRun button")
	public void user_clicks_on_DSrun_button() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	

		    objEditorPage.RunEditorCode();	
		    
		    Assert.assertEquals("User clicks on Run button in Try Editor page -> DS Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to click on Run button in Try Editor page -> DS Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to click on Run button in Try Editor page -> DS Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);		
		}
	}
	
	@Then("Navigates back to Time Complexity page")
	public void navigates_back_to_time_complexity_page() throws IOException
	{

		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	

			localDriver.navigate().back();
			Assert.assertEquals("User navigates back to Time Complexity page -> DS Page !",  objConstants.DSTimeComplexity_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to navigate back to Time Complexity page -> DS Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to navigate back to Time Complexity page -> DS Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);		
		}
	}
	
	@When("User clicks on DSPractice Questions link")
	public void user_clicks_on_DSpractice_questions_link() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
		
			objDSPage.ClickPQlnk();
			Assert.assertEquals("User clicks on Practice Questions link -> DS Page !",  objConstants.DSPracticeQuest_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to click on Practice Questions link -> DS Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to click on Practice Questions link -> DS Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);		
		}
	}
	
	@Then("User is directed to DSPractice Questions page")
	public void user_is_directed_to_DSpractice_questions_page() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
		
	        Assert.assertEquals("User is directed to Practice Questions Page -> DS Page !",  objConstants.DSPracticeQuest_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to land on Practice Questions Page -> DS Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to land on Practice Questions Page -> DS Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);		
		}
	}	

	// Linked List Page starts here.
	@Given("User is on the homepage")
	public void user_is_on_the_homepage() throws IOException 
	{	
		try
		{						
			objBaseClass = new BaseClassUtils();
			objConstants = new WebPageConstants();
			
			objBaseClass.LaunchBrowser();
			localDriver = objBaseClass.GetDriver();
	
			objHomePage = new HomePage(localDriver);
			localDriver.get(objConstants.HomePage_URL);
		
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();			
			sClassName = this.getClass().getName();
			
			Assert.assertEquals("User is in the Home Page to login any DS Page !",  objConstants.HomePage_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(this.getClass().getName(), strMethodName, "User launched Home Page to login and test any DS Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User failed to launch the Home Page to login and test any DS Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("User logs in with valid credentials")
	public void user_logs_in_with_valid_credentials() throws IOException 
	{
	    try
	    {	    	
	    	strMethodName = new Throwable().getStackTrace()[0].getMethodName();
	    	
	    	objHomePage.SignIn_Click();
		    objSignInPage = new SignInPage(localDriver);
			
			objSignInPage.getUsernameTextBox().sendKeys(objConstants.Username);
			objSignInPage.getPasswordTextBox().sendKeys(objConstants.Password);
			
			objSignInPage.ClickLogin();
		
			Assert.assertEquals("User logs in the Home Page to test any DS Page !",  objConstants.HomePage_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User logs in Home Page to test any DS Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User failed to log into the Home Page to test any DS Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Given("Click the GetStarted then redirected to LL Page")
	public void click_the_get_started_and_redirected_to_ll_page() throws IOException 
	{		
	    try
	    {	    	
	    	strMethodName = new Throwable().getStackTrace()[0].getMethodName();
	    	
	    	objHomePage.LinkedList_Btn_Click();
	    	
	    	strCurrPageName = objBaseClass.getCurrentURL(localDriver);
	    	
	    	Assert.assertEquals("User clicked Get Started Button of " + strCurrPageName + " in Home Page !",  objConstants.LL_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked on the Get Started Button of Linked List in Home Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Get Started Button of Linked List in Home Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}	    
	}

	@Then("Click on the Intro Link")
	public void click_on_the_intro_link() throws InterruptedException, IOException
	{	
		try
		{			
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
	
			objLLPage = new LinkedListPage(localDriver);
			objLLPage.PageIntroClick();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked Introduction to LL Link - LL Page !",  objConstants.LL_Page_Intro_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Introduction to LL Link - LL Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Introduction to LL Link - LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}		
	}

	@Then("User redirected to LLIntroPage and clicks the TryHere")
	public void user_redirected_to_ll_intro_page_and_clicks_the_try_here() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objLLPage.TryEditorClick();
			
			Assert.assertEquals("User is directed to the (INTRO) code editor Page (LL) !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is directed to the (INTRO) code editor Page (LL) !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is directed to the (INTRO) code editor Page (LL) !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to the Editor Page and works on code snippet")
	public void user_redirected_to_the_editor_page_and_works_on_code_snippet() throws IOException 
	{	
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			DisplayEditorCode(strCurrPageName, objLLPage.sClickedLLLinkName);
			
			Assert.assertEquals("User is trying a code snippet in the editor Page from (INTRO TO) LL !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is trying a code snippet in the editor Page from (INTRO TO) LL !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to try a code snippet in (INTRO TO) LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	public void DisplayEditorCode(String sPageName, String sLinkDesc) throws IOException
	{		
		try
		{
			String sCodeSnippet;
	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();			
			objEditorPage = new TryEditorPage(localDriver);
			
			switch (sPageName)
			{
				case "DS":
					sCodeSnippet ="print(" + "\"INTRO TO DATA STRUCTURES" + "\");";										
					break;
				case "Array":
					sCodeSnippet ="print(" + "\"INTRO TO ARRAY" + "\");";
					break;
				case "LL":
					sCodeSnippet ="print(" + "\"INTRO TO LINKED LIST" + "\");";	
					break;
				case "Stack":
					sCodeSnippet ="print(" + "\"STACK OPERATIONS" + "\");\n";	
					break;
				case "Queue":
					sCodeSnippet ="print(" + "\"QUEUE OPERATIONS" + "\");\n";
					break;
				case "Tree":
					sCodeSnippet ="print(" + "\"TREE OPERATIONS" + "\");\n";						
					break;
				case "Graph":
					sCodeSnippet ="print(" + "\"GRAPH OPERATIONS" + "\");\n";
					break;
				case "ArrayQuestion":
					sCodeSnippet ="print(" + "\"PRACTICE ARRAY" + "\");";
					break;
				default:
					sCodeSnippet ="print(" + "\"**********   <-- DO NOTHING HERE -->   **********" + "\");";
			}
			
			objEditorPage.TryThisCode(sCodeSnippet);
			
			if(sPageName != "ArrayQuestion")
			{
				sCodeSnippet ="print(" + "\"*********************" + "\");\n";
				objEditorPage.TryThisCode(sCodeSnippet);
				
				if(sLinkDesc != "" || sLinkDesc != null)
					sCodeSnippet = "print(" + "\"" + sLinkDesc + "\");\n";
	
				objEditorPage.TryThisCode(sCodeSnippet);
			}
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to paste the code snippet !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, false);
		}		
	}

	@When("Clicked on the RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_run_button_and_verify_the_output_try_a_print_hello_statement_here() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the RUN button in (INTRO TO) Linked List - LL Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the RUN button in (INTRO TO) Linked List - LL Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the RUN button in (INTRO TO) Linked List - LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("Click on Creating LL")
	public void click_on_creating_ll() throws InterruptedException, IOException 
	{		
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objLLPage.CreateLLClick();
			
			Assert.assertEquals("User clicked Create a Linked List - LL Page !",  objConstants.LL_Create_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Create a Linked List - LL Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Create Linked List - LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}		
	}

	@Then("User redirected to CreateLLPage and clicks the TryHere")
	public void user_redirected_to_create_ll_page_and_clicks_the_try_here() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objLLPage.TryEditorClick();
		
			Assert.assertEquals("User is in (Create) LL Page and clicked the TRY HERE button !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is in LL Page and clicked the (Create LL) TRY HERE button !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the TRY HERE Button in (Create) LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@Then("User redirected to the CreateLL Editor Page and works on code snippet")
	public void user_redirected_to_the_create_ll_editor_page_and_works_on_code_snippet() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			String sCodeSnippet ="\n\nprint(" + "\"CREATING LINKED LIST" + "\");";
			objEditorPage.TryThisCode(sCodeSnippet);
			
			sCodeSnippet ="\nprint(" + "\"**************************" + "\");";
			objEditorPage.TryThisCode(sCodeSnippet);		
			
			ResourceBundle rbLL = ResourceBundle.getBundle("LinkedList");
			
			String sCreateLLCode = "";
			
			String sClassNodeLLCode = "\n" ;
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("A") + "\n";
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("B")+ "\n";
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("C")+ "\n";
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("D")+ "\n";
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("E");
			objEditorPage.TryThisCode(sClassNodeLLCode);
			
			String sClassSingleLLCode=  "";
			sClassSingleLLCode = "\n\b\b\b\b" + rbLL.getString("F") + "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("G")+ "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("H")+ "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("I")+ "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("J")+ "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("K");
			objEditorPage.TryThisCode(sClassSingleLLCode);
			
			String sDefIterateItemCode = "";
			sDefIterateItemCode = "\n\b\b" + rbLL.getString("L")+ "\n";
			sDefIterateItemCode = sDefIterateItemCode + rbLL.getString("M")+ "\n";
			sDefIterateItemCode = sDefIterateItemCode + rbLL.getString("N")+ "\n";
			sDefIterateItemCode = sDefIterateItemCode + rbLL.getString("O")+ "\n";
			objEditorPage.TryThisCode(sDefIterateItemCode);
			
			String sWhileLoopCode = "";
			sWhileLoopCode = rbLL.getString("P")+ "\n";
			sWhileLoopCode = sWhileLoopCode + rbLL.getString("Q")+ "\n";
			sWhileLoopCode = sWhileLoopCode + rbLL.getString("R")+ "\n";		
			objEditorPage.TryThisCode(sWhileLoopCode);
			
			String sAppendItemCode = "";
			sAppendItemCode = "\n\b\b\b\b" + rbLL.getString("S")+ "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("T")+ "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("U")+ "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("V")+ "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("W")+ "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("X")+ "\n\b\b";
			sAppendItemCode = sAppendItemCode + rbLL.getString("Y")+ "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("Z")+ "\n";
			objEditorPage.TryThisCode(sAppendItemCode);
			
			String sIfElseLoopCode = "";
			sIfElseLoopCode = rbLL.getString("AA")+ "\n";
			sIfElseLoopCode = sIfElseLoopCode + "\b\b" + rbLL.getString("BB")+ "\n";
			sIfElseLoopCode = sIfElseLoopCode + "\b\b\b\b" + rbLL.getString("CC")+ "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("DD")+ "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("EE")+ "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("FF")+ "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("GG")+ "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("HH")+ "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("II")+ "\n";
	
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("JJ")+ "\n";
			sIfElseLoopCode = sIfElseLoopCode + "\b\b" + rbLL.getString("KK")+ "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("LL");
			objEditorPage.TryThisCode(sIfElseLoopCode);
	
			/*
			String fileContent = Files.readString(Paths.get("C:\\Users\\aasvi\\eclipse-workspace\\DS-Algo\\src\\test\\resources\\CreateLL.txt"));
			System.out.println(fileContent);
			
			objEditorPage.TryThisCode("");
			Thread.sleep(1000);
			objEditorPage.TryThisCode(fileContent);
			*/
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to read the code from the LinkedList.properties file for CREATING A LINKED LIST in LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("Clicked on the CreateLL RunButton and verify the output #Try a code snippet here.")
	public void clicked_on_the_run_button_and_verify_the_output_try_a_code_snippet_here() throws InterruptedException, IOException 
	{
		try
		{			
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(2000);
						
			Assert.assertEquals("User is in (Create) LL Page and clicked the RUN button !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is in LL Page and clicked the (Create LL) RUN button !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to RUN the code snippet to create a Linked List !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}	
	}

	@Then("Click on Types of LL")
	public void click_on_types_of_ll() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objLLPage.TypesOfLLClick();
			
			Assert.assertEquals("User clicked Types of Linked List - LL Page !",  objConstants.LL_Types_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Types of Linked List - LL Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Types of Linked List - LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to LLTypesPage and clicks the TryHere")
	public void user_redirected_to_ll_types_page_and_clicks_the_try_here() throws IOException 
	{	
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objLLPage.TryEditorClick();
			
			Assert.assertEquals("User is in (TypesOf)LL Page and clicked the TRY HERE button !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is in (TypesOf)LL Page and clicked the TRY HERE button !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the TRY HERE Button in (TypesOf)LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@Then("User redirected to the TypesofLL Editor Page and works on code snippet")
	public void user_redirected_to_the_typesof_ll_editor_page_and_works_on_code_snippet() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			String sCodeSnippet ="print(" + "\"TYPES OF LINKED LIST" + "\");" + "\n";
			objEditorPage.TryThisCode(sCodeSnippet);
			
			sCodeSnippet ="print(" + "\"********************" + "\");" + "\n";
			objEditorPage.TryThisCode(sCodeSnippet);
			
			sCodeSnippet = "print(" + "\"Singly Linked List" + "\");" + "\n";
			objEditorPage.TryThisCode(sCodeSnippet);
			
			sCodeSnippet = "print(" + "\"Doubly Linked List" + "\");";
			objEditorPage.TryThisCode(sCodeSnippet);
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to paste the code for TYPES OF LINKED LIST in LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("Clicked on the TypesofLL RunButton and verify the output #Try a code snippet for single and double LL here.")
	public void clicked_on_the_run_button_and_verify_the_output_try_a_code_snippet_for_single_and_double_ll_here() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(2000);
			
			Assert.assertEquals("User is in (TypesOf) LL Page and clicked the RUN button !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is in LL Page and clicked the (TypesOf LL) RUN button !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to RUN the code snippet for TYPES OF Linked List !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("Click on Implement LL in python")
	public void click_on_implement_ll_in_python() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objLLPage.ImplementLLClick();
			
			Assert.assertEquals("User clicked IMPLEMENT a Linked List - LL Page !",  objConstants.LL_Impl_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked IMPLEMENT a Linked List - LL Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the IMPLEMENT Linked List - LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}		
	}

	@Then("User redirected to ImplementLLPage and clicks the TryHere")
	public void user_redirected_to_implement_ll_page_and_clicks_the_try_here() throws IOException 
	{		
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objLLPage.TryEditorClick();
			
			Assert.assertEquals("User is in (IMPLEMENT) LL Page and clicked the TRY HERE button !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is in (IMPLEMENT) LL Page and clicked the TRY HERE button !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the (IMPLEMENT LL) TRY HERE Button LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to the ImplementLL Editor Page and works on code snippet")
	public void user_redirected_to_the_implement_ll_editor_page_and_works_on_code_snippet() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			String sCodeSnippet ="print(" + "\"IMPLEMENT LINKED LIST" + "\");" + "\n";
			objEditorPage.TryThisCode(sCodeSnippet);
			
			sCodeSnippet ="print(" + "\"***********************" + "\");" + "\n";
			objEditorPage.TryThisCode(sCodeSnippet);
			
			ResourceBundle rbLL = ResourceBundle.getBundle("ImplementLL");
			
			String sCreateLLCode = "";
			
			String sClassNodeLLCode = "" ;
			sClassNodeLLCode = rbLL.getString("A") + "\n";
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("B")+ "\n";
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("C")+ "\n";
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("D")+ "\n";
			sClassNodeLLCode = sClassNodeLLCode + "\b\b\b\b" + rbLL.getString("E");
			objEditorPage.TryThisCode(sClassNodeLLCode);
			
			String sClassSingleLLCode=  "";
			sClassSingleLLCode = "\n" + rbLL.getString("F") + "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("G")+ "\n";
			sClassSingleLLCode = sClassSingleLLCode + "\b\b\b\b" + rbLL.getString("H")+ "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("I")+ "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("J")+ "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("K");
			objEditorPage.TryThisCode(sClassSingleLLCode);
			
			String sDefIterateItemCode = "";
			sDefIterateItemCode = "\n" + rbLL.getString("L")+ "\n";
			sDefIterateItemCode = sDefIterateItemCode + rbLL.getString("M")+ "\n";
			sDefIterateItemCode = sDefIterateItemCode + rbLL.getString("N")+ "\n";
			sDefIterateItemCode = sDefIterateItemCode + rbLL.getString("O")+ "\n";
			objEditorPage.TryThisCode(sDefIterateItemCode);
			
			String sWhileLoopCode = "";
			sWhileLoopCode = rbLL.getString("P")+ "\n";
			sWhileLoopCode = sWhileLoopCode + rbLL.getString("Q")+ "\n";		
			objEditorPage.TryThisCode(sWhileLoopCode);
		}
		catch(Exception E)
		{
			strErrMsgToLog = "User unable to read the code from the ImplementLL.properties file for IMPLEMENTING A LINKED LIST in LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@When("Clicked on the ImplementLL RunButton and verify the output #Try a code snippet here.")
	public void clicked_on_the_implement_ll_run_button_and_verify_the_output_try_a_code_snippet_here() throws InterruptedException, IOException 
	{
	    try
	    {			
	    	strMethodName = new Throwable().getStackTrace()[0].getMethodName();
	    	
	    	objEditorPage.RunEditorCode();
		    Thread.sleep(1000);

		    Assert.assertEquals("User is in (IMPLEMENT) LL Page and clicked the RUN button !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is in (IMPLEMENT) LL Page and clicked the RUN button !");
		    
		    localDriver.navigate().back();
	    }
	    catch(Exception E)
		{
			strErrMsgToLog = "User unable to RUN the code for IMPLEMENTING A LINKED LIST in LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@Then("Click on Traversal")
	public void click_on_traversal() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objLLPage.TraverseClick();
			
			Assert.assertEquals("User clicked TRAVERSING a Linked List - LL Page !",  objConstants.LL_Traverse_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked TRAVERSING a Linked List - LL Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the TRAVERSE Linked List - LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}		
	}

	@Then("User redirected to TraversePage and clicks the TryHere")
	public void user_redirected_to_traverse_page_and_clicks_the_try_here() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();

			objLLPage.TryEditorClick();
			
			Assert.assertEquals("User clicked Try Here -> TRAVERSING a Linked List -> LL Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Try Here -> TRAVERSING a Linked List -> LL Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Try Here -> TRAVERSING a Linked List -> LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}	
	}

	@Then("User redirected to the Traversal Editor Page and works on code snippet")
	public void user_redirected_to_the_traversal_editor_page_and_works_on_code_snippet() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			String sCodeSnippet ="print(" + "\"TRAVERSE LINKED LIST" + "\");" + "\n";
			objEditorPage.TryThisCode(sCodeSnippet);
			
			sCodeSnippet ="print(" + "\"***********************" + "\");" + "\n";
			objEditorPage.TryThisCode(sCodeSnippet);		
			
			ResourceBundle rbLL = ResourceBundle.getBundle("TraverseLL");
			
			String sCreateLLCode = "";
			
			String sClassNodeLLCode = "" ;
			sClassNodeLLCode = rbLL.getString("A") + "\n";
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("B")+ "\n";
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("C")+ "\n";
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("D")+ "\n";
			sClassNodeLLCode = sClassNodeLLCode + "\b\b\b\b" + rbLL.getString("E");
			objEditorPage.TryThisCode(sClassNodeLLCode);
			
			String sClassSingleLLCode=  "";
			sClassSingleLLCode = "\n" + rbLL.getString("F") + "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("G")+ "\n";
			sClassSingleLLCode = sClassSingleLLCode + "\b\b" + rbLL.getString("H")+ "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("I")+ "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("J")+ "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("K");
			objEditorPage.TryThisCode(sClassSingleLLCode);
			
			String sDefIterateItemCode = "";
			sDefIterateItemCode = "\n" + rbLL.getString("L")+ "\n";
			sDefIterateItemCode = sDefIterateItemCode + "\b\b\b\b\b\b" + rbLL.getString("M")+ "\n";
			sDefIterateItemCode = sDefIterateItemCode + rbLL.getString("N")+ "\n";
			sDefIterateItemCode = sDefIterateItemCode + rbLL.getString("O")+ "\n";
			objEditorPage.TryThisCode(sDefIterateItemCode);
			
			String sWhileLoopCode = "";
			sWhileLoopCode = rbLL.getString("P")+ "\n";
			sWhileLoopCode = sWhileLoopCode + rbLL.getString("Q")+ "\n";
			sWhileLoopCode = sWhileLoopCode + rbLL.getString("R")+ "\n";		
			objEditorPage.TryThisCode(sWhileLoopCode);
			
			String sAppendItemCode = "";
			sAppendItemCode = "\n" + rbLL.getString("S")+ "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("T")+ "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("U")+ "\n";
			objEditorPage.TryThisCode(sAppendItemCode);
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to read the code from TraverseLL.properties -> Editor Page -> LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@When("Clicked on the Traversal RunButton and verify the output #Try a code snippet here. available in the same web page")
	public void clicked_on_the_traversal_run_button_and_verify_the_output_try_a_code_snippet_here_available_in_the_same_web_page() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(2000);
			
			Assert.assertEquals("User clicked on the RUN Button of (TRAVERSE) Editor Page -> LL Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked on the RUN Button of (TRAVERSE) Editor Page -> LL Page !");
			
			localDriver.navigate().back();			
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click on the RUN Button of (TRAVERSE) Editor Page -> LL Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("Click on Insertion")
	public void click_on_insertion() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objLLPage.InsertIntoLLClick();
			
			Assert.assertEquals("User clicked on the INSERT into Linked List -> LL Page !",  objConstants.LL_Insert_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked on the INSERT into Linked List -> LL Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click on the INSERT into Linked List -> LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to InsertInLLPage and clicks the TryHere")
	public void user_redirected_to_insert_in_ll_page_and_clicks_the_try_here() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objLLPage.TryEditorClick();
			
			Assert.assertEquals("User clicked on the Try Here Button (INSERT into Linked List) -> LL Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked on the Try Here Button (INSERT into Linked List) -> LL Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click on the Try Here Button (INSERT into Linked List) -> LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to the Insertion Editor Page and works on code snippet")
	public void user_redirected_to_the_insertion_editor_page_and_works_on_code_snippet() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			String sCodeSnippet ="print(" + "\"INSERTING AT THE BEGINNING OF THE LINKED LIST" + "\");";
			objEditorPage.TryThisCode(sCodeSnippet);
			
			sCodeSnippet ="\nprint(" + "\"**********************************************" + "\");";
			objEditorPage.TryThisCode(sCodeSnippet);		
			
			ResourceBundle rbLL = ResourceBundle.getBundle("InsertIntoLL");
			
			String sCreateLLCode = "";
			
			String sClassNodeLLCode = "\n" ;
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("A") + "\n";
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("B") + "\n";
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("C") + "\n";
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("D") + "\n";
			sClassNodeLLCode = sClassNodeLLCode + "\b\b\b\b" + rbLL.getString("E");
			//System.out.println("D:" + rbLL.getString("D") + "\nE: " + rbLL.getString("E") + "\nF:" + rbLL.getString("F"));
			//System.out.println(sClassNodeLLCode);
			objEditorPage.TryThisCode(sClassNodeLLCode);
			
			String sClassSingleLLCode=  "";
			sClassSingleLLCode = "\n" + rbLL.getString("F") + "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("G")+ "\n";
			sClassSingleLLCode = sClassSingleLLCode + "\b\b\b\b" + rbLL.getString("H")+ "\n";
			sClassSingleLLCode = sClassSingleLLCode + "  " + rbLL.getString("I")+ "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("J")+ "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("K");
			objEditorPage.TryThisCode(sClassSingleLLCode);
			
			String sDefIterateItemCode = "";
			sDefIterateItemCode = "\n" + rbLL.getString("L")+ "\n";
			sDefIterateItemCode = sDefIterateItemCode + rbLL.getString("M")+ "\n";
			sDefIterateItemCode = sDefIterateItemCode + "\b\b\b\b" + rbLL.getString("N")+ "\n";
			sDefIterateItemCode = sDefIterateItemCode + rbLL.getString("O")+ "\n";
			objEditorPage.TryThisCode(sDefIterateItemCode);
			
			String sWhileLoopCode = "";
			sWhileLoopCode = rbLL.getString("P")+ "\n";
			sWhileLoopCode = sWhileLoopCode + rbLL.getString("Q")+ "\n";
			sWhileLoopCode = sWhileLoopCode + rbLL.getString("R")+ "\n";		
			objEditorPage.TryThisCode(sWhileLoopCode);
			
			String sAppendItemCode = "";
			sAppendItemCode = "\b\b\b\b" + rbLL.getString("S")+ "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("T")+ "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("U")+ "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("V")+ "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("W")+ "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("X")+ "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("Y")+ "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("Z")+ "\n";
			objEditorPage.TryThisCode(sAppendItemCode);
			
			String sIfElseLoopCode = "";
			sIfElseLoopCode = rbLL.getString("AA")+ "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("BB")+ "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("CC")+ "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("DD")+ "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("EE")+ "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("FF")+ "\n";	
			objEditorPage.TryThisCode(sIfElseLoopCode);
			
			Assert.assertEquals("User copied PYTHON code from the InsertIntoLL.properties file to INSERT into Linked List) -> LL Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User copied PYTHON code from the InsertIntoLL.properties file to INSERT into Linked List -> LL Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to copy PYTHON code from the InsertIntoLL.properties file to INSERT into Linked List -> LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, false);
		}
	}
	
	@When("Clicked on the Insertion RunButton and verify the output #Try a code snippet here.")
	public void clicked_on_the_insertion_run_button_and_verify_the_output_try_a_code_snippet_here() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(2000);
			
			Assert.assertEquals("User clicked on the RUN Button of (Insertion) Editor Page -> LL Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked on the RUN Button of (Insertion) Editor Page -> LL Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click on the RUN Button of (Insertion) Editor Page -> LL Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@Then("Click on Deletion")
	public void click_on_deletion() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objLLPage.DelFromLLClick();
			
			Assert.assertEquals("User clicked DELETE from a Linked List - LL Page !",  objConstants.LL_Del_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked DELETE from a Linked List - LL Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the DELETE from a Linked List - LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@Then("User redirected to DeleteFromLLPage and clicks the TryHere")
	public void user_redirected_to_delete_from_ll_page_and_clicks_the_try_here() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objLLPage.TryEditorClick();
			
			Assert.assertEquals("User clicked Try Here -> DELETE from a Linked List -> LL Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Try Here -> DELETE from a Linked List -> LL Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Try Here -> DELETE from a Linked List -> LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to the Deletion Editor Page and works on code snippet")
	public void user_redirected_to_the_deletion_editor_page_and_works_on_code_snippet() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			String sCodeSnippet ="print(" + "\"DELETE FROM THE LINKED LIST" + "\");";
			objEditorPage.TryThisCode(sCodeSnippet);
			
			sCodeSnippet ="\nprint(" + "\"*************************************" + "\");";
			objEditorPage.TryThisCode(sCodeSnippet);		
			
			ResourceBundle rbLL = ResourceBundle.getBundle("DeleteFromLL");
			
			String sCreateLLCode = "";
			
			String sClassNodeLLCode = "\n" ;
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("A") + "\n";
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("B") + "\n";
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("C") + "\n";
			sClassNodeLLCode = sClassNodeLLCode + rbLL.getString("D") + "\n";
			sClassNodeLLCode = sClassNodeLLCode + "\b\b\b\b" + rbLL.getString("E");
			objEditorPage.TryThisCode(sClassNodeLLCode);
			
			String sClassSingleLLCode=  "";
			sClassSingleLLCode = "\n" + rbLL.getString("F") + "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("G") + "\n";
			sClassSingleLLCode = sClassSingleLLCode + "\b\b" + rbLL.getString("H") + "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("I") + "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("J") + "\n";
			sClassSingleLLCode = sClassSingleLLCode + rbLL.getString("K") + "\n";
			sClassSingleLLCode = sClassSingleLLCode + "\b\b\b\b" + rbLL.getString("K1");
			objEditorPage.TryThisCode(sClassSingleLLCode);
			
			String sDefIterateItemCode = "";
			sDefIterateItemCode = "\n  " + rbLL.getString("L") + "\n";
			sDefIterateItemCode = sDefIterateItemCode + rbLL.getString("M") + "\n";
			sDefIterateItemCode = sDefIterateItemCode + rbLL.getString("N") + "\n";
			sDefIterateItemCode = sDefIterateItemCode + rbLL.getString("O") + "\n";
			objEditorPage.TryThisCode(sDefIterateItemCode);
			
			String sWhileLoopCode = "";
			sWhileLoopCode = rbLL.getString("P") + "\n";
			sWhileLoopCode = sWhileLoopCode + rbLL.getString("Q") + "\n";
			sWhileLoopCode = sWhileLoopCode + rbLL.getString("R") + "\n";		
			objEditorPage.TryThisCode(sWhileLoopCode);
			
			String sAppendItemCode = "";
			sAppendItemCode = "\b\b" + rbLL.getString("S") + "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("T") + "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("U") + "\n";
			sAppendItemCode = sAppendItemCode + "\b\b" + rbLL.getString("V") + "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("W") + "\n";
			sAppendItemCode = sAppendItemCode + "\b\b" + rbLL.getString("X") + "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("Y") + "\n";
			sAppendItemCode = sAppendItemCode + rbLL.getString("Z") + "\n";
			objEditorPage.TryThisCode(sAppendItemCode);
			
			String sIfElseLoopCode = "";
			sIfElseLoopCode = rbLL.getString("AA") + "\n";
			sIfElseLoopCode = sIfElseLoopCode + "\b\b" + rbLL.getString("BB") + "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("CC") + "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("DD") + "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("EE") + "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("FF") + "\n";	
			objEditorPage.TryThisCode(sIfElseLoopCode);
			
			sIfElseLoopCode = "";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("GG") + "\n";
			sIfElseLoopCode = sIfElseLoopCode + "\b\b\b\b\b\b" + rbLL.getString("HH") + "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("II") + "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("JJ") + "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("KK") + "\n";	
			objEditorPage.TryThisCode(sIfElseLoopCode);
			
			sIfElseLoopCode = "";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("LL") + "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("MM") + "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("NN") + "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("OO") + "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("PP") + "\n";	
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("QQ") + "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("RR") + "\n";
			sIfElseLoopCode = sIfElseLoopCode + rbLL.getString("SS") + "\n";
			
			objEditorPage.TryThisCode(sIfElseLoopCode);
			
			Assert.assertEquals("User is reading the PYTHON CODE for (DELETING LL) pasting in Editor Page -> LL Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is reading the PYTHON CODE for (DELETING LL) pasting in Editor Page -> LL Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to read the PYTHON CODE for (DELETING LL) pasting in Editor Page -> LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@When("Clicked on the Deletion RunButton and verify the output #Try a code snippet here.")
	public void clicked_on_the_deletion_run_button_and_verify_the_output_try_a_code_snippet_here() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(2000);
			
			Assert.assertEquals("User clicked on the RUN Button of (DELETE) Editor Page -> LL Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked on the RUN Button of (DELETE) Editor Page -> LL Page !");
		
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click on the RUN Button of (DELETE) Editor Page -> LL Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@Then("Click on the PracticeQn")
	public void click_on_the_practice_qn() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objLLPage.ClickPracticeQn();
			Thread.sleep(2000);
			
			Assert.assertEquals("User clicked on the PRACTICE QUESTIONS in Linked List -> LL Page !",  objConstants.LL_Pract_Qstn_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked on the PRACTICE QUESTIONS in Linked List -> LL Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click on the PRACTICE QUESTIONS in Linked List -> LL Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to PractivePage and do nothing")
	public void user_redirected_to_practive_page_and_do_nothing() 
	{
		CheckForTestCasesResults(strCurrPageName);		
	}
	
	public void CheckForTestCasesResults(String sPageName)
	{
		strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
		try
		{
			switch (sPageName)
			{
				case "LL":
					Assert.assertEquals("LL Practice Page pass !",  objConstants.LL_Pract_Qstn_URL, localDriver.getCurrentUrl());
					objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is in the PRACTICE QUESTIONS in Linked List Page !");
					break;
				case "Stack":
					Assert.assertEquals("Stack Practice Page pass !", objConstants.Stack_Pract_Qstn_URL, localDriver.getCurrentUrl());
					objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is in the PRACTICE QUESTIONS in Stack Page !");
					break;				
				case "Tree":
					Assert.assertEquals("Tree Practice Page pass !", objConstants.Tree_PractQuest_Page_URL, localDriver.getCurrentUrl());
					objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is in the PRACTICE QUESTIONS in Tree Page !");
					break;
				default:
					
					break;
			}
		}
		catch(Exception e)
		{
			objBaseClass.LogWarningMsg(sClassName, strMethodName, "Error while User navigates to the PRACTICE QUESTIONS in " + sPageName + "!");
		}
	}

	@Then("go back to homePage")
	public void go_back_to_home_page() throws InterruptedException, IOException 
	{
		strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
		try
		{
			localDriver.navigate().back();
			Thread.sleep(1000);
			localDriver.get(objConstants.HomePage_URL);
			
			Assert.assertEquals("User landed on the Home Page from " + strCurrPageName + " Page !",  objConstants.HomePage_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User landed on the Home Page from " + strCurrPageName + " Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User failed to land on the Home Page from " + strCurrPageName + " Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	// STACK PAGE starts here.
	@When("Click the GetStarted then redirected to Stack Page")
	public void click_the_get_started_then_redirected_to_stack_page() throws IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objHomePage.Stack_Btn_Click();
			
			Assert.assertEquals("User clicked on the Get Started button in Home Page to test the Stack Page !",  objConstants.Stack_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked on the Get Started button in Home Page to test the Stack Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Get Started button in Home Page to test the Stack Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}		
	}

	@Then("Click on the Operations in Stack")
	public void click_on_the_operations_in_stack() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objStackPage = new StackPage(localDriver);
			Thread.sleep(1000);
			
			strCurrPageName = objBaseClass.getCurrentURL(localDriver);
			objStackPage.ClickStackOperations();
			
			Assert.assertEquals("User clicked on the Operations in Stack to test the Stack Page !",  objConstants.Stack_Op_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked on the Operations in Stack to test the Stack Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Operations in Stack to test the Stack Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}		
	}

	@Then("User redirected to Stack Operations Page and clicks the TryHere")
	public void user_redirected_to_stack_operations_page_and_clicks_the_try_here() throws InterruptedException, IOException 
	{
		try
		{			
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
	
			Thread.sleep(1000);
			
			// strCurrPageName = objBaseClass.getCurrentURL(localDriver);
			
			objStackPage.ClickTryHere();
		    objEditorPage = new TryEditorPage(localDriver);
		    
		    Assert.assertEquals("User clicked on the TRY HERE -> Operations in Stack -> Stack Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked on the TRY HERE -> Operations in Stack -> Stack Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the TRY HERE -> Operations in Stack -> Stack Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to the Stack Editor Page and works on code snippet")
	public void user_redirected_to_the_stack_editor_page_and_works_on_code_snippet() throws IOException 
	{
		DisplayEditorCode(strCurrPageName, objStackPage.sClickedStackLinkName);
	}
	
	@When("Clicked on the Stack Op RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_Stack_Op_run_button_and_verify_the_output_try_a_print_hello_statement_here() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the RUN button in (Stack Operations) Stack Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the RUN button in (Stack Operations) Stack Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the RUN button in (Stack Operations) Stack Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("Click on the Implementations in Stack")
	public void click_on_the_implementations_in_stack() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
	
			objStackPage.ClickStackImpl();
			
			Assert.assertEquals("User clicked the Stack Impl -> Stack Page !",  objConstants.Stack_Impl_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Stack Impl -> Stack Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Stack Impl -> Stack Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to Implementations Page and clicks the TryHere")
	public void user_redirected_to_implementations_page_and_clicks_the_try_here() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
	
			Thread.sleep(1000);
			objStackPage.ClickTryHere();
		    objEditorPage = new TryEditorPage(localDriver);
		    
			Assert.assertEquals("User clicked the Stack Impl -> Try Here -> Stack Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Stack Impl -> Try Here -> Stack Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Stack Impl -> Try Here -> Stack Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("Clicked on the Stack Impl RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_Stack_Impl_run_button_and_verify_the_output_try_a_print_hello_statement_here() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the RUN button in (Stack Impl) - Stack Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the RUN button in (Stack Impl) - Stack Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the RUN button in (Stack Impl) - Stack Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@Then("Click on the Applications in Stack")
	public void click_on_the_applications_in_stack() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objStackPage.ClickStackAppl();
			
			Assert.assertEquals("User clicked the Stack Applications -> Stack Page !",  objConstants.Stack_App_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Stack Applications -> Stack Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Stack Applications -> Stack Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to Applications Page and clicks the TryHere")
	public void user_redirected_to_applications_page_and_clicks_the_try_here() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			Thread.sleep(1000);
			objStackPage.ClickTryHere();
		    objEditorPage = new TryEditorPage(localDriver);
		    
			Assert.assertEquals("User clicked the Stack Applications -> Try Here -> Stack Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Stack Applications -> Try Here -> Stack Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Stack Applications -> Try Here -> Stack Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@When("Clicked on the Stack App RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_Stack_App_run_button_and_verify_the_output_try_a_print_hello_statement_here() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Stack App -> Run Button -> Stack Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Stack App -> Run Button -> Stack Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Stack App -> RUN Button -> Stack Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@Then("Click on the PracticeQuestion")
	public void click_on_the_practice_question() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();		
	
			objStackPage.ClickPractQuest();
			Thread.sleep(2000);			
			
			Assert.assertEquals("User clicked the Stack App -> Practice Question -> Stack Page !",  objConstants.Stack_Pract_Qstn_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Stack App -> Practice Question -> Stack Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Stack App -> Practice Question -> Stack Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@When("User redirected to the Stack Editor Page and works on invalid code snippet")
	public void User_redirected_to_the_Stack_Editor_Page_and_works_on_invalid_code_snippet() throws InterruptedException, IOException 
	{
		String strInvalidCodeSnippet;
		
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			Thread.sleep(1000);
			
			strCurrPageName = objBaseClass.getCurrentURL(localDriver);
			 
			strInvalidCodeSnippet ="print(";
			objEditorPage.TryThisCode(strInvalidCodeSnippet);
		    
			Assert.assertEquals("User is in the Stack Editor Page and works on an Invalid Code Snippet -> Stack Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is in the Stack Editor Page and works on an Invalid Code Snippet -> Stack Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to move to the Stack Editor Page to work on an Invalid Code Snippet -> Stack Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("Clicked on the Stack Op RunButton and verify the alert #Try an Invalid Print statement here.")
	public void Clicked_on_the_Stack_Op_RunButton_and_verify_the_alert() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
			
			objEditorPage.RunEditorCode();
			
			if(objBaseClass.isAlertPresent(localDriver))
				objBaseClass.LogSuccessMsg(sClassName, strMethodName, "System Handled ALERT for invalid code snippet !");
				
			else
				objBaseClass.LogErrMsg(sClassName, strMethodName, "System FAILED TO HANDLE THE ALERT for invalid code snippet !");
			
			Assert.assertEquals("User clicked the Stack Operations (to check invalid code snippet) -> Run Button -> Stack Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Stack Operations (to check invalid code snippet) -> Run Button -> Stack Page !");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			strErrMsgToLog = "User unable to click the Stack Operations (to check invalid code snippet) -> RUN Button -> Stack Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	//Tree Page starts here.	
	@When("Click the GetStarted then redirected to Tree Page")
	public void click_the_get_started_then_redirected_to_tree_page() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
		    objHomePage.Tree_Btn_Click();
		    objTreePage = new TreePage(localDriver);
		    
		    Assert.assertEquals("User clicked the Get Started Button in Home Page to Test the Tree operations !",  objConstants.Tree_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Get Started Button in Home Page to Test the Tree operations !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Get Started Button in Home Page to Test the Tree operations !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("Click on the Overview of Trees")
	public void click_on_the_overview_of_trees() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
	    	objTreePage.ClickTreeOverview();
	    	
	    	Assert.assertEquals("User clicked the Overview of Trees -> Tree Page !",  objConstants.Tree_Overview_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Overview of Trees -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Overview of Trees -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to Tree Overview Page and clicks the TryHere")
	public void user_redirected_to_tree_overview_page_and_clicks_the_try_here() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			Thread.sleep(1000);
			strCurrPageName = objBaseClass.getCurrentURL(localDriver);
	    	objTreePage.ClickTryHere();
	    	
	    	Assert.assertEquals("User clicked the Overview of Trees -> Try Here -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Overview of Trees -> Try Here -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Overview of Trees -> Try Here -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}	    	
	}

	@Then("User redirected to the Tree Editor Page and works on code snippet")
	public void user_redirected_to_the_tree_editor_page_and_works_on_code_snippet() throws IOException 
	{	    
	    DisplayEditorCode(strCurrPageName, objTreePage.sClickedTreeLinkName);
	}
	
	@When("Clicked on the Overview of Trees RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_overview_of_trees_run_button_and_verify_the_output_try_a_print_hello_statement_here() throws IOException 
	{
	    try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Tree Overview -> Run Button -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Tree Overview -> Run Button -> Tree Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Tree Overview -> Run Button -> Tree Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("Click on the Terminologies of Trees")
	public void click_on_the_terminologies_of_trees() throws IOException 
	{
	    try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
	    	objTreePage.ClickTreeTerms();
	    	
	    	Assert.assertEquals("User clicked the Terminologies of Trees -> Tree Page !",  objConstants.Tree_Terminology_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Terminologies of Trees -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Terminologies of Trees -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to Tree Terminologies and clicks the TryHere")
	public void user_redirected_to_tree_terminologies_and_clicks_the_try_here() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objTreePage.ClickTryHere();
		
			Assert.assertEquals("User clicked the Terminologies of Trees -> Try Here -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Terminologies of Trees -> Try Here -> Tree Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Terminologies of Trees -> Try Here -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@When("Clicked on the Terminologies of Trees RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_terminologies_of_trees_run_button_and_verify_the_output_try_a_print_hello_statement_here() throws IOException 
	{
	    try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Tree Terminologies -> Run Button -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Tree Terminologies -> Run Button -> Tree Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Tree Terminologies -> Run Button -> Tree Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("Click on the Types of Trees")
	public void click_on_the_types_of_trees() throws IOException 
	{
	    try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
	    	objTreePage.ClickTreeTypes();
	    	
	    	Assert.assertEquals("User clicked the Types of Trees -> Tree Page !",  objConstants.Tree_Types_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Types of Trees -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Types of Trees -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to Tree Types Page and clicks the TryHere")
	public void user_redirected_to_tree_types_page_and_clicks_the_try_here() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objTreePage.ClickTryHere();
			
			Assert.assertEquals("User clicked the Types of Trees -> Try here -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Types of Trees -> Try here -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Types of Trees -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("Clicked on the Types of Trees RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_types_of_trees_run_button_and_verify_the_output_try_a_print_hello_statement_here() throws IOException 
	{
	    try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Types of Tree -> Run Button -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Types of Tree -> Run Button -> Tree Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Types of Tree -> Run Button -> Tree Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("Click on the Tree Traversals")
	public void click_on_the_tree_traversals() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
	    	objTreePage.ClickTreeTraverse();
	    	
	    	Assert.assertEquals("User clicked the Tree Traversal -> Tree Page !",  objConstants.Tree_Traverse_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Tree Traversal -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Tree Traversal -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to Tree Traversals Page and clicks the TryHere")
	public void user_redirected_to_tree_traversals_page_and_clicks_the_try_here() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objTreePage.ClickTryHere();
			
			Assert.assertEquals("User clicked the Tree Traversal -> Try Here -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Tree Traversal -> Try Here -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Tree Traversal -> Try Here -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@When("Clicked on the Tree Traversals RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_tree_traversals_run_button_and_verify_the_output_try_a_print_hello_statement_here() throws IOException 
	{
	    try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Tree Traversals -> Run Button -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Tree Traversals -> Run Button -> Tree Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Tree Traversals -> Run Button -> Tree Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("Click on the Trees Traversal Illustration")
	public void click_on_the_trees_traversal_illustration() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
	    	objTreePage.ClickTraverseDemo();
	    	
	    	Assert.assertEquals("User clicked the Tree Traversal Illustration -> Tree Page !",  objConstants.Tree_Traverse_Illust_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Tree Traversal Illustration -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Tree Traversal Illustration -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to Tree Traversal Illustration Page and clicks the TryHere")
	public void user_redirected_to_tree_traversal_illustration_page_and_clicks_the_try_here() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objTreePage.ClickTryHere();
			
			Assert.assertEquals("User clicked the Tree Traversal Illustration -> Try Here -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Tree Traversal Illustration -> Tree Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Tree Traversal Illustration -> Try Here -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("Clicked on the Trees Traversal Illustration RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_trees_traversal_illustration_run_button_and_verify_the_output_try_a_print_hello_statement_here() throws IOException 
	{
	    try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Tree Traversals Illustration -> Run Button -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Tree Traversals Illustration -> Run Button -> Tree Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Tree Traversals Illustration -> Run Button -> Tree Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@Then("Click on the Binary Trees")
	public void click_on_the_binary_trees() throws IOException 
	{
	    try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
	    	objTreePage.ClickBinaryTree();
	    	
	    	Assert.assertEquals("User clicked the Binary Trees -> Tree Page !",  objConstants.Tree_Binary_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Binary Trees -> Tree Page !");
		}
		catch(Exception e)
		{		
			strErrMsgToLog = "User unable to click the Binary Trees -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to Binary Tree Page and clicks the TryHere")
	public void user_redirected_to_binary_tree_page_and_clicks_the_try_here() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objTreePage.ClickTryHere();
			
			Assert.assertEquals("User clicked the Binary Trees -> Try Here -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Binary Trees -> Try Here -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Binary Trees -> Try Here -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("Clicked on the Binary Trees RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_binary_trees_run_button_and_verify_the_output_try_a_print_hello_statement_here() throws IOException 
	{
	    try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Binary Tree -> Run Button -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Binary Tree -> Run Button -> Tree Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Binary Tree -> Run Button -> Tree Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("Click on the Types of Binary Trees")
	public void click_on_the_types_of_binary_trees() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objTreePage.ClickBinaryTreeTypes();
			
			Assert.assertEquals("User clicked the Types of Binary Trees -> Tree Page !",  objConstants.Tree_Binary_Types_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Types of Binary Trees -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Types of Binary Trees -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to Binary Tree Types Page and clicks the TryHere")
	public void user_redirected_to_binary_tree_types_page_and_clicks_the_try_here() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objTreePage.ClickTryHere();
			
			Assert.assertEquals("User clicked the Types of Binary Trees -> Try Here -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Types of Binary Trees -> Try Here -> Tree Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Types of Binary Trees -> Try Here -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("Clicked on the Types of Binary Trees RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_types_of_binary_trees_run_button_and_verify_the_output_try_a_print_hello_statement_here() throws IOException 
	{
	    try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Types of Binary Tree -> Run Button -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Types of Binary Tree -> Run Button -> Tree Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Types of Binary Tree -> Run Button -> Tree Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@Then("Click on the Implementation of Trees in Python")
	public void click_on_the_implementation_of_trees() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
	    	objTreePage.ClickTreeImplPython();
	    	
	    	Assert.assertEquals("User clicked the Implementation of Trees in Python -> Tree Page !",  objConstants.Tree_Impl_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Implementation of Trees in Python -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Implementation of Trees in Python -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to Tree Python Impl Page and clicks the TryHere")
	public void user_redirected_to_tree_Python_impl_page_and_clicks_the_try_here() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objTreePage.ClickTryHere();
			
			Assert.assertEquals("User clicked the Implementation of Trees using Python -> Try Here -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Implementation of Trees using Python -> Try Here -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Implementation of Trees using Python -> Try Here -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("Clicked on the Implementation of Trees in Python RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_implementation_of_trees_in_Python_run_button_and_verify_the_output_try_a_print_hello_statement_here() throws IOException 
	{
	    try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Implementation of Tree in Python -> Run Button -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Implementation of Tree in Python -> Run Button -> Tree Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Implementation of Tree in Python -> Run Button -> Tree Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("Click on the Binary Trees Traversal")
	public void click_on_the_binary_trees_traversal() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
	    	objTreePage.ClickBinTreeTraverse();
	    	
	    	Assert.assertEquals("User clicked the Binary Tree Traversal -> Tree Page !",  objConstants.Tree_BinTree_Traverse_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Binary Tree Traversal -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Binary Tree Traversal -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to Binary Tree Traversal Page and clicks the TryHere")
	public void user_redirected_to_binary_tree_traversal_page_and_clicks_the_try_here() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objTreePage.ClickTryHere();
			
			Assert.assertEquals("User clicked the Binary Tree Traversal -> Try Here -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Binary Tree Traversal -> Try Here -> Tree Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Binary Tree Traversal -> Try Here -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("Clicked on the Binary Trees Traversal RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_binary_trees_traversal_run_button_and_verify_the_output_try_a_print_hello_statement_here() throws IOException 
	{
	    try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Binary Tree Traversal -> Run Button -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Binary Tree Traversal -> Run Button -> Tree Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Binary Tree Traversal -> Run Button -> Tree Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("Click on the Impl of Binary Trees")
	public void click_on_the_impl_of_binary_trees() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
	    	objTreePage.ClickBinTreeImpl();
	    	
	    	Assert.assertEquals("User clicked the Binary Tree Implementation -> Tree Page !",  objConstants.Tree_BinTree_Impl_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Binary Tree Implementation -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Binary Tree Implementation -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to Impl of Binary Trees Page and clicks the TryHere")
	public void user_redirected_to_impl_of_binary_trees_page_and_clicks_the_try_here() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objTreePage.ClickTryHere();
			
			Assert.assertEquals("User clicked the Binary Tree Implementation -> Try Here -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Binary Tree Implementation -> Try Here -> Tree Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Binary Tree Implementation -> Try Here -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("Clicked on the Impl of Binary Trees RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_impl_of_binary_trees_run_button_and_verify_the_output_try_a_print_hello_statement_here() throws IOException 
	{
	    try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Implementation of Binary Tree -> Run Button -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Implementation of Binary Tree -> Run Button -> Tree Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Implementation of Binary Tree -> Run Button -> Tree Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@Then("Click on the Appl of Binary Trees")
	public void click_on_the_appl_of_binary_trees() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
	    	objTreePage.ClickBinTreeApplications();
	    	
	    	Assert.assertEquals("User clicked the Binary Tree Application -> Tree Page !",  objConstants.Tree_BinTree_Appn_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Binary Tree Application -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Binary Tree Application -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to  Appl of Binary Trees Page and clicks the TryHere")
	public void user_redirected_to_appl_of_binary_trees_page_and_clicks_the_try_here() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objTreePage.ClickTryHere();
			
			Assert.assertEquals("User clicked the Application of Binary Tree -> Try Here -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Application of Binary Tree -> Try Here -> Tree Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Application of Binary Tree -> Try Here -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("Clicked on the Appl of Binary Trees RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_appl_of_binary_trees_run_button_and_verify_the_output_try_a_print_hello_statement_here() throws IOException 
	{
	    try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Application of Binary Tree -> Run Button -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Application of Binary Tree -> Run Button -> Tree Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Application of Binary Tree -> Run Button -> Tree Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@Then("Click on the Binary Search Trees")
	public void click_on_the_binary_search_trees() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
	    	objTreePage.ClickBinTreeSearch();
	    	
	    	Assert.assertEquals("User clicked the Binary Tree Search -> Tree Page !",  objConstants.Tree_BinSearch_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Binary Tree Search -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Binary Tree Search -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to Binary Search Tree Page and clicks the TryHere")
	public void user_redirected_to_binary_search_tree_page_and_clicks_the_try_here() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
			objTreePage.ClickTryHere();
			
			Assert.assertEquals("User clicked the Binary Tree Search -> Try Here -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Binary Tree Search -> Try Here -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Binary Tree Search -> Try Here -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@When("Clicked on the Binary Search Trees RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_binary_search_trees_run_button_and_verify_the_output_try_a_print_hello_statement_here() throws IOException 
	{
	    try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Binary Search Tree -> Run Button -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Binary Search Tree -> Run Button -> Tree Page !");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Binary Search Tree -> Run Button -> Tree Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("Click on the Impl of BinSrch Tree")
	public void click_on_the_impl_of_bin_srch_tree() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
	
		    objTreePage.ClickImplofBST();
		    
		    Assert.assertEquals("User clicked the Binary Tree Search -> Tree Page !",  objConstants.Tree_BST_Impl_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Binary Tree Search -> Tree Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Binary Tree Search -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User redirected to Impl of BinSrch Tree Page and clicks the TryHere")
	public void user_redirected_to_impl_of_bin_srch_tree_page_and_clicks_the_try_here() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
	
			objTreePage.ClickTryHere();
			
			Assert.assertEquals("User clicked the Binary Tree Search -> Try Here -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Binary Tree Search -> Try Here -> Tree Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Binary Tree Search -> Try Here -> Tree Page !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@When("Clicked on the Impl of BinSrch Tree RunButton and verify the output #Try a PrintHello statement here.")
	public void clicked_on_the_Impl_of_BinSrch_Tree_Runbutton_and_verify_the_output_try_a_print_hello_statement_here() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the RUN button in Impl of Binary Tree Search -> Run Button -> Tree Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the RUN button in Impl of Binary Tree Search -> Run Button -> Tree Page");
			
			localDriver.navigate().back();
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the RUN button in Impl of Binary Tree Search -> Run Button -> Tree Page";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@Then("Click on the Tree PracticeQuestion")
	public void click_on_the_tree_practice_question() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
	
			objTreePage.ClickTreePracticeQn();
			Thread.sleep(2000);
			
			Assert.assertEquals("User clicked the Tree Page -> Practice Question !",  objConstants.Tree_PractQuest_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Tree Page -> Practice Question !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Tree Page -> Practice Question !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	// RAVALI Started Graph Page Here.
	@When("User clicks the Getstarted button of Graph") // get started button under graph
	public void user_clicks_the_getstarted_button_of_graph() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
		
			objHomePage.Graph_Btn_Click();

			strCurrPageName = objBaseClass.getCurrentURL(localDriver);
		
		    Assert.assertEquals("User clicks on Getstarted button -> Graph Page !",  objConstants.Graph_Page_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to click the Getstarted  button -> Graph Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to click the Getstarted  button -> Graph Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);			
		}
	}
	
	@Then("User is directed to Graph Page") //actual graph page
	public void user_is_directed_to_graph_page() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
			
			objGraphPage = new GraphPage(localDriver);
			Thread.sleep(2000);

			// localDriver.get(objConstants.Graph_Page_URL);
			Assert.assertEquals("User is on Graph -> Graph Page !",  objConstants.Graph_Page_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to land on Graph -> Graph Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to land on Graph -> Graph Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);			
		}
	}
		
	@When("User clicks on the Graph link") //scroll down under actual graph page n click on graph link
	public void user_clicks_on_the_graph_link() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
			
			objGraphPage.ClickGraphlink();
			Thread.sleep(2000);
			
			Assert.assertEquals("User clicks on Graph link -> GraphIntro Page !",  objConstants.Graph_Intro_Page_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to land on Graph -> Graph Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to land on Graph -> Graph Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);			
		}
	}
		
	@Then("User is directed to GraphIntro Page") // graph intro page
	public void user_is_directed_to_graph_intro_page() throws InterruptedException, IOException
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
			
			objGraphPage.ClickGraphlink();
			Thread.sleep(2000);
			
			Assert.assertEquals("User is directed to GraphIntro Page !",  objConstants.Graph_Intro_Page_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to land on GraphIntro Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to land on GraphIntro Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);			
		}
	}		
	
	@When("User clicks on GraphTry here button") //scroll down on graph intro page and click try here. try editor appears.write code. click run
	public void user_clicks_on_Graphtry_here_button() throws IOException
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
				
			objGraphPage.Clicktryherebtn();	
			// DisplayEditorCode(strCurrPageName);
			
			Assert.assertEquals("User clicks on Tryhere button -> Graph Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to click Try Here button -> Graph Page!");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to click Try Here button -> Graph Page!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);			
		}
	}
		
	@Then("User is directed to GraphtryEditor Page")
	public void user_is_directed_to_Graphtry_editor_page() throws IOException 
	{	
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
			
			objEditorPage = new TryEditorPage(localDriver);
			String sCodeSnippet;
		
			sCodeSnippet ="print(" + "\"INTRO TO Graph" + "\");";
			objEditorPage.TryThisCode(sCodeSnippet);	
		    
			Assert.assertEquals("User is directed to Try Editor Page -> Graph Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to land on TryEditor page -> Graph Page!");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to land on TryEditor page -> Graph Page!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);			
		}
	}
	
	@Then("User clicks on GraphRun button")
	public void user_clicks_on_Graphrun_button() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
		    objEditorPage.RunEditorCode();	
		    
		    Assert.assertEquals("User clicks on Run button -> Graph Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to click on Run button of TryEditor page -> Graph Page!");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to click on Run button of TryEditor page -> Graph Page!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);			
		}
	}		
		
	@Then("Navigates back to GraphIntro page") //navigate back to graph intro page
	public void navigates_back_to_graph_intro_page() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
				
			localDriver.navigate().back();
			Thread.sleep(1000);
			
			Assert.assertEquals("User navigates back to GraphIntro Page !",  objConstants.Graph_Intro_Page_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to navigate back to GraphIntro Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to navigate back to GraphIntro Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);			
		}
	}
	
	@When("User clicks on Graph Representations link") //click on graph representations link
	public void user_clicks_on_graph_representations_link() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
				
			objGraphPage.ClickGraphRepresentationslink();
			Thread.sleep(2000);
			
			Assert.assertEquals("User clicks on Graph Representations link -> Graph Page !",  objConstants.Graph_Repr_Link_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to click on Graph Representations link -> Graph Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to click on Graph Representations link -> Graph Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);			
		}
	}
		
	@When("User clicks on Try here button") //scroll down on graph intro page and click try here. try editor appears.write code. click run
	public void user_clicks_on_try_here_button() throws IOException
	{		

		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
				
			objGraphPage.Clicktryherebtn();	
			// DisplayEditorCode(strCurrPageName);
			
			Assert.assertEquals("User clicks on Tryhere button -> Graph Representations Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to click on Tryhere button -> Graph Representations Page!");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to click on Tryhere button -> Graph Representations Page!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);			
		}			
	}
	
	@Then("User is directed to tryEditor Page")
	public void user_is_directed_to_try_editor_page() throws IOException 
	{	
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
			
			objEditorPage = new TryEditorPage(localDriver);
			String sCodeSnippet;
			
			sCodeSnippet ="print(" + "\"INTRO TO Graph" + "\");";
			objEditorPage.TryThisCode(sCodeSnippet);	
			
			Assert.assertEquals("User is  directed to Try Editor Page!",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to land on TryEditor page!");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to land on TryEditor page!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);			
		}
	}
	
	@Then("User clicks on Run button")
	public void user_clicks_on_run_button() throws IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
			
		    objEditorPage.RunEditorCode();	
		  
		    Assert.assertEquals("User clicks on Run button!",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to click on Run button of TryEditor page");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to click on Run button of TryEditor page";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		
		}
	}

	@Then("Navigates back to Graph Representations page") //navigates back to graph repr page
	public void navigates_back_to_graph_repr_page() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
			
			localDriver.navigate().back();
	
			Thread.sleep(1000);
			Assert.assertEquals("User navigates back to Graph Representations Page !",  objConstants.Graph_Repr_Link_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to land on Graph Representations Page!");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to land on Graph Representations Page!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		
		}
	}
	
	@When("User clicks on GraphPractice Questions link")
	public void user_clicks_on_Graphpractice_questions_link() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
			
			objGraphPage.ClickPQlnk();
			Thread.sleep(2000);
			
			Assert.assertEquals("User clicks on GraphPractice Questions link !",  objConstants.Graph_PracticeQuest_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to click on GraphPractice Questions link !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to click on GraphPractice Questions link !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		
		}
	}
	
	@Then("User is directed to GraphPractice Questions page")
	public void user_is_directed_to_Graphpractice_questions_page() throws IOException 
	{	
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);	
			
			Assert.assertEquals("User is directed to GraphPractice Questions page !",  objConstants.Graph_PracticeQuest_URL, localDriver.getCurrentUrl());			
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is able to land on GraphPractice Questions page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User is unable to land on GraphPractice Questions page!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		
		}
	}
	
	//Array Page starts here.	
	@When("User clicks Get Started button under Array Column")
	public void user_clicks_get_started_button_under_array_column() throws InterruptedException, IOException  
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			
			objHomePage.Array_Btn_Click();

			Assert.assertEquals("User clicked on the Get Started Button under Array Column in Home Page !",  objConstants.Array_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked on the Get Started Button under Array Column in Home Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "Unable to click the Array Button !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	/*
	@Then("User is directed to Array page")
	public void user_is_directed_to_array_page() 
	{
		objArrayPage = new ArrayPage(localDriver);

		String Title = objArrayPage.getArrayPageTitle();
		
		assertEquals(Title, "Array", "Title do not match");
		// System.out.println("Array page creating "+ localDriver.getCurrentUrl());
	}
	*/
		
	@When("User clicks on Arrays in Python link")
	public void user_clicks_on_arrays_in_python_link() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName(); // Aug 7th objBaseClass = new BaseClassUtils();	localDriver = objBaseClass.GetDriver();
			objArrayPage = new ArrayPage(localDriver);			// Aug 7th System.out.println("Array obj created"); 			
			Thread.sleep(1000);
	
			objArrayPage.clickArrayInPython();	
			
			Assert.assertEquals("User clicked Arrays in Python link - Array Page !",  objConstants.Arrays_in_Python_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Arrays in Python link - Array Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Arrays in Python link - Array Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
    }

	/*	
	@Then("User is directed to arrays-in-python page")
	public void user_is_directed_to_arrays_in_python_page() throws InterruptedException 
	{
		String Title = objArrayPage.getArrayPageTitle();
		assertEquals( Title, "Arrays in Python", "Title do not match");
		Thread.sleep(1000);
		localDriver.navigate().back();
		Thread.sleep(1000);
		objArrayPage = new ArrayPage(localDriver);
	}
	*/

	@When("User clicks on Try Here button on Arrays in Python page")
	public void user_clicks_on_try_here_button_on_arrays_in_python_page() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			strCurrPageName = objBaseClass.getCurrentURL(localDriver);
			Thread.sleep(1000);
			
			objArrayPage.clickTryHereArray();
			
			Assert.assertEquals("User is directed to the editor Page -> Arrays in Python Page !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is directed to the editor Page -> Arrays in Python Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click the Try Here Button -> Arrays in Python Page! ";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
		
	@And("User navigates back to Array Page")
	public void user_navigates_back_to_array_page() throws InterruptedException, IOException 
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			localDriver.navigate().to(objConstants.Array_Page_URL);
			Thread.sleep(1000);
			//localDriver.get(objConstants.HomePage_URL);
			
			Assert.assertEquals("User landed on the Arrays page from " + strCurrPageName + " Page !",  objConstants.Array_Page_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User landed on the Arrays page from " + strCurrPageName + " Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User failed to land on the Arrays Page from " + strCurrPageName + " Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
		
	@When("User clicks on Arrays Using List link")
	public void user_clicks_on_arrays_using_list_link() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();// Aug 7th objBaseClass = new BaseClassUtils();	localDriver = objBaseClass.GetDriver();
			
			objArrayPage = new ArrayPage(localDriver);
			Thread.sleep(1000);
			
			objArrayPage.clickArrayUsingList();
			
			Assert.assertEquals("User clicked Arrays Using List link - Array Page !",  objConstants.Arrays_Using_List_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Arrays Using List link - Array Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click the Arrays Using List link - Array Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	/*	
	@Then("User is directed to arrays-using-list page")
	public void user_is_directed_to_arrays_using_list_page() throws InterruptedException 
	{
		String Title = objArrayPage.getArrayPageTitle();
		LoggerLoad.SuccessMsg("Title of current page is : " + Title);
		assertEquals( Title, "Arrays Using List","Title do not match");
		Thread.sleep(1000);
		localDriver.navigate().back();
		Thread.sleep(1000);
		objArrayPage = new ArrayPage(localDriver);
	} 
	*/

	@When("User clicks on Try Here button on Arrays Using List page")
	public void user_clicks_on_try_here_button_on_arrays_using_list_page() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			strCurrPageName = objBaseClass.getCurrentURL(localDriver);
			Thread.sleep(1000);

			objArrayPage.clickTryHereArray();
			
			Assert.assertEquals("User is directed to the editor Page (Array) !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is directed to the editor Page (Array) !");
		}

		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click the editor Page! ";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
		
	@When("User clicks on Basic Operations in Lists link")
	public void user_clicks_on_basic_operations_in_lists_link() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
			objArrayPage.clickBasicOperationsInLists();
			Assert.assertEquals("User clicked Basic Operations in Lists link - Array Page !",  objConstants.Arrays_Operations_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Basic Operations in Lists link - Array Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click the Basic Operations in Lists link - Array Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@Then("User is directed to basic-operations-in-lists page")
	public void user_is_directed_to_basic_operations_in_lists_page() throws InterruptedException 
	{
		String Title = objArrayPage.getArrayPageTitle();
		LoggerLoad.SuccessMsg("Title of current page is : " + Title);
		assertEquals(Title, "Basic Operations in Lists", "Title do not match");
		Thread.sleep(1000);
	
		localDriver.navigate().back();
		Thread.sleep(1000);
		
		objArrayPage = new ArrayPage(localDriver);
	}
		
	@When("User clicks on Try Here button on Basic Operations in Lists page")
	public void user_clicks_on_try_here_button_on_basic_operations_in_lists_page() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			strCurrPageName = objBaseClass.getCurrentURL(localDriver);
			Thread.sleep(1000);
		
			objArrayPage.clickTryHereArray();
			
			Assert.assertEquals("User is directed to the editor Page (Array) !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is directed to the editor Page (Array) !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click the editor Page! ";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("User clicks on Applications of Array link")
	public void user_clicks_on_applications_of_array_link() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
			
			objArrayPage = new ArrayPage(localDriver);		// delete this.
			objArrayPage.clickApplicationOfArray();
			
			Assert.assertEquals("User clicked Applications of Array link - Array Page !",  objConstants.Arrays_Applications_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Applications of Array link - Array Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click the Applications of Array link - Array Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}		
	}

	@Then("User is directed to applications-of-array page")
	public void user_is_directed_to_applications_of_array_page() throws InterruptedException 
	{
		String Title = objArrayPage.getArrayPageTitle();
		LoggerLoad.SuccessMsg("Title of current page is : " + Title);

		assertEquals( Title, "Applications of Array","Title do not match");
		Thread.sleep(1000);

		Thread.sleep(1000);
		objArrayPage = new ArrayPage(localDriver);
	}
		
	@When("User clicks on Try Here button on Applications of Array page")
	public void user_clicks_on_try_here_button_on_applications_of_array_page() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			strCurrPageName = objBaseClass.getCurrentURL(localDriver);
			Thread.sleep(1000);
			objArrayPage.clickTryHereArray();
			
			Assert.assertEquals("User is directed to the editor Page (Array) !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is directed to the editor Page (Array) !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click the editor Page! ";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	/*
	@Then("User is redirected to tryEditor page of DS-Algo portal")
	public void user_is_redirected_to_try_editor_page_of_ds_algo_portal() 
	{
		String Title = objArrayPage.getArrayPageTitle();
		LoggerLoad.SuccessMsg("Title of current page is : " + Title);
		System.out.println(Title);
		assertEquals(Title, "Assessment", "Title do not match" );
	}
	*/

	@When("User inputs a python code on tryEditor page of Array")
	public void user_inputs_a_python_code_on_try_editor_page_of_Array() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			DisplayEditorCode(strCurrPageName, objArrayPage.sClickedArrayLinkName);
			Thread.sleep(1000);

			Assert.assertEquals("User is trying a code snippet in the editor Page of Array !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is trying a code snippet in the editor Page of Array !");
		}

		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click the editor Page! ";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
		
	/*
	@When("User clicks on Run button")
	public void user_clicks_on_run_button() throws InterruptedException , IOException
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			Assert.assertEquals("User clicked the RUN button in Try Editor",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the RUN button in Try Editor");
			//localDriver.navigate().back();
		}	
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the RUN button in Try Editor";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	*/
		
	@When("User navigates back")
	public void user_navigates_back() throws InterruptedException , IOException
	{
		strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		try
		{
			localDriver.navigate().back();
			Thread.sleep(1000);	
			
			localDriver.get(objConstants.Arrays_Applications_URL);
			
			Assert.assertEquals("User landed on the Applications of Array Page from " + strCurrPageName + " Page !",  objConstants.Arrays_Applications_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User landed on the Applications of Array Page from " + strCurrPageName + " Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User failed to land on the Applications of Array Page from " + strCurrPageName + " Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}

	}
		
	@When("User clicks on Practice Question link")
	public void user_clicks_on_practice_question_link() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
			
			objArrayPage.clickArrayPracticeQues();
			
			Assert.assertEquals("User clicked Practice Question link - Array Page !",  objConstants.Arrays_Practice_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Practice Question link - Array Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click the Practice Question link - Array Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	/*
	@Then("User is directed to practice page of Array")
	public void user_is_directed_to_practice_page_of_array() throws InterruptedException 
	{
		String Title = objArrayPage.getArrayPageTitle();
		LoggerLoad.SuccessMsg("Title of current page is : " + Title);
		assertEquals(Title , "Practice Questions","Title do not match");
		Thread.sleep(1000);
		//localDriver.navigate().back();
		Thread.sleep(1000);
		objArrayPage = new ArrayPage(localDriver);
	}
	*/

	@When("the user clicks on Search the array link")
	public void the_user_clicks_on_search_the_array_link() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
	
			objBaseClass = new BaseClassUtils();
			localDriver = objBaseClass.GetDriver();
			objArrayPage.clickArraySearch();
			
			Assert.assertEquals("User clicked Search the array link - Array Page !",  objConstants.Arrays_Search_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Search the array link - Array Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click the Search the array link - Array Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	/*
	@Then("User is redirected to question1 page")
	public void user_is_redirected_to_question1_page() throws InterruptedException , IOException
	{
		String Title = objArrayPage.getArrayPageTitle();
		LoggerLoad.SuccessMsg("Title of current page is : " + Title);
		assertEquals(Title , "Assessment","Title do not match");
		Thread.sleep(1000);
		objArrayPage = new ArrayPage(localDriver);
	}
	*/
		
	@When("User inputs a python code on tryEditor page for Practice Question1")
	public void user_inputs_a_python_code_on_try_editor_page_for_Practice_Question1() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			strCurrPageName = objBaseClass.getCurrentURL(localDriver);
	
			DisplayEditorCode(strCurrPageName, "");
			
			Assert.assertEquals("User is trying a code snippet in the editor Page from (question1 page) Array !",  objConstants.Arrays_Search_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is trying a code snippet in the editor Page from (question1 page) Array !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to try a code snippet in (question1 page) Array !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
		
	@When("User clicks on Run button for Practice Question1")
	public void user_clicks_on_run_button_for_practice_question1() throws InterruptedException , IOException
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
	
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the RUN button in (Question1) Array !",  objConstants.Arrays_Search_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the RUN button in (Question1) Array !");
			//localDriver.navigate().back();
		}	
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the RUN button in (Try Editor) Array !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("User clicks on Submit button for Practice Question1")
	public void user_clicks_on_submit_button_for_practice_question1() throws InterruptedException , IOException
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			objArrayPage.clickSubmitButton();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Submit button in editor Page from (question1 page) Array !",  objConstants.Arrays_Search_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Submit button in editor Page from (question1 page) Array !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Submit button in (Try Editor)  Array !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
		Thread.sleep(3000);
	}

	@Then("User receives a message Submitted successfully")
	public void user_receives_a_message_submitted_successfully() throws InterruptedException 
	{
		Thread.sleep(1000);
		String actualMsg = objArrayPage.getOutput();

		assertEquals(actualMsg, "Error occurred during submission" , "Result do not match"); //"Submitted Successfully"
	}

	@Then("User receives a message Submitted the code successfully")
	public void user_receives_a_message_submitted_the_code_successfully() throws InterruptedException 
	{
		Thread.sleep(1000);
		String actualMsg = objArrayPage.getOutput();

		assertEquals(actualMsg, " No tests were collected" , "Result do not match"); //"Submitted Successfully"
	}

	@Then("User navigates back to Array Practice page")
	public void user_navigates_back_to_array_practice_page() 
	{
		localDriver.navigate().to(objConstants.Arrays_Practice_URL);
	}

	@When("the user clicks on Max Consecutive Ones link")
	public void the_user_clicks_on_max_consecutive_ones_link() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
	
			objBaseClass = new BaseClassUtils();
			localDriver = objBaseClass.GetDriver();
			
			objArrayPage.clickMaxConsecutiveOnes();
			
			Assert.assertEquals("User clicked Max Consecutive Ones link - Array Page !",  objConstants.Array_Consecutive_Ones_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Max Consecutive Ones link - Array Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click the Max Consecutive Ones link - Array Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@When("User inputs a python code on tryEditor page for Practice Question2")
	public void user_inputs_a_python_code_on_try_editor_page_for_Practice_Question2() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			strCurrPageName = objBaseClass.getCurrentURL(localDriver);
			
			DisplayEditorCode(strCurrPageName, objArrayPage.sClickedArrayLinkName);
			
			Assert.assertEquals("User is trying a code snippet in the editor Page from (question2 page) Array !",  objConstants.Array_Consecutive_Ones_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is trying a code snippet in the editor Page from (question2 page) Array !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to try a code snippet in (question2 page) Array !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("User clicks on Run button for Practice Question2")
	public void user_clicks_on_run_button_for_practice_question2() throws InterruptedException , IOException
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the RUN button in (Question2) Array !",  objConstants.Array_Consecutive_Ones_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the RUN button in (Question2) Array !");
		}	
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the RUN button in (Try Editor) Array !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
		
	@When("User clicks on Submit button for Practice Question2")
	public void user_clicks_on_submit_button_for_practice_question2() throws InterruptedException , IOException
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			objArrayPage.clickSubmitButton();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Submit button in editor Page from (question2 page) Array !",  objConstants.Array_Consecutive_Ones_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Submit button in editor Page from (question1 page) Array !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Submit button in (Try Editor) Array !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
		Thread.sleep(3000);
	}
		
	@When("the user clicks on Find Numbers with Even Number of Digits link")
	public void the_user_clicks_on_find_numbers_with_even_number_of_digits_link() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
	
			objBaseClass = new BaseClassUtils();
			localDriver = objBaseClass.GetDriver();
			objArrayPage.clickEvenNumberOfDigits();
			
			Assert.assertEquals("User clicked Find Numbers with Even Number of Digits link - Array Page !",  objConstants.Array_Even_Digits_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Find Numbers with Even Number of Digits link - Array Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click the Find Numbers with Even Number of Digits link - Array Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("User inputs a python code on tryEditor page for Practice Question3")
	public void user_inputs_a_python_code_on_try_editor_page_for_Practice_Question3() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			strCurrPageName = objBaseClass.getCurrentURL(localDriver);
	
			DisplayEditorCode(strCurrPageName, objArrayPage.sClickedArrayLinkName);
			
			Assert.assertEquals("User is trying a code snippet in the editor Page from (question3 page) Array !",  objConstants.Array_Even_Digits_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is trying a code snippet in the editor Page from (question3 page) Array !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to try a code snippet in (question3 page) Array !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
		
	@When("User clicks on Run button for Practice Question3")
	public void user_clicks_on_run_button_for_practice_question3() throws InterruptedException , IOException
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the RUN button in (Question3) Array !",  objConstants.Array_Even_Digits_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the RUN button in (Question3) Array !");
		}	
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the RUN button in (Try Editor) Array !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
		
	@When("User clicks on Submit button for Practice Question3")
	public void user_clicks_on_submit_button_for_practice_question3() throws InterruptedException , IOException
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			objArrayPage.clickSubmitButton();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Submit button in editor Page from (question3 page) Array !",  objConstants.Array_Even_Digits_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Submit button in editor Page from (question3 page) Array !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Submit button in (Try Editor) Array !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
		Thread.sleep(3000);
	}

	@When("the user clicks on Squares of a Sorted Array link")
	public void the_user_clicks_on_squares_of_a_sorted_array_link() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
			
			objArrayPage.clickSquaresOfArray();
			
			Assert.assertEquals("User clicked Squares of a Sorted Array link - Array Page !",  objConstants.Array_Squares_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Squares of a Sorted Array link - Array Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click the Squares of a Sorted Array link - Array Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	/*
	@Then("User is redirected to question4 page")
	public void user_is_redirected_to_question4_page() throws InterruptedException 
	{
		String Title = objArrayPage.getArrayPageTitle();
		System.out.println("sorted array");

		LoggerLoad.SuccessMsg("Title of current page is : " + Title);
		assertEquals(Title , "Assessment","Title do not match");
		Thread.sleep(1000);
		objArrayPage = new ArrayPage(localDriver);	
	}
	*/
		
	@When("User inputs a python code on tryEditor page for Practice Question4")
	public void user_inputs_a_python_code_on_try_editor_page_for_Practice_Question4() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			strCurrPageName = objBaseClass.getCurrentURL(localDriver);
			
			DisplayEditorCode(strCurrPageName, objArrayPage.sClickedArrayLinkName);
			
			Assert.assertEquals("User is trying a code snippet in the editor Page from (question4 page) Array !",  objConstants.Array_Squares_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is trying a code snippet in the editor Page from (question4 page) Array !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to try a code snippet in (question4 page) Array !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("User clicks on Run button for Practice Question4")
	public void user_clicks_on_run_button_for_practice_question4() throws InterruptedException , IOException
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
			
			objEditorPage.RunEditorCode();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the RUN button in (Question4) Array !",  objConstants.Array_Squares_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the RUN button in (Question4) Array !");
		}	
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the RUN button in (Try Editor) Array !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
		
	@When("User clicks on Submit button for Practice Question4")
	public void user_clicks_on_submit_button_for_practice_question4() throws InterruptedException , IOException
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			objArrayPage.clickSubmitButton();
			Thread.sleep(1000);
			
			Assert.assertEquals("User clicked the Submit button in editor Page from (question4 page) Array !",  objConstants.Array_Squares_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Submit button in editor Page from (question4 page) Array !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User unable to click the Submit button in (Try Editor) Array !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
		Thread.sleep(3000);
	}
	
	@Then("User navigates back to Home page")
	public void user_navigates_back_to_home_page() 
	{
		objConstants = new WebPageConstants();
		
		objHomePage = new HomePage(localDriver);
		localDriver.navigate().to(objConstants.HomePage_URL);
	}

	//Queue Page starts here.		
	@When("User clicks Get Started button under Queue Column")
	public void user_clicks_get_started_button_under_queue_column() throws InterruptedException , IOException
	{	
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			objBaseClass = new BaseClassUtils();
			localDriver = objBaseClass.GetDriver();
			objHomePage = new HomePage(localDriver);
			objHomePage.Queue_Btn_Click();
			
			Assert.assertEquals("User clicked on the Get Started Button under Queue Column in Home Page !",  objConstants.QPage_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked on the Get Started Button under Queue Column in Home Page !");
			
			objQueuePage = new QueuePage(localDriver);
		}
		catch(Exception e)
		{
			strErrMsgToLog = "Unable to click the Queue Button !!";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	/*	
 	@Then("User is directed to Queue page")
	public void user_is_directed_to_queue_page() 
	{
		objQueuePage = new QueuePage(localDriver);

		String Title = objQueuePage.getQueuePageTitle();
		System.out.println("Title of the current page is :" + Title);
		LoggerLoad.SuccessMsg("Title of current page is : " + Title);
		assertEquals(Title, "Queue", "Title do not match");
		System.out.println("Queue page creating "+ localDriver.getCurrentUrl());
	}
	*/
	
	@When("User clicks on Queue in Python link")
	public void user_clicks_on_queue_in_python_link() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
			
			objQueuePage.clickQueueInPython();
			
			Assert.assertEquals("User clicked Queue in Python link - Queue Page !",  objConstants.QList_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Queue in Python link - Queue Page !");
		}
		catch(Exception e)
		{	
			System.out.println(e.getMessage());
			strErrMsgToLog = "User is unable to click the Queue in Python link- Queue Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("User clicks on Try Here button on Queue in Python page")
	public void user_clicks_on_try_here_button_on_queue_in_python_page() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			strCurrPageName = objBaseClass.getCurrentURL(localDriver);
			Thread.sleep(1000);
			
			objQueuePage.clickTryHereQueue();
			
			Assert.assertEquals("User is directed to editor Page (Queue) !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is directed to editor Page (Queue) !");
		}

		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click editor Page (Queue) ! ";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@When("User inputs a python code on tryEditor page of Queue")
	public void user_inputs_a_python_code_on_try_editor_page_of_Queue() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			DisplayEditorCode(strCurrPageName, objQueuePage.sClickedQueueLinkName);
			Thread.sleep(1000);
			Assert.assertEquals("User is trying a code snippet in the editor Page of Queue !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is trying a code snippet in the editor Page of Queue !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to try a code snippet in the editor Page of Queue !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@And("User navigates back to Queue Page")
	public void user_navigates_back_to_queue_page() throws InterruptedException, IOException 
	{
		strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
		try
		{
			localDriver.navigate().to(objConstants.QPage_URL);
			Thread.sleep(1000);
			
			Assert.assertEquals("User landed on the Queue page from " + strCurrPageName + " Page !",  objConstants.QPage_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User landed on the Queue page from " + strCurrPageName + " Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User failed to land on the Queue Page from " + strCurrPageName + " Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
		
	/*	
 	@Then("User is directed to implementation-lists page")
	public void user_is_directed_to_implementation_lists_page() throws InterruptedException
	{
		String Title = objQueuePage.getQueuePageTitle();
		System.out.println("********************"+ Title);
		LoggerLoad.SuccessMsg("Title of current page is : " + Title);
		assertEquals( Title, "Implementation of Queue in Python", "Title do not match");
		Thread.sleep(1000);
		System.out.println("-----------------");
		localDriver.navigate().back();
		System.out.println("..................");

		Thread.sleep(1000);
		objQueuePage = new QueuePage(localDriver);
	}
	*/

	@When("User clicks on Implementation using collections deque link")
	public void user_clicks_on_implementation_using_collections_deque_link() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
			objBaseClass = new BaseClassUtils();
			localDriver = objBaseClass.GetDriver();
			objQueuePage.clickImplementationUsingCollection();
			Assert.assertEquals("User clicked Implementation using collections.deque link - Queue Page !",  objConstants.QColln_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Implementation using collections.deque link - Queue Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click the Implementation using collections.deque link - Queue Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	@When("User clicks on Try Here button on Implementation using collections deque page")
	public void user_clicks_on_try_here_button_on_implementation_using_collections_deque_page() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			strCurrPageName = objBaseClass.getCurrentURL(localDriver);
			Thread.sleep(1000);

			objQueuePage.clickTryHereQueue();
			
			Assert.assertEquals("User is directed to editor Page (Queue) !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is directed to editor Page (Queue) !");
		}

		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click editor Page (Queue) ! ";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
		
	@When("User clicks on Implementation using array link")
	public void user_clicks_on_implementation_using_array_link() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
			objBaseClass = new BaseClassUtils();
			localDriver = objBaseClass.GetDriver();
			objQueuePage.clickImplementationUsingArray();
			Assert.assertEquals("User clicked Implementation using array link - Queue Page !",  objConstants.QArray_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Implementation using array link - Queue Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click the Implementation using array link - Queue Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	/*	
 	@Then("User is directed to Implementation-array page")
	public void user_is_directed_to_implementation_array_page() throws InterruptedException 
	{
		String Title = objQueuePage.getQueuePageTitle();
		System.out.println("********************"+ Title);
		LoggerLoad.SuccessMsg("Title of current page is : " + Title);
		assertEquals( Title, "Implementation using array", "Title do not match");
		Thread.sleep(1000);
		localDriver.navigate().back();

		Thread.sleep(1000);
		objQueuePage = new QueuePage(localDriver);
	}
	*/
		
	@When("User clicks on Try Here button on Implementation using array page")
	public void user_clicks_on_try_here_button_on_implementation_using_array_pagee() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			strCurrPageName = objBaseClass.getCurrentURL(localDriver);
			Thread.sleep(1000);

			objQueuePage.clickTryHereQueue();
			
			Assert.assertEquals("User is directed to editor Page (Queue) !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is directed to editor Page (Queue) !");
		}

		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click editor Page (Queue) ! ";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
		
	@When("User clicks on Queue Operations link")
	public void user_clicks_on_queue_operations_link() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			Thread.sleep(1000);
			
			objQueuePage.clickQueueOperations();
			
			Assert.assertEquals("User clicked Queue Operations link - Queue Page !",  objConstants.QOp_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked Queue Operations link - Queue Page !");
		}
		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click the Queue Operations link - Queue Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}

	/*
	@Then("User is directed to QueueOp page")
	public void user_is_directed_to_queue_op_page() throws InterruptedException 
	{
		String Title = objQueuePage.getQueuePageTitle();
		System.out.println("********************"+ Title);
		LoggerLoad.SuccessMsg("Title of current page is : " + Title);
		assertEquals( Title, "Queue Operations", "Title do not match");
		Thread.sleep(1000);
		Thread.sleep(1000);
		objQueuePage = new QueuePage(localDriver);
	}
	*/
		
	@When("User clicks on Try Here button on Queue Operations page")
	public void user_clicks_on_try_here_button_on_queue_operations_pagee() throws InterruptedException , IOException
	{
		try
		{
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();
			strCurrPageName = objBaseClass.getCurrentURL(localDriver);
			Thread.sleep(1000);
			
			objQueuePage.clickTryHereQueue();
			
			Assert.assertEquals("User is directed to editor Page (Queue) !",  objConstants.Try_Editor_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User is directed to editor Page (Queue) !");
		}

		catch(Exception e)
		{	
			strErrMsgToLog = "User is unable to click editor Page (Queue) ! ";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@And("User is directed to QueueOp page")
	public void user_navigates_back_to_queueOp_page() throws InterruptedException, IOException 
	{
		strMethodName = new Throwable().getStackTrace()[0].getMethodName();
		
		try
		{
			localDriver.navigate().back();
			Thread.sleep(1000);
			
			Assert.assertEquals("User landed on the Queue page from " + strCurrPageName + " Page !",  objConstants.QOp_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User landed on the Queue page from " + strCurrPageName + " Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User failed to land on the Queue Page from " + strCurrPageName + " Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
	
	@Then("Click on the Queue PracticeQuestion")
	public void click_on_the_Queue_practice_question() throws InterruptedException, IOException 
	{
		try
		{	
			strMethodName = new Throwable().getStackTrace()[0].getMethodName();		
	
			objQueuePage.clickQueuePracticeQues();	
			Thread.sleep(2000);
			
			Assert.assertEquals("User clicked the Practice Question -> Queue Page !",  objConstants.QPracticeQues_URL, localDriver.getCurrentUrl());
			objBaseClass.LogSuccessMsg(sClassName, strMethodName, "User clicked the Practice Question -> Queue Page !");
		}
		catch(Exception e)
		{
			strErrMsgToLog = "User unable to click the Practice Question -> Queue Page !";
			objBaseClass.WriteErrorLogs(sClassName, strMethodName, strErrMsgToLog, localDriver, true);
		}
	}
}