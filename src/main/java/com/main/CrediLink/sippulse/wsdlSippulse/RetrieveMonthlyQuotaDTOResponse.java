
package com.main.CrediLink.sippulse.wsdlSippulse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for retrieveMonthlyQuotaDTOResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="retrieveMonthlyQuotaDTOResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="monthlyQuotaDTO" type="{http://service.ws.sippulse.voffice.com.br/}subscriberMonthlyQuotaDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retrieveMonthlyQuotaDTOResponse", propOrder = {
    "monthlyQuotaDTO"
})
public class RetrieveMonthlyQuotaDTOResponse {

    protected SubscriberMonthlyQuotaDTO monthlyQuotaDTO;

    /**
     * Gets the value of the monthlyQuotaDTO property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberMonthlyQuotaDTO }
     *     
     */
    public SubscriberMonthlyQuotaDTO getMonthlyQuotaDTO() {
        return monthlyQuotaDTO;
    }

    /**
     * Sets the value of the monthlyQuotaDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberMonthlyQuotaDTO }
     *     
     */
    public void setMonthlyQuotaDTO(SubscriberMonthlyQuotaDTO value) {
        this.monthlyQuotaDTO = value;
    }

}
