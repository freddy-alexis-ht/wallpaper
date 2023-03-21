package com.taller.wallpaper.services;

import java.util.List;

import com.taller.wallpaper.model.Person;

public interface IPerson {
    List<Person> findAll();
    Person findById(Long Id);
    void save(Person p);
    void delete(Long Id);
}
