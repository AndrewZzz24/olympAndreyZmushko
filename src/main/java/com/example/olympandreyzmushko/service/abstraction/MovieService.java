package com.example.olympandreyzmushko.service.abstraction;

import com.example.olympandreyzmushko.controller.dto.MovieDto;
import com.example.olympandreyzmushko.model.MovieEntity;

import java.util.List;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
public interface MovieService {
    List<MovieEntity> getAllMovies();

    MovieEntity getMovieById(Long id);

    MovieEntity addMovie(MovieDto movieDto);

    MovieEntity updateMovie(Long id, MovieDto movie);

    Boolean deleteMovie(Long id);
}
