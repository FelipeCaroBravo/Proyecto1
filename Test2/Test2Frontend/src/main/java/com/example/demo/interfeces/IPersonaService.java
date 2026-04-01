package com.example.demo.interfeces;

import java.util.List;

import com.example.demo.entity.PersonaEntity;

public interface IPersonaService{
	public List<PersonaEntity> listarTodos();
	PersonaEntity findById(long Id);
	PersonaEntity save(PersonaEntity persona);
	void deleteById(long Id);
}
