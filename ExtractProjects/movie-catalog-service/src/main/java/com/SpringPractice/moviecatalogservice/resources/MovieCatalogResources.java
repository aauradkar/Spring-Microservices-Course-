package com.SpringPractice.moviecatalogservice.resources;

import com.SpringPractice.moviecatalogservice.models.CatalogItem;
import com.SpringPractice.moviecatalogservice.models.Movie;
import com.SpringPractice.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
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
        List<Rating> ratingList = Arrays.asList(
                new Rating("1234","4"),
                new Rating("5678","3")
        );

        //get movies by calling movieInfo service
        return ratingList.stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(),Movie.class);
            return new CatalogItem(movie.getMovieName(), movie.getDesc(), rating.getRating());
        }).collect(Collectors.toList());
    }
}
