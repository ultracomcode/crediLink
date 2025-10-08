
package com.main.CrediLink.integration.sippulse.wsdlSippulse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for retrieveDailyQuotaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="retrieveDailyQuotaResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dailyQuotaDTO" type="{http://service.ws.sippulse.voffice.com.br/}subscriberDailyQuotaDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retrieveDailyQuotaResponse", propOrder = {
    "dailyQuotaDTO"
})
public class RetrieveDailyQuotaResponse {

    protected SubscriberDailyQuotaDTO dailyQuotaDTO;

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

}
