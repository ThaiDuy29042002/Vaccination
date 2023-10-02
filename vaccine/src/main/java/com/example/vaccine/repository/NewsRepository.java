package com.example.vaccine.repository;

import com.example.vaccine.models.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Integer> {
}
