package com.orange.hrm.ess.module.list;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OH07_menu_new_window_browser {
	
	public WebDriver driver;
	
	@FindBy(xpath="//img[@alt='OrangeHRM']")
	static WebElement click_img_new_browser;
	
	public OH07_menu_new_window_browser(WebDriver driver) {
		
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	
	public static void new_Windows() {
		
		click_img_new_browser.click();
		
	}
	
	

}
