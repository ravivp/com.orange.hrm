package com.orange.hrm.ess.module.testcase;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.orange.hrm.ess.module.list.OH07_menu_new_window_browser;

public class OHT04_orange_hrm_ess_SwitchTo_NewWindow extends OHT00_orange_hrm_ess_base {

	public OH07_menu_new_window_browser newBrowser;
	
	@Test
	public void windowHandles() throws InterruptedException {
		
		newBrowser=new OH07_menu_new_window_browser(driver);
		newBrowser.new_Windows();
		
		Set<String> winows_Set=driver.getWindowHandles();
		
		Iterator<String> it=winows_Set.iterator();
		
		String parent_Window=it.next();
		
		logger.info("parent_Window id is **********************->"+parent_Window);
		
		String child_window=it.next();
		logger.info("child_window id is ******************* ->"+child_window);
		
		driver.switchTo().window(child_window);
		
		Thread.sleep(2000);;
		
		driver.switchTo().window(parent_Window);
		
		Thread.sleep(3000);;
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"t");
		
		logger.info("new tab is succussfully opened**************");
	}
}
