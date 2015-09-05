package com.retailsols.main;

import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.retailsols.db.SQLConstants;
import com.retailsols.jaxb.beans.ChangeTypeSubtype;
import com.retailsols.jaxb.beans.DisplayMessageType;
import com.retailsols.jaxb.beans.LanguageType;
import com.retailsols.jaxb.beans.LocalizedMessageDescriptionType;
import com.retailsols.jaxb.beans.PreloadDataType;
import com.retailsols.jaxb.beans.SupplierType;
import com.retailsols.jaxb.beans.UOMType;

public class GeneratePreloadData {

	public static PreloadDataType getPreloadData(Logger logger,Connection conn){
		PreloadDataType dataType=new PreloadDataType();
		
		try {
			// generate uom start
			ArrayList<UOMType> uomTypes=new ArrayList<UOMType>();
			PreparedStatement ps_uom=conn.prepareStatement(SQLConstants.getUOMs);
			ResultSet rs_uom=ps_uom.executeQuery();
			while(rs_uom.next()){
				System.out.println("Processing UOM : "+rs_uom.getString("CODE"));
				UOMType type=new UOMType();
				type.setChangeType(ChangeTypeSubtype.UPS);
				type.setCode(rs_uom.getString("CODE"));
				type.setIsDefault((rs_uom.getString("ISDEFAULT").equals("0")?true:false));
				type.setName(rs_uom.getString("NAME"));
				type.setDescription(rs_uom.getString("DESCRIPTION"));
				uomTypes.add(type);				
			}
			dataType.getUOM().addAll(uomTypes);
			// uom end
			
			// supplier start
			ArrayList<SupplierType> supplier=new ArrayList<SupplierType>();
			PreparedStatement ps_supp=conn.prepareStatement(SQLConstants.getSuppliers);
			ResultSet rs_supp=ps_supp.executeQuery();
			while(rs_supp.next()){
				System.out.println("Processing Supplier : "+rs_supp.getString("ID"));
				SupplierType supplierType=new SupplierType();
				supplierType.setChangeType(ChangeTypeSubtype.UPS);
				supplierType.setID(rs_supp.getString("ID"));
				supplierType.setName(rs_supp.getString("NAME"));
				supplier.add(supplierType);
				
			}
			dataType.getSupplier().addAll(supplier);
			// supplier end
			
			// messages start
			ArrayList<DisplayMessageType> messages=new ArrayList<DisplayMessageType>();
			PreparedStatement ps_msg=conn.prepareStatement(SQLConstants.getMessages);
			ResultSet rs_msg=ps_msg.executeQuery();
			while(rs_msg.next()){
				System.out.println("Processing Message :"+rs_msg.getInt("ID_MSG"));
				DisplayMessageType msgtype=new DisplayMessageType();
				msgtype.setChangeType(ChangeTypeSubtype.UPS);
				msgtype.setID(rs_msg.getInt("ID_MSG"));
				LocalizedMessageDescriptionType desctype=new LocalizedMessageDescriptionType();
				desctype.setLanguage(LanguageType.EN);
//				StringBuilder sb = new StringBuilder();
//				Reader reader=rs_msg.getClob("MESSAGE").getCharacterStream();
//				BufferedReader br = new BufferedReader(reader);
//
//		        String line;
//		        while(null != (line = br.readLine())) {
//		            sb.append(line);
//		        }
//		        br.close();
				desctype.setValue(rs_msg.getString("NA_MSG_DPLY"));
				
				msgtype.getMsgText().add(desctype);
				messages.add(msgtype);
			}
			dataType.getMessage().addAll(messages);
			// messages end
			ps_uom.close();
			ps_supp.close();
			ps_msg.close();
			
		} catch (Exception e) {
			logger.error("", e);
		}
		return dataType;
	}
}
