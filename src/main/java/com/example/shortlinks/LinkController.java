package com.example.shortlinks;

import com.example.shortlinks.dto.LinkRequest;
import com.example.shortlinks.dto.LinkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api")
public class LinkController {
    private final LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<LinkResponse> shortenLink(@RequestBody LinkRequest request) {
        LinkResponse response = linkService.shortenUrl(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortCode}")
    public LinkResponse redirect(@PathVariable String shortCode) {
        String originalUrl = linkService.getOriginalUrl(shortCode);
        return new LinkResponse(originalUrl, "http://localhost:8080/" + shortCode);
    }
}