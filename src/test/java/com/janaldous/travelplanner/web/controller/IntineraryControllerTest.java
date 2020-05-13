package com.janaldous.travelplanner.web.controller;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.janaldous.travelplanner.domain.Itinerary;

class IntineraryControllerTest {

	@Test
	void testGetItinerary() {
		ItineraryController itineraryController = new ItineraryController();
		Itinerary result = itineraryController.getItinerary();
		
		assertEquals(14, result.getDays());
	}

}
