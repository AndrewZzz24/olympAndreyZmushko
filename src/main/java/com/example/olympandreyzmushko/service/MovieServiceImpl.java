package com.example.olympandreyzmushko.service;

import com.example.olympandreyzmushko.controller.dto.MovieDto;
import com.example.olympandreyzmushko.exception.NoSuchMovieException;
import com.example.olympandreyzmushko.model.MovieEntity;
import com.example.olympandreyzmushko.repository.MovieRepository;
import com.example.olympandreyzmushko.service.abstraction.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieEntity> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public MovieEntity getMovieById(Long id) {
        MovieEntity foundMovie = movieRepository.findById(id)
                .orElseThrow(() -> new NoSuchMovieException(String.format("Cannot find movie with id=%s", id)));
        return foundMovie;
    }

    @Override
    public MovieEntity addMovie(MovieDto movieDto) {
        var entityToSave = MovieEntity.builder()
                .id(movieDto.getId())
                .title(movieDto.getTitle())
                .year(movieDto.getYear())
                .director(movieDto.getDirector())
                .length(LocalTime.parse(movieDto.getLength()))
                .rating(movieDto.getRating())
                .build();

        return movieRepository.save(entityToSave);
    }

    @Override
    @Transactional
    public MovieEntity updateMovie(Long id, MovieDto movie) {
        var existedMovie = getMovieById(id);
        existedMovie.setTitle(movie.getTitle());
        existedMovie.setDirector(movie.getDirector());
        existedMovie.setLength(LocalTime.parse(movie.getLength()));
        existedMovie.setRating(movie.getRating());

        return movieRepository.save(existedMovie);
    }

    @Override
    public Boolean deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            return false;
        }
        movieRepository.deleteById(id);
        return true;
    }
}
