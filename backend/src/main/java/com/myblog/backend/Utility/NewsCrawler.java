package com.myblog.backend.Utility;

import java.io.IOException;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

import com.myblog.backend.Entity.News;

import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsCrawler {
    // 경제 뉴스 url
    private static final String naverNewsUrl = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=101";
    
    //연결 함수
    public void crawling() {
        Connection connection = Jsoup.connect(naverNewsUrl);

        Document document;
        try {
            document = connection.get();
            //연결 됬을경우
            List<News> list = getDataList(document);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    private List<News> getDataList(Document document){
        List<News> list = new ArrayList<>();

        Elements selects = document.select("ul.sh_list li");

        for(Element e: selects) {
            
            Element article = e.select("div.sh_text").first();
            String articleUrl = article.selectFirst("a").attr("href");
            String articleTitle = article.selectFirst("a").text();
            String articleSummary = article.selectFirst("div.sh_text_lede").text();
            String articleSite = article.selectFirst("div.sh_text_press").text();

            News news = News.builder()
                .newsTitle(articleTitle)
                .newsSummary(articleSummary)
                .newsSite(articleSite)
                .newsUrl(articleUrl)
                .build();
            list.add(news);
        }

        return list;
    }

}
