package com.neo.model;


public class Material {

    private String title;

    private String source;

    private Integer duration;

    public Material() {
    }

    public Material(String title, String source, Integer duration) {
        this.title = title;
        this.source = source;
        this.duration = duration;
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
