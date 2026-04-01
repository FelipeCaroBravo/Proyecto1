package com.example.demo.service;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.PersonaEntity;
import com.example.demo.interfeces.IPersonaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PersonaServiceImpl implements IPersonaService{

	@Override
	public List<PersonaEntity> listarTodos() {
	    try {
	        ObjectMapper unMapper = new ObjectMapper();
	        List<PersonaEntity> personas = Arrays.asList(unMapper
	            .readValue(new URL("http://localhost:8886/api/v1/entities/persona/"),
	                PersonaEntity[].class)
	        );
	        return personas;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public PersonaEntity findById(long id) {

	    RestTemplate restTemplate = new RestTemplate();

	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setAccept(Collections.singletonList(
	            MediaType.APPLICATION_JSON));

	    HttpEntity<PersonaEntity> entity =
	            new HttpEntity<PersonaEntity>(httpHeaders);

	    try {
	        ResponseEntity<PersonaEntity> response = restTemplate.exchange(
	                "http://localhost:8886/api/v1/entities/persona/" + id,
	                HttpMethod.GET,
	                entity,
	                PersonaEntity.class);

	        return response.getBody();

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public PersonaEntity save(PersonaEntity persona) {

	    RestTemplate restTemplate = new RestTemplate();

	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	    httpHeaders.setAccept(Collections.singletonList(
	            MediaType.APPLICATION_JSON));

	    HttpEntity<PersonaEntity> entity =
	            new HttpEntity<>(persona, httpHeaders);

	    try {
	        ResponseEntity<PersonaEntity> response = restTemplate.exchange(
	                "http://localhost:8886/api/v1/entities/persona/",
	                HttpMethod.POST,
	                entity,
	                PersonaEntity.class);

	        return response.getBody();

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public void deleteById(long id) {

	    RestTemplate restTemplate = new RestTemplate();

	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setAccept(Collections.singletonList(
	            MediaType.APPLICATION_JSON));

	    HttpEntity<PersonaEntity> entity
	            = new HttpEntity<PersonaEntity>(httpHeaders);

	    ResponseEntity<PersonaEntity> pe = restTemplate.exchange(
	            "http://localhost:8886/api/v1/entities/persona/" + id,
	            HttpMethod.DELETE,
	            entity,
	            PersonaEntity.class);
	}

}
