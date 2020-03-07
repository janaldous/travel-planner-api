package com.janaldous.travelplanner.service;

import java.util.List;

import com.janaldous.travelplanner.domain.Foo;

public interface IFooService {

	Foo findById(Long id);

	List<Foo> findAll();

	Long create(Foo resource);

	void update(Foo resource);

	void deleteById(Long id);

	Foo getById(Long id);

}
