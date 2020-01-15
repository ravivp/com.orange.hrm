package com.orange.hrm.ess.module.testcase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.orange.hrm.ess.utilities.OHTC00_generate_ess_user_password_dynamically;
import com.orange.hrm.ess.utilities.OHTC02_read_properties_file;
import com.orange.hrm.ess.utilities.OHTC05_orange_hrm_ess_listner;

public class OHT00_orange_hrm_ess_base {

	
	
	
	public static WebDriver driver;
	public static Logger logger;
	public OHTC02_read_properties_file properties;
	public static String createESS_username;
	public static String loginName;
	public static String loginpass;
	
	public static String EmployeeName;
	@Parameters("browser")
	@BeforeTest
	public void setup(String browser) {
		
		logger=Logger.getLogger("orange hrm ess");
		PropertyConfigurator.configure("log4j.properties");
		properties = new OHTC02_read_properties_file();
		loginName=properties.getusername();
		loginpass=properties.getpassword();
	
		createESS_username=OHTC00_generate_ess_user_password_dynamically.user_passowrd();
		if(browser.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", properties.getchrome());
			driver = new ChromeDriver();
			//driver=	new HtmlUnitDriver();
			
		}else if(browser.equals("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", properties.getfirebox());
			driver = new FirefoxDriver();
			
		}else if(browser.equals("ie")) {
			
			System.setProperty("webdriver.ie.driver", properties.getie());
			driver=new InternetExplorerDriver();
		}else {
			
			driver = new HtmlUnitDriver();
			
			logger.info("you are not passing proper browser to launch application ");
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(properties.geturl());
		
		//driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	
	
}
