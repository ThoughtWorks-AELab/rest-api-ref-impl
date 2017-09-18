package com.thoughtworks.dps.restapirefimpl.services;

import com.thoughtworks.dps.restapirefimpl.entities.Article;
import com.thoughtworks.dps.restapirefimpl.resources.ArticleRequest;
import com.thoughtworks.dps.restapirefimpl.entities.User;
import com.thoughtworks.dps.restapirefimpl.exceptions.ForbiddenException;
import com.thoughtworks.dps.restapirefimpl.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleService {
    private final Map<String, Article> articles = new HashMap<>();

    @Autowired
    public ArticleService() {
        articles.put("1", new Article("1", "Yay", "words", User.USERS.get(0), false));
    }
    public Collection<Article> getArticles() {
        return articles.values();
    }

    public Article createArticle(ArticleRequest request, User user) {

        Article article = new Article(UUID.randomUUID().toString(), request.getTitle(), request.getBody(), user, false);
        articles.put(article.getId(), article);
        return article;
    }

    public Optional<Article> getArticle(String id) {
        return Optional.ofNullable(articles.get(id));
    }

    public Optional<Article> deleteById(String id, User user) {
        Optional<Article> article = Optional.ofNullable(articles.get(id));
        if (!article.isPresent()) {
            return article;
        }
        if (article.get().getAuthor().equals(user)) {
            articles.remove(id);
            return article;
        }
        throw new ForbiddenException();
    }

    public void updateArticle(String id, ArticleRequest articleRequest, User user) {
        Optional<Article> article = Optional.ofNullable(articles.get(id));
        if (article.isPresent()) {
            if (article.get().getAuthor().equals(user)) {
                Article updatedArticle = new Article(
                        id,
                        articleRequest.getTitle(),
                        articleRequest.getBody(),
                        user,
                        articleRequest.isDraft());
                articles.put(id, updatedArticle);
                return;
            }
            throw new ForbiddenException();
        }
        throw new NotFoundException();
    }

    public void publish(String id, User user) {
        Optional<Article> article = Optional.ofNullable(articles.get(id));
        if (article.isPresent()) {
            if (article.get().getAuthor().equals(user)) {
                articles.put(id, article.get().withDraft(false));
                return;
            }
            throw new ForbiddenException();
        }
        throw new NotFoundException();
    }

    public boolean exists(String articleId) {
        return articles.containsKey(articleId);
    }
}
