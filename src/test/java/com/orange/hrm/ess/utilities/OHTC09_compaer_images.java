package com.orange.hrm.ess.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class OHTC09_compaer_images {

	
	public boolean Compare_Image(WebDriver driver,WebElement CapturImg, String file) throws IOException {
		
		BufferedImage expected_img=ImageIO.read(new File(file));
		
		Screenshot capture_img=new AShot().takeScreenshot(driver, CapturImg);
		BufferedImage actuall_img=capture_img.getImage();
		
		ImageDiffer differ=new ImageDiffer();
		ImageDiff diff=differ.makeDiff(expected_img, actuall_img);
		
		if(diff.hasDiff()==true) {
			
			return false;
		}else
			
		return true;
	}
	
	
	public void capture_new_img(WebDriver driver,WebElement element, String file) {
		
		Screenshot capture_img=new AShot().takeScreenshot(driver, element);
		
		try {
			
			ImageIO.write(capture_img.getImage(), "png", new File(file));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		File f = new File(file);
		
		if(f.exists()) {
			
			System.out.println("Image File is captured");
		}else {
			
			System.out.println("Image File is not capture");
		}
		
	}
}
