package com.sahil.newscenter;

public class NewsObj {
    private  String title;
    private  String description;
    private  String imgUrl;
    private  String author;
    private  String webUrl;
    private  String publishDate;

    public NewsObj(){}
    public NewsObj(String title, String description, String imgUrl, String author, String webUrl, String publishDate){
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.author = author;
        this.webUrl = webUrl;
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
