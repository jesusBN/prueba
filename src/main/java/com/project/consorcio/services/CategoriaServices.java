package com.project.consorcio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.consorcio.entity.Categoria;
import com.project.consorcio.repository.CategoriaRepository;

@Service
public class CategoriaServices {
@Autowired
private CategoriaRepository repo;

public List<Categoria>listarTodos(){
	return repo.findAll();
	
}
}
