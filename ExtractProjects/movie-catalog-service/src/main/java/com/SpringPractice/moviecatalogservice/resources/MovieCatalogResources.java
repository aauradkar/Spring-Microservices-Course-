package com.SpringPractice.moviecatalogservice.resources;

import com.SpringPractice.moviecatalogservice.models.CatalogItem;
import com.SpringPractice.moviecatalogservice.models.Movie;
import com.SpringPractice.moviecatalogservice.models.Rating;
import com.SpringPractice.moviecatalogservice.models.UserRatings;
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
    @RequestMapping("/{userID}")
    public List<CatalogItem> getCatalog(@PathVariable("userID") String userID){
        
        //get ratings
        UserRatings userRatings = getUserRatings(userID);

        //get movies by calling movieInfo service
        return userRatings.getUserRatings().stream().map(rating -> {
            return getCatalogItem(rating);
        }).collect(Collectors.toList());

        /*
            This type of fallback mechanism will cause the fallback method to not invoke
            and that's why it fails. The main reason behind this is proxy class implementation
            by spring framework.
         */
    }
    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    private CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(),Movie.class);
        return new CatalogItem(movie.getMovieName(), movie.getDesc(), rating.getRating());
    }
    @HystrixCommand(fallbackMethod = "getFallbackUserRatings")
    private UserRatings getUserRatings(@PathVariable("userID") String userID) {
        return restTemplate.getForObject("http://movie-ratings-service/ratings/user/" + userID, UserRatings.class);
    }

    public List<CatalogItem> getFallbackCatalogItem(@PathVariable("userID") String userID){
        return Arrays.asList(new CatalogItem("No movie","No desc","No rating"));
    }

    public UserRatings getFallbackUserRatings(@PathVariable("userID") String userID){
        UserRatings userRatings = new UserRatings();
        userRatings.setUserId(userID);
        userRatings.setUserRatings(
                Arrays.asList(new Rating("0","0"))
        );
        return userRatings;
    }
}
