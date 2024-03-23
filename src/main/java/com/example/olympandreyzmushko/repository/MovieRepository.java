package com.example.olympandreyzmushko.repository;

import com.example.olympandreyzmushko.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
}
