package com.myblog.backend.DTO;


import com.myblog.backend.Entity.News;

import lombok.Data;

@Data
public class NewsDTO {
    private String newsTitle;
    private String newsSummary;
    private String newsSite;
    private String newsUrl;
    public NewsDTO(final News article) {
        this.newsTitle = article.getNewsTitle();
        this.newsSummary = article.getNewsSummary();
        this.newsSite = article.getNewsSite();
        this.newsUrl = article.getNewsUrl();
    }
}