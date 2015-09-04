//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.23 at 04:35:00 PM IST 
//


package com.retailsols.jaxb.beans;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UOM_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UOM_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LocalizedNameDescription" type="{}LocalizedNameDescription_type" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ChangeType" type="{}ChangeType_subtype" default="UPS" />
 *       &lt;attribute name="Code" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="2"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="TypeCode">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="2"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="System">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Standard"/>
 *             &lt;enumeration value="Metric"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Name" type="{}Name_type" />
 *       &lt;attribute name="Description" type="{}Description_type" />
 *       &lt;attribute name="IsDefault" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="DefaultEntryCode" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="Enabled" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="SortIndex" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UOM_type", propOrder = {
    "localizedNameDescription"
})
public class UOMType {

    @XmlElement(name = "LocalizedNameDescription")
    protected List<LocalizedNameDescriptionType> localizedNameDescription;
    @XmlAttribute(name = "ChangeType")
    protected ChangeTypeSubtype changeType;
    @XmlAttribute(name = "Code", required = true)
    protected String code;
    @XmlAttribute(name = "TypeCode")
    protected String typeCode;
    @XmlAttribute(name = "System")
    protected String system;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "Description")
    protected String description;
    @XmlAttribute(name = "IsDefault")
    protected Boolean isDefault;
    @XmlAttribute(name = "DefaultEntryCode")
    protected Boolean defaultEntryCode;
    @XmlAttribute(name = "Enabled")
    protected Boolean enabled;
    @XmlAttribute(name = "SortIndex", required = true)
    protected int sortIndex;

    /**
     * Gets the value of the localizedNameDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the localizedNameDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocalizedNameDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalizedNameDescriptionType }
     * 
     * 
     */
    public List<LocalizedNameDescriptionType> getLocalizedNameDescription() {
        if (localizedNameDescription == null) {
            localizedNameDescription = new ArrayList<LocalizedNameDescriptionType>();
        }
        return this.localizedNameDescription;
    }

    /**
     * Gets the value of the changeType property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeTypeSubtype }
     *     
     */
    public ChangeTypeSubtype getChangeType() {
        if (changeType == null) {
            return ChangeTypeSubtype.UPS;
        } else {
            return changeType;
        }
    }

    /**
     * Sets the value of the changeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeTypeSubtype }
     *     
     */
    public void setChangeType(ChangeTypeSubtype value) {
        this.changeType = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the typeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * Sets the value of the typeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeCode(String value) {
        this.typeCode = value;
    }

    /**
     * Gets the value of the system property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystem() {
        return system;
    }

    /**
     * Sets the value of the system property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystem(String value) {
        this.system = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the isDefault property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsDefault() {
        if (isDefault == null) {
            return false;
        } else {
            return isDefault;
        }
    }

    /**
     * Sets the value of the isDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDefault(Boolean value) {
        this.isDefault = value;
    }

    /**
     * Gets the value of the defaultEntryCode property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isDefaultEntryCode() {
        if (defaultEntryCode == null) {
            return false;
        } else {
            return defaultEntryCode;
        }
    }

    /**
     * Sets the value of the defaultEntryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDefaultEntryCode(Boolean value) {
        this.defaultEntryCode = value;
    }

    /**
     * Gets the value of the enabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isEnabled() {
        if (enabled == null) {
            return true;
        } else {
            return enabled;
        }
    }

    /**
     * Sets the value of the enabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnabled(Boolean value) {
        this.enabled = value;
    }

    /**
     * Gets the value of the sortIndex property.
     * 
     */
    public int getSortIndex() {
        return sortIndex;
    }

    /**
     * Sets the value of the sortIndex property.
     * 
     */
    public void setSortIndex(int value) {
        this.sortIndex = value;
    }

}
