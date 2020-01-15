package com.orange.hrm.ess.module.testcase;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.orange.hrm.ess.module.list.OH08_menu_home_verify_broken_links;

public class OHT05_orange_hrm_admin_verify_AllLinks extends OHT00_orange_hrm_ess_base {

	OH08_menu_home_verify_broken_links checklink;
	
	@Test
	public void verify_all_links() throws MalformedURLException, IOException{
		
	    
		checklink =new OH08_menu_home_verify_broken_links(driver);
		checklink.verify_links();
		//List<WebElement> noBroken=checklink.verify_links();
		/*
		logger.info("size of no broken liks is---------->"+noBroken.size());
		for(int i=0;i<noBroken.size();i++) {
			
			HttpURLConnection connection=(HttpURLConnection)new URL(noBroken.get(i).getAttribute("href")).openConnection();
			
			connection.connect();
			
			String response=connection.getResponseMessage();
			
			connection.disconnect();
			
			System.out.println(noBroken.get(i).getAttribute("href")+"-------------------> "+response);
			//logger.info(noBroken.get(i).getAttribute("href")+"-------------------> "+response);
			
			}*/
		
		/*Map<List<WebElement>,List<WebElement>> store_all_links=new HashMap<>();
		
		store_all_links=checklink.verify_links();
		
		for(Entry<List<WebElement>, List<WebElement>> lists:store_all_links.entrySet()) {
			
			lists.getKey().forEach(no_broken->{
				
				logger.info("total no broken links is : "+no_broken.getSize());
				logger.info(" all non broken links -->> "+no_broken);
				
			});
			
			lists.getValue().forEach(broken->{
				
				logger.info("total broken links is : "+broken.getSize());
				logger.info("all broken links "+broken);
			
			});
		}
		
		logger.info("All links are verified sucuussfully");*/
		
	}
}
