package com.orange.hrm.ess.module.list;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OH04_menu_pim_viewMyDetails_module {

	public WebDriver driver;
	public WebDriver gdriver;
	public Actions action;
	public Select select;
	public OH01_orange_hrm_ess_home_module myinformation_Link;
	
	public OH04_menu_pim_viewMyDetails_module(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	  myinformation_Link=new OH01_orange_hrm_ess_home_module(driver);
	  
	}
	
	@FindBy(id="menu_pim_viewMyDetails")//menu_pim_viewMyDetails
	WebElement myinfo_link;
	
	@FindBy(linkText="Personal Details")
	WebElement Personal_DetailLink;
	
	@FindBy(id="personal_txtEmpFirstName")
	public WebElement FirstName;
	
	@FindBy(id="personal_txtEmpMiddleName")
	public WebElement MiddleName;
	
	@FindBy(id="personal_txtEmpLastName")
	public WebElement LastName;
	
	@FindBy(css="ul.radio_list li input#personal_optGender_1")
	WebElement click_on_male_radioa_button;
	@FindBy(css="label[for=personal_optGender_1]")
	WebElement verify_male_label;
	
	@FindBy(css="input#personal_optGender_2")
	WebElement click_on_female_radioa_button;
	
	@FindBy(css="label[for=personal_optGender_2]")
	WebElement verify_female_label;
	
	
	@FindBy(css="input#btnSave")
	WebElement personal_edit_button;
	
	@FindBy(id="personal_txtLicExpDate")//personal_txtLicExpDate
	WebElement click_License_Expiry_Date;
	
    @FindBy(className="ui-datepicker-month")
	WebElement Select_License_Expiry_Month;
	
    @FindBy(className="ui-datepicker-year")
	WebElement Select_License_Expiry_Year;
    
    @FindBy(id="personal_DOB")
    public WebElement verify_date_of_birth;//17-11-1980
    
    
    
			
    @FindBy(css="select[id=personal_cmbMarital] option[selected='selected']")
    public WebElement verify_default_Marital_Status;
    
    @FindBy(name="personal[cmbMarital]")//Single,Married
    public WebElement Select_Marital_Status;
	
    @FindBy(css="select[id=personal_cmbNation] option[selected='selected']")
    public WebElement verify_default_Select_Nationality;
    
    
	@FindBy(name="personal[cmbNation]")//Indian Indian
	public WebElement Select_Nationality;
	
	@FindBy(id="btnSave")
	WebElement Save_personal_details;
	
	@FindBy(xpath="//img[@alt='Employee Photo']")
	WebElement click_photo_upload;
	
	@FindBy(id="photofile")
	WebElement click_choose_file;
	
	@FindBy(xpath="//input[@id='btnSave' and @value='Upload'] ")
	public WebElement photo_uploadbutton;
	
	@FindBy(id="btnDelete")
	
	public WebElement click_on_delete;
	
	@FindBy(xpath="//div[@id='deleteConfModal']//div//p")
	public WebElement confirm_photo_delete;
	
	
	
	@FindBy(xpath="//div[@id='deleteConfModal']//div//input[@id='btnYes']")
	public WebElement delete_photo_ok_button;
	
	@FindBy(linkText="Contact Details")
	WebElement Contact_Details;
	
	@FindBy(linkText="Emergency Contacts")
	WebElement Emergency_Contacts;
	
	@FindBy(linkText="Dependents")
	WebElement Dependents;
	
	
	public void click_MyInfoLink() {
		
		myinformation_Link.MyInfoLink();
		Personal_DetailLink.click();
	}
	public void click_Personal_Details(WebDriver ldriver,String firstname ,String middlename,String laststname,String date,String Gender,String status,String nation) throws InterruptedException {
		//ul[@class='radio_list']//li//label[text()='Male'];
		
		gdriver = ldriver;
		String gender_befor_css="ul[class='radio_list']>li>input[value='";
		String gender_after="']";
		
		
		
		
		/*myinformation_Link.MyInfoLink();
		Personal_DetailLink.click();*/
		personal_edit_button.click();
		
		FirstName.clear();
		FirstName.sendKeys(firstname);   //Thomas 
		MiddleName.sendKeys(middlename);
		LastName.clear();
		LastName.sendKeys(laststname);   //Fleming
		select_license( date);
		
		boolean enabled=driver.findElement(By.cssSelector(gender_befor_css+Gender+gender_after)).isEnabled();
		boolean isSelected=driver.findElement(By.cssSelector(gender_befor_css+Gender+gender_after)).isSelected();
		if(enabled&&!(isSelected)) {
			driver.findElement(By.cssSelector(gender_befor_css+Gender+gender_after)).click();
		}
		
		selectItems(Select_Marital_Status,status);
		selectItems(Select_Nationality,"Indian");
		
		Save_personal_details.click();
		
		
	}
	
	
	
	public OH041_menu_myinfo_contact_details contact_detail_verify() {
		
		Contact_Details.click();
		
		return new OH041_menu_myinfo_contact_details(driver);
	}
	public void select_license(String date) throws InterruptedException {
		
		
		//table[@class='ui-datepicker-calendar']//tbody//tr[1]//td[2]
		
		String[] splitdate=date.split("-");
		String day=splitdate[0];
		String month=splitdate[1];
		String year=splitdate[2];
		
		//table[@class='ui-datepicker-calendar']//tbody//tr[2]//td[2]//a
		//table[@class='ui-datepicker-calendar']//tbody//tr[1]//td[1]
		String before="//table[@class='ui-datepicker-calendar']//tbody//tr[";
		String after="]//td[";
		String afterlink="]//a";
		
		click_License_Expiry_Date.click();
		selectItems(Select_License_Expiry_Month,month);
		selectItems(Select_License_Expiry_Year,year);
		
		//Thread.sleep(2000);
		//List<WebElement> calendar=gdriver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//tbody//tr//td//a"));
		
		/*calendar.forEach(days->{
			System.out.println(days.getText()+"**********************************************************************");
			if(days.getText().equals(day)){
				days.click();
			}
		});*/
		
		int totalweedays = 7;
		boolean flag=false;
		String dayvalue=null;
		
		for(int row=1;row<=totalweedays;row++) {
			
			for(int coloumn=1; coloumn<=totalweedays;coloumn++) {
		
				try {
					
					dayvalue=gdriver.findElement(By.xpath(before+row+after+coloumn+afterlink)).getText();
				}catch(NoSuchElementException e) {
					System.out.println("enter correct date format");
					flag=false;
					break;
					
				}catch(StaleElementReferenceException e) {
					System.out.println("invalid date format please provide valide date");
					flag=false;
					break;
				}
				
				if(dayvalue.equals(day)) {
					
					driver.findElement(By.xpath(before+row+after+coloumn+afterlink)).click();
					flag=true;
					break;
				}
			}
			if(flag) {
				break;
			}
		}
		
		
	}
	
	
	public void selectItems(WebElement selectitem,String item ) {
		select=new Select(selectitem);
		select.selectByVisibleText(item);
	}
	
	
	public  void upload_image() {
		click_photo_upload.click();
		click_choose_file.click();
		
		
	}
	public void photouploadbutton() {
		photo_uploadbutton.click();
	}
}
