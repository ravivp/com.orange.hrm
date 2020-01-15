package com.orange.hrm.ess.module.testcase;

import java.io.IOException;

import org.sikuli.script.FindFailed;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orange.hrm.ess.module.list.OH041_menu_myinfo_contact_details;
import com.orange.hrm.ess.utilities.OHTC03_read_excel_data;

public class OHT021_orange_hrm_ess_myinfo_conatct_details_testcase extends OHT00_orange_hrm_ess_base {

	OH041_menu_myinfo_contact_details contact;
	
	
	
	@Test(priority=0,dataProvider="getdata")
	
	public void verify_conact_details(String add1, String add2, String city, String state, String country, String zcode,String email, String Number) throws InterruptedException {
		
		contact = new OH041_menu_myinfo_contact_details(driver);
		contact.myinfo_contact_details_edit(add1, add2, city, state, country, zcode, email, Number);
		
		
		
	}
	
	@Test(priority=1,dependsOnMethods="verify_conact_details")
	public void attached_extra_documents() throws FindFailed, InterruptedException {
		
		contact = new OH041_menu_myinfo_contact_details(driver);
		contact.addatche_documents();
	}
	
	@DataProvider(name="getdata")
	public String[][] testdata() throws IOException{
		
		String filepath=System.getProperty("user.dir")+"/src/test/java/com/orange/hrm/ess/xl/orange_ess_user.xlsx";
		
		String sheetName="contact";
		
		String data[][]=OHTC03_read_excel_data.getData(filepath, sheetName);
		
		return data;
	}
}
