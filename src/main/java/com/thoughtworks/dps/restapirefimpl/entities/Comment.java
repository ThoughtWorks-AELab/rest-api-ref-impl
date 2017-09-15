package com.thoughtworks.dps.restapirefimpl.entities;

import com.thoughtworks.dps.restapirefimpl.resources.CommentRequest;

import java.util.UUID;

public class Comment {
    private final String id;
    private final String postId;
    private final String body;
    private final User author;

    public Comment(String id, String postId, String body, User principal) {

        this.id = id;
        this.postId = postId;
        this.body = body;
        this.author = principal;
    }

    public String getPostId() {
        return postId;
    }

    public static Comment fromRequest(CommentRequest commentRequest) {
        return new Comment(UUID.randomUUID().toString(), commentRequest.getPostId(), commentRequest.getBody(), null);
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Comment withAuthor(User principal) {
        return new Comment(id, postId, body, principal);
    }

    public User getAuthor() {
        return author;
    }
}
