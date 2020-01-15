package com.orange.hrm.ess.utilities;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.ITestResult;
public class OHTC00_generate_ess_user_password_dynamically {

	
	public static String user_passowrd() {
		
		String generate_user_password=RandomStringUtils.randomNumeric(3);
		
		return ("ravi@"+generate_user_password);
	}
	
	
	public static void takescreenshot(ITestResult result) {
		
		
		
	}
	
	public static List<String> get_User_Password(String value) {
		
		List<String> listItems = new ArrayList<String>();
		value=value.replaceAll("[\\s()]+","");
		
		String[] split_data=value.split("\\|");
		
		for(String items:split_data) {
			
			String[] split_sub_data=items.split("\\:");
			
			for(String sub:split_sub_data) {
				
				if((sub.equals("Admin"))||(sub.equals("admin123"))) {
					
				listItems.add(sub);
				
				}
			}
		}
		
		return listItems;
	}
	
}
