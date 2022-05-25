package com.SpringPractice.ratingdataservice.resources;

import com.SpringPractice.ratingdataservice.models.Rating;
import com.SpringPractice.ratingdataservice.models.UserRatings;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingDataResource {

    @RequestMapping("/{movieId}")
    public Rating getRatings(@PathVariable("movieId") String movieId){

        return new Rating(movieId,"4");
    }

    @RequestMapping("/user/{userId}")
    public UserRatings getUserRatings(@PathVariable("userId") String userId){
        List<Rating> ratingList = Arrays.asList(
                new Rating("1234","4"),
                new Rating("5678","3")
        );
        UserRatings userRatings = new UserRatings();
        userRatings.setUserRatings(ratingList);
        return userRatings;
    }
}
