package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.*;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	
	//we are initializing the browser 100 times if 100 test cases are there. its a best practice
	//becoz if we are not closing the browser and on the same browser you are excuting 100 test cases , your browser wil be exhausted
	//it could be memory issue or cache issue. Some time browser is crashed or application is crashed
	
	HomePage homePage;
	LoginPage loginPage;
	TestUtil testUtil;
	ContactsPage ContactsPage;
	
	public HomePageTest() {
		super();
	}
	
	//Test cases should be separated -- independent with each other
	//Before each test case -- launch the browser and login
	//@tests -- execute test case
	//after each test case -- close the browser
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		ContactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String title = homePage.verifyHomePageTitle();
		Assert.assertEquals(title, "CRMPRO","Home Page title Not Matched");
	}
	
	@Test(priority=2)
	public void verifyUserNameTest() {
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}
	
	@Test (priority=3)
	public void verifyContactsLinkTest() {
		testUtil.switchToFrame();
		ContactsPage = homePage.clickOnContactsLink();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
