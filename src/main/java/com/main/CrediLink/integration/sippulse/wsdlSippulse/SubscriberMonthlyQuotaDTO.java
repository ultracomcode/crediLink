
package com.main.CrediLink.integration.sippulse.wsdlSippulse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subscriberMonthlyQuotaDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subscriberMonthlyQuotaDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="domain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="monthlyQuota" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="monthlyQuotaConsumed" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="monthlyQuotaLimit" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="monthlyQuotaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "subscriberMonthlyQuotaDTO", propOrder = {
    "domain",
    "monthlyQuota",
    "monthlyQuotaConsumed",
    "monthlyQuotaLimit",
    "monthlyQuotaType",
    "username"
})
public class SubscriberMonthlyQuotaDTO {

    protected String domain;
    protected boolean monthlyQuota;
    protected double monthlyQuotaConsumed;
    protected double monthlyQuotaLimit;
    protected String monthlyQuotaType;
    protected String username;

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
     * Gets the value of the monthlyQuota property.
     * 
     */
    public boolean isMonthlyQuota() {
        return monthlyQuota;
    }

    /**
     * Sets the value of the monthlyQuota property.
     * 
     */
    public void setMonthlyQuota(boolean value) {
        this.monthlyQuota = value;
    }

    /**
     * Gets the value of the monthlyQuotaConsumed property.
     * 
     */
    public double getMonthlyQuotaConsumed() {
        return monthlyQuotaConsumed;
    }

    /**
     * Sets the value of the monthlyQuotaConsumed property.
     * 
     */
    public void setMonthlyQuotaConsumed(double value) {
        this.monthlyQuotaConsumed = value;
    }

    /**
     * Gets the value of the monthlyQuotaLimit property.
     * 
     */
    public double getMonthlyQuotaLimit() {
        return monthlyQuotaLimit;
    }

    /**
     * Sets the value of the monthlyQuotaLimit property.
     * 
     */
    public void setMonthlyQuotaLimit(double value) {
        this.monthlyQuotaLimit = value;
    }

    /**
     * Gets the value of the monthlyQuotaType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonthlyQuotaType() {
        return monthlyQuotaType;
    }

    /**
     * Sets the value of the monthlyQuotaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonthlyQuotaType(String value) {
        this.monthlyQuotaType = value;
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
