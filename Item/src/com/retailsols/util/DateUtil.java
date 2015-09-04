package com.retailsols.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtil {
	static XMLGregorianCalendar xmlGrogerianCalendar = null;
	
	public static XMLGregorianCalendar getTodayXMLDate() {
		try {
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(new Date(System.currentTimeMillis()));
			xmlGrogerianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
			xmlGrogerianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			xmlGrogerianCalendar.setFractionalSecond(null);
		} catch (DatatypeConfigurationException e) {			
			e.printStackTrace();
		}
		return xmlGrogerianCalendar;
	}

	public static XMLGregorianCalendar getXMLDate(java.util.Date date) {
		try {
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(date);
			xmlGrogerianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
			xmlGrogerianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			xmlGrogerianCalendar.setFractionalSecond(null);
		} catch (DatatypeConfigurationException e) {			
			e.printStackTrace();
		}
		return xmlGrogerianCalendar;
	}
	
	public static StringBuffer getMOMFileDate(){
		StringBuffer jarname=null;
		try {
			Date d=new java.util.Date();
			Date today = new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMDD");
			SimpleDateFormat sdf1=new SimpleDateFormat("hhmmss");
			jarname= new StringBuffer("/MOM-corp-"+sdf.format(today)+"_"+sdf1.format(today)+"-1"+".jar");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jarname;
	}
	public static void main(String[] args) {
		System.out.println(DateUtil.getMOMFileDate());
	}
}
