package com.example.tryonetask.pojo.videos_data;

/**
 * Created by Alaa Moaataz on 2020-02-04.
 */
public class VideoModel {

    private String id;
    private String site;
    private String key;

    public VideoModel(String id, String site, String key) {
        this.id = id;
        this.site = site;
        this.key = key;
    }

    public VideoModel() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
