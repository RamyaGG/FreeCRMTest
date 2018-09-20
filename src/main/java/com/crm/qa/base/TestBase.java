package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

/*
 * Page Object Model (POM) Design With Selenium WebDriver- Part -2:

+Complete Page Object Model (POM) using Page Factory in Selenium
+Learn Page Object Model Step by Step
+POM design - Architecture & Pattern
+What is Page Chaining Model
+Use of PageFactory API in Selenium

=================================================
Technologies Used: 
• Selenium WebDriver - v3.0.4 (Open Source) 
• JDK 1.8 (Java Development Kit) 
• TestNG (Test Unit Framework) 
• Log4j (logging API) 
• Maven (Build Automation Tool) 
• Apache POI API (Read-Write utilities for Excel - Test Data Handling) Eclipse/IntelliJ (Java Editor) 
• Browser - Google Chrome/FF

Automation Framework Architecture: 
• POM (Page Object Model) Design Page Factory API of WebDriver 
• Maven (Build Automation Tool) 
• Test Libraries for different UI Pages 
• Test Utilities for different generic functions 
• Report - Dashboard (Pass/Fail Test) by using Extent Report 
• API Jenkins - Continuous Integration Tool 
• GITHub Repo (Code Versioning Tool)
 * 
 * */

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public TestBase() {
	
		try {
			prop = new Properties();
			try {
				FileInputStream ip = new FileInputStream("H:\\Edureka\\Selenium\\WorkSpace\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			
			prop.load(ip);
			
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}
	
	
	public static void initialization() {
		
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "H:\\Edureka\\Selenium\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			
		}else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "H:\\Edureka\\Selenium\\chromedriver_win32\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
		
		
	}
	
	
	
	
	

}
