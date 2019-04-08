package com.neo.model;

/**
 * 广告主
 *
 * @author
 */
public class Advertiser {

    private String companyName;

    private Integer testAdvNum;

    public Advertiser() {
    }

    public Advertiser(String companyName, Integer testAdvNum) {
        this.companyName = companyName;
        this.testAdvNum = testAdvNum;
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
