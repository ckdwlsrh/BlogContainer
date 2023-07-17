package com.myblog.backend.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myblog.backend.Entity.News;

@Repository
public interface NaverNewsRepository extends JpaRepository<News, String> {
    
}
