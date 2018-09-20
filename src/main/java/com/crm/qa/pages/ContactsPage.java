package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {
	
	HomePage homePage;
	
	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="surname")
	WebElement lastName;
	
	@FindBy(name="client_lookup")
	WebElement company;
	
	@FindBy(xpath="//input[@value='Load From Company']//following-sibling::input[@type='submit' and @value='Save']")
	WebElement saveBtn;
	
	
	//a[contains(text(),'test2 test2')]//parent::td//preceding-sibling::td//input[@name='contact_id']
	
	//Initializing the page Objects:
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}
	
	//it is not compulsory to define all the page factory for the all the elemnts 
	//becoz test2 test2 i am here if want to pass ui uiii again i have come and change here
	public void selectContactsByName(String name) {
		
		driver.findElement(By.xpath("//a[contains(text(),'"+name+"')]//parent::td//preceding-sibling::td"
				+"//input[@name='contact_id']")).click();
	}
	
	public void createNewContact(String title, String ftName, String ltName, String comp) {
		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);
		firstName.sendKeys(ftName);
		lastName.sendKeys(ltName);
		company.sendKeys(comp);
		saveBtn.click();
		
		
	}
	
	public boolean verifySingleContacts(String contact) {
		return driver.findElement(By.xpath("//a[contains(text(),'"+contact+"')]")).isDisplayed();
	}

}
