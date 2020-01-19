package com.h2.jpa.swagger.spring.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ErroDetalheModel {

	private String titulo;
	private int status;
	private String detalheErro;
	private long timestamp;
	private String classeErro;
}
