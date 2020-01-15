package com.orange.hrm.ess.module.testcase;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import com.orange.hrm.ess.module.list.OD00_orange_hrm_admin_module;
import com.orange.hrm.ess.module.list.OH00_orange_hrm_login_module;
import com.orange.hrm.ess.utilities.OHTC01_orange_hrm_ess_verification_pojo;
import com.orange.hrm.ess.utilities.OHTC03_read_excel_data;

public class verifydata extends OHT00_orange_hrm_ess_base{

	public OD00_orange_hrm_admin_module adminModel;
	public OH00_orange_hrm_login_module loginModel;
	public OHTC01_orange_hrm_ess_verification_pojo pojo;
	
	@Test
	public void create_verify_status() throws IOException, InterruptedException {
	logger.info("ESS user is created by Admin ");
	pojo = new OHTC01_orange_hrm_ess_verification_pojo();
	loginModel=new OH00_orange_hrm_login_module(driver);
	loginModel.logincredentional(null,null);//"Admin", "admin123"
	
	
	
	adminModel=new OD00_orange_hrm_admin_module(driver);
	
	List<String> employeeList=OHTC03_read_excel_data.get_single_Employee_Coloumn();
	
	employeeList.forEach(list->{
		
		logger.info("getting Employee List from excle **************:"+list);
	});
	
	String employeeName=adminModel.create_ess_user_using_existing_user(employeeList);
	logger.info("employee name from application is :"+employeeName);
	
	//adminModel.adim(createESS_username, employeeName);

	}
}
