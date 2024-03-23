package com.example.olympandreyzmushko.service;

import com.example.olympandreyzmushko.controller.dto.DirectorDto;
import com.example.olympandreyzmushko.exception.NoSuchDirectorException;
import com.example.olympandreyzmushko.model.DirectorEntity;
import com.example.olympandreyzmushko.repository.DirectorRepository;
import com.example.olympandreyzmushko.service.abstraction.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
@Service
public class DirectorServiceImpl implements DirectorService {

    private DirectorRepository directorRepository;

    @Autowired
    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public List<DirectorEntity> getAllDirectors() {
        return directorRepository.findAll();
    }

    @Override
    public DirectorEntity getDirectorById(Long id) {
        DirectorEntity foundDirector = directorRepository.findById(id)
                .orElseThrow(() -> new NoSuchDirectorException(String.format("Cannot find director with id=%s", id)));
        return foundDirector;
    }

    @Override
    public DirectorEntity addDirector(DirectorDto director) {
        var entityToSave = DirectorEntity.builder()
                .id(director.getId())
                .nameSurnamePatronymic(director.getNameSurnamePatronymic())
                .build();

        return directorRepository.save(entityToSave);
    }

    @Override
    public DirectorEntity updateDirectorInfo(Long id, DirectorDto director) {
        var existedDirector = getDirectorById(id);
        existedDirector.setNameSurnamePatronymic(director.getNameSurnamePatronymic());

        return directorRepository.save(existedDirector);
    }

    @Override
    public Boolean deleteDirectorInfo(Long id) {
        if (!directorRepository.existsById(id)) {
            return false;
        }
        directorRepository.deleteById(id);
        return true;
    }
}
