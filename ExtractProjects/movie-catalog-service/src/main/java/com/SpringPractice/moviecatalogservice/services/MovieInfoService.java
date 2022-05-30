package com.SpringPractice.moviecatalogservice.services;

import com.SpringPractice.moviecatalogservice.models.CatalogItem;
import com.SpringPractice.moviecatalogservice.models.Movie;
import com.SpringPractice.moviecatalogservice.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class MovieInfoService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000"),//wait for this time and if doesn't happen call for timeout
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "5"),//look at last n request
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50"),//take 50% of the last request to examine the circuit break
                                                                                        // say if 3 of the last 6 request fail then circuit is gonna break
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000")//sleep of circuit break which determines the time of circuit to remain in break state
    })
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(),Movie.class);
        return new CatalogItem(movie.getMovieName(), movie.getDesc(), rating.getRating());
    }
    public CatalogItem getFallbackCatalogItem(Rating rating){
        return new CatalogItem("No movie","No desc", rating.getRating());
    }
}
