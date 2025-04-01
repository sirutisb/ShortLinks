package com.example.shortlinks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LinkController {
    private final LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<Link> shortenLink(@RequestBody ShortenRequest shortenRequest) {
        Link link = linkService.shortenUrl(shortenRequest.getOriginalUrl());
        return ResponseEntity.ok(link);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<String> getLink(@PathVariable String shortCode) {
        String originalUrl = linkService.getOriginalUrl(shortCode);
        return ResponseEntity.ok(originalUrl);
    }
}

class ShortenRequest {
    private String originalUrl;

    public String getOriginalUrl() { return originalUrl; }
}
