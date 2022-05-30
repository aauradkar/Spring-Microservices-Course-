package com.SpringPractice.moviecatalogservice.models;

import java.util.List;

public class UserRatings {
    private List<Rating> userRatings;
    private String userID;

    public List<Rating> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(List<Rating> userRatings) {
        this.userRatings = userRatings;
    }

    public void setUserId(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }
}
