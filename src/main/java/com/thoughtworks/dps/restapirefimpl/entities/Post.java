package com.thoughtworks.dps.restapirefimpl.entities;

public class Post {

    private final String id;
    private final String title;
    private final String body;
    private final User author;

    public Post(String id, String title, String body, User author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public User getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }
}
