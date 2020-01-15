package com.orange.hrm.ess.module.list;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
public class OH08_menu_home_verify_broken_links {
	
	WebDriver driver;

	public OH08_menu_home_verify_broken_links(WebDriver driver) {
		
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(tagName="a")
	List<WebElement> links;
	
	@FindBy(tagName="img")
	List<WebElement> img_links;
	
	public void verify_links() throws MalformedURLException, IOException {
		
		
		links.addAll(img_links);
		
		//Map<List<WebElement>,List<WebElement>> broken_nonkroken=new HashMap<>();
		
		List<WebElement> no_broken_links=new ArrayList<>();
		
		List<WebElement> broken_links=new ArrayList<>();
		
		links.forEach(Alllink->{
			
			
			if(Alllink.getAttribute("href")!=null) {
				
				no_broken_links.add(Alllink);
			}else {
				
				broken_links.add(Alllink);
			}
		});
		
		
		//verify_no_brokenLinks(no_broken_links);
	
		//verify_no_brokenLinks(broken_links);
		
	 
		//return no_broken_links;
	}
	
	public List<WebElement> NoBroken_Links(){
		
		links.addAll(img_links);
		
		
		List<WebElement> NO_broken_links=new ArrayList<>();
		
		links.forEach(links->{
			
			if(links.getAttribute("href")!=null) {
				
				NO_broken_links.add(links);
			}
			
			
		});
		
		return NO_broken_links;
	}
	
	public void verify_no_brokenLinks(List<WebElement> check_Broken) throws MalformedURLException, IOException {
		
		for(int i=0;i<check_Broken.size();i++) {
			
		HttpURLConnection connection=(HttpURLConnection)new URL(check_Broken.get(i).getAttribute("href")).openConnection();
		
		connection.connect();
		
		String response=connection.getResponseMessage();
		int code=connection.getResponseCode();
		
		connection.disconnect();
		
		System.out.println(check_Broken.get(i).getAttribute("href")+"------ Links-------------> response message :-> "
		+response+ " & response code : -> "+code);
		
		}
		
		}
	
	public int verify_AllLinks(List<WebElement> check_links) throws MalformedURLException, IOException {
		
		int count=0;
		for(int i=0;i<check_links.size();i++) {
			
			HttpURLConnection connection=(HttpURLConnection)new URL(check_links.get(i).getAttribute("href")).openConnection();
			
			connection.connect();
			
		//	String response=connection.getResponseMessage();
			int code=connection.getResponseCode();
			
			if(code!=200) {
				
				count=count+1;
				
			}
			
			connection.disconnect();
			
		}
		
		return count;
	}
	
	
}
