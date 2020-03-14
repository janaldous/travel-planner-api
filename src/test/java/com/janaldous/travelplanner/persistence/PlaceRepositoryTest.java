package com.janaldous.travelplanner.persistence;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class PlaceRepositoryTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PlaceRepository personRepository;
	
	@Autowired
	private ObjectMapper mapper;

	@BeforeEach
	public void deleteAllBeforeTests() throws Exception {
		personRepository.deleteAll();
	}

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {

		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(
				jsonPath("$._links.place").exists());
	}

	@Test
	public void shouldCreateEntity() throws Exception {
		PlaceEntity place = getMockPlace();
		
		mockMvc.perform(post("/places")
				.content(mapper.writeValueAsString(place)))
		.andDo(print())
		.andExpect(status().isCreated());
	}

	@Test
	public void shouldRetrieveEntity() throws Exception {
		PlaceEntity place = getMockPlace();

		MvcResult mvcResult = mockMvc.perform(post("/places")
				.content(mapper.writeValueAsString(place)))
				.andExpect(status().isCreated())
				.andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(get(location))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value(place.getName())).andExpect(
						jsonPath("$.description").value(place.getDescription()));
	}

	private PlaceEntity getMockPlace() {
		PlaceEntity place = new PlaceEntity();
		place.setName("Monkey Forest");
		place.setDescription("Forest with monkeys");
		place.setAddress("Ubud");
		return place;
	}

	@Test
	public void shouldUpdateEntity() throws Exception {
		PlaceEntity place = getMockPlace();

		MvcResult mvcResult = mockMvc.perform(post("/places")
				.content(mapper.writeValueAsString(place)))
				.andExpect(status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		
		place.setName("new name");
		place.setDescription("new description");
		mockMvc.perform(put(location)
				.content(mapper.writeValueAsString(place)))
				.andExpect(status().isNoContent());

		mockMvc.perform(get(location))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(place.getName())).andExpect(
						jsonPath("$.description").value(place.getDescription()));
	}

	@Test
	public void shouldPartiallyUpdateEntity() throws Exception {
		PlaceEntity place = getMockPlace();

		MvcResult mvcResult = mockMvc.perform(post("/places")
				.content(mapper.writeValueAsBytes(place)))
				.andExpect(status().isCreated())
				.andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(
				patch(location).content("{\"name\": \"New name\"}")).andExpect(
						status().isNoContent());

		mockMvc.perform(get(location))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value("New name"))
			.andExpect(jsonPath("$.description").value(place.getDescription()));
	}

	@Test
	public void shouldDeleteEntity() throws Exception {
		PlaceEntity place = getMockPlace();

		MvcResult mvcResult = mockMvc.perform(post("/places")
				.content(mapper.writeValueAsString(place)))
				.andExpect(status().isCreated())
				.andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(delete(location)).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}
}