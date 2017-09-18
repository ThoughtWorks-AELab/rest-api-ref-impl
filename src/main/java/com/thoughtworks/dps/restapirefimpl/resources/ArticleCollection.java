package com.thoughtworks.dps.restapirefimpl.resources;

import com.thoughtworks.dps.restapirefimpl.entities.Article;

import java.util.Collection;

public class ArticleCollection {
    private final Collection<Article> articles;

    public ArticleCollection(Collection<Article> articles) {
        this.articles = articles;
    }

    public Collection<Article> getArticles() {
        return articles;
    }
}
