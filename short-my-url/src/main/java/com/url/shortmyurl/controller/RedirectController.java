package com.url.shortmyurl.controller;

import com.url.shortmyurl.model.UrlMapping;
import com.url.shortmyurl.service.UrlMappingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RedirectController {

    private UrlMappingService urlMappingService;

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl){
        UrlMapping urlMapping = urlMappingService.getOriginalUrl(shortUrl);
        if (urlMapping != null) {


            String target = urlMapping.getOriginalUrl(); if (!target.matches("^[a-zA-Z][a-zA-Z0-9+.-]*://.*$")) {
                // add a default scheme if missing
                target = "https://" + target;
            }            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Location", target);
            return ResponseEntity.status(302).headers(httpHeaders).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}