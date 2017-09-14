package com.thoughtworks.dps.restapirefimpl.resources;

import com.thoughtworks.dps.restapirefimpl.entities.Comment;

import java.util.List;

public class CommentCollection {
    private List<Comment> comments;

    public CommentCollection(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
