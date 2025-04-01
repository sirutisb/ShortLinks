package com.example.shortlinks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByShortCode(String shortUrl);
    boolean existsByShortCode(String shortUrl);
}
