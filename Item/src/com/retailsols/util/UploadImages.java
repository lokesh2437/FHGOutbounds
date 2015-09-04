

package com.retailsols.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import com.retailsols.db.DBUtility;
import com.retailsols.jaxb.beans.ItemImport;
import com.retailsols.main.ItemImportGenerator;

public class UploadImages {

	static Logger logger = Logger.getLogger(ItemImportGenerator.class.getName());
	static Layout layout = null;
	static Appender appender=null;
	ItemImport itemImport=null;
	static Properties properties=null;
	static Connection con=null;
	
	public static void main(String[] args) {
		try {
			
			// loading resources
			layout = new SimpleLayout();
			appender = new FileAppender(layout, "C:\\fhg\\Item-Upload.LOG", true);

			logger.addAppender(appender);
			logger.info(":: Item Image Upload Started :: "+new java.util.Date().toString());
			properties=new Properties();
			properties.load(new FileInputStream(new File("C:\\fhg\\parameters.properties")));

			// end loading resources
			String filenmaes=properties.getProperty("itemdir");
			String[] farr=filenmaes.split(",");
			Connection conn=DBUtility.getConnection(properties, logger);
			PreparedStatement ps_img=conn.prepareStatement("INSERT INTO XXFHG_POS_ITEM_IMG(ID,IMAGE) VALUES(?,?)");
			for(int j=0;j<farr.length;j++){
			File file=new File("X:/"+farr[j]);
			String[] extensions = new String[] { "jpg", "png", "bmp"};
			List<File> arr=(List<File>)FileUtils.listFiles(file,extensions,true);
			
			for(int i=0;i<arr.size();i++){
				
			FileInputStream fis=new FileInputStream(arr.get(i));
			logger.info("Uploading Image : " +arr.get(i) +" of Size : " +fis.available());
			System.out.println("Uploading Image : " +arr.get(i) +" of Size : " +fis.available());	
			
			
			ps_img.setString(1, arr.get(i).getName().substring(0,arr.get(i).getName().lastIndexOf(".")));
			ps_img.setBinaryStream(2,fis,fis.available());
			ps_img.executeUpdate();
			
			fis.close();
			}
			
			}
			ps_img.close();
			conn.close();
		} catch (Exception e) {
			logger.error("",e);
		}
		logger.info(":: Item Image Upload Completed :: "+new java.util.Date().toString());
	}

}
