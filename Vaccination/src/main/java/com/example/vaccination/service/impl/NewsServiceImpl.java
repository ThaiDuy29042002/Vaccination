package com.example.vaccination.service.impl;

import com.example.vaccination.model.entity.News;
import com.example.vaccination.repository.NewsRepository;
import com.example.vaccination.service.NewsService;

public class NewsServiceImpl implements NewsService {
    private NewsRepository newsRepository;

    @Override
    public String createNews(News news) {
        return "/createNews";
    }

    @Override
    public void updateNews(News news) {
        newsRepository.updateNews(news);
    }

    @Override
    public void deleteNews(int id) {
        newsRepository.deleteNews(id);
    }
}
