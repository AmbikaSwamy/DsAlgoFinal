package Utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseClassUtils 
{
	public static WebDriver myDriver;
	
	public File XLFileName;
	public Workbook XLWorkBook;
	public Sheet shtWorkSheet;
	
	public String strBrowser;
	public String strValidUserNameStatus;

	public String Home_Page_URL;
	public String SignInPage_URL;
	public String RegisterUserPage_URL;
	public String sCurrURL;
	public String strMethodName;
	
	public String DSIntroPage_URL;
	
	public boolean bElementEnabled;
	public boolean bElementVisible;
	public boolean bElementSelected;
	
	public static WebPageConstants objPageConstants;
	
	public BaseClassUtils()
	{	
		objPageConstants = new WebPageConstants();
		
		strBrowser = objPageConstants.Browser;
		
		Home_Page_URL = objPageConstants.HomePage_URL;
		SignInPage_URL = objPageConstants.SignIn_Page_URL;
		RegisterUserPage_URL = objPageConstants.User_Regn_Page_URL;		
		DSIntroPage_URL = objPageConstants.DSIntro_Page_URL;
	}
	
	@SuppressWarnings("deprecation")
	public void LaunchBrowser() 
	{
		// System.setProperty("webdriver.http.factory", "jdk-http-client");
		// strBrowser = "chrome";
		
		/*
		 * System.setProperty("webdriver.chrome.silentOutput", "true");
		 * System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,
		 * "true"); System.setProperty("webdriver.chrome.whitelistedIps", "");
		 * java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF)
		 * ;
		 */
		
		try
		{
			ChromeOptions cOptions = new ChromeOptions();
			FirefoxOptions fOptions = new FirefoxOptions();
			EdgeOptions eOptions=new EdgeOptions();
			
			// options.addArguments("--headless");
			// WebDriver driver = new ChromeDriver(options);
			
			if(strBrowser.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				
				if(objPageConstants.HeadLessOption.equalsIgnoreCase("true"))
				{
					cOptions.addArguments("--headless");
					myDriver = new ChromeDriver(cOptions);
				}
				else
				{
					myDriver = new ChromeDriver();
				}
			}
			else if(strBrowser.equalsIgnoreCase("edge"))
			{
				WebDriverManager.edgedriver().setup();				
				if(objPageConstants.HeadLessOption.equalsIgnoreCase("true"))
				{
					eOptions.addArguments("--headless");
					myDriver = new EdgeDriver(eOptions);
				}
				else
				{
					myDriver = new EdgeDriver();
				}
			}
			else if(strBrowser.equalsIgnoreCase("ie"))
			{
				WebDriverManager.iedriver().setup();
				myDriver = new InternetExplorerDriver();
			}
			else if(strBrowser.equalsIgnoreCase("firefox"))
			{		
				WebDriverManager.firefoxdriver().setup();
				
				if(objPageConstants.HeadLessOption.equalsIgnoreCase("true"))
				{
					fOptions.addArguments("--headless");
					myDriver = new FirefoxDriver(fOptions);
				}
				else
				{
					myDriver = new FirefoxDriver();
				}
			}
			
			myDriver.manage().window().maximize();		
		}
		catch(Exception E)
		{
			LoggerLoad.FatalErrMsg("Cannot launch the browser !!");
		}
	}
	
	public WebDriver GetDriver()
	{
		return myDriver;
	}
	
	public void PageLoad(String sPageAddr, Long lWaitTime)
	{
		try
		{
			myDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(lWaitTime));
		}
		catch(Exception E)
		{
			LoggerLoad.ErrorMsg("Cannot load the page : " + sPageAddr);
		}
	}
	
	public void WriteErrorLogs(String sClassName, String sMethodName, String sMsgToLog, WebDriver rDriver, Boolean bGetScreenShotYN) throws IOException
	{
		PrintErrorMsgHeader();
		LogWarningMsg(sClassName, sMethodName, sMsgToLog);		
		PrintErrorMsgFooter();
		
		if(bGetScreenShotYN)
			Capture_ScreeShots(rDriver, "Failed", sMethodName);
	}
	
	public void Capture_ScreeShots(WebDriver rDriver, String sTestStatus, String sMethodName) throws IOException
	{
		try
		{
			Date currDate = new Date();		
			
			String strDateStamp = currDate.toString().replace(" ", "_").replace(":", "-");
			
			if(rDriver !=  null)
			{
				TakesScreenshot ScreenShot = ((TakesScreenshot) rDriver);
				File SourceFile = ScreenShot.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(SourceFile, new File(".//screenshot//" + sTestStatus + "_" + sMethodName + "_" + strDateStamp +  ".png"));				
			}
			else
			{				
				TakesScreenshot ScreenShot = ((TakesScreenshot) myDriver);
				File SourceFile = ScreenShot.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(SourceFile, new File(".//screenshot//" + sTestStatus + "_" + sMethodName + "_" + strDateStamp +  ".png"));				
			}
			
			/*
			System.out.println("Before SS: " + rDriver.getCurrentUrl());
			 TakesScreenshot ScreenShot = ((TakesScreenshot) rDriver);
			 System.out.println("After SS: " + rDriver.getCurrentUrl());
			 File SourceFile = ScreenShot.getScreenshotAs(OutputType.FILE);
			 
			 // File ssFile = ScreenShot.getScreenshotAs(OutputType.FILE);
			 
			 System.out.println("After File create: " + rDriver.getCurrentUrl());
			 FileUtils.copyFile(SourceFile, new File(".//screenshot//" + sTestStatus + "_" + sMethodName + "_" + strDateStamp +  ".png"));
			 System.out.println("After File save: " + rDriver.getCurrentUrl());
			*/
			 
			// Method 2 of capturing a screenshot.
			/*
			 * LoggerLoad.LogInfo(scenario.getName() + " is failed"); byte[] Screenshot =
			 * ((TakesScreenshot) lDriver).getScreenshotAs(OutputType.BYTES);
			 * scenario.attach(Screenshot, "image/png", scenario.getName() + " Failed");
			 * System.out.println("Screenshot from hooks success !");
			 */
		}
		catch(Exception E)
		{			
			if(rDriver == null)
				LoggerLoad.ErrorMsg("Driver is null to capture a ScreenShot !!");
			else	
				LoggerLoad.ErrorMsg("Invalid inputs to capture a ScreenShot !!");
		}
	}
	
	public void LogErrMsg(String sClassName, String sMethodName, String sErrMsg)
	{
		if(sMethodName == "" || sMethodName == null)
			LoggerLoad.ErrorMsg("Class Name: " + sClassName + " -> Error Msg: " + sErrMsg);
		else
			LoggerLoad.ErrorMsg("Class Name: " + sClassName + " -> Method: " + sMethodName +  " -> Error Msg: " + sErrMsg);
	}
	
	public void LogWarningMsg(String sClassName, String sMethodName, String sWarnMsg)
	{
		if(sMethodName == "" || sMethodName == null)
			LoggerLoad.WarningMsg("Class Name: " + sClassName + " -> Warning Msg: " + sWarnMsg);
		else
			LoggerLoad.WarningMsg("Class Name: " + sClassName + " -> Method: " + sMethodName +  " -> Warning Msg: " + sWarnMsg);
	}
	
	public void LogDebugMsg(String sClassName, String sMethodName, String sDebugMsg)
	{
		if(sMethodName == "" || sMethodName == null)
			LoggerLoad.DebugMsg("Class Name: " + sClassName + " -> Debug Msg: " + sDebugMsg);
		else
			LoggerLoad.DebugMsg("Class Name: " + sClassName + " -> Method: " + sMethodName +  " -> Debug Msg: " + sDebugMsg);
	}
	
	public void LogFatalErrMsg(String sClassName, String sMethodName, String sFatalErrMsg)
	{
		if(sMethodName == "" || sMethodName == null)
			LoggerLoad.FatalErrMsg("Class Name: " + sClassName + " -> Fatal Err Msg: " + sFatalErrMsg);
		else
			LoggerLoad.FatalErrMsg("Class Name: " + sClassName + " -> Method: " + sMethodName +  " -> Fatal Err Msg: " + sFatalErrMsg);
	}
	
	public void LogGeneralMsg(String sGeneralMsg)
	{
		LoggerLoad.SuccessMsg("General Msg: " + sGeneralMsg);
	}
	
	public void LogSuccessMsg(String sClassName, String sMethodName, String sSuccessMsg)
	{
		if(sMethodName == "" || sMethodName == null)
			LoggerLoad.SuccessMsg("Class Name: " + sClassName +  " -> SUCCESS Msg: " + sSuccessMsg);
		else			
			LoggerLoad.SuccessMsg("Class Name: " + sClassName + " -> Method: " + sMethodName +  " -> SUCCESS Msg: " + sSuccessMsg);
	}
	
	public void PrintLogFileHeaderLines()
	{
		LoggerLoad.SuccessMsg("<<<<< ---------- X X X ---------- X X X <<< SCENARIO STARTS >>> X X X ---------- X X X ---------- >>>>>");
	}
	
	public void PrintLogFileFooterLines()
	{
		LoggerLoad.SuccessMsg("<<<<< ---------- X X X ---------- X X X <<<< SCENARIO ENDS >>>> X X X ---------- X X X ---------- >>>>>");
	}
	
	public void PrintPlainLines()
	{
		LoggerLoad.SuccessMsg("***********************************************************************************************************");
	}
	
	public void PrintErrorMsgHeader()
	{
		System.out.println("-> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> -> ->");
	}
	
	public void PrintErrorMsgFooter()
	{
		System.out.println("<- <- <- <- <- <- <- <- <- <- <- <- <- <- <- <- <- <- <- <- <- <- <- <- <- <- <- <- <-");
	}
	
	public boolean isElementEnabled(WebElement weElement)
	{
		if(weElement.isEnabled())
		{
			bElementEnabled = true;
		}
		else
		{
			bElementEnabled = false;
		}
		return bElementEnabled;
	}
	
	public boolean isElementVisible(WebElement weElement)
	{
		if(weElement.isDisplayed() )
		{
			bElementVisible = true;
		}
		else
		{
			bElementVisible = false;
		}
		return bElementVisible;
	}
	
	public boolean isElementSelected(WebElement weElement)
	{
		if(weElement.isSelected() )
		{
			bElementSelected = true;
		}
		else
		{
			bElementSelected = false;
		}
		return bElementSelected;
	}
	
	public void setText(WebElement txtElement, String strTextToDisplay)
	{
		if(txtElement.isDisplayed())
		{
			if(txtElement.isEnabled())
			{
				txtElement.clear();
				txtElement.sendKeys(strTextToDisplay);
			}
			else
			{
				LoggerLoad.ErrorMsg("Element is not enabled !!");
			}
		}
		else
		{
			LoggerLoad.ErrorMsg("Element is not visible !!");
		}
	}

	public boolean isAllNumeric(String strStringToTest)
	{
		if(strStringToTest != "")
		{
			String sClean = strStringToTest.replaceAll("\\D+",""); //remove non-digits -- returns all (only) numbers.
			
			if(sClean != "")
			{
				int iExtractedNumbers = Integer.parseInt(sClean);
				
				if(strStringToTest.length() == Integer.toString(iExtractedNumbers).length())			
					return true;
				else
					return false;
			}
			else
				return false;
		}
		else		
			return false;
	}
	
	public boolean isAllAlphabets(String strStringToTest)
	{
		if(strStringToTest != "")
		{
			String sClean = strStringToTest.replaceAll("\\d",""); //remove digits -- returns all (only) alphabets.
			
			if(strStringToTest.length() == sClean.length())			
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public static String randomString()
	{
		String strRandom = RandomStringUtils.randomAlphabetic(9);
		return strRandom;
	}
	
	public boolean IsValidUserName(String strUserName)
	{
		Pattern pattern = Pattern.compile("^[[^\\*\\(\\)\\~\\`\\!\\#\\%\\&\\*\\^ ]a-zA-Z0-9_\\-\\.\\+\\@]{8,150}$");
		Matcher matcher = pattern.matcher(strUserName);			
		
		if(matcher.find())		
			return true;
		else
		{
			if(strUserName.length() < 8)
				strValidUserNameStatus = " Reason : (Shorter than 8 characters)";
			else if(strUserName.length() > 150)
				strValidUserNameStatus = " Reason : (Longer than 150 characters)";
			else
				strValidUserNameStatus = " Reason : Invalid characters found";
			return false;
		}
	}
	
	public String getCurrentURL(WebDriver remoteDriver)
	{
		// System.out.println("Get Current URL: " + remoteDriver.getCurrentUrl());
		
		if(remoteDriver.getCurrentUrl().contains("linked-list"))
			sCurrURL = "LL";		
		else if(remoteDriver.getCurrentUrl().contains("stack"))
			sCurrURL = "Stack";
		else if(remoteDriver.getCurrentUrl().contains("tree"))
			sCurrURL = "Tree";
		else if(remoteDriver.getCurrentUrl().contains("queue"))
			sCurrURL = "Queue";
		else if(remoteDriver.getCurrentUrl().contains("graph"))
			sCurrURL = "Graph";
		else if(remoteDriver.getCurrentUrl().contains("array"))
			sCurrURL = "Array";
		else if(remoteDriver.getCurrentUrl().contains("question"))
			sCurrURL = "ArrayQuestion";
		else if(remoteDriver.getCurrentUrl().contains("data-structures"))
			sCurrURL = "DS";
		
		return sCurrURL;
	}
	
	public boolean isAlertPresent(WebDriver rDriver) 
	{ 
	    try 
	    { 
	    	rDriver.switchTo().alert(); 
	        return true; 
	    }  
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   
	}   
	
	public void tearDown()
	{
		if (myDriver != null)
			myDriver.close();
	}
	
	/* Checks if a valid eMail ID is provided !
	public void validateMailid()
	{
		Pattern pattern = Pattern.compile("^[[^\\*\\(\\)\\~\\`\\!\\#\\%\\&\\* ]a-zA-Z0-9_\\-\\.]{4,150}[@][a-z0-9\\-]{2,20}[\\.][a-z]{2,4}$");
		Matcher matcher = pattern.matcher("lam.b"										
										+ "@mg-m.xox");
				
		System.out.println("Match found: " + matcher.find());
	}
	*/
}
