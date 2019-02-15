package com.neo.model;

import java.util.List;

/**
 * 创意素材
 *
 * @author linjintian
 */
public class Material {

    /**
     * 小米 创意Id
     */
    private String materialId;
    /**
     * DSP 创意Id
     */
    private String creativeId;
    /**
     * 广告主Id
     */
    private String advId;
    /**
     * 模版Id
     */
    private String templateId;
    /**
     * 标题
     */
    private String title;
    /**
     * 广告来源
     */
    private String source;
    /**
     * 图片地址
     */
    private List<String> imgUrl;
    /**
     * 目标地址
     */
    private String landingUrl;
    /**
     * 下载链接
     */
    private String actionUrl;
    /**
     * 视频地址
     */
    private String videoUrl;
    /**
     * 下载应用包名
     */
    private String packageName;
    /**
     * 贴片时长
     */
    private Integer duration;

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(String creativeId) {
        this.creativeId = creativeId;
    }

    public String getAdvId() {
        return advId;
    }

    public void setAdvId(String advId) {
        this.advId = advId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
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

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLandingUrl() {
        return landingUrl;
    }

    public void setLandingUrl(String landingUrl) {
        this.landingUrl = landingUrl;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Material [creativeId=" + creativeId + ", advId=" + advId + ", templateId=" + templateId + ", title=" + title + ", source=" + source + ", imgUrl=" + imgUrl + ", landingUrl="
                + landingUrl + ", actionUrl=" + actionUrl + ", videoUrl=" + videoUrl + ", packageName=" + packageName + ", duration=" + duration + "]";
    }
}
