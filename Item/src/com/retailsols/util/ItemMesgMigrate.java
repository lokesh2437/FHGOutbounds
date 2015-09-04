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

public class ItemMesgMigrate {

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
			con=DBUtility.getConnection(properties, logger);
			PreparedStatement ps_msg=con.prepareStatement("");
			
		} catch (Exception e) {
			logger.error("",e);
		}

	}

}
