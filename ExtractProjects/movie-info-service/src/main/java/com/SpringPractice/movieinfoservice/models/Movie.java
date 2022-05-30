package com.SpringPractice.movieinfoservice.models;

public class Movie {
    private String movieId;
    private String movieName;
    private String desc;
    private double popularity;

    public Movie(String movieId, String movieName, String desc,double popularity) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.desc = desc;
        this.popularity = popularity;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
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

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
