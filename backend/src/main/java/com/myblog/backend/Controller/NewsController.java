package com.myblog.backend.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.backend.DTO.NewsDTO;
import com.myblog.backend.DTO.ResponseDTO;
import com.myblog.backend.Entity.News;
import com.myblog.backend.Service.NewsService;

@RestController
public class NewsController {
    
    @Autowired
    private NewsService service;

    @GetMapping("/naverNewsApi")
    public ResponseEntity<?> getArticles() {
        try {
            List<News> articles = service.todayNews();

            List<NewsDTO> dtos = articles.stream().map(NewsDTO::new).collect(Collectors.toList());

            ResponseDTO<NewsDTO> response = ResponseDTO.<NewsDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        }
        catch(Exception e) {
            String error = e.getMessage();
            ResponseDTO<NewsDTO> response = ResponseDTO.<NewsDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
