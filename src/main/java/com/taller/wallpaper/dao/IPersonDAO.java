package com.taller.wallpaper.dao;

import com.taller.wallpaper.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface IPersonDAO extends CrudRepository<Person, Long>{

}
