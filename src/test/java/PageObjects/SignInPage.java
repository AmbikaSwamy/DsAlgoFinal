package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.BaseClassUtils;

public class SignInPage 
{
	// By hrefSignIn = By.linkText("Sign in");
	
	By txtUserName = By.xpath("//input[@name='username']");
	By txtPassword = By.xpath("//input[@name='password']");
	
	By btnLogin = By.xpath("//input[@type='submit']");
	By hrefSignOut = By.linkText("Sign out");
	
	public WebDriver localDriver;
	
	public String sUserName;
	public String sPassword;
	
	public WebElement linkSignIn;
	public WebElement textUserName;
	public WebElement textUserPassword;
	public WebElement btnSignIn;
	public WebElement SignoutButton;
	
	public SignInPage(WebDriver remoteDriver)
	{
		localDriver = remoteDriver;
	}
	
	public WebElement getUsernameTextBox()
	{
		WebElement textUserName = localDriver.findElement(txtUserName);
		return textUserName;
	}
	
	public WebElement getPasswordTextBox()
	{
		WebElement textUserPassword = localDriver.findElement(txtPassword);
		return textUserPassword;
	}
	
	public void ClickLogin()
	{
		WebElement LoginButton = localDriver.findElement(btnLogin);
		LoginButton.click();
	}
	
	public WebElement SignOut()
	{
		WebElement SignoutButton = localDriver.findElement(hrefSignOut);
		return SignoutButton;
	}
}
