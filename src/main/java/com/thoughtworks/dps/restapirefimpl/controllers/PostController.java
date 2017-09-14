package com.thoughtworks.dps.restapirefimpl.controllers;

import com.thoughtworks.dps.restapirefimpl.entities.Post;
import com.thoughtworks.dps.restapirefimpl.entities.PostCollection;
import com.thoughtworks.dps.restapirefimpl.entities.PostRequest;
import com.thoughtworks.dps.restapirefimpl.entities.User;
import com.thoughtworks.dps.restapirefimpl.exceptions.NotFoundException;
import com.thoughtworks.dps.restapirefimpl.services.PostService;
import com.thoughtworks.dps.restapirefimpl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    PostService postService;
    private UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping(method = GET)
    public ResponseEntity<PostCollection> getAll() {
        Collection<Post> posts = postService.getPosts();
        PostCollection response = new PostCollection(posts);
        return new ResponseEntity<>(response, OK);
    }

    @RequestMapping(method = POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Post> create(@RequestBody PostRequest post, Principal principal) {
        //yuck
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        Post createdPost = postService.createPost(post, user);
        return new ResponseEntity<>(createdPost, CREATED);
    }

    @RequestMapping(path = "/{id}", method = GET)
    public ResponseEntity<Post> getOne(@PathVariable String id) {
        Post post = postService.getPost(id).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(post, OK);
    }

    @RequestMapping(path = "/{id}", method = DELETE)
    public ResponseEntity<Post> delete(@PathVariable String id, Principal principal) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        Post post = postService.deleteById(id, user).orElseThrow(NotFoundException::new);
        return new ResponseEntity<Post>(post, OK);
    }

    @RequestMapping(path = "/{id}", method = PUT)
    public ResponseEntity<Post> put(@PathVariable String id, @RequestBody PostRequest post, Principal principal) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        postService.updatePost(id, post, user);
        return new ResponseEntity<Post>(NO_CONTENT);
    }
}
