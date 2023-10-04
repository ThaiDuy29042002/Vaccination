package com.example.vaccination.service;

import com.example.vaccination.model.entity.News;

public interface NewsService {
    String createNews(News news);
    void updateNews(News news);
    void deleteNews(int id);
}
