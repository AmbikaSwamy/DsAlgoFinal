package PageObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import Utilities.BaseClassUtils;

public class RegisterUserPage 
{
	public BaseClassUtils objBaseClass;
	
	public WebDriver localDriver;
	
	public String Username;
	public String Pass1;
	public String Pass2;
	
	public String strUserNameValidatedMsg;
	public String strPwdValidatedMsg;
	public String strPwd2ValidatedMsg;
	
	@FindBy(xpath="//input[@name='username']")
	@CacheLookup
	WebElement weTxtUsername;			// //input[@name='username']
	
	@FindBy(id = "id_password1")
	@CacheLookup
	WebElement weTxtNewPassword;		// //input[@id='password1']
	
	@FindBy(name = "password2")
	@CacheLookup
	WebElement weTxtConfirmPassword;	// //input[@id='password2']
	
	@FindBy(xpath = "//input[@type='submit']")
	@CacheLookup
	WebElement weBtnRegister;			// //input[@type='submit']
	
	@FindBy()
	@CacheLookup
	WebElement wehRefLogin;				// Use LinkText Tag.
	
	@FindBy(linkText = "Sign out")
	@CacheLookup
	WebElement wehRefLogout;	
	
	@FindBy(xpath = "//div[@class='alert alert-primary']")
	@CacheLookup
	WebElement weAlertMsg;
	
	public RegisterUserPage(WebDriver remoteDriver)
	{
		localDriver = remoteDriver;
		PageFactory.initElements(localDriver, this);
		objBaseClass = new BaseClassUtils();
	}
	
	public void setUserName(String strUsername)
	{
		Username = strUsername;		
		objBaseClass.setText(weTxtUsername, strUsername);
	}
	
	public void setPassword(String strPassword)
	{
		Pass1 = strPassword;		
		objBaseClass.setText(weTxtNewPassword, strPassword);
	}
	
	public void setNewPassword(String strNewPassword)
	{
		Pass2 = strNewPassword;		
		objBaseClass.setText(weTxtConfirmPassword, strNewPassword);
	}
	
	public void ClickRegisterUser()
	{
		weBtnRegister.click();		
		strUserNameValidatedMsg = weTxtUsername.getAttribute("validationMessage");
		strPwdValidatedMsg = weTxtNewPassword.getAttribute("validationMessage");
		strPwd2ValidatedMsg = weTxtConfirmPassword.getAttribute("validationMessage");
	}
	
	public void ClickLogout()
	{
		wehRefLogout.click();
	}
	
	public String LoginSuccessMsg()
	{		
		String strRegnStatus = "";
		
		if(Username.isBlank())
			strRegnStatus = "Username is empty";
		else if(Pass1.isBlank())
			strRegnStatus = "Password 1 is empty";
		else if(Pass2.isBlank())
			strRegnStatus = "Password 2 is empty";
		else if(!(Pass1.equals(Pass2)))
			//strRegnStatus = weAlertMsg.getText();
			strRegnStatus = "Passwords mismatch";
		else
			// validation of all the strings is pending.
			strRegnStatus = "New Account Created. You are logged in as " + Username;
		
		return strRegnStatus;
	}

}
