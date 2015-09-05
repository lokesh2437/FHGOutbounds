package com.retailsols.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

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
		PreparedStatement ps_msg=null;
		PreparedStatement ps_msg_ins=null;
		try {
			
			// loading resources
			layout = new SimpleLayout();
			appender = new FileAppender(layout, "C:\\fhg\\Item-Message-Migrate.LOG", true);

			logger.addAppender(appender);
			logger.info(":: Item Image Upload Started :: "+new java.util.Date().toString());
			properties=new Properties();
			properties.load(new FileInputStream(new File("C:\\fhg\\parameters.properties")));

			// end loading resources
			con=DBUtility.getConnection(properties, logger);
			ps_msg=con.prepareStatement("SELECT ID,STR_AGG(ID) AS MSG FROM XXFHG_POS_ATTRIBS GROUP BY ID");
			ps_msg_ins=con.prepareStatement("INSERT INTO XXFHG_POS_MSG_REF(ID_MSG,NA_MSG_DPLY,ITEM_ID) VALUES(MSG_SEQ.NEXTVAL,?,?)");
			ResultSet rs_ms=ps_msg.executeQuery();
			while(rs_ms.next()){
				System.out.println("Processing Message for Item : "+rs_ms.getString("ID") + "Encoded Message : "+rs_ms.getString("MSG") +" and Message is : "+MessageUtil.getOrigMessage(rs_ms.getString("MSG")));
				ps_msg_ins.setString(1, MessageUtil.getOrigMessage(rs_ms.getString("MSG")));
				ps_msg_ins.setString(2, rs_ms.getString("ID"));
				ps_msg_ins.executeUpdate();			
			}
			
		} catch (Exception e) {
			logger.error("",e);
		}
		finally{
			try {
				ps_msg.close();
				ps_msg_ins.close();
			} catch (Exception e2) {
				// TODO: handle exception
				logger.error("", e2);
			}
		}

	}

}
