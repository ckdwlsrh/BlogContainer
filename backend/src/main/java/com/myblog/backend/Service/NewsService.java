package com.myblog.backend.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.backend.Entity.News;
import com.myblog.backend.Persistence.NaverNewsRepository;

@Service
public class NewsService {
    @Autowired
    private NaverNewsRepository naverNewsRepository;

    public List<News> todayNews() {
        // today 0:0:0 ~ 23:59:59 
        LocalDateTime start = LocalDateTime.of(LocalDate.now(),LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));

        List<News> articles = naverNewsRepository.findByCreatedDateBetween(start, end);

        return articles;
    }


}
