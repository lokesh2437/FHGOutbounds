package com.retailsols.main;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import com.retailsols.db.DBUtility;
import com.retailsols.jaxb.beans.FillTypeType;
import com.retailsols.jaxb.beans.ItemImport;
import com.retailsols.util.Data_Capture;
import com.retailsols.util.DateUtil;
import com.retailsols.util.FHG_MOM_File_Generator;

public class ItemImportGenerator {

	static Logger logger = Logger.getLogger(ItemImportGenerator.class.getName());
	static Layout layout = null;
	static Appender appender=null;
	ItemImport itemImport=null;
	static Properties properties=null;
	static Connection con=null;
	JAXBContext context = null;
	Marshaller marshal=null; 
	
	// generate xml by marshall
	public void generateXMLByMarshal(){	
		try {
			context = JAXBContext.newInstance(ItemImport.class);
			 marshal= context.createMarshaller();
			 marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);			 
			 marshal.marshal(itemImport, new File(properties.getProperty("itemxmldest")));
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	public void generateItemImportObject(){
		try {
			
			itemImport=new ItemImport();
			itemImport.setPriority(0);
			itemImport.setFillType(FillTypeType.FULL_INCREMENTAL);
			itemImport.setBatch(0);
			itemImport.setVersion("1.0");
			itemImport.setCreationDate(DateUtil.getTodayXMLDate());
			
			// getting DB Connection
			con=DBUtility.getConnection(properties,logger);
			
			int count[]=Data_Capture.checkDataCapture(con, logger);
			System.out.println("New UOM's :: "+count[0] );
			System.out.println("New Suppliers's :: "+count[1] );
			System.out.println("New Messages's :: "+count[2] );
			System.out.println("New Items's :: "+count[3] );
			
			if(count[0] >0 || count[1] > 0 || count[2] > 0 || count[3] > 0){
				itemImport.getItem().addAll(new GenerateItemBeans().generateItemType(logger, con));
				itemImport.setPreloadData(GeneratePreloadData.getPreloadData(logger, con));
				this.generateXMLByMarshal();
				
				FHG_MOM_File_Generator.createMOMFile(new File(properties.getProperty("xmlsrc")), new File(properties.getProperty("momdest")), logger);
				
				FHG_MOM_File_Generator.deleteXmls(new File(properties.getProperty("xmlsrc")), logger);
				
				// preload data capture
				Data_Capture.doPreloadDataCapture(con, logger);
			}
			else{
				System.out.println("No New Items Found");
			}
			
			DBUtility.closeConnection(con, logger);
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("", e);
		}
	}
	
	public static void main(String[] args) {
		try {
			// loading resources
						layout = new SimpleLayout();
						appender = new FileAppender(layout, "C:\\fhg\\integration.LOG", true);
//						appender = new FileAppender(layout, "/home/clone/ORSS/CLONE/DMC-JOBS/DISCOUNTS/Pricing/pricing.LOG", true);
						logger.addAppender(appender);
						logger.info(":: Item Import XML Generation Started :: "+new java.util.Date().toString());
						properties=new Properties();
						properties.load(new FileInputStream(new File("C:\\fhg\\parameters.properties")));
//						properties.load(new FileInputStream(new File("/home/clone/ORSS/CLONE/DMC-JOBS/DISCOUNTS/Pricing/parameters.properties")));
						// end loading resources
						
						// start generating the xml & MOM
						ItemImportGenerator generator=new ItemImportGenerator();
						generator.generateItemImportObject();
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}
