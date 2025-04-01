package com.example.shortlinks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LinkService {
    private final LinkRepository linkRepository;

    private static final int SHORT_CODE_LENGTH = 6;

    @Autowired
    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link shortenUrl(String originalUrl) {
        String shortCode;
        do {
            shortCode = generateShortCode();
        } while (linkRepository.findByShortCode(shortCode) != null);

        Link link = new Link(originalUrl, shortCode);
        return linkRepository.save(link);
    }

    public String getOriginalUrl(String shortCode) {
        return linkRepository.findByShortCode(shortCode).getOriginalUrl();
    }


    private String generateShortCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return sb.toString();
    }
}
