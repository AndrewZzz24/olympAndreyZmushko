package com.example.olympandreyzmushko.controller.dto.request;

import com.example.olympandreyzmushko.controller.dto.DirectorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDirectorInfoRequest {
    private DirectorDto director;
}
