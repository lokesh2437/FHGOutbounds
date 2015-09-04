package com.retailsols.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.retailsols.db.SQLConstants;
 
public class Data_Capture {

	public static void doPreloadDataCapture(Connection con, Logger logger){
		
		try {
			
			// Update flag
			PreparedStatement ps_uom=con.prepareStatement(SQLConstants.updateUOM);
			PreparedStatement ps_supp=con.prepareStatement(SQLConstants.updateSupplier);
			PreparedStatement ps_mesg=con.prepareStatement(SQLConstants.updateMessages);
			PreparedStatement ps_itm=con.prepareStatement(SQLConstants.updateItem);
			
			ps_uom.executeUpdate();	
			ps_supp.executeUpdate();
			ps_mesg.executeUpdate();
			ps_itm.executeUpdate();
			
			ps_uom.close();
			ps_supp.close();
			ps_mesg.close();
			ps_itm.close();
			
		} catch (Exception e) {
			logger.error("", e);
		}
		
		
	}
	
	public static int[] checkDataCapture(Connection con,Logger logger){
		
		int count[] = new int[4];
		try {
			PreparedStatement ps_check_data_capture=con.prepareStatement(SQLConstants.checkDataCapture);
			ResultSet rs=ps_check_data_capture.executeQuery();
			if(rs.next()){
				count[3]=rs.getInt(1);
			}
			
			PreparedStatement ps_check_UOM=con.prepareStatement(SQLConstants.checkNewUOM);
			ResultSet rs_UOM=ps_check_UOM.executeQuery();
			if(rs_UOM.next()){
				count[0]=rs_UOM.getInt(1);
			}
			
			PreparedStatement ps_check_supp=con.prepareStatement(SQLConstants.chcekMewSupplier);
			ResultSet rs_supp=ps_check_supp.executeQuery();
			if(rs_supp.next()){
				count[1]=rs_supp.getInt(1);
			}
			
			PreparedStatement ps_check_msg=con.prepareStatement(SQLConstants.checkNewMesgs);
			ResultSet rs_msg=ps_check_msg.executeQuery();
			if(rs_msg.next()){
				count[2]=rs_msg.getInt(1);
			}
			
			ps_check_data_capture.close();
		} catch (Exception e) {
			logger.error("", e);
		}
		
		return count;
	}
}
