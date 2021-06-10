package com.example.googlebooksapp;

public class Book {
    private String title;
    private String author;
    private int pagesCount;
    private String time;
    private String coverImage;
    private double rating;

    public Book(){}
    public Book(String title, String author,int pages,String time, String img, double rating){
        this.title=title;
        this.author=author;
        this.pagesCount=pages;
        this.time=time;
        this.coverImage=img;
        this.rating=rating;

    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
