package com.example.olympandreyzmushko.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectorDto {
    private Long id;
    private String nameSurnamePatronymic;
}
