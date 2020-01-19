package com.h2.jpa.swagger.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "ControleSwagger")
public class Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "Id do cliente", example = "123")
	private Long id;

	@ApiModelProperty(value = "Nome do cliente ", example = "pamcary123")
	@NotNull(message = "Nome do cliente não pode está nulo")
	@NotBlank(message = "O campo nome não pode esta em  branco")
	@NotEmpty(message = "Nome do cliente não pode está vazio")
	@Column(name = "nome", length = 50)
	private String nome;

	@ApiModelProperty(value = "Idade do cliente", example = "23")
	@NotNull(message = "Idade do cliente não pode está nulo")
	@PositiveOrZero(message = "A idade deve ser maior ou igual a zero")
	@Column(name = "idade", length = 50)
	private Integer idade;
}
