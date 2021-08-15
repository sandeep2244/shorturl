package com.create.shorturl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class URLDto {
    private String url;

    @JsonCreator
    public URLDto() {

    }

    @JsonCreator
    public URLDto(@JsonProperty("url") String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
