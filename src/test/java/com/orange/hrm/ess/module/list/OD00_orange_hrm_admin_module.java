package com.orange.hrm.ess.module.list;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.orange.hrm.ess.utilities.OHTC01_orange_hrm_ess_verification_pojo;

public class OD00_orange_hrm_admin_module {

	
public WebDriver driver;
	
	public Actions action;
	public Select select;
	
	public JavascriptExecutor js;
	
	public  OHTC01_orange_hrm_ess_verification_pojo pojo;
	public OD00_orange_hrm_admin_module(WebDriver driver) {
		
		this.driver= driver;
	
		PageFactory.initElements(driver, this);
	}
	
	/*@FindBy(xpath="//div[@class='menu']//ul//li/a[@id='menu_admin_viewAdminModule']")
	WebElement AdminModel;*/
	
	
	@FindBy(id="menu_admin_viewAdminModule")
	WebElement AdminModel;
	
	@FindBy(id="menu_admin_UserManagement")//
	WebElement usermanagement;
	
	@FindBy(xpath="//div[@class='menu']//ul//li/a[@id='menu_admin_viewSystemUsers']")
	WebElement Users;
	
	@FindBy(xpath="//table[@id='resultTable']//tbody//tr//td[4]")
	public List<WebElement> existing_user_list;
	
	
	@FindBy(css="input#btnAdd")
	WebElement addbutton;
	
	@FindBy(id="systemUser_userType")
	WebElement selectUser;
	
	@FindBy(id="systemUser_employeeName_empName")
	WebElement employeename;
	
	@FindBy(id="systemUser_userName")
	WebElement username;
	
	@FindBy(id="systemUser_status")
	WebElement selectStatus;
	
	@FindBy(id="systemUser_password")
	WebElement password;
	
	//systemUser_confirmPassword
	@FindBy(id="systemUser_confirmPassword")
	WebElement confirmpassword;
	
	@FindBy(name="btnSave")
	WebElement savebutton;
	

	@FindBy(id="welcome")
	WebElement welcomelink;
	
	@FindBy(xpath="//div[@id='welcome-menu']//ul//li//a[text()='Logout']")
	WebElement logout;
	

	public void adim(String createESS_username,String employeeName) {
		
		
		
		addbutton.click();
		
		selectItems(selectUser,"ESS");
		
		
		employeename.sendKeys(employeeName);
		username.sendKeys(createESS_username);
		selectItems(selectStatus,"Enabled");
		
		password.sendKeys(createESS_username);
		confirmpassword.sendKeys(createESS_username);
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		
		//swait.until(ExpectedConditions.elementToBeClickable(savebutton));
		//savebutton.click();
		savebutton.click();
		
	}
	
	public void selectItems(WebElement selectitem,String item ) {
		select=new Select(selectitem);
		select.selectByVisibleText(item);
	}

	public List<String> verify_Created_ESS(String createESS_username,String UserRole,String employeename) {
		
		js = (JavascriptExecutor)driver;
		//pojo = new OHTC01_orange_hrm_ess_verification_pojo();
		List<String> list=new ArrayList<>();
		String before="//table[@id='resultTable']//tbody//tr//td//a[text()='";
		String after="']";
		js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath(before+createESS_username+after)) );
		String Username=driver.findElement(By.xpath(before+createESS_username+after)).getText();
		
		
		String BeforeStatus="//table[@id='resultTable']//tbody//tr//td[text()='";
		String AfterStatus="']";
		
		String UsersRole=driver.findElement(By.xpath(BeforeStatus+UserRole+AfterStatus)).getText();
		String EmployeeName=driver.findElement(By.xpath(BeforeStatus+employeename+AfterStatus)).getText();
		
		list.add(Username);
		list.add(UsersRole);
		list.add(EmployeeName);
		//document.getElementById()
			
		/*
		list.forEach(userdetails->{
			
			if(userdetails.equals(createESS_username)) {
				pojo.setUsername (userdetails);
			}else if(list.equals(UserRole)) {
				pojo.setUserRole(userdetails);
			}else if(list.equals(employeename)){
				pojo.setEmployeename(userdetails);
			}else {
			System.out.println("user is not correct");
			}
		});*/
		
		return list;
		
	}
	
	
	public String create_ess_user_using_existing_user(List<String> EmployeeName) throws InterruptedException{
		
	
		action= new Actions(driver);
		action.moveToElement(AdminModel).build().perform();
		action.moveToElement(usermanagement).build().perform();
		//AdminModel.click();
	
		Users.click();
		//js = (JavascriptExecutor)driver;
		
		String username=null;
		
		List<WebElement> user_list=driver.findElements(By.xpath("//table[@id='resultTable']//tbody//tr//td[4]"));
		int size=user_list.size();
		boolean flag=false;
		Thread.sleep(2000);
		user_list.forEach(employeelist->{
			
			System.out.println("Name of Employee from application  is :"+employeelist);
		});
		
		for(int employeeList_from_app=0;employeeList_from_app<size;employeeList_from_app++) {
			
			for(int excelList=0;excelList<EmployeeName.size();excelList++) {
				
				if(existing_user_list.get(employeeList_from_app).getText()==null) {
					
					break;
				}else {
					
			if(existing_user_list.get(employeeList_from_app).getText().equalsIgnoreCase(EmployeeName.get(excelList))) {
				
			username=existing_user_list.get(employeeList_from_app).getText();
			flag=true;
			break;
				
				
			}
			}
			}
			
			if(flag) {
				break;
			}
		}
		return username;
		
		
	}
	
	
	
	public void logout() {
		
		welcomelink.click();
		logout.click();
	}
}
