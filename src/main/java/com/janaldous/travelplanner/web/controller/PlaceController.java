package com.janaldous.travelplanner.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.janaldous.travelplanner.persistence.PlaceEntity;
import com.janaldous.travelplanner.persistence.PlaceRepository;
import com.janaldous.travelplanner.web.exception.ResourceNotFoundException;

import io.swagger.annotations.Api;

@Api
@RestController
public class PlaceController {

	@Autowired
	private PlaceRepository respository;
	
	@GetMapping("places")
	public List<PlaceEntity> getAllPlaces() {
		return respository.findAll();
	}
	
	@GetMapping("places/{id}")
	public PlaceEntity getPlace(@PathVariable Long id) {
		return respository.findById(id).orElseThrow(ResourceNotFoundException::new);
	}
	
}
