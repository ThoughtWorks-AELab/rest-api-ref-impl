package com.thoughtworks.dps.restapirefimpl.entities;

import java.util.Collection;
import java.util.List;

public class PostCollection {
    private final Collection<Post> posts;

    public PostCollection(Collection<Post> posts) {
        this.posts = posts;
    }

    public Collection<Post> getPosts() {
        return posts;
    }
}
