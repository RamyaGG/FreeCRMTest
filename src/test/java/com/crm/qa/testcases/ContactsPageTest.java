package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.*;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

/*
 * In Hybrid framework all the test cases should be independent
 * and all the test classes should be independent. There is no link b/w one class to another class in POM and Data Driven
 * */

public class ContactsPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	TestUtil testUtil;
	
	String sheetName = "contacts";
	String singleContact = "Tom";

	public ContactsPageTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
		
	}
	
	
	@Test(priority =1)
	public void verifyContactsPageLabelTest() {
	Assert.assertTrue(contactsPage.verifyContactsLabel(), "contact label is missing on the page");	
	
	}
	
	@Test(priority =2)
	public void selectSingleContactsTest() {
		contactsPage.selectContactsByName("test2 test2");
		
	}

	
	@Test(priority =3)
	public void selectMultipleContactsTest() {
		contactsPage.selectContactsByName("test2 test2");
		contactsPage.selectContactsByName("ui uiii");
		
		
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
		
	}
	
	@Test(priority =4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title, String firstname , String lastname, String company) {
		homePage.clickOnNewContactLink();
		//contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
		contactsPage.createNewContact(title, firstname, lastname, company);
	
	}
	
	@Test(priority =5)
	public void validateSingleContactTest() {
	
		Assert.assertTrue(contactsPage.verifySingleContacts(singleContact));
	}
	
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	
}
