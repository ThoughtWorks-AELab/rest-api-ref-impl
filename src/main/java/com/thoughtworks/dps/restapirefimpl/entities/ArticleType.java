package com.thoughtworks.dps.restapirefimpl.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ArticleType {
    @JsonProperty("sports")
    SPORTS,
    @JsonProperty("general")
    GENERAL,
    @JsonProperty("arts")
    ARTS,
    @JsonProperty("literature")
    LITERATURE,
    @JsonProperty("world")
    WORLD,
    @JsonProperty("opinion")
    OPINION;

    public static ArticleType value(String string) {
        try {
            return valueOf(string);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
