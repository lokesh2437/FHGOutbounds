package com.retailsols.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

public class Test8i {

	public static void main(String[] args) {
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@192.168.5.9:1521:ORCL","FHGStaging","FHG1234");
			Connection conn1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:rispl","pos00216","pos00216");
			PreparedStatement ps=conn.prepareStatement(" select ID_CT,INV_NUM,INV_AMT,BAL_DUE,INV_DATE,INV_STS from XXFHG_CC_PEND_INV");
			ResultSet rs=ps.executeQuery();
			ArrayList<InvoiceBean> list=new ArrayList<InvoiceBean>();
			while(rs.next()){
				InvoiceBean bean=new InvoiceBean();
				bean.setId_ct(rs.getString("ID_CT"));
				bean.setInv_num(rs.getInt("INV_NUM"));
				bean.setInv_amt(rs.getInt("INV_AMT"));
				bean.setBal_due(rs.getInt("BAL_DUE"));
				bean.setInv_date(rs.getString("INV_DATE"));
				bean.setInv_sts(rs.getString("INV_STS"));
				list.add(bean);
			}
			
			Iterator<InvoiceBean> itr=list.iterator();
			PreparedStatement ps1=conn1.prepareStatement("insert into CC_INV_PEND values(?,?,?,?,?,?)");
			while(itr.hasNext()){
				InvoiceBean bean=(InvoiceBean)itr.next();
				ps1.setString(1, bean.getId_ct());
				ps1.setInt(2, bean.getInv_num());
				ps1.setInt(3, bean.getInv_amt());
				ps1.setInt(4, bean.getBal_due());
				ps1.setString(5,bean.getInv_date());
				ps1.setString(6, bean.getInv_sts());
				ps1.executeUpdate();
				
			}
			
			System.out.println("Sucess");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class InvoiceBean{
	private String id_ct;
	private int inv_num;
	private int inv_amt;
	private int bal_due;
	private String inv_date;
	private String inv_sts;
	public String getId_ct() {
		return id_ct;
	}
	public void setId_ct(String id_ct) {
		this.id_ct = id_ct;
	}
	public int getInv_num() {
		return inv_num;
	}
	public void setInv_num(int inv_num) {
		this.inv_num = inv_num;
	}
	public int getInv_amt() {
		return inv_amt;
	}
	public void setInv_amt(int inv_amt) {
		this.inv_amt = inv_amt;
	}
	public int getBal_due() {
		return bal_due;
	}
	public void setBal_due(int bal_due) {
		this.bal_due = bal_due;
	}
	public String getInv_date() {
		return inv_date;
	}
	public void setInv_date(String inv_date) {
		this.inv_date = inv_date;
	}
	public String getInv_sts() {
		return inv_sts;
	}
	public void setInv_sts(String inv_sts) {
		this.inv_sts = inv_sts;
	}
	
}