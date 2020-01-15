package com.orange.hrm.ess.module.list;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OH01_orange_hrm_ess_home_module {

	public WebDriver driver;
	public Actions action;
	public OH01_orange_hrm_ess_home_module(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="menu_leave_viewLeaveModule")
	WebElement menu_leave_viewLeaveModule;
	
	@FindBy(id="menu_time_viewTimeModule")
	WebElement menu_time_viewTimeModule;
	
	@FindBy(id="menu_pim_viewMyDetails")
	WebElement menu_pim_viewMyDetails;
	
	@FindBy(id="menu__Performance")
	WebElement menu__Performance;
	
	@FindBy(id="menu_dashboard_index")
	WebElement menu_dashboard_index_default_module;
	
	@FindBy(id="menu_directory_viewDirectory")
	WebElement menu_directory_viewDirectory;
	
	
	//leave links
	
	
	public OH02_menu_leave_viewleave_module LeaveLink(String seeLeaves,String subdetails) {
		
		//menu_leave_viewLeaveModule;
		
		action = new Actions(driver);
		
		action.moveToElement(menu_leave_viewLeaveModule).build().perform();
		
		String beforeXpath="//div[@class='menu']//ul//li//ul//li//a[text()='";
		if(seeLeaves.equals("Apply")) {
			
			driver.findElement(By.xpath(beforeXpath+seeLeaves+"']")).click();
			
		}else if(seeLeaves.equals("My Leave")){
			
			driver.findElement(By.xpath(beforeXpath+seeLeaves+"']")).click();
			
		}else if(seeLeaves.equals("Entitlements")) {
		
			WebElement Entitlements =driver.findElement(By.xpath(beforeXpath+seeLeaves+"']"));
		
			action.moveToElement(Entitlements).build().perform();//My Entitlements
			driver.findElement(By.xpath(beforeXpath+"My Entitlements"+"']")).click();
			
		}else if(seeLeaves.equals("Reports")) {
			
			action.moveToElement(driver.findElement(By.xpath(beforeXpath+seeLeaves+"']"))).build().perform();
			driver.findElement(By.xpath(beforeXpath+"My Leave Entitlements and Usage Report"+"']")).click();
		}else {
			
			System.out.println("invalide checking");
		}
		
		return new OH02_menu_leave_viewleave_module();
	}
	
	
	
	public OH04_menu_pim_viewMyDetails_module MyInfoLink() {
		
		menu_pim_viewMyDetails.click();
		
		
		return new OH04_menu_pim_viewMyDetails_module(driver);
	}
}
