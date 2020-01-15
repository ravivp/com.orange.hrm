package com.orange.hrm.ess.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class OHTC02_read_properties_file {
	
	Properties prop;
	String path="./config/config.properties";
	FileInputStream input;
	
	public OHTC02_read_properties_file() {
		
		File file = new File(path);
		
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		try {
			prop=new Properties();
			prop.load(input);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public  List<String> getCredential() {
		
		List<String> list = new ArrayList<>();
		String url=prop.getProperty("BaseURL");
		String username=prop.getProperty("Username");
		String password=prop.getProperty("Password");
		String chrome=prop.getProperty("chrome");
		String firebox=prop.getProperty("firebox");
		String ie=prop.getProperty("ie");
		list.add(url);
		list.add(username);
		list.add(password);
		list.add(chrome);
		list.add(firebox);
		list.add(ie);
		return list;
	}
	
	public String geturl() {
		String url=prop.getProperty("BaseURL");
		return url;
	}
	
	public String getusername() {
		String username=prop.getProperty("Username");
		return username;
	}
	
	public String getpassword() {
		String password=prop.getProperty("Password");
		return password;
	}
	
	public String getchrome() {
		String chrome=prop.getProperty("chrome");
		return chrome;
	}
	
	public String getfirebox() {
		String firebox=prop.getProperty("firefox");
		return firebox;
	}
	
	public String getie() {
		String ie=prop.getProperty("ie");
		return ie;
	}
	
public static String takescreenshot(WebDriver gdriver,String methodName) throws IOException {
		
		String dataformate=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		File screenshotFile = ((TakesScreenshot)gdriver).getScreenshotAs(OutputType.FILE);
		
		String destination= "./screenshot/"+methodName+".png";
		File dest = new File(destination);
		
		FileUtils.copyFile(screenshotFile,dest );
		
		return destination;
		
	}
}
