package com.janaldous.travelplanner.util;

import com.janaldous.travelplanner.domain.Foo;
import com.janaldous.travelplanner.web.exception.ResourceNotFoundException;

public class RestPreconditions {
    public static <T> T checkFound(T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException();
        }
        return resource;
    }

	public static Foo checkNotNull(Foo resource) {
		if (resource == null) {
            throw new ResourceNotFoundException();
        }
        return resource;
	}
}