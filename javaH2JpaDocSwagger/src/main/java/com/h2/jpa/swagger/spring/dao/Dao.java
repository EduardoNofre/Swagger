package com.h2.jpa.swagger.spring.dao;

import java.util.List;
import java.util.Optional;

import com.h2.jpa.swagger.spring.model.Model;

public interface Dao {

	List<Model> select();

	Optional<Model> selectId(Long id);

	void deleteIdParam(Long id);

	Model insereObjeto(Model model);

	Model updateObjeto(Model model);

}
