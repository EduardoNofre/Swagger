package com.h2.jpa.swagger.spring.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.h2.jpa.swagger.spring.dao.Dao;
import com.h2.jpa.swagger.spring.model.Model;
import com.h2.jpa.swagger.spring.repository.Repositorys;

@Repository
public class Impl implements Dao {

	@Autowired
	private Repositorys repositorys;

	public List<Model> select() {
		return repositorys.findAll();
	}

	public Optional<Model> selectId(Long id) {
		return repositorys.findById(id);
	}

	public void deleteIdParam(Long id) {
		repositorys.deleteById(id);

	}

	public Model insereObjeto(Model model) {
		return repositorys.save(model);
	}

	public Model updateObjeto(Model model) {
		return repositorys.save(model);
	}
}
