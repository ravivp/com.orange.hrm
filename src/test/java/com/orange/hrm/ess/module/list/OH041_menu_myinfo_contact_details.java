package com.orange.hrm.ess.module.list;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;

import com.orange.hrm.ess.utilities.OHTC04_upload_img_sikuli;

public class OH041_menu_myinfo_contact_details {

	public WebDriver driver;
	public Select select;
	public WebDriverWait wait;
	
	public OH041_menu_myinfo_contact_details(WebDriver driver) {
		
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Contact Details")
	WebElement Contact_Details; 
	
	@FindBy(id="btnSave")
	WebElement contact_details_edit;
	
	
	@FindBy(name="contact[street1]")
	WebElement street1;
	
	@FindBy(name="contact[street2]")
	WebElement street2;
	
	@FindBy(name="contact[city]")
	WebElement city_name;
	
	//contact[state]
	@FindBy(name="contact[state]")
	WebElement select_state;
	
	
	@FindBy(id="contact_emp_zipcode")
	WebElement zipcode;
	
	@FindBy(id="contact_country")
	WebElement select_country;
	
	
	
	@FindBy(id="contact_emp_hm_telephone")
	WebElement home_telephone;
	
	@FindBy(id="contact_emp_mobile")
	WebElement employee_mobile;
	
	@FindBy(id="contact_emp_work_telephone")
	WebElement emp_work_telephone;
	
	@FindBy(id="contact_emp_work_email")
	WebElement emp_work_email;
	
	@FindBy(id="contact_emp_oth_email")
	WebElement emp_oth_email;
	
	@FindBy(id="btnSave")
	By save_contact_details_button;
	
	
	//add extra documents btnAddAttachment
	
	@FindBy(id="btnAddAttachment")
	WebElement AddAttachment_button;
	
	@FindBy(id="ufile")
	WebElement choose_file;
	
	@FindBy(id="txtAttDesc")
	WebElement comment;
	
	@FindBy(id="btnSaveAttachment")
	By SaveAttachment_button;
	
	@FindBy(id="cancelButton")
	WebElement cancelButton_button;
	
	
	public void myinfo_contact_details_edit(String add1,String add2, String city, String state, String country,String zcode, 
			String email,String Number) throws InterruptedException {
		
		 Contact_Details.click();
		 
		contact_details_edit.click();
		street1.clear();
		street1.sendKeys(add1);
		
		street2.clear();
		street2.sendKeys(add2);
		
		select_from_list(select_country,country);
		
		select_from_list(select_state,state);
		
		city_name.clear();
		city_name.sendKeys(city);
		
		zipcode.clear();
		zipcode.sendKeys(zcode);
		
		employee_mobile.clear();
		employee_mobile.sendKeys(Number);
		
		emp_oth_email.clear();
		emp_oth_email.sendKeys(email);
		
		wait = new WebDriverWait(driver, 30);
		WebElement savebutton=wait.until(ExpectedConditions.visibilityOfElementLocated(save_contact_details_button));
		savebutton.click();
		
		Thread.sleep(2000);
	}
	
	
	public void addatche_documents() throws FindFailed, InterruptedException {
		
		AddAttachment_button.click();
		
		choose_file.click();
		
		OHTC04_upload_img_sikuli.upload_documents("documents.txt");
		
		comment.sendKeys("hello this is for practise purpose,Regards India");
		
		wait = new WebDriverWait(driver, 30);
		WebElement attachment_butoon=wait.until(ExpectedConditions.visibilityOfElementLocated(SaveAttachment_button));
		attachment_butoon.click();
	}
	
	public void select_from_list(WebElement element, String item) {
		
		
		select =new Select(element);
		select.selectByVisibleText(item);
		
	}
	
	
}
