package com.SpringPractice.moviecatalogservice.models;

public class CatalogItem {
    private String movieName;
    private String desc;
    private String rating;


    public CatalogItem(String movieName, String desc, String rating) {
        this.movieName = movieName;
        this.desc = desc;
        this.rating = rating;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
