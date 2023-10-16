package com.example.vaccination.service.impl;

import com.example.vaccination.repository.NewsRepository;
import com.example.vaccination.model.entity.News;
import com.example.vaccination.service.NewsServices;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsServices {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    //
    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public void createNews(News news) {
        LocalDate date = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = date.format(formatter);

        news.setPostdate(formattedDate);
        newsRepository.save(news);
    }

    @Override
    public News findbyId(int newsId) {
        return newsRepository.findById(newsId).orElse(null);
    }

    @Override
    public void updateNews(News news) {
        News existingNews = newsRepository.findById(news.getNewsId()).orElse(null);
        if (existingNews != null) {
            existingNews.setTitle(news.getTitle());
            existingNews.setPreview(news.getPreview());
            existingNews.setContent(news.getContent());

            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDate = date.format(formatter);
            existingNews.setPostdate(formattedDate);

            newsRepository.saveAndFlush(existingNews);
        }
    }

    @Override
    public void deleteNews(int Id) {
        newsRepository.deleteById(Id);
    }

}

