package com.neo.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

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

    public Advertiser() {
    }

    public Advertiser(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
//        return "Advertiser{" +
//                "companyName='" + companyName + '\'' +
//                '}';
        return JSONObject.toJSONString(this);
    }
}
