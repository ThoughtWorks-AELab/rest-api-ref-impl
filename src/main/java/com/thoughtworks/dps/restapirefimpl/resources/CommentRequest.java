package com.thoughtworks.dps.restapirefimpl.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentRequest {

    private final String articleId;
    private final String body;

    public CommentRequest(
            @JsonProperty("articleId") String articleId,
            @JsonProperty("body") String body) {

        this.articleId = articleId;
        this.body = body;
    }

    public String getArticleId() {
        return articleId;
    }

    public String getBody() {
        return body;
    }
}
