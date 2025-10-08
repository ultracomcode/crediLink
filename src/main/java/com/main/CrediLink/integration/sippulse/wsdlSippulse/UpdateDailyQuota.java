
package com.main.CrediLink.integration.sippulse.wsdlSippulse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateDailyQuota complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateDailyQuota"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dailyQuotaDTO" type="{http://service.ws.sippulse.voffice.com.br/}subscriberDailyQuotaDTO" minOccurs="0"/&gt;
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
@XmlType(name = "updateDailyQuota", propOrder = {
    "dailyQuotaDTO",
    "principal"
})
public class UpdateDailyQuota {

    protected SubscriberDailyQuotaDTO dailyQuotaDTO;
    protected UserPrincipalDTO principal;

    /**
     * Gets the value of the dailyQuotaDTO property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberDailyQuotaDTO }
     *     
     */
    public SubscriberDailyQuotaDTO getDailyQuotaDTO() {
        return dailyQuotaDTO;
    }

    /**
     * Sets the value of the dailyQuotaDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberDailyQuotaDTO }
     *     
     */
    public void setDailyQuotaDTO(SubscriberDailyQuotaDTO value) {
        this.dailyQuotaDTO = value;
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
