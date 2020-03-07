package com.janaldous.travelplanner.domain;

import lombok.Data;

@Data
public class Place {
	
	private long id;
	private double lat;
	private double lng;
	private String area;
	private int day;
	private TimeOfDay timeOfDay;
	private PlaceType typeOfPlace;
	private String address;
	private String name;
	
}
