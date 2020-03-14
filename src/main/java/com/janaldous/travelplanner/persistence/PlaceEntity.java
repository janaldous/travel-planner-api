package com.janaldous.travelplanner.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.janaldous.travelplanner.domain.PlaceType;
import com.janaldous.travelplanner.domain.TimeOfDay;

import lombok.Data;

@Data
@Entity
public class PlaceEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private float lat;
	private float lng;
	private String name;
	private String description;
	private String address;
	private int day;
	private TimeOfDay timeOfDay;
	private PlaceType typeOfPlace;
}
