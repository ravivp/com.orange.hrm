package com.orange.hrm.ess.module.testcase;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orange.hrm.ess.module.list.OH00_orange_hrm_login_module;
import com.orange.hrm.ess.module.list.OH08_menu_home_verify_broken_links;
import com.orange.hrm.ess.utilities.OHTC00_generate_ess_user_password_dynamically;
import com.orange.hrm.ess.utilities.OHTC02_read_properties_file;
import com.orange.hrm.ess.utilities.OHTC03_read_excel_data;

public class OHT01_orange_hrm_ess_login_testcases extends OHT00_orange_hrm_ess_base  {

	
	public OH00_orange_hrm_login_module login;
	OH08_menu_home_verify_broken_links checklink;
	String filepath=System.getProperty("user.dir")+"/src/test/java/com/orange/hrm/ess/xl/orange_ess_user.xlsx";
	String sheet="login";
	
	@Test
	public void valid_invalid_credentials() {
		
		logger.info("ESS user login : enter credential ");
		login = new OH00_orange_hrm_login_module(driver);
		login.logincredentional(createESS_username, "ravi");
		
		Assert.assertEquals("Invalid credentials", login.invalide.getText());//Invalid credentials
		
		logger.info("valid user name and invalide password");
	}
	
	
	@Test(dependsOnMethods="valid_invalid_credentials")
	public void invalid_valid_credentials() {
		
		logger.info("ESS user login : enter credential ");
		login = new OH00_orange_hrm_login_module(driver);
		login.logincredentional("ravi", "createESS_username");
		Assert.assertEquals("Invalid credentials", login.invalide.getText());
		
		logger.info("invalide user name and valid password ");
	}
	
	@Test(dependsOnMethods="invalid_valid_credentials")
	public void invalid_invalid_credentials() {
		
		logger.info("ESS user login : enter credential ");
		login = new OH00_orange_hrm_login_module(driver);
		login.logincredentional("ravi", "ravi");
		Assert.assertEquals("Invalid credentials", login.invalide.getText());
		
		logger.info("invalids user and password");
	}
	
	
	@Test(dependsOnMethods="invalid_invalid_credentials")
	public void login_using_ess_credentials() {
		
		logger.info("ESS user login : enter credential ");
		login = new OH00_orange_hrm_login_module(driver);
		login.logincredentional(createESS_username, createESS_username);
		
		
		
		String[] first_lats_name=EmployeeName.split("\\s+");
		
		
		Assert.assertEquals("Welcome "+first_lats_name[0], driver.findElement(By.id("welcome")).getText());
		logger.info("ESS user is sucuussfull login");
	}
	
	
/*	@DataProvider
	public String[][] getlogin_data() throws IOException{
		
		String[][] data ;
		
		data=OHTC03_read_excel_data.getData(filepath, sheet);
		
		return data;
	}*/
	
	@Test(enabled=false)
   public void verify_all_links() throws MalformedURLException, IOException {
		
		logger.info("verifing all links***************************");
		
		checklink =new OH08_menu_home_verify_broken_links(driver);
		
		//List<WebElement> links=checklink.NoBroken_Links();
		
		int broken_links=checklink.verify_AllLinks(checklink.NoBroken_Links());
		
		logger.info("Total Number of links broken : "+broken_links);
		
		Assert.assertEquals(broken_links, 0);
		
		logger.info("No links are broken******************************** ");
	}
}
