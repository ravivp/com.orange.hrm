package com.orange.hrm.ess.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.orange.hrm.ess.module.testcase.OHT00_orange_hrm_ess_base;

public class OHTC05_orange_hrm_ess_listner extends TestListenerAdapter{ //org.testng.ITestNGListener

	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extents;
	public ExtentTest logg;
	
	
	public void onStart(ITestContext tescontext) {
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportname="test-Report"+timestamp+"myReport.html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/reports/myReport.html");
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		htmlReporter.config().setDocumentTitle("automation report");
		htmlReporter.config().setReportName("functional testing");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		
		extents=new ExtentReports();
		extents.attachReporter(htmlReporter);
		extents.setSystemInfo("HostName","localhost");
		extents.setSystemInfo("Environemnt", "QA");
		extents.setSystemInfo("user","ravi");
		extents.setSystemInfo("project name", "employeeapi");
	}
	
	public void onTestSuccess(ITestResult iresult) {
		
		logg=extents.createTest(iresult.getName());
		logg.log(Status.PASS, MarkupHelper.createLabel(iresult.getName(), ExtentColor.GREEN));
		
	}
	
	 public void onTestFailure(ITestResult iresult) {
		 
		 
		 logg=extents.createTest(iresult.getName());
		 logg.log(Status.FAIL, MarkupHelper.createLabel(iresult.getName(), ExtentColor.RED));
		//log(Status.FAIL, iresult.getThrowable());
		 
		// ITestContext context=iresult.getTestContext();
		// WebDriver driver = (WebDriver) context.getAttribute("driver");
		 
		/* String MethodName=iresult.getName();
		 try {
			OHT00_orange_hrm_ess_base.takescreenshot(driver,MethodName);
		} catch (IOException e) {
			
			e.printStackTrace();
		}*/
	 }
	 
	 
    public void onTestSkiped(ITestResult iresult) {
		 
		 logg=extents.createTest(iresult.getName());
		 logg.log(Status.SKIP, MarkupHelper.createLabel(iresult.getName(), ExtentColor.ORANGE));
	 }
    
    public void onFinish(ITestContext context) {
    	extents.flush();
    }
}
