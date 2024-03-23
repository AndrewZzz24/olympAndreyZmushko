package com.example.olympandreyzmushko.service.abstraction;

import com.example.olympandreyzmushko.controller.dto.DirectorDto;
import com.example.olympandreyzmushko.model.DirectorEntity;

import java.util.List;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
public interface DirectorService {
    List<DirectorEntity> getAllDirectors();

    DirectorEntity getDirectorById(Long id);

    DirectorEntity addDirector(DirectorDto director);

    DirectorEntity updateDirectorInfo(Long id, DirectorDto director);

    Boolean deleteDirectorInfo(Long id);
}
