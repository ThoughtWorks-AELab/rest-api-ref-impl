package com.thoughtworks.dps.restapirefimpl.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.dps.restapirefimpl.entities.ArticleType;

public class ArticleRequest {

    private final String title;
    private final String body;
    private boolean draft;
    private final String type;

    public ArticleRequest(@JsonProperty("title") String title,
                          @JsonProperty("body") String body,
                          @JsonProperty("draft") boolean draft,
                          @JsonProperty("type") String type) {

        this.title = title;
        this.body = body;
        this.draft = draft;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public boolean isDraft() {
        return draft;
    }

    public ArticleType getType() {
        return ArticleType.valueOf(type);
    }
}
