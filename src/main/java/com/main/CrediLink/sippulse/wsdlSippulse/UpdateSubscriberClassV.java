
package com.main.CrediLink.sippulse.wsdlSippulse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateSubscriberClassV complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateSubscriberClassV"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="subscriber" type="{http://service.ws.sippulse.voffice.com.br/}subscriberClassVDTO" minOccurs="0"/&gt;
 *         &lt;element name="principal" type="{http://service.ws.sippulse.voffice.com.br/}userPrincipalDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateSubscriberClassV", propOrder = {
    "subscriber",
    "principal"
})
public class UpdateSubscriberClassV {

    protected SubscriberClassVDTO subscriber;
    protected UserPrincipalDTO principal;

    /**
     * Gets the value of the subscriber property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberClassVDTO }
     *     
     */
    public SubscriberClassVDTO getSubscriber() {
        return subscriber;
    }

    /**
     * Sets the value of the subscriber property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberClassVDTO }
     *     
     */
    public void setSubscriber(SubscriberClassVDTO value) {
        this.subscriber = value;
    }

    /**
     * Gets the value of the principal property.
     * 
     * @return
     *     possible object is
     *     {@link UserPrincipalDTO }
     *     
     */
    public UserPrincipalDTO getPrincipal() {
        return principal;
    }

    /**
     * Sets the value of the principal property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserPrincipalDTO }
     *     
     */
    public void setPrincipal(UserPrincipalDTO value) {
        this.principal = value;
    }

}
