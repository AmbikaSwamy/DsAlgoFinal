package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedListPage 
{
	public WebDriver localDriver;
	public String sClickedLLLinkName = "";
	
	@FindBy(linkText = "Introduction")
	WebElement LLPageIntroLink;
	
	@FindBy(linkText = "Creating Linked LIst")
	WebElement CreateLLLink;
	
	@FindBy(linkText = "Types of Linked List")
	WebElement TypesOfLLLink;
	
	@FindBy(linkText = "Implement Linked List in Python")
	WebElement ImplementLLink;
	
	@FindBy(linkText = "Traversal")
	WebElement TraversalLLLink;
	
	@FindBy(linkText = "Insertion")
	WebElement InsertIntoLLLink;
	
	@FindBy(linkText = "Deletion")
	WebElement DeleteFromLLLink;
	
	@FindBy(xpath = "//a[@href='/tryEditor']")
	WebElement TryEditorLLLink;
	
	@FindBy(linkText = "Practice Questions")
	WebElement PracticeQnLink;
	
	public LinkedListPage(WebDriver remoteDriver)
	{
		localDriver = remoteDriver;		
		PageFactory.initElements(localDriver, this);
	}
	
	public void PageIntroClick()
	{
		LLPageIntroLink.click();
		sClickedLLLinkName = LLPageIntroLink.getText();
	}
	
	public void CreateLLClick()
	{
		CreateLLLink.click();
		sClickedLLLinkName = CreateLLLink.getText();
	}
	
	public void TypesOfLLClick()
	{
		TypesOfLLLink.click();
		sClickedLLLinkName = TypesOfLLLink.getText();
	}
	
	public void ImplementLLClick()
	{
		ImplementLLink.click();
		sClickedLLLinkName = ImplementLLink.getText();
	}
	
	public void TraverseClick()
	{
		TraversalLLLink.click();
		sClickedLLLinkName = TraversalLLLink.getText();
	}
	
	public void InsertIntoLLClick()
	{
		InsertIntoLLLink.click();
		sClickedLLLinkName = InsertIntoLLLink.getText();
	}
	
	public void DelFromLLClick()
	{
		DeleteFromLLLink.click();
		sClickedLLLinkName = DeleteFromLLLink.getText();
	}
	
	public void TryEditorClick()
	{
		// TryEditorLLLink.click();
		Actions actionTry = new Actions(localDriver);
		actionTry.scrollToElement(TryEditorLLLink).click(TryEditorLLLink);
		actionTry.perform();
	}
	
	public void ClickPracticeQn()
	{
		PracticeQnLink.click();
	}
}
