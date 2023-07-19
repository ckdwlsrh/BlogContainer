package com.myblog.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myblog.backend.Utility.NewsCrawler;

@RestController
@RequestMapping("/api")
public class TestController {


    @Autowired
    private NewsCrawler newsCrawler;

    @GetMapping("/crawlingapi")
    public String crawlingapi() {
        newsCrawler.crawling();
        return "hello";
    }    
}