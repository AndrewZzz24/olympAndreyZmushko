package com.example.olympandreyzmushko.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
@Entity
@Table(name = "ProducerEntity")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "fio")
    private String nameSurnamePatronymic;
}
