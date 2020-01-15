package com.orange.hrm.ess.module.testcase;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.orange.hrm.ess.module.list.OH00_orange_hrm_login_module;
import com.orange.hrm.ess.module.list.OH08_menu_home_verify_broken_links;

public class OCT00_orange_hrm_admin_login  extends OHT00_orange_hrm_ess_base{
	
	public OH00_orange_hrm_login_module loginModel;
	
	public OH08_menu_home_verify_broken_links checklink; 
	
	@Test
	public void AdminLogin() {
		
		loginModel = new OH00_orange_hrm_login_module(driver);
		loginModel.AdminLogin(null, null);
		
		
		
	}
	
	@Test(alwaysRun=false,enabled=false)
	
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
