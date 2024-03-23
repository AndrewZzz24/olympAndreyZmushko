package com.example.olympandreyzmushko.exception;

import lombok.NoArgsConstructor;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
@NoArgsConstructor
public class NoSuchDirectorException extends RuntimeException{
    public NoSuchDirectorException(String message){
        super(message);
    }
}
