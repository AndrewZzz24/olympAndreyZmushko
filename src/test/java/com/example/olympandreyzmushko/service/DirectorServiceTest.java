package com.example.olympandreyzmushko.service;

import com.example.olympandreyzmushko.controller.dto.DirectorDto;
import com.example.olympandreyzmushko.model.DirectorEntity;
import com.example.olympandreyzmushko.repository.MovieRepository;
import com.example.olympandreyzmushko.service.abstraction.DirectorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
@SpringBootTest
public class DirectorServiceTest {

    @Autowired
    private DirectorService service;

    @Autowired
    private MovieRepository repository;

    @BeforeEach
    public void setup(){
        repository.deleteAll();
    }

    @Test
    public void testFindAll_ReturnEmpty() {
        var allMovies = service.getAllDirectors();
        assertEquals(new ArrayList<>(), allMovies);
    }

    @Test
    public void testFindAll_ReturnEntities() {
        setup();
        var directorEntity1 = new DirectorEntity(1L, "Nicolas Andreev");
        var directorEntity2 = new DirectorEntity(2L, "AVM");

        var director1 = new DirectorDto(1L, "Nicolas Andreev");
        var director2 = new DirectorDto(2L, "AVM");
        var res = service.addDirector(director1);
        var res3 = service.addDirector(director2);
        var allMovies = service.getAllDirectors();

        assertEquals(List.of(directorEntity1, directorEntity2), allMovies);
    }

    @Test
    public void testFindById_ReturnEntity() {
        setup();
        var directorEntity1 = new DirectorEntity(1L, "Nicolas Andreev");
        var directorEntity2 = new DirectorEntity(2L, "AVM");

        var director1 = new DirectorDto(1L, "Nicolas Andreev");
        var director2 = new DirectorDto(2L, "AVM");
        var res1 = service.addDirector(director1);
        assertEquals(directorEntity1, res1);
        var res2 = service.addDirector(director2);
        assertEquals(directorEntity2, res2);
        var movieById = service.getDirectorById(2L);

        assertEquals(directorEntity2, movieById);
    }

    @Test
    public void testAddMovieInfo_Success() {
        setup();
        var directorEntity1 = new DirectorEntity(1L, "Nicolas Andreev");
        var directorEntity2 = new DirectorEntity(2L, "AVM");

        var director1 = new DirectorDto(1L, "Nicolas Andreev");
        var director2 = new DirectorDto(2L, "AVM");

        var res1 = service.addDirector(director1);
        var res2 = service.addDirector(director2);
        assertEquals(directorEntity1, res1);
        assertEquals(directorEntity2, res2);
    }

}
