package com.thoughtworks.dps.restapirefimpl.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostRequest {

    private final String title;
    private final String body;

    public PostRequest(@JsonProperty("title") String title, @JsonProperty("body") String body) {

        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
