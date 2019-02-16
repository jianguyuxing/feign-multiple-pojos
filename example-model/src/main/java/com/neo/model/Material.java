package com.neo.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 创意素材
 *
 * @author linjintian
 */
public class Material {

    private String title;

    /**
     * 广告来源
     */
    private String source;

    public Material() {
    }

    public Material(String title) {
        this.title = title;
    }

    public Material(String title, String source) {
        this.title = title;
        this.source = source;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
//        return "Material{" +
//                "title='" + title + '\'' +
//                ", source='" + source + '\'' +
//                '}';

        return JSONObject.toJSONString(this);
    }
}
