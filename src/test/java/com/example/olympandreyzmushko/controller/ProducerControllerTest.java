package com.example.olympandreyzmushko.controller;

import com.example.olympandreyzmushko.model.DirectorEntity;
import com.example.olympandreyzmushko.model.DirectorEntity;
import com.example.olympandreyzmushko.service.DirectorServiceImpl;
import com.example.olympandreyzmushko.service.abstraction.DirectorService;
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
public class ProducerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private DirectorService directorService;

    @Test
    public void getAllDirectors_Success() throws Exception {
        DirectorServiceImpl directorService = Mockito.mock(DirectorServiceImpl.class);
        when(directorService.getAllDirectors()).thenReturn(List.of(DirectorEntity.builder().id(1L).build(), DirectorEntity.builder().id(2L).build()));
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/directors")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getDirectorById_Success() throws Exception {
        when(directorService.getDirectorById(3L)).thenReturn(DirectorEntity.builder().id(3L).build());
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/directors/" + "3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
