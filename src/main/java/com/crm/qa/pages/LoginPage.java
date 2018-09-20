package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	//Page Factory or Object repository : it is a collection of all the Web Objects/WebElements
	
	@FindBy(name = "username")
	WebElement username;
	
	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//button[text(),'Sign Up']")
	WebElement signUpBtn;
	
	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;
	
	//Initializing the page objects:
	public LoginPage() {
	//How will you inialize your PageFactory
	//We have one method PageFactory.initElements with driver and (this means)current class object
	//instead of this we can also use LoginPage.class Better to use this 
		PageFactory.initElements(driver, this);
		
	}
	
	//Actions :
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();
	}

	public HomePage login(String un , String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.submit();
		
		return new HomePage();
		
	}
	
	
}
