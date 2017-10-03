package com.payne.isaac.jsonlist.model;

/**
 * Created by isaac on 2/10/17.
 */

public class DataModel {

    private int id;
    private String title;
    private String subtitle;
    private String content;

    /**
     *  Simple class to represent the data coming from the JSON API
     * @param id The ID of the article
     * @param title The title of the article
     * @param subtitle The subtitle of the article
     * @param content The content of the article
     */
    public DataModel(int id, String title, String subtitle, String content) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
