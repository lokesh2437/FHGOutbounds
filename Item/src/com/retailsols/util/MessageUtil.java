package com.retailsols.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MessageUtil {
	
	public static String getOrigMessage(String enc_msg){
		String org_msg="";
		try {
			
			String msgprts[]=enc_msg.split(",");
			for(String sub1 : msgprts){
				String subparts[]=sub1.split(":");
					if(subparts[0].equals("3"))
						org_msg=org_msg+","+"GW:"+subparts[2];
					if(subparts[0].equals("4"))
						org_msg=org_msg+","+"SW:"+subparts[2];
					if(subparts[0].equals("5"))
						org_msg=org_msg+","+"PW:"+subparts[2];
					
					if(subparts[0].equals("1") && subparts[1].equals("1"))
						org_msg=org_msg+","+"D:"+subparts[2];
					if(subparts[0].equals("1") && subparts[1].equals("2"))
						org_msg=org_msg+","+"E:"+subparts[2];
					if(subparts[0].equals("1") && subparts[1].equals("3"))
						org_msg=org_msg+","+"R:"+subparts[2];
					if(subparts[0].equals("1") && subparts[1].equals("4"))
						org_msg=org_msg+","+"S:"+subparts[2];
					if(subparts[0].equals("1") && subparts[1].equals("5"))
						org_msg=org_msg+","+"LS:"+subparts[2];
					if(subparts[0].equals("1") && subparts[1].equals("6"))
						org_msg=org_msg+","+"PS:"+subparts[2];
					if(subparts[0].equals("1") && subparts[1].equals("7"))
						org_msg=org_msg+","+"YS:"+subparts[2];
					if(subparts[0].equals("1") && subparts[1].equals("8"))
						org_msg=org_msg+","+"MS:"+subparts[2];
					if(subparts[0].equals("1") && subparts[1].equals("9"))
						org_msg=org_msg+","+"OS:"+subparts[2];
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return org_msg;
	}
	
	public static int getMessageID(String itemid,Connection conn){
		int id=0;
		try {
			PreparedStatement ps=conn.prepareStatement("SELECT ID_MSG FROM XXFHG_POS_MSG_REF WHERE ITEM_ID=?");
			ps.setString(1, itemid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				id=rs.getInt("ID_MSG");
			}
			else{
				id=0;
			}
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return id;
	}
}


