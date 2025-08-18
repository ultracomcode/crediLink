
package com.main.CrediLink.sippulse.wsdlSippulse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subscriberServicesDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subscriberServicesDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="areaCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="callLimit" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="cityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="countryCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="cps" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="domain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="keepAlive" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="localArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rpid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "subscriberServicesDTO", propOrder = {
    "areaCode",
    "callLimit",
    "cityCode",
    "countryCode",
    "cps",
    "domain",
    "email",
    "keepAlive",
    "localArea",
    "rpid",
    "username"
})
public class SubscriberServicesDTO {

    protected Integer areaCode;
    protected Integer callLimit;
    protected String cityCode;
    protected Integer countryCode;
    protected int cps;
    protected String domain;
    protected String email;
    protected int keepAlive;
    protected String localArea;
    protected String rpid;
    protected String username;

    /**
     * Gets the value of the areaCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAreaCode() {
        return areaCode;
    }

    /**
     * Sets the value of the areaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAreaCode(Integer value) {
        this.areaCode = value;
    }

    /**
     * Gets the value of the callLimit property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCallLimit() {
        return callLimit;
    }

    /**
     * Sets the value of the callLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCallLimit(Integer value) {
        this.callLimit = value;
    }

    /**
     * Gets the value of the cityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Sets the value of the cityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityCode(String value) {
        this.cityCode = value;
    }

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCountryCode(Integer value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the cps property.
     * 
     */
    public int getCps() {
        return cps;
    }

    /**
     * Sets the value of the cps property.
     * 
     */
    public void setCps(int value) {
        this.cps = value;
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
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the keepAlive property.
     * 
     */
    public int getKeepAlive() {
        return keepAlive;
    }

    /**
     * Sets the value of the keepAlive property.
     * 
     */
    public void setKeepAlive(int value) {
        this.keepAlive = value;
    }

    /**
     * Gets the value of the localArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalArea() {
        return localArea;
    }

    /**
     * Sets the value of the localArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalArea(String value) {
        this.localArea = value;
    }

    /**
     * Gets the value of the rpid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRpid() {
        return rpid;
    }

    /**
     * Sets the value of the rpid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRpid(String value) {
        this.rpid = value;
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
