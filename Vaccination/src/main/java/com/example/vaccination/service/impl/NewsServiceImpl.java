package com.example.vaccination.service.impl;

import com.example.vaccination.repository.NewsRepository;
import com.example.vaccination.model.entity.News;
import com.example.vaccination.service.NewsServices;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsServices {
    private final NewsRepository newsRepository;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

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

    public List<News> findAllByOrderByPostdateDesc() {
        return newsRepository.findAllByOrderByPostdateDesc();
    }

    @Override
    public void createNews(News news) {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        news.setPostdate(date);
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
            existingNews.setPostdate(new Date());

            newsRepository.saveAndFlush(existingNews);
        }
    }

    @Override
    public void deleteNews(int Id) {
        newsRepository.deleteById(Id);
    }

}

