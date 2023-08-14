package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TreePage 
{
	WebDriver localDriver;
	
	public String sClickedTreeLinkName = "";
	
	@FindBy(linkText =  "Overview of Trees")
	WebElement weTreeOverview;
	
	@FindBy(linkText =  "Terminologies")
	WebElement weTreeTerminology;
	
	@FindBy(linkText =  "Types of Trees")
	WebElement weTreeTypes;
	
	@FindBy(linkText =  "Tree Traversals")
	WebElement weTreeTraverse;
	
	@FindBy(linkText =  "Traversals-Illustration")
	WebElement weTreeTraverseDemo;
	
	@FindBy(linkText =  "Binary Trees")
	WebElement weBinaryTrees;
	
	@FindBy(linkText =  "Types of Binary Trees")
	WebElement weBinTreeTypes;
	
	@FindBy(linkText =  "Implementation in Python")
	WebElement weTreeImplPython;
	
	@FindBy(linkText =  "Binary Tree Traversals")
	WebElement weBinTreeTraverse;
	
	@FindBy(linkText =  "Implementation of Binary Trees")
	WebElement weBinTreeImpl;
	
	@FindBy(linkText =  "Applications of Binary trees")
	WebElement weBinTreeAppl;
	
	@FindBy(linkText =  "Binary Search Trees")
	WebElement weBinSearchTrees;
	
	@FindBy(linkText =  "Implementation Of BST")
	WebElement weBinSearchTreeImpl;
	
	@FindBy(xpath  =  "//a[@href='/tryEditor']")
	WebElement weTryHere;
	
	@FindBy(linkText  =  "Practice Questions")
	WebElement wePractQn;
	
	public TreePage(WebDriver remoteDriver)
	{
		PageFactory.initElements(remoteDriver, this);
		localDriver = remoteDriver;
	}
	
	public void ClickTreeOverview()
	{
		weTreeOverview.click();
		sClickedTreeLinkName = weTreeOverview.getText(); 
	}
	
	public void ClickTreeTerms()
	{
		weTreeTerminology.click();
		sClickedTreeLinkName = weTreeTerminology.getText();
	}
	
	public void ClickTreeTypes()
	{
		weTreeTypes.click();
		sClickedTreeLinkName = weTreeTypes.getText();
	}
	
	public void ClickTreeTraverse()
	{
		weTreeTraverse.click();
		sClickedTreeLinkName = weTreeTraverse.getText();
	}
	
	public void ClickTraverseDemo()
	{
		weTreeTraverseDemo.click();
		sClickedTreeLinkName = weTreeTraverseDemo.getText();
	}

	public void ClickBinaryTree()
	{
		weBinaryTrees.click();
		sClickedTreeLinkName = weBinaryTrees.getText();
	}
	
	public void ClickBinaryTreeTypes()
	{
		weBinTreeTypes.click();
		sClickedTreeLinkName = weBinTreeTypes.getText();
	}
	
	public void ClickTreeImplPython()
	{
		weTreeImplPython.click();
		sClickedTreeLinkName = weTreeImplPython.getText();
	}
	
	public void ClickBinTreeTraverse()
	{
		weBinTreeTraverse.click();
		sClickedTreeLinkName = weBinTreeTraverse.getText();
	}
	
	public void ClickBinTreeImpl()
	{
		weBinTreeImpl.click();
		sClickedTreeLinkName = weBinTreeImpl.getText();
	}
	
	public void ClickBinTreeApplications()
	{
		weBinTreeAppl.click();
		sClickedTreeLinkName = weBinTreeAppl.getText();
	}
	
	public void ClickBinTreeSearch()
	{
		weBinSearchTrees.click();
		sClickedTreeLinkName = weBinSearchTrees.getText();
	}
	
	public void ClickImplofBST()
	{
		weBinSearchTreeImpl.click();
		sClickedTreeLinkName = weBinSearchTreeImpl.getText();
	}
	
	public void ClickTryHere()
	{
		weTryHere.click();
	}
	
	public void ClickTreePracticeQn()
	{
		wePractQn.click();
	}
}
