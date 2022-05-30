package com.SpringPractice.movieinfoservice.models;


import com.fasterxml.jackson.annotation.JsonAlias;

public class MovieSummary {
    private String id;
    private String title;
    private String overview;
    @JsonAlias("popularity")
    private double popular;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopular() {
        return popular;
    }

    public void setPopular(double popular) {
        this.popular = popular;
    }
}
