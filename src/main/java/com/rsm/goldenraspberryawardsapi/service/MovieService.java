package com.rsm.goldenraspberryawardsapi.service;

import com.rsm.goldenraspberryawardsapi.entity.Movie;
import com.rsm.goldenraspberryawardsapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }
}
