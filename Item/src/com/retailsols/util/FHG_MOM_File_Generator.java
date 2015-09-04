package com.retailsols.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.jar.Attributes;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;



public class FHG_MOM_File_Generator 
{
	public static String storeid="corp";
	public static Manifest getManifestFile(File xmlsourceFolder)throws Exception
	{
		try
		{
			
			 FileFilter filefilter = new FileFilter() {
				 
			        public boolean accept(File file) {
			            //if the file extension is .xml return true, else false
			            if (file.getName().endsWith(storeid+".xml")) {
			                return true;
			            }
			            return false;
			        }
			    };
			File[] xmls=xmlsourceFolder.listFiles(filefilter);
		  	if(xmls.length==0)
		  	{
		  		return null;
		  	}
		  	else{
		Manifest manifest = new Manifest();
		int filecount=1,l=0;
		String filelist="";
		manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
	  	manifest.getMainAttributes().put(new Attributes.Name("BatchID"), "1");
	  	manifest.getMainAttributes().put(new Attributes.Name("StoreID"), "CORP");
	  	
	  	
	  	/**
		*   making a string with the file names in an array.
		*/
	  	 while(l<xmls.length)
			{
			   if(xmls[l]!=null)
				{
				   filelist=filelist+";"+xmls[l];
				}
				l++;
			}
	  	Arrays.sort(xmls, new Comparator<File>(){
	  	    public int compare(File f1, File f2)
	  	    {
	  	        return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
	  	    } });
	  	 /**
		 *   inserting entities into manifest.mf file
		 */
	  	for(File eachFile : xmls){
			if(!eachFile.isDirectory() && eachFile.getName().contains("MerchandiseHier_"+storeid+".xml")){
				manifest.getMainAttributes().put(new Attributes.Name("File"+filecount), "MerchandiseHier_"+storeid+".xml[]");
				filecount++;
			}
			if(!eachFile.isDirectory() && eachFile.getName().contains("StoreOrgHier_"+storeid+".xml")){
				manifest.getMainAttributes().put(new Attributes.Name("File"+filecount), "StoreOrgHier_"+storeid+".xml[]");
				filecount++;
			}
			if(!eachFile.isDirectory() && eachFile.getName().contains("ItemExport_"+storeid+".xml")){
				String itemexp="";
				if(filelist.contains("StoreOrgHier_"+storeid+".xml") && filelist.contains("MerchandiseHier_"+storeid+".xml"))
				{
					itemexp="ItemExport_"+storeid+".xml[MerchandiseHier_"+storeid+".xml,StoreOrgHier_"+storeid+".xml]";
				}
				else if(filelist.contains("StoreOrgHier_"+storeid+".xml"))
				{
					itemexp="ItemExport_"+storeid+".xml[StoreOrgHier_"+storeid+".xml]";
				}
				else if(filelist.contains("MerchandiseHier_"+storeid+".xml"))
				{
					itemexp="ItemExport_"+storeid+".xml[MerchandiseHier_"+storeid+".xml]";
				}
				else
				{
					itemexp="ItemExport_"+storeid+".xml[]";
				}
				manifest.getMainAttributes().put(new Attributes.Name("File"+filecount),itemexp);
				filecount++;
			}
			if(!eachFile.isDirectory() && eachFile.getName().contains("ItemExportCoupon_"+storeid+".xml")){
				String itemcoupexp="";
				if(filelist.contains("ItemExport_"+storeid+".xml"))
				{
					itemcoupexp="ItemExportCoupon_"+storeid+".xml[ItemExport_"+storeid+".xml]";
				}
				else
				{
					itemcoupexp="ItemExportCoupon_"+storeid+".xml[]";
				}
				manifest.getMainAttributes().put(new Attributes.Name("File"+filecount),itemcoupexp);
				filecount++;
			}
			if(!eachFile.isDirectory() && eachFile.getName().contains("CustomerExtractCustSeg.xml")){
				manifest.getMainAttributes().put(new Attributes.Name("File"+filecount),"CustomerExtractCustSeg.xml");
				filecount++;
			}
			if(!eachFile.isDirectory() && eachFile.getName().contains("PricingExtractCoupon_"+storeid+".xml")){
				String pricecoupon="";
				if(filelist.contains("ItemExportCoupon_"+storeid+".xml"))
				{
					pricecoupon="PricingExtractCoupon_"+storeid+".xml[ItemExportCoupon_"+storeid+".xml]";
				}
				else
				{
					pricecoupon="PricingExtractCoupon_"+storeid+".xml[]";
				}
				manifest.getMainAttributes().put(new Attributes.Name("File"+filecount),pricecoupon);
				filecount++;
			}
			if(!eachFile.isDirectory() && eachFile.getName().contains("ItemClassificationMod.xml")){
				String itemclass="";
				if(filelist.contains("ItemExport_"+storeid+".xml"))
				{
					itemclass="ItemClassificationMod.xml[ItemExport_"+storeid+".xml]";
				}
				else
				{
					itemclass="ItemClassificationMod.xml[]";
				}
				manifest.getMainAttributes().put(new Attributes.Name("File"+filecount),itemclass);
				filecount++;
			}
			if(!eachFile.isDirectory() && eachFile.getName().contains("PricingExtract_"+storeid+".xml")){
				String pricing="";
				if(filelist.contains("ItemClassificationMod.xml") && filelist.contains("ItemExport_"+storeid+".xml"))
				{
					pricing="PricingExtract_"+storeid+".xml[ItemClassificationMod.xml,ItemExport"+storeid+".xml]";
				}
				else if(filelist.contains("ItemClassificationMod.xml"))
				{
					pricing="PricingExtract_"+storeid+".xml[ItemClassificationMod.xml]";
				}
				else if(filelist.contains("ItemExport_"+storeid+".xml"))
				{
					pricing="PricingExtract_"+storeid+".xml[ItemExport"+storeid+".xml]";
				}
				else
				{
					pricing="PricingExtract_"+storeid+".xml[]";
				}
				manifest.getMainAttributes().put(new Attributes.Name("File"+filecount),pricing);
				filecount++;
			}
			if(!eachFile.isDirectory() && eachFile.getName().contains("CreditPromotionImport_"+storeid+".xml")){
				manifest.getMainAttributes().put(new Attributes.Name("File"+filecount),"CreditPromotionImport_"+storeid+".xml[]");
				filecount++;
			}
			if(!eachFile.isDirectory() && eachFile.getName().contains("EmployeeImport_"+storeid+".xml")){
				manifest.getMainAttributes().put(new Attributes.Name("File"+filecount),"EmployeeImport_"+storeid+".xml[]");
				filecount++;
			}
			if(!eachFile.isDirectory() && eachFile.getName().contains("CurrencyExport_"+storeid+".xml")){
				manifest.getMainAttributes().put(new Attributes.Name("File"+filecount),"CurrencyExport_"+storeid+".xml[]");
				filecount++;
			}
		}
		return manifest;
	  	}
	}
	catch(Exception e){
		throw new Exception("The Error Occured in MOM_File_Generator.getManifestFile() is :: "+e.getMessage());
	   }
	}
	
