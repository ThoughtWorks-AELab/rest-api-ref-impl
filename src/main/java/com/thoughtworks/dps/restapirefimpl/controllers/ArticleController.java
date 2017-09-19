package com.thoughtworks.dps.restapirefimpl.controllers;

import com.thoughtworks.dps.restapirefimpl.entities.*;
import com.thoughtworks.dps.restapirefimpl.exceptions.NotFoundException;
import com.thoughtworks.dps.restapirefimpl.resources.CommentCollection;
import com.thoughtworks.dps.restapirefimpl.resources.ArticleCollection;
import com.thoughtworks.dps.restapirefimpl.resources.ArticleRequest;
import com.thoughtworks.dps.restapirefimpl.services.CommentService;
import com.thoughtworks.dps.restapirefimpl.services.ArticleService;
import com.thoughtworks.dps.restapirefimpl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    ArticleService articleService;
    private UserService userService;
    private CommentService commentService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService, CommentService commentService) {
        this.articleService = articleService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @RequestMapping(method = GET)
    public ResponseEntity<ArticleCollection> getAll(@RequestParam(value = "type", required = false) String type) {
        Collection<Article> articles = articleService.getArticlesByType(type);
        ArticleCollection response = new ArticleCollection(articles);
        return new ResponseEntity<>(response, OK);
    }

    @RequestMapping(method = POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Article> create(@RequestBody ArticleRequest article, Principal principal) {
        //yuck
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        Article createdArticle = articleService.createArticle(article, user);
        return new ResponseEntity<>(createdArticle, CREATED);
    }

    @RequestMapping(path = "/{id}", method = GET)
    public ResponseEntity<Article> getOne(@PathVariable String id) {
        Article article = articleService.getArticle(id).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(article, OK);
    }

    @RequestMapping(path = "/{id}", method = DELETE)
    public ResponseEntity<Article> delete(@PathVariable String id, Principal principal) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        Article article = articleService.deleteById(id, user).orElseThrow(NotFoundException::new);
        return new ResponseEntity<Article>(article, OK);
    }

    @RequestMapping(path = "/{id}/publish", method = POST)
    public ResponseEntity<Article> publish(@PathVariable String id, Principal principal) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        articleService.publish(id, user);
        return new ResponseEntity<Article>(NO_CONTENT);
    }

    @RequestMapping(path = "/{id}", method = PUT)
    public ResponseEntity<Article> put(@PathVariable String id, @RequestBody ArticleRequest article, Principal principal) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        articleService.updateArticle(id, article, user);
        return new ResponseEntity<Article>(NO_CONTENT);
    }

    @RequestMapping(path = "/{id}/comments", method = GET)
    public ResponseEntity<CommentCollection> getComments(@PathVariable String id) {
        articleService.getArticle(id).orElseThrow(NotFoundException::new);
        List<Comment> comments = commentService.getCommentsForArticle(id);
        return new ResponseEntity<CommentCollection>(new CommentCollection(comments), OK);
    }
}
