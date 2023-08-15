package StepDefinitions;

import java.lang.reflect.Method;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.core.gherkin.Step;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.BeforeStep;

import io.cucumber.java.Scenario;
import Utilities.BaseClassUtils;
import Utilities.LoggerLoad;

public class ServiceHooks
{
	public static BaseClassUtils objBaseClass;
	public static WebDriver lDriver;
	
	public String sMethodName;
	
	public ServiceHooks()
	{
		
	}
	
	@BeforeAll(order = 2)
	public static void setHooksDriver()
	{
		objBaseClass = new BaseClassUtils();
		lDriver = objBaseClass.GetDriver();
		//System.out.println("Setting @BeforeAll Hook Order #2");
	}
	
	@BeforeAll(order = 1)
	public static void before_all()
	{		
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		//System.out.println("Setting @BeforeAll Hook Order #1");
	}
	
	@Before
	public static void BeforeTest(Scenario scenario)
	{
		objBaseClass.PrintPlainLines();
		LoggerLoad.SuccessMsg("###################### ###################### SCENARIO BEGINS ###################### ######################");
		objBaseClass.PrintPlainLines();
	}
	
	@BeforeStep(order = 1)
	public void DoThisBeforeEachMethod(Scenario scenario)
	{
		
	}
	
	@AfterStep
	public void DoThisAfterEachMethod(Scenario scenario)
	{		
		Method cSteps;		
		cSteps = Step.class.getMethods()[6];
					
		// System.out.println("Hook After each method !");
		// objBaseClass.LogGeneralMsg("Scenario: " + cSteps.getName().toString());
		
		if(scenario.isFailed())
		{
			try
			{				
				String sTestStatus = "Failed";
				
				lDriver = objBaseClass.GetDriver();
				
				if(sMethodName == null || sMethodName == "")
				{
					objBaseClass.Capture_ScreeShots(lDriver, sTestStatus, "");
					LoggerLoad.ErrorMsg("SCENARIO: $$$$$ " + scenario.getName().toUpperCase() + " $$$$$ failed"); 
					byte[] Screenshot =((TakesScreenshot) lDriver).getScreenshotAs(OutputType.BYTES);
					scenario.attach(Screenshot, "image/png", scenario.getName() + " Failed");
				}
				else
				{	
					objBaseClass.Capture_ScreeShots(lDriver, sTestStatus, sMethodName);
					LoggerLoad.ErrorMsg("SCENARIO: " + scenario.getName() + " failed"); 
					byte[] Screenshot =((TakesScreenshot) lDriver).getScreenshotAs(OutputType.BYTES);
					scenario.attach(Screenshot, "image/png", scenario.getName() + " Failed");
				}
				
				lDriver.close();
				LoggerLoad.SuccessMsg("###################### ###################### SCENARIO ENDS ###################### ###################### \n");
			}
		
			catch(Exception E)
			{
				E.printStackTrace();
			}
						
			
		}
		else
		{
			// LoggerLoad.SuccessMsg("Test passed");
		}
	}
	
	@After
	public static void AfterTest(Scenario scenario)
	{
		if(!scenario.isFailed())
		{
			// objBaseClass.LogSuccessMsg(scenario.getName(), "", "Scenario passed successfully !");
			LoggerLoad.SuccessMsg("SCENARIO : *** " + scenario.getName().toUpperCase() + " *** passed successfully !");
			objBaseClass.PrintPlainLines();
			LoggerLoad.SuccessMsg("###################### ###################### SCENARIO ENDS ###################### ######################");
			objBaseClass.PrintPlainLines();
			// LoggerLoad.SuccessMsg("\n");
		}
	}
	
	@AfterAll
	public static void afterall()
	{
		
	}
}
