package com.create.shorturl.repository;

import com.create.shorturl.util.IDGenerate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class URLService {

    private static final Logger LOGGER = LoggerFactory.getLogger(URLService.class);
    private final URLRepository urlRepository;

    @Autowired
    public URLService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenURL(String localURL, String longUrl) {
        LOGGER.info("Shortening {}", longUrl);
        Long id = urlRepository.readLatestId();
        String uniqueID = IDGenerate.INSTANCE.createUniqueID(id);
        String baseString = formatLocalURLFromShortener(localURL);
        String shortenedURL = baseString + uniqueID;
        urlRepository.saveUrl(id,uniqueID,shortenedURL,longUrl);
        return shortenedURL;
    }

    private String formatLocalURLFromShortener(String localURL) {
        String[] addressComponents = localURL.split("/");
        // remove the endpoint (last index)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addressComponents.length - 1; ++i) {
            if(addressComponents[i].equals("http:") || addressComponents[i].equals("https:")){
                sb.append(addressComponents[i]+"//");
            }else {
                sb.append(addressComponents[i]);
            }
        }
        sb.append('/');
        return sb.toString();
    }


    public String findURL(String longUrl){
        return urlRepository.getUrl(longUrl);
    }




}
