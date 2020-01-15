package com.orange.hrm.ess.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class getData {

	
	static int count;
	
    static int customerID;
	
	static String userRole;
	static String employeename;
	
	static String status;
	
	public static void main(String[] args) throws IOException {
		
		//System.out.println(OHTC03_read_excel_data.getdata_from_map(1,"104"));//ESS_Employee
		
		List<Map<String,String>> list=OHTC03_read_excel_data.getmultiple();
		
		
		list.forEach(items->{
			
		for(Map.Entry<String, String> map:items.entrySet()) {
		
			System.out.println("keys : "+map.getKey()+" and values :"+map.getValue());
		//	System.out.println("values : "+map.getValue());
		}
		});
		
		List<String> listItems=OHTC03_read_excel_data.employee_details();
		
		listItems.forEach(lists->{
			
			System.out.println("list of ess employee: "+lists);
		});
		
		List<String> allEmployee_name=OHTC03_read_excel_data.get_single_Employee_Coloumn();
		
		allEmployee_name.forEach(all->{
			
			System.out.println("all employee names only*************"+all);
		});
		
		
		/*List<Map<String, String>> getlogin_data=OHTC03_read_excel_data.getmultiple();
		
		for(Map<String, String> login:getlogin_data) {
			
		Set<Map.Entry<String, String>> key_value=	login.entrySet();
			for(Map.Entry<String, String> login_details:key_value) {
				
				System.out.println("username : "+login_details.getKey()+"and passowrd : "+login_details.getValue());
			}
		}*/
		
		
	}
	
	
	
  public static List<pojo_employee> getlistdata(List<String> listItems){
		
		
			List<pojo_employee> Employee_List=new ArrayList<pojo_employee>();
		
		for(int i=0;i<listItems.size();i++) {
			
			
			if(i==count) {
				
				customerID=Integer.valueOf(listItems.get(i));
				//Ess.setCustomerID(Integer.valueOf(listItems.get(i)));
				
				
				count++;
			}else if(i==1) {
				userRole=listItems.get(i);
				//Ess.setUserRole(listItems.get(i));
				count++;
				
			}else if(i==2) {
				employeename=listItems.get(i);
				//Ess.setEmployeename(listItems.get(i));
				count++;
			}else if(i==3) {
				status=listItems.get(i);
				//Ess.setStatus(listItems.get(i));
				count++;
			}else {
				
				count=0;
			}
			
			
			Employee_List.add(new pojo_employee(customerID,userRole,employeename,status));
			
		}
		
	

		return Employee_List;
	}
		
		
}
