package com.thoughtworks.dps.restapirefimpl.services;

import com.thoughtworks.dps.restapirefimpl.entities.Post;
import com.thoughtworks.dps.restapirefimpl.entities.PostRequest;
import com.thoughtworks.dps.restapirefimpl.entities.User;
import com.thoughtworks.dps.restapirefimpl.exceptions.BadRequestException;
import com.thoughtworks.dps.restapirefimpl.exceptions.ForbiddenException;
import com.thoughtworks.dps.restapirefimpl.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {
    private final Map<String, Post> posts = new HashMap<>();

    @Autowired
    public PostService() {
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
        return Optional.ofNullable(posts.get(id));
    }

    public Optional<Post> deleteById(String id, User user) {
        Optional<Post> post = Optional.ofNullable(posts.get(id));
        if (!post.isPresent()) {
            return post;
        }
        if (post.get().getAuthor().equals(user)) {
            posts.remove(id);
            return post;
        }
        throw new ForbiddenException();
    }

    public void updatePost(String id, PostRequest postRequest, User user) {
        Optional<Post> post = Optional.ofNullable(posts.get(id));
        if (post.isPresent()) {
            if (post.get().getAuthor().equals(user)) {
                Post updatedPost = new Post(
                        id,
                        postRequest.getTitle(),
                        postRequest.getBody(),
                        user
                );
                posts.put(id, updatedPost);
                return;
            }
            throw new ForbiddenException();
        }
        throw new NotFoundException();
    }
}
