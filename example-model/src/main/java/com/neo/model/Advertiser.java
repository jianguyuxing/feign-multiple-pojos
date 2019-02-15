package com.neo.model;

import java.util.List;

/**
 * 广告主
 *
 * @author linjintian
 */
public class Advertiser {

    /**
     * 广告主标识 (修改使用)
     */
    private String advId;
    /**
     * 公司注册名称
     */
    private String companyName;
    /**
     * 营业执照注册号
     */
    private String businessLicenseNum;
    /**
     * 营业执照扫描件
     */
    private String businessLicensePic;
    /**
     * 网站名称
     */
    private String websiteName;
    /**
     * ICP备案截图
     */
    private String icpPic;
    /**
     * 网站地址
     */
    private String websiteAddress;
    /**
     * 税务登记证号
     */
    private String taxRegistryNum;
    /**
     * 税务登记证扫描件
     */
    private String taxRegistryPic;
    /**
     * 行业
     */
    private String industry;
    /**
     * 联系人
     */
    private String contactPerson;
    /**
     * 电子邮件
     */
    private String contactsEmail;
    /**
     * 手机号
     */
    private String mobilePhone;
    /**
     * 固定电话
     */
    private String telephone;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 资质列表
     */
    private List<Qualification> qualifications;

    public String getAdvId() {
        return advId;
    }

    public void setAdvId(String advId) {
        this.advId = advId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessLicenseNum() {
        return businessLicenseNum;
    }

    public void setBusinessLicenseNum(String businessLicenseNum) {
        this.businessLicenseNum = businessLicenseNum;
    }

    public String getBusinessLicensePic() {
        return businessLicensePic;
    }

    public void setBusinessLicensePic(String businessLicensePic) {
        this.businessLicensePic = businessLicensePic;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getIcpPic() {
        return icpPic;
    }

    public void setIcpPic(String icpPic) {
        this.icpPic = icpPic;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

    public String getTaxRegistryNum() {
        return taxRegistryNum;
    }

    public void setTaxRegistryNum(String taxRegistryNum) {
        this.taxRegistryNum = taxRegistryNum;
    }

    public String getTaxRegistryPic() {
        return taxRegistryPic;
    }

    public void setTaxRegistryPic(String taxRegistryPic) {
        this.taxRegistryPic = taxRegistryPic;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactsEmail() {
        return contactsEmail;
    }

    public void setContactsEmail(String contactsEmail) {
        this.contactsEmail = contactsEmail;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    @Override
    public String toString() {
        return "Advertiser [companyName=" + companyName + ", businessLicenseNum=" + businessLicenseNum + ", businessLicensePic=" + businessLicensePic + ", websiteName=" + websiteName + ", icpPic="
                + icpPic + ", websiteAddress=" + websiteAddress + ", taxRegistryNum=" + taxRegistryNum + ", taxRegistryPic=" + taxRegistryPic + ", industry=" + industry + ", contactPerson="
                + contactPerson + ", contactsEmail=" + contactsEmail + ", mobilePhone=" + mobilePhone + ", telephone=" + telephone + ", address=" + address + ", qualifications=" + qualifications
                + "]";
    }

    public static class Qualification {
        private String key;
        private String url;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "Qualification [key=" + key + ", url=" + url + "]";
        }

    }
}
