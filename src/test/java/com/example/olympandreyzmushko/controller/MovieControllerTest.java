package com.example.olympandreyzmushko.controller;

import com.example.olympandreyzmushko.model.MovieEntity;
import com.example.olympandreyzmushko.repository.MovieRepository;
import com.example.olympandreyzmushko.service.MovieServiceImpl;
import com.example.olympandreyzmushko.service.abstraction.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private MovieService movieService;

    @Test
    public void getAllMovies_Success() throws Exception {
        MovieServiceImpl movieService = Mockito.mock(MovieServiceImpl.class);
        when(movieService.getAllMovies()).thenReturn(List.of(MovieEntity.builder().id(1L).build(), MovieEntity.builder().id(2L).build()));
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/movies")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getMovieById_Success() throws Exception {
        when(movieService.getMovieById(3L)).thenReturn(MovieEntity.builder().id(3L).build());
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/movies/" + "3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
