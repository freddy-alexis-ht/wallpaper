package com.taller.wallpaper.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller.wallpaper.dao.IPersonDAO;
import com.taller.wallpaper.model.Person;
import com.taller.wallpaper.services.IPerson;

@Service
public class IPersonImpl implements IPerson{

    @Autowired
    private IPersonDAO personDAO;

    @Transactional
    @Override
    public List<Person> findAll() {
        return (List<Person>) personDAO.findAll();
    }

    @Transactional
    @Override
    public Person findById(Long Id) {
        return personDAO.findById(Id).orElse(null);
    }

    @Transactional
    @Override
    public void save(Person p) {
        personDAO.save(p);
    }

    @Transactional
    @Override
    public void delete(Long Id) {
       personDAO.deleteById(Id);
    }
    
}
