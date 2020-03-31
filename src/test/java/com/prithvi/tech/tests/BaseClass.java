package com.prithvi.tech.tests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public WebDriver driver;
	
	public static String browserName = "default";
	
	public static String environment = "default";
	
	public static Logger log = Logger.getLogger(BaseClass.class);
	
	@BeforeClass
	public void setUp() {
		System.setProperty("log.file", "automation_"+System.currentTimeMillis()+".log");
		PropertyConfigurator.configure("log4j.properties");
		log.info("Starting Test");
		initBrowser(browserName);
	}
	
	@AfterClass
	public void tearDown() {
		log.info("Ending Test");
		closeSession();
	}
	
	@AfterSuite
	public void quit() {
		quitSession();
	}
	
	public WebDriver initBrowser(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		System.out.println("Environment: "+environment);
		return driver;
	}
	
	public void closeSession() {
		driver.close();
	}
	
	public void quitSession() {
		driver.quit();
	}

}
