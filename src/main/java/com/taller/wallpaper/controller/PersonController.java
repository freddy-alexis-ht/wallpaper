package com.taller.wallpaper.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.taller.wallpaper.dto.PersonDTO;
import com.taller.wallpaper.exception.PersonNotFoundException;
import com.taller.wallpaper.model.Person;
import com.taller.wallpaper.model.Response;
import com.taller.wallpaper.services.IPerson;
import com.taller.wallpaper.services.IUploadFile;
import com.taller.wallpaper.util.Constans;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private IPerson personService;

    @Autowired
    private IUploadFile uploadService;

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
       return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable(name = "id") Long id) {
        Person personTemp = personService.findById(id);
        if(personTemp != null) {
            return ResponseEntity.ok(personTemp); 
        } else {
           throw new PersonNotFoundException(id);
        }
        
    }
    @PostMapping()
    public ResponseEntity<Response> add(@RequestBody PersonDTO dto) {
        
        Person person = Person.builder()
                            .firstName(dto.getFirstName())
                            .lastName(dto.getLastName())
                            .email(dto.getEmail())
                            .pass(dto.getPass())
                            .age(dto.getAge())
                            .createAt(new Date())
                            .build();
        personService.save(person);
        return ResponseEntity.ok().body(new Response(Constans.MESSAGE_SUCCESS));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable(name = "id") Long id, @RequestBody PersonDTO dto) {
        Person person = personService.findById(id);
        if(person != null) {
            person.setFirstName(dto.getFirstName());
            person.setLastName(dto.getLastName());
            personService.save(person);
            return ResponseEntity.ok(person); 
        } else {
            throw new PersonNotFoundException(id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> delete(@PathVariable(name = "id") Long id) {
        Person person = personService.findById(id);
        if(person != null) {
            personService.delete(id);
            return ResponseEntity.ok(person); 
        } else {
            throw new PersonNotFoundException(id);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PersonNotFoundException.class)
    public Map<String, String> handlePersonNotFoundException(PersonNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "No se encontró la persona con ID " + ex.getId());
        return response;
    }

    
}
