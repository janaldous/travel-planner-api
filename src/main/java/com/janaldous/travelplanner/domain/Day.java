package com.janaldous.travelplanner.domain;

import java.util.List;

import lombok.Data;

@Data
public class Day {
	
	private long id;
	private int day;
	private List<Place> placesToGo;
	private Place accommodation;
	
}
