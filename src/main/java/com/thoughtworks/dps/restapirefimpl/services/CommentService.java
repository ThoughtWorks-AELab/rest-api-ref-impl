package com.thoughtworks.dps.restapirefimpl.services;

import com.thoughtworks.dps.restapirefimpl.entities.Comment;
import com.thoughtworks.dps.restapirefimpl.entities.User;
import com.thoughtworks.dps.restapirefimpl.exceptions.BadRequestException;
import com.thoughtworks.dps.restapirefimpl.resources.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service
public class CommentService {
    Map<String, Comment> comments = new HashMap<>();
    private PostService postService;

    @Autowired
    public CommentService(PostService postService) {
        this.postService = postService;
    }

    public List<Comment> getCommentsForPost(String id) {
        return comments.values().stream()
                .filter(c -> c.getPostId().equals(id))
                .collect(toList());
    }

    public Comment createComment(CommentRequest commentRequest, User principal) {
        if (!postService.exists(commentRequest.getPostId())) {
            throw new BadRequestException(String.format("no post with id %s", commentRequest.getPostId()));
        }
        Comment comment = Comment.fromRequest(commentRequest).withAuthor(principal);
        comments.put(comment.getId(), comment);
        return comment;
    }
}
