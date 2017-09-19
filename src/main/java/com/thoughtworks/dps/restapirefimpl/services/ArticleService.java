package com.thoughtworks.dps.restapirefimpl.services;

import com.thoughtworks.dps.restapirefimpl.entities.Article;
import com.thoughtworks.dps.restapirefimpl.entities.ArticleType;
import com.thoughtworks.dps.restapirefimpl.resources.ArticleRequest;
import com.thoughtworks.dps.restapirefimpl.entities.User;
import com.thoughtworks.dps.restapirefimpl.exceptions.ForbiddenException;
import com.thoughtworks.dps.restapirefimpl.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class ArticleService {
    private final Map<String, Article> articles = new HashMap<>();

    @Autowired
    public ArticleService() {
        articles.put("1", new Article("1", "An expose on the arts", "Painting forgery reaches an all-time high", User.USERS.get(0), true, ArticleType.ARTS));
        articles.put("2", new Article("2", "Current affairs", "The world is crashing down", User.USERS.get(0), true, ArticleType.WORLD));
        articles.put("3", new Article("3", "Jake Butt wins toilet paper sponsorship", "Michigan tight end Jake Butt looks to move into the NFL with a sponsorship from Charmin", User.USERS.get(1), true, ArticleType.SPORTS));
        articles.put("4", new Article("4", "Taco Charlton awarded scholarship from a Mexican restaurant", "Michigan defensive end Taco Charlton looks to move into the NFL playing for the Dallas Cowboys with a sponsorship from local Mexican restaurant Taco Bueno", User.USERS.get(1), true, ArticleType.SPORTS));
        articles.put("5", new Article("5", "Are e-readers putting bookstores out of business?", "Local bookstores offer something that the big e-readers can't: community", User.USERS.get(1), true, ArticleType.LITERATURE));
    }

    public Collection<Article> getArticles() {
        return articles.values();
    }

    public Article createArticle(ArticleRequest request, User user) {
        Article article = new Article(UUID.randomUUID().toString(), request.getTitle(), request.getBody(), user, true, request.getType());
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
                        articleRequest.isDraft(),
                        articleRequest.getType());
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

    public Collection<Article> getArticlesByType(String type) {
        if (type == null) {
            return getArticles();
        }
        ArticleType articleType = ArticleType.valueOf(type);
        Collection<Article> articles = getArticles();
        return articles.stream().filter(article -> article.getType().equals(articleType)).collect(toList());
    }
}
