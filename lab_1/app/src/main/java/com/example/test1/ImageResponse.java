package com.example.test1;

import java.util.List;

public class ImageResponse {
    private List<ImageResult> hits;

    public List<ImageResult> getHits() {
        return hits;
    }
}

class ImageResult {
    private String largeImageURL;

    public String getLargeImageURL() {
        return largeImageURL;
    }
}
