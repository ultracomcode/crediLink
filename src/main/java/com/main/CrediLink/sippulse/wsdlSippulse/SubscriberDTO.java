
package com.main.CrediLink.sippulse.wsdlSippulse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subscriberDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subscriberDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accountCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="activeIncomingCalls" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="activeOutgoingCalls" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="addServiceHeader" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="areaCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="blockAnonymousCalls" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="blockCollectCalls" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="blockEntry0303" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="callFwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="callLimit" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="callsOnlyByIp" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="complement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="contractNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="copyPassertedRpid" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="countryCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="document" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="domain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="emailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fwdBusy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="localArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lowCreditLimit" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="lowCreditNotification" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="noAnswer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="passwordPortal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="profile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="quarter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ratePlanId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="resellerBillingType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="resellerId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="resellerMarkup" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="resellerRatePlanId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="rings" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="rpid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="softphoneAllowed" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="validateSource0303" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="voiceMailPassword" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="voicemail" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="zip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subscriberDTO", propOrder = {
    "accountCode",
    "activeIncomingCalls",
    "activeOutgoingCalls",
    "addServiceHeader",
    "address",
    "areaCode",
    "blockAnonymousCalls",
    "blockCollectCalls",
    "blockEntry0303",
    "callFwd",
    "callLimit",
    "callsOnlyByIp",
    "city",
    "cityCode",
    "complement",
    "contractNumber",
    "copyPassertedRpid",
    "countryCode",
    "document",
    "domain",
    "emailAddress",
    "firstName",
    "fwdBusy",
    "id",
    "lastName",
    "localArea",
    "lowCreditLimit",
    "lowCreditNotification",
    "mobile",
    "noAnswer",
    "number",
    "password",
    "passwordPortal",
    "phone",
    "profile",
    "quarter",
    "ratePlanId",
    "resellerBillingType",
    "resellerId",
    "resellerMarkup",
    "resellerRatePlanId",
    "rings",
    "rpid",
    "softphoneAllowed",
    "state",
    "username",
    "validateSource0303",
    "voiceMailPassword",
    "voicemail",
    "zip"
})
public class SubscriberDTO {

    protected String accountCode;
    protected boolean activeIncomingCalls;
    protected boolean activeOutgoingCalls;
    protected boolean addServiceHeader;
    protected String address;
    protected Integer areaCode;
    protected boolean blockAnonymousCalls;
    protected Boolean blockCollectCalls;
    protected boolean blockEntry0303;
    protected String callFwd;
    protected Integer callLimit;
    protected boolean callsOnlyByIp;
    protected String city;
    protected String cityCode;
    protected String complement;
    protected String contractNumber;
    protected boolean copyPassertedRpid;
    protected Integer countryCode;
    protected String document;
    protected String domain;
    protected String emailAddress;
    protected String firstName;
    protected String fwdBusy;
    protected Integer id;
    protected String lastName;
    protected String localArea;
    protected double lowCreditLimit;
    protected boolean lowCreditNotification;
    protected Long mobile;
    protected String noAnswer;
    protected String number;
    protected String password;
    protected String passwordPortal;
    protected Long phone;
    protected String profile;
    protected String quarter;
    protected Integer ratePlanId;
    protected int resellerBillingType;
    protected Integer resellerId;
    protected double resellerMarkup;
    protected Integer resellerRatePlanId;
    protected Integer rings;
    protected String rpid;
    protected boolean softphoneAllowed;
    protected String state;
    protected String username;
    protected boolean validateSource0303;
    protected Long voiceMailPassword;
    protected boolean voicemail;
    protected String zip;

    /**
     * Gets the value of the accountCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * Sets the value of the accountCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountCode(String value) {
        this.accountCode = value;
    }

    /**
     * Gets the value of the activeIncomingCalls property.
     * 
     */
    public boolean isActiveIncomingCalls() {
        return activeIncomingCalls;
    }

    /**
     * Sets the value of the activeIncomingCalls property.
     * 
     */
    public void setActiveIncomingCalls(boolean value) {
        this.activeIncomingCalls = value;
    }

    /**
     * Gets the value of the activeOutgoingCalls property.
     * 
     */
    public boolean isActiveOutgoingCalls() {
        return activeOutgoingCalls;
    }

    /**
     * Sets the value of the activeOutgoingCalls property.
     * 
     */
    public void setActiveOutgoingCalls(boolean value) {
        this.activeOutgoingCalls = value;
    }

    /**
     * Gets the value of the addServiceHeader property.
     * 
     */
    public boolean isAddServiceHeader() {
        return addServiceHeader;
    }

