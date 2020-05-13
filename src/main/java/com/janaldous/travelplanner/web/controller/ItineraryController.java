package com.janaldous.travelplanner.web.controller;

import org.springframework.web.bind.annotation.GetMapping;

import com.janaldous.travelplanner.domain.Itinerary;

public class ItineraryController {

	@GetMapping("itinerary")
	public Itinerary getItinerary() {
		return Itinerary.builder().days(14).build();
	}
	
}
