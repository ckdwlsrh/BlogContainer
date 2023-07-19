package com.myblog.backend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class News extends BaseTimeEntity {
    @Id
    private String newsTitle;
    private String newsSummary;
    private String newsSite;
    private String newsUrl;
}