    /**
     * Sets the value of the addServiceHeader property.
     * 
     */
    public void setAddServiceHeader(boolean value) {
        this.addServiceHeader = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

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
     * Gets the value of the blockAnonymousCalls property.
     * 
     */
    public boolean isBlockAnonymousCalls() {
        return blockAnonymousCalls;
    }

    /**
     * Sets the value of the blockAnonymousCalls property.
     * 
     */
    public void setBlockAnonymousCalls(boolean value) {
        this.blockAnonymousCalls = value;
    }

    /**
     * Gets the value of the blockCollectCalls property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBlockCollectCalls() {
        return blockCollectCalls;
    }

    /**
     * Sets the value of the blockCollectCalls property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBlockCollectCalls(Boolean value) {
        this.blockCollectCalls = value;
    }

    /**
     * Gets the value of the blockEntry0303 property.
     * 
     */
    public boolean isBlockEntry0303() {
        return blockEntry0303;
    }

    /**
     * Sets the value of the blockEntry0303 property.
     * 
     */
    public void setBlockEntry0303(boolean value) {
        this.blockEntry0303 = value;
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
     * Gets the value of the callsOnlyByIp property.
     * 
     */
    public boolean isCallsOnlyByIp() {
        return callsOnlyByIp;
    }

    /**
     * Sets the value of the callsOnlyByIp property.
     * 
     */
    public void setCallsOnlyByIp(boolean value) {
        this.callsOnlyByIp = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
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
     * Gets the value of the complement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplement() {
        return complement;
    }

    /**
     * Sets the value of the complement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplement(String value) {
        this.complement = value;
    }

    /**
     * Gets the value of the contractNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractNumber() {
        return contractNumber;
    }

    /**
     * Sets the value of the contractNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractNumber(String value) {
        this.contractNumber = value;
    }

    /**
     * Gets the value of the copyPassertedRpid property.
     * 
     */
    public boolean isCopyPassertedRpid() {
        return copyPassertedRpid;
    }

    /**
     * Sets the value of the copyPassertedRpid property.
     * 
     */
    public void setCopyPassertedRpid(boolean value) {
        this.copyPassertedRpid = value;
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
     * Gets the value of the document property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocument() {
        return document;
    }

    /**
     * Sets the value of the document property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocument(String value) {
        this.document = value;
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
     * Gets the value of the emailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the value of the emailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAddress(String value) {
        this.emailAddress = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
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
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
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
     * Gets the value of the lowCreditLimit property.
     * 
     */
    public double getLowCreditLimit() {
        return lowCreditLimit;
    }

    /**
     * Sets the value of the lowCreditLimit property.
     * 
     */
    public void setLowCreditLimit(double value) {
        this.lowCreditLimit = value;
    }

    /**
     * Gets the value of the lowCreditNotification property.
     * 
     */
    public boolean isLowCreditNotification() {
        return lowCreditNotification;
    }

    /**
     * Sets the value of the lowCreditNotification property.
     * 
     */
    public void setLowCreditNotification(boolean value) {
        this.lowCreditNotification = value;
    }

    /**
     * Gets the value of the mobile property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMobile() {
        return mobile;
    }

    /**
     * Sets the value of the mobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMobile(Long value) {
        this.mobile = value;
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
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumber(String value) {
        this.number = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the passwordPortal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPasswordPortal() {
        return passwordPortal;
    }

    /**
     * Sets the value of the passwordPortal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPasswordPortal(String value) {
        this.passwordPortal = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPhone(Long value) {
        this.phone = value;
    }

    /**
     * Gets the value of the profile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfile() {
        return profile;
    }

    /**
     * Sets the value of the profile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfile(String value) {
        this.profile = value;
    }

    /**
     * Gets the value of the quarter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuarter() {
        return quarter;
    }

    /**
     * Sets the value of the quarter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuarter(String value) {
        this.quarter = value;
    }

    /**
     * Gets the value of the ratePlanId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRatePlanId() {
        return ratePlanId;
    }

    /**
     * Sets the value of the ratePlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRatePlanId(Integer value) {
        this.ratePlanId = value;
    }

    /**
     * Gets the value of the resellerBillingType property.
     * 
     */
    public int getResellerBillingType() {
        return resellerBillingType;
    }

    /**
     * Sets the value of the resellerBillingType property.
     * 
     */
    public void setResellerBillingType(int value) {
        this.resellerBillingType = value;
    }

    /**
     * Gets the value of the resellerId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getResellerId() {
        return resellerId;
    }

    /**
     * Sets the value of the resellerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setResellerId(Integer value) {
        this.resellerId = value;
    }

    /**
     * Gets the value of the resellerMarkup property.
     * 
     */
    public double getResellerMarkup() {
        return resellerMarkup;
    }

    /**
     * Sets the value of the resellerMarkup property.
     * 
     */
    public void setResellerMarkup(double value) {
        this.resellerMarkup = value;
    }

    /**
     * Gets the value of the resellerRatePlanId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getResellerRatePlanId() {
        return resellerRatePlanId;
    }

    /**
     * Sets the value of the resellerRatePlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setResellerRatePlanId(Integer value) {
        this.resellerRatePlanId = value;
    }

    /**
     * Gets the value of the rings property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRings() {
        return rings;
    }

    /**
     * Sets the value of the rings property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRings(Integer value) {
        this.rings = value;
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
     * Gets the value of the softphoneAllowed property.
     * 
     */
    public boolean isSoftphoneAllowed() {
        return softphoneAllowed;
    }

    /**
     * Sets the value of the softphoneAllowed property.
     * 
     */
    public void setSoftphoneAllowed(boolean value) {
        this.softphoneAllowed = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
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

    /**
     * Gets the value of the validateSource0303 property.
     * 
     */
    public boolean isValidateSource0303() {
        return validateSource0303;
    }

    /**
     * Sets the value of the validateSource0303 property.
     * 
     */
    public void setValidateSource0303(boolean value) {
        this.validateSource0303 = value;
    }

    /**
     * Gets the value of the voiceMailPassword property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVoiceMailPassword() {
        return voiceMailPassword;
    }

    /**
     * Sets the value of the voiceMailPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVoiceMailPassword(Long value) {
        this.voiceMailPassword = value;
    }

    /**
     * Gets the value of the voicemail property.
     * 
     */
    public boolean isVoicemail() {
        return voicemail;
    }

    /**
     * Sets the value of the voicemail property.
     * 
     */
    public void setVoicemail(boolean value) {
        this.voicemail = value;
    }

    /**
     * Gets the value of the zip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the value of the zip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZip(String value) {
        this.zip = value;
    }

}
