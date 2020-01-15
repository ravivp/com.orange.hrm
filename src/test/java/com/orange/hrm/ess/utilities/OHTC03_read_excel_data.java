package com.orange.hrm.ess.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class OHTC03_read_excel_data {

	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFCell cell;
	public static XSSFRow row;
 
	
	public static String xslFileName="./src/test/java/com/orange/hrm/ess/xl/orange_ess_user.xlsx";
	public static int getRowCount(String xlFileName, String xlSheetName) throws IOException {
		
		fi =new FileInputStream(xlFileName);
		
		wb=new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheetName);
		int rowCount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}
	
	
	public static int getCellCount(String xlFileName,String xlSheetName, int rownum) throws IOException {
		
       fi =new FileInputStream(xlFileName);
		
		wb=new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheetName);
		row = ws.getRow(rownum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		
		
		return cellCount;
	}
	
	
	public static String getCelldata(String xlFileName,String xlSheetName, int rownum, int colnum) throws IOException {
		
		 
		    fi =new FileInputStream(xlFileName);
			
			wb=new XSSFWorkbook(fi);
			ws = wb.getSheet(xlSheetName);
			row = ws.getRow(rownum);
		    cell=row.getCell(colnum);
		    
		    String data;
		    
		    
		    try {
		    	
		    	DataFormatter format = new DataFormatter();
		    	String celldata = format.formatCellValue(cell);
		    	return celldata;
		    }catch(Exception e) {
		    	
		    	data="";
		    }
		    wb.close();
		    fi.close();
		
		return data;
	}
	
	public static void setCelldata(String xlFileName,String xlSheetName, int rownum, int colnum,String data) throws IOException {
		
		fi = new FileInputStream(xlFileName);
		wb = new XSSFWorkbook(fi);
		ws=wb.getSheet(xlSheetName);
		row  = ws.getRow(rownum);
		cell = row.getCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(xlFileName);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
		
	}
	
	public static String[][] getData(String file,String sheet) throws IOException{
		
		int row = OHTC03_read_excel_data.getRowCount(file, sheet);
		int column = OHTC03_read_excel_data.getCellCount(file, sheet, row);
		
		String data[][] = new String[row][column];
		
		for(int rowCount=1;rowCount<=row;rowCount++) {
			
			for(int colCount = 0;colCount<column;colCount++) {
				
				data[rowCount-1][colCount] = getCelldata(file, sheet, rowCount, colCount);
			}
		}
		return data;
	}
	
	
	public static List storedata(String xlFileName, String xlSheetName) throws IOException {
		
		
		List myList = new ArrayList();
		 
			 fi =new FileInputStream(xlFileName);
				
				wb=new XSSFWorkbook(fi);
				ws = wb.getSheet(xlSheetName);
		        HashMap<Integer, OHTC07_Store_data_excelTo_list> mp= new HashMap<Integer, OHTC07_Store_data_excelTo_list>();
		        //Create Workbook instance holding reference to .xlsx file
		        

		        
		        		
		       
		         System.out.println("");

		        //Iterate through each rows one by one
		        Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = ws.iterator();
		        while (rowIterator.hasNext()) 
		        {
		        	org.apache.poi.ss.usermodel.Row row = rowIterator.next();
		        	Iterator<org.apache.poi.ss.usermodel.Cell> cell = row.cellIterator();
		            
		            //For each row, iterate through all the columns
		           
		            while (cell.hasNext()) 
		            {
		            	Object data=cell.next();
		            	
		            	if(data.equals(""))
		            	
		            	if (((org.apache.poi.ss.usermodel.Cell) cell).getCellTypeEnum() == CellType.STRING) 
		            	{
		            	System.out.print(((org.apache.poi.ss.usermodel.Cell) cell).getCellTypeEnum());
		            	myList.add(((org.apache.poi.ss.usermodel.Cell) cell).getStringCellValue());
		            	} 
		            	
		            }


		           
		        }
		        
		        
		        wb.close();
		        
		        fi.close();
		        
		        
		    
		 return myList;
		
	}
	
	public static Map<Integer,Map<String,String>> read_data_fromEXcel() throws IOException{
		
		String xlSheetName="login";
		
		fi = new FileInputStream(xslFileName);
		wb = new XSSFWorkbook(fi);
		ws=wb.getSheet(xlSheetName);
		
		Map<Integer,Map<String,String>> suparMap=new HashMap<Integer,Map<String,String>>();
		
		Map<String,String> mymap=new HashMap<String,String>();
		
		for(int i=1;i<ws.getLastRowNum()+1;i++) {
			
			String cellkey=null;
			row = ws.getRow(i);
			if(row.getCell(0).getCellTypeEnum()==CellType.NUMERIC) {
				
			int num=(int) row.getCell(0).getNumericCellValue();// String cellkey=row.getCell(0).getStringCellValue()
			cellkey=num+"";
			
			}else if(row.getCell(0).getCellTypeEnum()==CellType.STRING) {
				
				cellkey=row.getCell(0).getStringCellValue();
			}else if(row.getCell(0).getCellTypeEnum()==CellType.BOOLEAN) {
				
				boolean boolean_cellkey=row.getCell(0).getBooleanCellValue();
				cellkey=boolean_cellkey+"";
			}
			
			
			
			int colomn = row.getLastCellNum();
			
			for(int j=1;j<colomn;j++) {
				
				String value=row.getCell(j).getStringCellValue();
				mymap.put(cellkey, value);
				
			}
			
			
			suparMap.put(1, mymap);
			
		}
		
		wb.close();
		fi.close();
		return suparMap;
	}
	
	
	public static String getdata_from_map(Integer supercellkey,String keys) throws IOException {
		
		
		Map<Integer,Map<String, String>> getvalue=read_data_fromEXcel();
		
		Map<String, String> myvalue=getvalue.get(supercellkey);
		
		String key=myvalue.get(keys);
		
		return key;
	}
	
	public static List<Map<String,String>> getmultiple() throws IOException{
		
		
		String xlSheetName="ESS_Employee";
		
		String sheetname="login";
		fi = new FileInputStream(xslFileName);
		wb = new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetname);
		
		
		List<Map<String,String>> mylist=new ArrayList<>();
		
		int rows=ws.getLastRowNum();
		row = ws.getRow(0); 
		
		for(int CellNum=1;CellNum<row.getLastCellNum();CellNum++) {
			
			Map<String, String> mymap=new HashMap<>();
			
			for(int RowNum=1;RowNum<rows;RowNum++) {
				
				Row r=CellUtil.getRow(RowNum, ws);
				String key = null;
				
				if(CellUtil.getCell(r, 0).getCellTypeEnum()==CellType.NUMERIC) {
					
					Integer numeric=(int) CellUtil.getCell(r, 0).getNumericCellValue();
					 key=numeric+"";
					
				}else if(CellUtil.getCell(r, 0).getCellTypeEnum()==CellType.STRING) {
					
					key=CellUtil.getCell(r, 0).getStringCellValue();
					
				}else if(CellUtil.getCell(r, 0).getCellTypeEnum()==CellType.BOOLEAN) {
					
					boolean booleanValue =CellUtil.getCell(r, 0).getBooleanCellValue();
					key=booleanValue+"";
				}
				
				
				String value=CellUtil.getCell(r, CellNum).getStringCellValue();
				
				mymap.put(key, value);
				
			}
			
			mylist.add(mymap);
		}
		
		wb.close();
		fi.close();
		return mylist;
	}
	
	//getting all columns data
	public static List<String> employee_details() throws IOException{
		
		
          String xlSheetName="ESS_Employee";
		
		fi = new FileInputStream(xslFileName);
		wb = new XSSFWorkbook(fi);
		ws=wb.getSheet(xlSheetName);
		
		Iterator<Row> rowNum=ws.iterator();
		
		List<String> InnerArray = new ArrayList<>() ;
		
		
		 
		while(rowNum.hasNext()) {
			
			
			Row row1=rowNum.next();
			
			Iterator<Cell> cells= row1.cellIterator();
			
			while(cells.hasNext()) {
				
				Cell cell1=cells.next();
				
				
				if(cell1.getCellTypeEnum()==CellType.STRING) {
					
					if((cell1.getStringCellValue().equals("CustomerID"))||(cell1.getStringCellValue().equals("User Role"))||(cell1.getStringCellValue().equals("Employee Name"))||(cell1.getStringCellValue().equals("Status"))) {
						
						break;
						
					}else {
						String value=cell1.getStringCellValue();
						InnerArray.add(value);
					}
					
					
				}else if(cell1.getCellTypeEnum()==CellType.NUMERIC) {
					
					int num=(int) cell1.getNumericCellValue();
					
					InnerArray.add(String.valueOf(num));
				}else if(cell1.getCellTypeEnum()==CellType.BOOLEAN){
					
					boolean boo=cell1.getBooleanCellValue();
					InnerArray.add(String.valueOf(boo));
				}
				
			
			}
			
		}
			
		return InnerArray;
	}
	
	
	//OHTC01_orange_hrm_ess_verification_pojo  ess=new OHTC01_orange_hrm_ess_verification_pojo();
	
	public static List<OHTC01_orange_hrm_ess_verification_pojo> set_Employee() throws IOException{
		
        String xlSheetName="ESS_Employee";
		
		fi = new FileInputStream(xslFileName);
		wb = new XSSFWorkbook(fi);
		ws=wb.getSheet(xlSheetName);
		
		List<OHTC01_orange_hrm_ess_verification_pojo> setESS=new ArrayList<>();
		
		
		int numRow=ws.getLastRowNum();
		Iterator<Row> row1=ws.iterator();
		while(row1.hasNext()) {
			
			OHTC01_orange_hrm_ess_verification_pojo Ess=new OHTC01_orange_hrm_ess_verification_pojo();
			Row row2=row1.next();
			
			Iterator<Cell> cell1=row2.cellIterator();
			
			while(cell1.hasNext()) {
				
			     
				
			}
			
		}
		wb.close();
		fi.close();
		return setESS;
	}
	
	//// getting single column data 
	public static List<String> get_single_Employee_Coloumn() throws IOException{
		
		 String xlSheetName="ESS_Employee";
			
			fi = new FileInputStream(xslFileName);
			wb = new XSSFWorkbook(fi);
			ws=wb.getSheet(xlSheetName);
			int RowCount =ws.getLastRowNum();
			
			row =ws.getRow(RowCount);
			int cellCount=row.getLastCellNum();
			
			List<String> employee_list = new ArrayList<>() ;
			
			
			for(int NumCell=2;NumCell<3;NumCell++) {
				
				for(int NumRow=1;NumRow<=RowCount; NumRow++) {
					
					Row r=CellUtil.getRow(NumRow, ws);
					
					String Cellvalue=CellUtil.getCell(r, NumCell).getStringCellValue();
					employee_list.add(Cellvalue);
				}
				
				
				
			}
		
		return employee_list;
	}
}
