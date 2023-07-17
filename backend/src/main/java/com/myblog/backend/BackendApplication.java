package com.myblog.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.myblog.backend.Utility.NewsCrawler;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

		NewsCrawler a = new NewsCrawler();
		a.crawling();
	}

}
