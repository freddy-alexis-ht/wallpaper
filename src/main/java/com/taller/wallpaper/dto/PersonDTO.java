package com.taller.wallpaper.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PersonDTO {
    private String firstName;
    private String lastName;
    private String age;
    private String email;
    private String pass;
    private String foto;
}
