package com.janaldous.travelplanner.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.swagger.annotations.Api;

@Api
@RepositoryRestResource(collectionResourceRel = "place", path = "places")
public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {

}
