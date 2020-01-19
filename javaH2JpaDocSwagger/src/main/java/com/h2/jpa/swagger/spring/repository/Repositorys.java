package com.h2.jpa.swagger.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.h2.jpa.swagger.spring.model.Model;

@Repository
public interface Repositorys extends JpaRepository<Model, Long>{

}
