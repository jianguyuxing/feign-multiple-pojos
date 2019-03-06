package com.neo.model;

/**
 * 广告主
 *
 * @author linjintian
 */
public class Advertiser {

    /**
     * 公司注册名称
     */
    private String companyName;

    private Integer testAdvNum;

    public Advertiser() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getTestAdvNum() {
        return testAdvNum;
    }

    public void setTestAdvNum(Integer testAdvNum) {
        this.testAdvNum = testAdvNum;
    }

    @Override
    public String toString() {
        return "Advertiser{" +
                "companyName='" + companyName + '\'' +
                ", testAdvNum=" + testAdvNum +
                '}';
    }
}
