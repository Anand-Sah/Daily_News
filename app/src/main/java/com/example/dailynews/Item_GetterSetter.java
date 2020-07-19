package com.example.dailynews;

public class Item_GetterSetter {

    private String title;
    private String description;
    private String urlToImage;
    private String pageURL;
    private String publishedDate;
    private String publishedTime;

    public Item_GetterSetter(String title, String description,String pageURL, String urlToImage, String publishedDate, String publishedTime) {
        this.title = title;
        this.description = description;
        this.pageURL = pageURL;
        this.urlToImage = urlToImage;
        this.publishedDate = publishedDate;
        this.publishedTime = publishedTime;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPageURL() {
        return pageURL;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getPublishedTime() {
        return publishedTime;
    }
}
