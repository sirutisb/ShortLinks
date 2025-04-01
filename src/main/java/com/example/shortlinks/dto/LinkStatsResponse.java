package com.example.shortlinks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkStatsResponse {
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime createdAt;
    private int uses;
}
