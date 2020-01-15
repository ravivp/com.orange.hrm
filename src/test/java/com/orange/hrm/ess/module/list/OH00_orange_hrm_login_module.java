package com.orange.hrm.ess.module.list;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orange.hrm.ess.utilities.OHTC00_generate_ess_user_password_dynamically;

public class OH00_orange_hrm_login_module {

	
	public WebDriver driver;
	public String invalidMsg="Invalid credentials";
	public OH00_orange_hrm_login_module(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//( Username : Admin | Password : admin123 )
	@FindBy(xpath="//div[@style='position: relative; top: 210px; left: 655px; z-index:100;']//span")
	WebElement getText;
	
	@FindBy(id="txtUsername")
	WebElement username;
	

	@FindBy(id="txtPassword")
	WebElement passowrd;
	
	@FindBy(id="btnLogin")
	WebElement login;
	
	@FindBy(css="span#spanMessage")
	public WebElement invalide;
	
	public OD00_orange_hrm_admin_module AdminLogin(String user,String pass) {
		
	
		try {
			if(((user==null)&&(pass==null))||((user.equals("Admin"))&&(pass.equals("admin123")))) {
				String captureText=getText.getText();
				
				List<String> userdetails=OHTC00_generate_ess_user_password_dynamically.get_User_Password(captureText);
				username.sendKeys(userdetails.get(0));
				passowrd.sendKeys(userdetails.get(1));
				login.click();
			}
		} catch (Exception e) {
			System.out.println("Entered invalide credentials");
			e.printStackTrace();
		}
		
		
		return new OD00_orange_hrm_admin_module(driver);
	}
	
	public OH01_orange_hrm_ess_home_module logincredentional(String user,String pass) {
		
		boolean flag=false;
		
			
			username.sendKeys(user);
			passowrd.sendKeys(pass);
			login.click();
			
			try {
				
                if((invalide.isDisplayed())&&(invalide.getText().equals("Invalid credentials"))) {
				
				flag=true;
			
				
				
			    }
		    }catch(NoSuchElementException e) {
		    	
		    	e.printStackTrace();
			
		    	//return new OH01_orange_hrm_ess_home_module(driver);
			
		}
			if(flag) {
				
				return null;
			
			}else {
				
				return new OH01_orange_hrm_ess_home_module(driver);
			}
			
		}
		
		
		
	
	
	
}