	public static boolean createMOMFile(File source,File destination,Logger logger)throws Exception
	{
		
		boolean momGenerated = false;
		File checkLock = new File(destination.getParent()+"//Folder.lock");
	    if(source.listFiles().length!=0)
		{
	  	  try {
	  		logger.info(":: Started Generating the MOM File ::");
				Manifest manifest = getManifestFile(source);
				
				if(manifest !=null)
				{
				JarOutputStream zos=null;
				
				Date today = new Date();
				
				StringBuffer jarname= DateUtil.getMOMFileDate();
				
				boolean lockexist=true;
				do{
					if(!checkLock.exists())
					{
						lockexist=false;
					}
				}while(lockexist);
				if(!lockexist)
			   {
					//checkLock.createNewFile();
					if(!checkLock.exists()){
						checkLock.createNewFile();
						checkLock.setExecutable(true);
						checkLock.setWritable(true);
						checkLock.setReadable(true);
					}
			 	zos = new JarOutputStream(new FileOutputStream(destination.getAbsolutePath()+jarname.toString()), manifest);
			 	momGenerated=addFilesToMOMFile(zos, source);
		          // close the ZipOutputStream
		           zos.close();
		           removeLock(checkLock);
			   }
		  	}
			logger.info(":: MOM Generation Sucess ::");
	     	}catch(Exception e){
			removeLock(checkLock);
			logger.error("", e);
		   }
	    }
		return momGenerated;
	}
	private static void removeLock(File folderlock) throws Exception
	{
		try{
		folderlock.deleteOnExit();
		}catch(Exception e){
			throw new Exception("The Error Occured in MOM_File_Generator.removeLock() is :: "+e.getMessage());
		   }
	}
	private static boolean addFilesToMOMFile(ZipOutputStream zos, File srcFile)throws Exception
	{
		try{
		boolean created = false;
        File[] files = srcFile.listFiles();
	
        for (int i = 0; i < files.length; i++) {
           // if the file is directory dont add it to jar
            if (!files[i].isDirectory() && files[i].getName().contains(storeid+".xml") || files[i].getName().contains(".jpg") || files[i].getName().contains(".jpeg")) {
                              		            
           try {

                // create byte buffer
                byte[] buffer = new byte[(int)files[i].length()];
                FileInputStream fis = new FileInputStream(files[i]);
                fis.read(buffer);
                zos.putNextEntry(new ZipEntry(files[i].getName()));
                zos.write(buffer);
                created = true;
                zos.closeEntry();
                // close the InputStream
                fis.close();
            } catch (IOException ioe) {
                return created;
            }
          }
        }
        return created;
		}
		catch(Exception e){
			throw new Exception("The Error Occured in MOM_File_Generator.addFilesToMOMFile() is :: "+e.getMessage());
		   }
       
    }  
	public static boolean deleteXmls(File srcFile,Logger logger)throws Exception
    {
		try{
    	boolean b=false;
    	File[] filelist=srcFile.listFiles();
    	for(int i=0;i<filelist.length;i++)//deleting all xml files from that directory after creatign ajr file
		{
			if(!filelist[i].isDirectory() && filelist[i].getName().contains(storeid+".xml") || filelist[i].getName().contains(".jpg") || filelist[i].getName().contains(".jpeg"))
			{
			 if(filelist[i].delete())
				{	
					b=true;
				}
			}
		}
    	return b;
		}
		catch(Exception e){
			logger.error("", e);
			throw new Exception("The Error Occured in MOM_File_Generator.addFilesToMOMFile() is :: "+e.getMessage());			
		   }
    }
}
