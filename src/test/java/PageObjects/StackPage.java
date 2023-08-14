package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StackPage 
{
	@FindBy(linkText = "Operations in Stack")
	WebElement weStackOperations;
	
	@FindBy(linkText = "Implementation")
	WebElement weStackImpl;
	
	@FindBy(linkText = "Applications")
	WebElement weStackAppl;
	
	@FindBy(linkText = "Practice Questions")
	WebElement wePractQuest;
	
	@FindBy(xpath = "//a[@href='/tryEditor']")
	WebElement weTryHere;
	
	WebDriver localDriver;
	
	public String sClickedStackLinkName = "";
	
	public StackPage(WebDriver remoteDriver)
	{
		localDriver = remoteDriver;
		PageFactory.initElements(localDriver, this);
	}
	
	public void ClickStackOperations()
	{
		weStackOperations.click();
		sClickedStackLinkName = weStackOperations.getText();
	}
	
	public void ClickStackImpl()
	{
		weStackImpl.click();
		sClickedStackLinkName = weStackImpl.getText();
	}
	
	public void ClickStackAppl()
	{
		weStackAppl.click();
		sClickedStackLinkName = weStackAppl.getText();
	}
	
	public void ClickPractQuest()
	{
		wePractQuest.click();
	}
	
	public void ClickTryHere()
	{
		weTryHere.click();
	}
}
