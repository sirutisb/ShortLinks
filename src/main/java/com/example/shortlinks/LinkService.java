package com.example.shortlinks;

import com.example.shortlinks.dto.LinkRequest;
import com.example.shortlinks.dto.LinkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LinkService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 6;
    private static final Random RANDOM = new Random();
    private static final String baseUrl = "http://localhost:8080/";

    private final LinkRepository linkRepository;

    @Autowired
    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public LinkResponse shortenUrl(LinkRequest request) {
        String shortCode = generateShortCode();
        Link link = new Link();
        link.setOriginalUrl(request.getUrl());
        link.setShortCode(shortCode);
        linkRepository.save(link);
        return new LinkResponse(request.getUrl(), baseUrl + shortCode);
    }

    public Link get(String shortCode) {
        return linkRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new IllegalArgumentException("No link found for " + shortCode));
    }

    public String getOriginalUrl(String shortCode) {
        Link link = get(shortCode);
        link.setUses(link.getUses() + 1);
        linkRepository.save(link);
        return link.getOriginalUrl();
    }


    private String generateShortCode() {
        String shortCode;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < CODE_LENGTH; i++) {
                sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
            }
            shortCode = sb.toString();
        } while (linkRepository.existsByShortCode(shortCode));
        return shortCode;
    }
}
