package com.SpringPractice.ratingdataservice.resources;

import com.SpringPractice.ratingdataservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingData {

    @RequestMapping("/{movieId}")
    public Rating getRatings(@PathVariable("movieId") String movieId){

        return new Rating(movieId,"4");
    }
}
