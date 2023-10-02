package com.example.vaccine.repository;

import com.example.vaccine.models.entity.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsTypeRepository extends JpaRepository<NewsType, Integer> {
}
