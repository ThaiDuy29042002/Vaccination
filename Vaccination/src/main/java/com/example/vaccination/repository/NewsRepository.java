package com.example.vaccination.repository;

import com.example.vaccination.model.entity.News;

import java.util.List;

public interface NewsRepository {

    List<News> getAllNews();
    void createNews(News news);
    void updateNews(News news);
    void deleteNews(int id);

}
