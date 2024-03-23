package com.example.olympandreyzmushko.repository;

import com.example.olympandreyzmushko.controller.dto.DirectorDto;
import com.example.olympandreyzmushko.model.DirectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
@Repository
public interface DirectorRepository extends JpaRepository<DirectorEntity, Long> {
}
