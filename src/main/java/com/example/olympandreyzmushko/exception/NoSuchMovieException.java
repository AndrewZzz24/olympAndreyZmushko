package com.example.olympandreyzmushko.exception;

import lombok.NoArgsConstructor;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
@NoArgsConstructor
public class NoSuchMovieException extends RuntimeException{
    public NoSuchMovieException(String message){
        super(message);
    }
}
