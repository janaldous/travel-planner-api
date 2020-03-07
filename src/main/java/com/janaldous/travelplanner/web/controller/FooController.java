package com.janaldous.travelplanner.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.google.common.base.Preconditions;
import com.janaldous.travelplanner.domain.Foo;
import com.janaldous.travelplanner.service.IFooService;
import com.janaldous.travelplanner.util.RestPreconditions;

@RestController
@RequestMapping("/foos")
class FooController {
 
    @Autowired
    private IFooService service;
 
    @GetMapping
    public List<Foo> findAll() {
        return service.findAll();
    }
 
    @GetMapping(value = "/{id}")
    public Foo findById(@PathVariable("id") Long id) {
        return RestPreconditions.checkFound(service.findById(id));
    }
 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Foo resource) {
        Preconditions.checkNotNull(resource);
        return service.create(resource);
    }
 
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Foo resource) {
        Preconditions.checkNotNull(resource);
        RestPreconditions.checkNotNull(service.getById(resource.getId()));
        service.update(resource);
    }
 
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }
 
}
