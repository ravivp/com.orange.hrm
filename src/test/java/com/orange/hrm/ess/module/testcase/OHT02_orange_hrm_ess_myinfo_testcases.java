package com.orange.hrm.ess.module.testcase;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orange.hrm.ess.module.list.OH04_menu_pim_viewMyDetails_module;
import com.orange.hrm.ess.utilities.OHTC03_read_excel_data;
import com.orange.hrm.ess.utilities.OHTC04_upload_img_sikuli;

import junit.framework.Assert;

public class OHT02_orange_hrm_ess_myinfo_testcases extends OHT00_orange_hrm_ess_base {
	
	/*public String firstname="Jasmine";//John Smith//Jasmine Morgan
	public String moddlename="alex";
	public String lastname="Morgan";
	public String license="2-Nov-2019";
	public String gender="1"; //1 is Male 2 is Female case  
	public String marital_status="Single";
	public String nationality="Indian";*/
	public String licenses="10-Nov-2020";//8-Nov-19

	OH04_menu_pim_viewMyDetails_module myinfo ;
	@Test(priority=0)
	public void Orange_HRM_info_view_persona_details() {
		
		myinfo = new OH04_menu_pim_viewMyDetails_module(driver);
		myinfo.click_MyInfoLink();
		
		String[] employeename=EmployeeName.split("\\s+");
		
		Assert.assertEquals(employeename[0], myinfo.FirstName.getAttribute("value"));
		Assert.assertEquals(employeename[1], myinfo.LastName.getAttribute("value"));
		//Assert.assertEquals("American", myinfo.verify_default_Select_Nationality.getText());
		//Assert.assertEquals("Single", myinfo.verify_default_Marital_Status.getText());
		
	}
	
	
	
	@Test(priority=1,dataProvider="getdata",alwaysRun=true)
	public void Orange_HRM_info_persona_details(String FirstName, String MiddleName,String LastNamme, String license, String Gendare,String marital_status, String Nationality) throws InterruptedException {
		
		logger.info("ESS :"+createESS_username+" user details display below  ->First name :"+FirstName+" , middle name :"+MiddleName+", last name"+LastNamme+", license  :"+license+",Gendare  :"+Gendare+
				", marital_status  :"+marital_status+"license  :"+Nationality);
		
		 myinfo = new OH04_menu_pim_viewMyDetails_module(driver);
		 String[] employeename=EmployeeName.split("\\s+");
		myinfo.click_Personal_Details(driver,employeename[0],MiddleName,employeename[1],licenses,Gendare,marital_status,Nationality);
		logger.info(createESS_username+": personal details is succussfully displayed");
		
		
	}
	
	@Test(dependsOnMethods="Orange_HRM_info_persona_details",alwaysRun=true)
	public void Orange_HRM_yinfo_upload_photo() throws FindFailed, InterruptedException {
		
		OH04_menu_pim_viewMyDetails_module myinfo = new OH04_menu_pim_viewMyDetails_module(driver);
		
		myinfo.upload_image();
		
		OHTC04_upload_img_sikuli.upload();
		//myinfo.photouploadbutton();
		Thread.sleep(4000);
		myinfo.click_on_delete.click();
		//driver.findElement(By.id("btnDelete")).click();
		
		
		Thread.sleep(2000);
		
		String get_delete_text=myinfo.confirm_photo_delete.getText();
		
		Thread.sleep(2000);
		
		Assert.assertEquals("Delete photograph?", get_delete_text);
		Thread.sleep(2000);
		myinfo.delete_photo_ok_button.click();
		Thread.sleep(4000);
		//String currentWindow=driver.getWindowHandle();
		
		/*Set<String> windows=driver.getWindowHandles();
		
		
		Iterator<String> list=windows.iterator();
		String parent=list.next();
		logger.info("parent window is : "+parent);
		
		String child=list.next();
		logger.info("child window is : "+child);*/
		
		
		/*Alert alerts=driver.switchTo().alert();
		
		String alert_message=driver.switchTo().alert().getText();
		logger.info("**********"+alert_message+"**************************");
		Assert.assertEquals("Delete photograph?", alert_message);
		
		Thread.sleep(2000);
		alerts.accept();*/
		
		//driver.switchTo().window(currentWindow);
		
		//String alert_message=driver.findElement(By.xpath("//div[@id='deleteConfModal']//div[@class='modal-body']//p")).getText();
		
		//Assert.assertEquals("Delete photograph?", alert_message);
		
		//driver.findElement(By.cssSelector("#btnYes")).click();//#btnNo
		logger.info("photo is deleted succussfully****************************");
		
		
	}
	
	@DataProvider(name="getdata")
	public String[][] getdata() throws IOException{
		
		String filename=System.getProperty("user.dir")+"/src/test/java/com/orange/hrm/ess/xl/orange_ess_user.xlsx";
		String sheetname="ESS_user";
		String data[][]=OHTC03_read_excel_data.getData(filename, sheetname);
		return data;
		
	}

}
