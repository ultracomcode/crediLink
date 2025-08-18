
package com.main.CrediLink.sippulse.wsdlSippulse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subscriberClassVDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subscriberClassVDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bossSecretary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="callFwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="callGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="domain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fwdBusy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="noAnswer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pickupGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pinCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "subscriberClassVDTO", propOrder = {
    "bossSecretary",
    "callFwd",
    "callGroup",
    "domain",
    "fwdBusy",
    "noAnswer",
    "pickupGroup",
    "pinCode",
    "username"
})
public class SubscriberClassVDTO {

    protected String bossSecretary;
    protected String callFwd;
    protected String callGroup;
    protected String domain;
    protected String fwdBusy;
    protected String noAnswer;
    protected String pickupGroup;
    protected String pinCode;
    protected String username;

    /**
     * Gets the value of the bossSecretary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBossSecretary() {
        return bossSecretary;
    }

    /**
     * Sets the value of the bossSecretary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBossSecretary(String value) {
        this.bossSecretary = value;
    }

    /**
     * Gets the value of the callFwd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallFwd() {
        return callFwd;
    }

    /**
     * Sets the value of the callFwd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallFwd(String value) {
        this.callFwd = value;
    }

    /**
     * Gets the value of the callGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallGroup() {
        return callGroup;
    }

    /**
     * Sets the value of the callGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallGroup(String value) {
        this.callGroup = value;
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
     * Gets the value of the fwdBusy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFwdBusy() {
        return fwdBusy;
    }

    /**
     * Sets the value of the fwdBusy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFwdBusy(String value) {
        this.fwdBusy = value;
    }

    /**
     * Gets the value of the noAnswer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoAnswer() {
        return noAnswer;
    }

    /**
     * Sets the value of the noAnswer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoAnswer(String value) {
        this.noAnswer = value;
    }

    /**
     * Gets the value of the pickupGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickupGroup() {
        return pickupGroup;
    }

    /**
     * Sets the value of the pickupGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickupGroup(String value) {
        this.pickupGroup = value;
    }

    /**
     * Gets the value of the pinCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPinCode() {
        return pinCode;
    }

    /**
     * Sets the value of the pinCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPinCode(String value) {
        this.pinCode = value;
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
