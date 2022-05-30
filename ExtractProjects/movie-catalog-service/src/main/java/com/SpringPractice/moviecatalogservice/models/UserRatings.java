package com.SpringPractice.moviecatalogservice.models;

import java.util.List;

public class UserRatings {
    private List<Rating> userRatingsP;
    private String userID;

    public List<Rating> getUserRatings() {
        return userRatingsP;
    }

    public void setUserRatings(List<Rating> userRatings) {
        this.userRatingsP = userRatings;
    }

    public void setUserId(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }
}
