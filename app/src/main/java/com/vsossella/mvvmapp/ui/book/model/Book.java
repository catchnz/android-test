package com.vsossella.mvvmapp.ui.book.model;

/**
 * Created by vsossella on 26/06/17.
 */

public class Book {

    public String title;
    public String subtitle;
    public String content;

    public Book(String title, String subtitle, String content) {
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getContent() {
        return content;
    }
}
