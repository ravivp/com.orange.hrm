package com.orange.hrm.ess.utilities;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class OHTC04_upload_img_sikuli {

	static String  imgfilepath=System.getProperty("user.dir")+"/windows_screenshot_imges/"; //path of images taken form application
	//static String  inputfilepath="C:\\Users\\DELL\\Desktop\\upload_sikuli\\";//it is path of upload files
	
	static String  inputfilepath=System.getProperty("user.dir")+"/windows_screenshot_imges/";
	static String upload_save_button=System.getProperty("user.dir")+"/windows_screenshot_imges/photo_uploaded_save_button.PNG";
	
	public static  Screen s;
	public static void upload() throws FindFailed, InterruptedException {
		
		s = new Screen();
		Pattern fileInputTextBox = new Pattern(imgfilepath+"filetextbox.PNG");//screen taken from application
		Pattern openbutton= new Pattern(imgfilepath+"openbutton.PNG");
		Pattern click_save_button=new Pattern(upload_save_button);
		
		
		Thread.sleep(2000);
		s.wait(fileInputTextBox, 20);
		s.type(fileInputTextBox, inputfilepath+"orange_hrm_photo.jpg");//type has screenshot image from app and filepth of upload file
		s.click(openbutton);
		
		Thread.sleep(2000);
		s.click(click_save_button);
	}
	
	public static void  upload_documents(String fileNamw) throws InterruptedException, FindFailed {
		
		s=new Screen();
		Pattern fileInputTextBox = new Pattern(inputfilepath+"filetextbox.PNG");
		Pattern openbutton=new Pattern(inputfilepath+"openbutton.PNG");
		
		Thread.sleep(2000);
		s.wait(fileInputTextBox, 30);
		s.type(fileInputTextBox, inputfilepath+fileNamw);
		s.click(openbutton);
		
		Thread.sleep(2000);
		
	}
}
