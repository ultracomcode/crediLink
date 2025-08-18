
package com.main.CrediLink.sippulse.wsdlSippulse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subscriberDailyQuotaDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subscriberDailyQuotaDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dailyQuota" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="dailyQuotaConsumed" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="dailyQuotaLimit" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="dailyQuotaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="domain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subscriberDailyQuotaDTO", propOrder = {
    "dailyQuota",
    "dailyQuotaConsumed",
    "dailyQuotaLimit",
    "dailyQuotaType",
    "domain",
    "username"
})
public class SubscriberDailyQuotaDTO {

    protected boolean dailyQuota;
    protected double dailyQuotaConsumed;
    protected double dailyQuotaLimit;
    protected String dailyQuotaType;
    protected String domain;
    protected String username;

    /**
     * Gets the value of the dailyQuota property.
     * 
     */
    public boolean isDailyQuota() {
        return dailyQuota;
    }

    /**
     * Sets the value of the dailyQuota property.
     * 
     */
    public void setDailyQuota(boolean value) {
        this.dailyQuota = value;
    }

    /**
     * Gets the value of the dailyQuotaConsumed property.
     * 
     */
    public double getDailyQuotaConsumed() {
        return dailyQuotaConsumed;
    }

    /**
     * Sets the value of the dailyQuotaConsumed property.
     * 
     */
    public void setDailyQuotaConsumed(double value) {
        this.dailyQuotaConsumed = value;
    }

    /**
     * Gets the value of the dailyQuotaLimit property.
     * 
     */
    public double getDailyQuotaLimit() {
        return dailyQuotaLimit;
    }

    /**
     * Sets the value of the dailyQuotaLimit property.
     * 
     */
    public void setDailyQuotaLimit(double value) {
        this.dailyQuotaLimit = value;
    }

    /**
     * Gets the value of the dailyQuotaType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDailyQuotaType() {
        return dailyQuotaType;
    }

    /**
     * Sets the value of the dailyQuotaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDailyQuotaType(String value) {
        this.dailyQuotaType = value;
    }

    /**
     * Gets the value of the domain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets the value of the domain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomain(String value) {
        this.domain = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

}
