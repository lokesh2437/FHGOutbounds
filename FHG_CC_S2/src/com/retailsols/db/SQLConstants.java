package com.retailsols.db;

public class SQLConstants {

	// main preload data & item data load
	public static String getItemHeader = "SELECT ID,TYPE,'3:'||POSDEPARTMENTID AS POSDEPARTMENTID,ITEMCOST,UOMCODE,DISCOUNTABLE,DAMAGEDISCOUNTABLE,SERIALIZEDITEM,IMAGEFILENAME,IMAGELOCATION,SHORT_DESCRIPTION,LONG_DESCRIPTION,'5:'||SUB_CLASS_ID AS SUB_CLASS_ID FROM XXFHG_POS_ITEM HD WHERE TRANSFERRED_POS='-1' AND ROWNUM < 50";
	public static String getPosIdentity = "SELECT POSITEMID,SUPPLIERID,MINIMUMSALEUNITCOUNT,REGULAR_PRICE,ID_MSG FROM XXFHG_POS_IDENTITY WHERE ID=?";
	public static String getRelatedItems = "SELECT RELATED_ITEM FROM XXFHG_POS_RELATED_ITEM WHERE ID=?";
	public static String getStoreIds = "SELECT DISTINCT(LPAD(ID_STR_RT,5,0)) AS ID_STR_RT FROM XXFHG_POS_ORG_HIERARCHY";
	public static String getSuppliers = "SELECT ID,NAME FROM XXFHG_POS_SUPPLIER WHERE TRANSFERRED_POS='-1'";
	public static String getUOMs = "SELECT CODE,TYPECODE,NAME,DESCRIPTION,ISDEFAULT FROM XXFHG_POS_UOM WHERE TRANSFERRED_POS='-1'";
	public static String getMessages = "SELECT ID,MESSAGE FROM XXFHG_POS_MESSAGES WHERE TRANSFERRED_POS='-1'";

	// data capture jobs
	public static String updateItem = "UPDATE XXFHG_POS_ITEM SET TRANSFERRED_POS='1'";
	public static String updateUOM = "UPDATE XXFHG_POS_UOM SET TRANSFERRED_POS='1'";
	public static String updateSupplier = "UPDATE XXFHG_POS_SUPPLIER SET TRANSFERRED_POS='1'";
	public static String updateMessages = "UPDATE XXFHG_POS_MESSAGES SET TRANSFERRED_POS='1'";

	// check preload data capture
	public static String checkDataCapture = "SELECT COUNT(*) AS COUNT FROM XXFHG_POS_ITEM HD WHERE TRANSFERRED_POS='-1'";
	public static String checkNewUOM = "SELECT COUNT(*) AS COUNT FROM XXFHG_POS_UOM WHERE TRANSFERRED_POS='-1'";
	public static String chcekMewSupplier = "SELECT COUNT(*) FROM XXFHG_POS_SUPPLIER WHERE TRANSFERRED_POS='-1'";
	public static String checkNewMesgs = "SELECT COUNT(*) FROM XXFHG_POS_MESSAGES WHERE TRANSFERRED_POS='-1'";
}
