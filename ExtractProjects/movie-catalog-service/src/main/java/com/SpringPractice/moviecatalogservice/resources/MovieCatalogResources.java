package com.SpringPractice.moviecatalogservice.resources;

import com.SpringPractice.moviecatalogservice.models.CatalogItem;
import com.SpringPractice.moviecatalogservice.models.Movie;
import com.SpringPractice.moviecatalogservice.models.Rating;
import com.SpringPractice.moviecatalogservice.models.UserRatings;
import com.SpringPractice.moviecatalogservice.services.MovieInfoService;
import com.SpringPractice.moviecatalogservice.services.UserRatingInfoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")

public class MovieCatalogResources {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    MovieInfoService movieInfoService;

    @Autowired
    UserRatingInfoService userRatingInfoService;
    @RequestMapping("/{userID}")
    public List<CatalogItem> getCatalog(@PathVariable("userID") String userID){
        
        //get ratings
        UserRatings userRatings = userRatingInfoService.getUserRatings(userID);

        //get movies by calling movieInfo service
        return userRatings.getUserRatings().stream().map(rating ->
            movieInfoService.getCatalogItem(rating)
        ).collect(Collectors.toList());
    }
}
