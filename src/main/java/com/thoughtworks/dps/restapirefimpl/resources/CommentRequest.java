package com.thoughtworks.dps.restapirefimpl.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentRequest {

    private final String postId;
    private final String body;

    public CommentRequest(
            @JsonProperty("postId") String postId,
            @JsonProperty("body") String body) {

        this.postId = postId;
        this.body = body;
    }

    public String getPostId() {
        return postId;
    }

    public String getBody() {
        return body;
    }
}
