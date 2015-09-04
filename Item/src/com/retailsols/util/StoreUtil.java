package com.retailsols.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.retailsols.db.SQLConstants;

public class StoreUtil {

	public static ArrayList<String> getStoreIDs(Connection con, Logger logger){
		ArrayList<String> stores=new ArrayList<String>();
		try {
			PreparedStatement ps_stores=con.prepareStatement(SQLConstants.getStoreIds);
			ResultSet rs=ps_stores.executeQuery();
			while(rs.next()){
				stores.add(rs.getString("ID_STR_RT"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("", e);
		}
		return stores;
	}
}
