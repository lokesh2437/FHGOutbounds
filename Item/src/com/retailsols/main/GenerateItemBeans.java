package com.retailsols.main;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.retailsols.db.SQLConstants;
import com.retailsols.jaxb.beans.ChangeTypeSubtype;
import com.retailsols.jaxb.beans.ItemLevelMessagesType;
import com.retailsols.jaxb.beans.ItemType;
import com.retailsols.jaxb.beans.LanguageType;
import com.retailsols.jaxb.beans.LocalizedDescriptionType;
import com.retailsols.jaxb.beans.LocalizedNameDescriptionType;
import com.retailsols.jaxb.beans.LocalizedNameType;
import com.retailsols.jaxb.beans.MerchandiseHierarchyType;
import com.retailsols.jaxb.beans.MessageType;
import com.retailsols.jaxb.beans.POSIdentityType;
import com.retailsols.jaxb.beans.RegularPriceType;
import com.retailsols.jaxb.beans.RelatedItemAssociationType;
import com.retailsols.jaxb.beans.RetailStoreItemType;
import com.retailsols.util.MessageUtil;

public class GenerateItemBeans {
	
	private PreparedStatement ps_itm=null;
	private PreparedStatement ps_idnty=null;
	public PreparedStatement ps_related=null;
	
	public ArrayList<ItemType> generateItemType(Logger logger,Connection conn){
		ArrayList<ItemType> itemTypeList=new ArrayList<ItemType>();
		try {
			
			logger.info(":: Started Generating the Item Type ::");
			
			ps_itm=conn.prepareStatement(SQLConstants.getItemHeader);
			ps_idnty=conn.prepareStatement(SQLConstants.getPosIdentity);
			ps_related=conn.prepareStatement(SQLConstants.getRelatedItems);
			
			ResultSet rs_itm=ps_itm.executeQuery();
			double count=0;
			while(rs_itm.next()){
				count=count+1;
				ItemType itemType=new ItemType();
				
				// set Change Type
				itemType.setChangeType(ChangeTypeSubtype.UPS);
				
				// set item id
				String item_id=rs_itm.getString("ID");
				itemType.setID(rs_itm.getString("ID"));
				System.out.println("Processing Item ID : "+item_id+" :: Count : "+count);
				// set item type
				String type=rs_itm.getString("TYPE");
				
				if(type.equals("1"))					
					itemType.setType("Stock");
					
				else if(type.equals("2"))
					itemType.setType("Service");
				else
					itemType.setType("Coupon");
				
				// set Item Cost
				itemType.setItemCost(rs_itm.getString("ITEMCOST")!=null?new BigDecimal(rs_itm.getString("ITEMCOST")):null);
				
				// set taxable
				itemType.setTaxable(false);
				
				// set department id
				itemType.setPOSDepartmentID(rs_itm.getString("POSDEPARTMENTID"));
				
				// kit set code
				itemType.setKitSetCode("0");
				
				// default values
				itemType.setActivationRequired(false);
				itemType.setRegistryEligible(true);
				itemType.setAuthorizedForSale(true);
				
				// serialize flag
				itemType.setSerializedItem(rs_itm.getString("SERIALIZEDITEM").equals("0")?false:true);
				
				
				// discountable
				itemType.setDiscountable(rs_itm.getString("DISCOUNTABLE").equals("0")?false:true);
//				
				itemType.setDamageDiscountable(rs_itm.getString("DAMAGEDISCOUNTABLE").equals("0")?false:true);
				itemType.setDamageDiscountable(true);
				//item image file
				/*PreparedStatement ps_img=conn.prepareStatement(SQLConstants.getImageName);
				ResultSet rs_img=ps_img.executeQuery()mess;
				rs_img.next();
				String image_name=rs_img.getString("IMAGE");
				ps_img.close();
				itemType.setImageFileName(image_name);*/
				
				// description
				LocalizedNameDescriptionType localdesc=new LocalizedNameDescriptionType();
				localdesc.setLanguage(LanguageType.EN);
				localdesc.setDescription(rs_itm.getString("LONG_DESCRIPTION"));
				localdesc.setName(rs_itm.getString("LONG_DESCRIPTION"));
				itemType.getLocalizedNameDescription().add(localdesc);
				
				LocalizedDescriptionType longdesc=new LocalizedDescriptionType();
				longdesc.setValue(rs_itm.getString("LONG_DESCRIPTION"));
				longdesc.setLanguage(LanguageType.EN);
				
				LocalizedNameType localizedNameType=new LocalizedNameType();
				localizedNameType.setValue(rs_itm.getString("LONG_DESCRIPTION"));
				localizedNameType.setLanguage(LanguageType.EN);
				itemType.getShortName().add(localizedNameType);
				
				itemType.getLongDescription().add(longdesc);
				
				
				// merchandise hierarchy
				MerchandiseHierarchyType merch=new MerchandiseHierarchyType();
				merch.setValue(rs_itm.getString("SUB_CLASS_ID"));
				itemType.setMerchandiseHierarchy(merch);
				
				// retailstore item start
					RetailStoreItemType retailStoreItemType=new RetailStoreItemType();
						// add stores
					//retailStoreItemType.getRRetailStoreId().addAll(StoreUtil.getStoreIDs(conn, logger));
					
					ps_idnty.setString(1, item_id);
					ResultSet rs_idn=ps_idnty.executeQuery();
					if(rs_idn.next()){
						RegularPriceType priceType=new RegularPriceType();
						priceType.setCurrencyCode("SAR");
						priceType.setValue(new BigDecimal(rs_idn.getString("REGULAR_PRICE")));
						
						retailStoreItemType.getRegularPrice().add(priceType);
						
						POSIdentityType identityType=new POSIdentityType();
						
						identityType.setPOSItemID(item_id);
						identityType.setSupplierID(rs_idn.getString("SUPPLIERID"));
						identityType.setMinimumSaleUnitCount(new BigDecimal(rs_idn.getString("MINIMUMSALEUNITCOUNT")));
						identityType.setEmployeeDiscountAllowed(true);
						identityType.setQuantityModifiable("true");
						
						identityType.setReturnable(true);
						identityType.setPriceModifiable(false);
						
						if(type.equals("1"))					
							identityType.setPriceEntryRequired(false);							
						else if(type.equals("2"))
							identityType.setPriceEntryRequired(true);
						else
							identityType.setPriceEntryRequired(false);
						
						
						retailStoreItemType.getPOSIdentity().add(identityType);
					}
					
					
				// retailstore item end
					
				// add retailstore item to item	
				itemType.getRetailStoreItem().add(retailStoreItemType);
				
				// item level messages
				ItemLevelMessagesType itemLevelMessagesType = new ItemLevelMessagesType();
				MessageType messageType=new MessageType();
				//messageType.setID(MessageUtil.getMessageID(item_id,conn));
				messageType.setMessageType("Screen");
				messageType.setTransactionType("Receipt");
				
				itemLevelMessagesType.getItemMsgAscn().add(messageType);
				
				itemType.getDisplayMessage().add(itemLevelMessagesType);
				
				// related items
				ps_related.setString(1, item_id);
				ResultSet rs_related=ps_related.executeQuery();
				ArrayList<RelatedItemAssociationType> related_items=new ArrayList<RelatedItemAssociationType>();
				while(rs_related.next()){
					RelatedItemAssociationType associationType=new RelatedItemAssociationType();
					associationType.setChangeType(ChangeTypeSubtype.UPS);
					associationType.setRelatedItemID(rs_related.getString("RELATED_ITEM"));
					associationType.setTypeCode("AUTO");
					associationType.setRemoveAllowed(true);
					associationType.setReturnAllowed(true);
					related_items.add(associationType);
				}
				
				itemType.getRelatedItemAssociation().addAll(related_items);
				itemTypeList.add(itemType);
				
				
			}
			ps_itm.close();
			ps_idnty.close();
			ps_related.close();
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
			logger.error("",e);
		}
		/*finally{
			// close resources
			try {
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("", e);
			}
			
		}*/
		return itemTypeList;
	}

}
