package com.myblog.backend.Persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myblog.backend.Entity.News;

@Repository
public interface NaverNewsRepository extends JpaRepository<News, String> {

    News findByNewsTitle(String newsTitle);
    List<News> findByCreatedDateBetween(LocalDateTime start, LocalDateTime end);
}
