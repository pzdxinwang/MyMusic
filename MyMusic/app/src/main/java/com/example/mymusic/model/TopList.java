package com.example.mymusic.model;


public class TopList {
    private String updateFrequency;
    private String coverImgUrl;
    private String description;
    private String name;
    private String id;

    public TopList(String updateFrequency, String coverImgUrl, String description, String name, String id) {
        this.updateFrequency = updateFrequency;
        this.coverImgUrl = coverImgUrl;
        this.description = description;
        this.name = name;
        this.id = id;
    }

    public String getUpdateFrequency() {
        return updateFrequency;
    }

    public void setUpdateFrequency(String updateFrequency) {
        this.updateFrequency = updateFrequency;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
