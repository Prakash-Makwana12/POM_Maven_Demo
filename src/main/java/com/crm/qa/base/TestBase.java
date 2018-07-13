package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtils;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	
	public static WebDriver driver;
	public static Properties prop;
	
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase()
	{
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("/Users/Prakash/eclipse-workspace/PractiseTest/src/"
					+ "main/java/com/crm/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void initalization()
	{
		 String browserName = prop.getProperty("browser");
		 
		 if(browserName.equals("chrome"))
		 {
				System.setProperty("webdriver.chrome.driver", "/Users/Prakash/Downloads/chromedriver");
				
				 driver = new ChromeDriver();
		 }
		 
		 e_driver = new EventFiringWebDriver(driver);
		 eventListener = new WebEventListener();
		 e_driver.register(eventListener);
		 driver=e_driver;
		 	
		 
		 
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		
		driver.get(prop.getProperty("url"));
		
	}
	
	
	
	

}
