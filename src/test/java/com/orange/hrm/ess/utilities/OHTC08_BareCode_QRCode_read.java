package com.orange.hrm.ess.utilities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class OHTC08_BareCode_QRCode_read {

	
	public void readBareCode(String barecodeURL,String CodeValue) throws NotFoundException, IOException {
		
		URL url=new URL(barecodeURL);
				
				
				BufferedImage image=ImageIO.read(url);
				
				LuminanceSource luminancesource=new BufferedImageLuminanceSource(image);
				
				BinaryBitmap map=new BinaryBitmap(new HybridBinarizer(luminancesource));
				
				Result result=new MultiFormatReader().decode(map);
				
				System.out.println("barecode value :"+result +" & code value ->>>>>> "+CodeValue);
			}

}
