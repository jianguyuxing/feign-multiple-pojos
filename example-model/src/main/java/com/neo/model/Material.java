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

    /**
     * 贴片时长
     */
    private Integer duration;

    public Material() {
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Material{" +
                "title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", duration=" + duration +
                '}';
    }
}
