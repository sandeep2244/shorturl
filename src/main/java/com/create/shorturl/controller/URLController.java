package com.create.shorturl.controller;


import com.create.shorturl.model.URLDto;

import com.create.shorturl.repository.URLService;
import com.create.shorturl.util.URLValidator;
import org.apache.tomcat.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@RestController
public class URLController {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLController.class);
    private final URLService urlService;

    public URLController(URLService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/short")
    public ResponseEntity createShortenUrl(@RequestBody @Valid final URLDto shortenRequest, HttpServletRequest request){
        try{
            LOGGER.info("Received url to shorten: " + shortenRequest.getUrl());
            String longUrl = shortenRequest.getUrl();
            String shortenedUrl = null;
            String existingUrl;
            if (URLValidator.INSTANCE.validateURL(longUrl)) {
                String localURL = request.getRequestURL().toString();
                existingUrl = urlService.findURL(shortenRequest.getUrl());
                if(existingUrl!=null){
                    shortenedUrl = existingUrl;
                }else {
                    shortenedUrl = urlService.shortenURL(localURL, shortenRequest.getUrl());
                }
                LOGGER.info("Shortened url to: " + shortenedUrl);
                return new ResponseEntity<>(shortenedUrl,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("InValid URL", HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
