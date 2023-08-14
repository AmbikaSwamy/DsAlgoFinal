package PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TryEditorPage 
{
	WebDriver localDriver;
	
	@FindBy(xpath = "//textarea[@tabindex='0']")
	WebElement weCodeEditor;
	
	@FindBy(xpath = "//button")
	WebElement weCodeEditorRunButton;
	
	public TryEditorPage(WebDriver remoteDriver)
	{
		localDriver = remoteDriver;
		PageFactory.initElements(localDriver, this);
	}
	
	public void TryThisCode(String sCodeSnippet)
	{		
		
		if(localDriver.getCurrentUrl().contains("/question"))
		{
			// if(weCodeEditor.isDisplayed())
				// weCodeEditor.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			
			Actions act = new Actions(localDriver);
			act.click(weCodeEditor).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).build().perform();
		}
		
		weCodeEditor.sendKeys(sCodeSnippet);
	}
	
	public void RunEditorCode()
	{
		weCodeEditorRunButton.click();
	}
}
