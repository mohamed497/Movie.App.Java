package com.example.tryonetask.pojo.reviews_data;

/**
 * Created by Alaa Moaataz on 2020-02-05.
 */
public class ReviewModel {


    private String id;
    private String author;
    private String content;

    public ReviewModel() {
    }

    public ReviewModel(String id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
