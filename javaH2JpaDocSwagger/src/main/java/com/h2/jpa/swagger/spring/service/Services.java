package com.h2.jpa.swagger.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2.jpa.swagger.spring.dao.impl.Impl;
import com.h2.jpa.swagger.spring.exception.ExceptionNotFoundExcepion;
import com.h2.jpa.swagger.spring.model.Model;

@Service
public class Services {

	@Autowired
	private Impl impl;

	public List<Model> select() {
		return impl.select();
	}

	public Optional<Model> selectId(Long id) {
		 Optional<Model> response = impl.selectId(id);
		 
		 if(response.isPresent()) {
			 
			return response;
		 }
		 
		 throw new ExceptionNotFoundExcepion(" Cliente não encontrado ");
		 
	}

	public void deleteIdParam(Long id) {
		impl.deleteIdParam(id);
	}

	public Model insereObjeto(Model model) {
		return impl.insereObjeto(model);
	}

	public Model insereParam(String nome, Integer idade) {
		
		Model model = new Model();
		model.setNome(nome);
		model.setIdade(idade);
		return impl.insereObjeto(model);
	}

	public Model updateObjeto(Model model) {
		return impl.updateObjeto(model);
	}

	public Model updateParam(Long id, String nome, Integer idade) {
		Model model = new Model();
		model.setId(id);
		model.setNome(nome);
		model.setIdade(idade);
		return impl.updateObjeto(model);
	}

}
