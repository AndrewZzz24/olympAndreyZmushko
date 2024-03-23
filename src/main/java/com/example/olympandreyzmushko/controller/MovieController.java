package com.example.olympandreyzmushko.controller;

import com.example.olympandreyzmushko.controller.dto.request.AddMovieInfoRequest;
import com.example.olympandreyzmushko.controller.dto.request.UpdateMovieInfoRequest;
import com.example.olympandreyzmushko.exception.NoSuchMovieException;
import com.example.olympandreyzmushko.model.MovieEntity;
import com.example.olympandreyzmushko.service.abstraction.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<?> getAllMovies() {
        var allMovies = movieService.getAllMovies();
        return ResponseEntity.ok(Map.of("list", allMovies));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable("id") Long id) {
        MovieEntity movie;
        try {
            movie = movieService.getMovieById(id);
        } catch (NoSuchMovieException e) {
            return new ResponseEntity<>(String.format("Cannot find movie with id=%s", id), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("status", 500, "reason", e.getMessage()));
        }
        return ResponseEntity.ok(Map.of("movie", movie));
    }

    @PostMapping
    public ResponseEntity<?> addMovieInfo(@RequestBody AddMovieInfoRequest request) {
        MovieEntity createdMovieInfo;
        try {
            createdMovieInfo = movieService.addMovie(request.getMovie());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("status", 500, "reason", e.getMessage()));
        }
        return ResponseEntity.ok(Map.of("movie", createdMovieInfo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateMovieInfo(@PathVariable("id") Long id, @RequestBody UpdateMovieInfoRequest request) {
        MovieEntity updatedMovieInfo;
        try {
            updatedMovieInfo = movieService.updateMovie(id, request.getMovie());
            if (updatedMovieInfo == null) {
                return ResponseEntity.internalServerError().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("status", 500, "reason", e.getMessage()));
        }
        return ResponseEntity.ok(Map.of("movie", updatedMovieInfo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") Long id) {
        try {
            boolean deletedMovie = movieService.deleteMovie(id);
            if (!deletedMovie) {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("status", 500, "reason", e.getMessage()));
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
