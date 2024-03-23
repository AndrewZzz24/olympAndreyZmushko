package com.example.olympandreyzmushko.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
@Entity
@Table(name = "MovieEntity")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "year")
    private Integer year;

    @NotNull
    @Column(name = "director")
    private Long director;

    @NotNull
    @Column(name = "length")
    private LocalTime length;

    @NotNull
    @Column(name = "rating")
    private Integer rating;
}
