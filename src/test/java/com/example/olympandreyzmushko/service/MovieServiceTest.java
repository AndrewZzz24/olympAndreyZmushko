package com.example.olympandreyzmushko.service;

import com.example.olympandreyzmushko.controller.dto.MovieDto;
import com.example.olympandreyzmushko.model.MovieEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
@SpringBootTest
public class MovieServiceTest {

    @Autowired
    private MovieServiceImpl service;


    @Test
    public void testFindAll_ReturnEmpty() {
        var allMovies = service.getAllMovies();
        assertEquals(new ArrayList<>(), allMovies);
    }

    @Test
    public void testFindAll_ReturnEntities() {
        var movieEntity1 = new MovieEntity(1L, "how to do web", 20, 2L, LocalTime.parse("03:02:00"), 2);
        var movieEntity2 = new MovieEntity(2L, "how to handle linux", 40, 2L, LocalTime.parse("05:02:00"), 4);

        var movie1 = new MovieDto(1L, "how to do web", 20, 2L, "03:02:00", 2);
        var movie2 = new MovieDto(2L, "how to handle linux", 40, 2L, "05:02:00", 4);
        var res = service.addMovie(movie1);
        var res3 = service.addMovie(movie2);
        var allMovies = service.getAllMovies();

        assertEquals(List.of(movieEntity1, movieEntity2), allMovies);
    }

    @Test
    public void testFindById_ReturnEntity() {
        var movieEntity1 = new MovieEntity(1L, "how to do web", 20, 2L, LocalTime.parse("03:02:00"), 2);
        var movieEntity2 = new MovieEntity(2L, "how to handle linux", 40, 2L, LocalTime.parse("05:02:00"), 4);

        var movie1 = new MovieDto(1L, "how to do web", 20, 2L, "03:02:00", 2);
        var movie2 = new MovieDto(2L, "how to handle linux", 40, 2L, "05:02:00", 4);
        var res1 = service.addMovie(movie1);
        assertEquals(movieEntity1, res1);
        var res2 = service.addMovie(movie2);
        assertEquals(movieEntity2, res2);
        var movieById = service.getMovieById(2L);

        assertEquals(movieEntity2, movieById);
    }

    @Test
    public void testAddMovieInfo_Success() {
        var movieEntity1 = new MovieEntity(1L, "how to do web", 20, 2L, LocalTime.parse("03:02:00"), 2);
        var movieEntity2 = new MovieEntity(2L, "how to handle linux", 40, 2L, LocalTime.parse("05:02:00"), 4);

        var movie1 = new MovieDto(1L, "how to do web", 20, 2L, "03:02:00", 2);
        var movie2 = new MovieDto(2L, "how to handle linux", 40, 2L, "05:02:00", 4);

        var res1 = service.addMovie(movie1);
        var res2 = service.addMovie(movie2);
        assertEquals(movieEntity1, res1);
        assertEquals(movieEntity2, res2);
    }

}
