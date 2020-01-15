package com.orange.hrm.ess.module.testcase;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.orange.hrm.ess.module.list.OD00_orange_hrm_admin_module;
import com.orange.hrm.ess.module.list.OH00_orange_hrm_login_module;
//import com.orange.hrm.ess.models.orange_hrm_ess_verification_pojo;
import com.orange.hrm.ess.utilities.OHTC00_generate_ess_user_password_dynamically;
import com.orange.hrm.ess.utilities.OHTC01_orange_hrm_ess_verification_pojo;
import com.orange.hrm.ess.utilities.OHTC03_read_excel_data;

public class ODT00_orange_hrm_create_ess_user_by_admin extends OHT00_orange_hrm_ess_base{
	
	public OD00_orange_hrm_admin_module adminModel;
	//public OH00_orange_hrm_login_module loginModel;
	public OHTC01_orange_hrm_ess_verification_pojo pojo;
	
	//String employeeName="Steven Edwards";
	String UserRole="ESS";
	
	@Test
	public void create_verify_status() throws IOException, InterruptedException {
		
		logger.info("ESS user is created by Admin ");
		pojo = new OHTC01_orange_hrm_ess_verification_pojo();
		/*loginModel=new OH00_orange_hrm_login_module(driver);
		loginModel.logincredentional(null,null);//"Admin", "admin123"
*/		
		
		
		adminModel=new OD00_orange_hrm_admin_module(driver);
		EmployeeName=adminModel.create_ess_user_using_existing_user(OHTC03_read_excel_data.get_single_Employee_Coloumn());
		adminModel.adim(createESS_username, EmployeeName);
		
		logger.info("username************* -> "+createESS_username);
		
		logger.info("employeeName************* -> "+EmployeeName);
		
		logger.info("UserRole************* -> "+UserRole);
		
		//adminModel.verify_Created_ESS(createESS_username, UserRole, employeeName);
		List<String> listitems=adminModel.verify_Created_ESS(createESS_username, UserRole, EmployeeName);
		for(String list:listitems) {
			
			
			if(list.equals(createESS_username)) {
				pojo.setUsername (list);
			}else if(list.equals(UserRole)) {
				pojo.setUserRole(list);
			}else if(list.equals(EmployeeName)){
				pojo.setEmployeename(list);
			}else {
				logger.info("");
			}
			
			
			
		}
	
		Assert.assertEquals(createESS_username, pojo.getUsername());
		Assert.assertEquals(UserRole, pojo.getUserRole());
		Assert.assertEquals(EmployeeName, pojo.getEmployeename());
		logger.info("sucussfully created ESS Employee --> "+"username is : "+createESS_username+", user role is :" +UserRole+", employeeName is: "+EmployeeName);
		
		adminModel.logout();
		logger.info("admin is logout succussfull");
	}

	
}
