package com.myblog.backend.Utility;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myblog.backend.Entity.News;
import com.myblog.backend.Persistence.NaverNewsRepository;

import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Component
public class NewsCrawler {
    // 경제 뉴스 url
    private static final String naverNewsUrl = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=101";
    
    @Autowired
    private NaverNewsRepository naverNewsRepository;

    //연결 함수
    public void crawling() {
        Connection connection = Jsoup.connect(naverNewsUrl);

        Document document;
        try {
            // 네이버 뉴스 연결 시도
            document = connection.get();
            //연결 됬을경우
            List<News> articles = getNaverNewsData(document);
            // 디비에 저장
            naverNewsRepository.saveAll(articles);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    private void validate(News news) {
        //엔티티 자체 문제
        if (news == null) {
            throw new RuntimeException("undefined news entity.");
        }
        // 제목 문제
        if (news.getNewsTitle() == null) {
            throw new RuntimeException("unknown title.");
        }
        // 요약 문제
        if (news.getNewsSummary() == null) {
            throw new RuntimeException("unknown summary.");
        }
        // url문제
        if (news.getNewsUrl() == null) {
            throw new RuntimeException("unknown url.");

        }
        // 출처 사이트 문제
        if (news.getNewsSite() == null) {
            throw new RuntimeException("unknown site.");

        }
    }

    private List<News> getNaverNewsData(Document document){
        List<News> list = new ArrayList<>();

        Elements selects = document.select("ul.sh_list li");

        for(Element e: selects) {
            // 크롤링 파서 부분
            Element article = e.select("div.sh_text").first();
            String articleUrl = article.selectFirst("a").attr("href");
            String articleTitle = article.selectFirst("a").text();
            String articleSummary = article.selectFirst("div.sh_text_lede").text();
            String articleSite = article.selectFirst("div.sh_text_press").text();
            // 엔티티 생성
            News news = News.builder()
                .newsTitle(articleTitle)
                .newsSummary(articleSummary)
                .newsSite(articleSite)
                .newsUrl(articleUrl)
                .build();
            // 엔티티 검증
            validate(news);

            // 이미 같은 제목이 존재할 경우
            if (naverNewsRepository.findByNewsTitle(articleTitle) != null) continue;
            // 반환값에 저장
            list.add(news);
        }

        return list;
    }

}
