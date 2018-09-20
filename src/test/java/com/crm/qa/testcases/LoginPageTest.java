package com.crm.qa.testcases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.*;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	public LoginPageTest() {
		super();
		// what super() will do is it will call super class Constructor
		// to inialize properties. It is compalsory to call TestBase class Construcotr
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();

	}
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Free CRM in the cloud software boosts sales");
		
		
	}
	
	@Test(priority=2)
	public void crmLogoImageTest() {
	boolean flag = loginPage.validateCRMImage();
	Assert.assertTrue(flag); // if flag is true then assertion will pass
	}
	
	
	@Test(priority=3)
	public void loginTest() {
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
