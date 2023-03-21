package com.taller.wallpaper.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PersonNotFoundException extends RuntimeException{
    private Long id;
}
