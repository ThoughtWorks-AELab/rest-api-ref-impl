package com.thoughtworks.dps.restapirefimpl.services;

import com.thoughtworks.dps.restapirefimpl.entities.Post;
import com.thoughtworks.dps.restapirefimpl.entities.PostRequest;
import com.thoughtworks.dps.restapirefimpl.entities.User;
import com.thoughtworks.dps.restapirefimpl.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {
    private final Map<String, Post> posts = new HashMap<>();
    private UserService userService;

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
        posts.put("1", new Post("1", "Yay", "words", User.USERS.get(0)));
    }
    public Collection<Post> getPosts() {
        return posts.values();
    }

    public Post createPost(PostRequest request, User user) {

        Post post = new Post(UUID.randomUUID().toString(), request.getTitle(), request.getBody(), user);
        posts.put(post.getId(), post);
        return post;
    }

    public Optional<Post> getPost(String id) {
        return null;
    }

    public Optional<Post> deleteById(String id) {
        Optional<Post> post = Optional.ofNullable(posts.get(id));
        if (!post.isPresent()) {
            return post;
        }
        return null;
    }
}
