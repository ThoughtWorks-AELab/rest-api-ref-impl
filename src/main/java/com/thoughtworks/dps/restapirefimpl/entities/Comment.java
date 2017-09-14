package com.thoughtworks.dps.restapirefimpl.entities;

import com.thoughtworks.dps.restapirefimpl.resources.CommentRequest;

import java.security.Principal;
import java.util.UUID;

public class Comment {
    private final String id;
    private final String postId;
    private final String body;

    public Comment(String id, String postId, String body) {

        this.id = id;
        this.postId = postId;
        this.body = body;
    }

    public String getPostId() {
        return postId;
    }

    public static Comment fromRequest(CommentRequest commentRequest) {
        return new Comment(UUID.randomUUID().toString(), commentRequest.getPostId(), commentRequest.getBody());
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Comment withAuthor(User principal) {
        return null;
    }
}
