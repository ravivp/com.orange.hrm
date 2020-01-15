package com.orange.hrm.ess.utilities;

public class pojo_employee {

private int customerID;
	
	private String userRole;
	private String employeename;
	
	private String status;
	
	
	public pojo_employee(int customerID, String userRole, String employeename,
			String status) {
		super();
		this.customerID = customerID;
		this.userRole = userRole;
		this.employeename = employeename;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public int getCustomerID() {
		return customerID;
	}

	public String getUserRole() {
		return userRole;
	}
		
	@Override
	public String toString() {
		return "OHTC01_orange_hrm_ess_verification_pojo [customerID=" + customerID + ", userRole=" + userRole
				+ ", employeename=" + employeename + ", status=" + status + "]";
	}

	public String getEmployeename() {
		return employeename;
	}
}
