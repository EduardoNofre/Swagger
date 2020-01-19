package com.h2.jpa.swagger.spring.controle;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.h2.jpa.swagger.spring.model.Model;
import com.h2.jpa.swagger.spring.service.Services;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "/v1/crud", description = "Este projeto foi utilizado como modelo de documentação swagger")
@RestController
@Validated
@RequestMapping("/v1/crud")
@CrossOrigin(origins = "*")
public class Controle {

	@Autowired
	private Services services;

	@ApiOperation(value = "Busca tudos os clientes", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "HttpStatus 200 = Executado com sucesso."),
							@ApiResponse(code = 400, message = "HttpStatus 400 = Falhas ao buscar."),
							@ApiResponse(code = 500, message = "Código da falha: 500 = Erro interno sem causa mapeada.") })
	@GetMapping(path = "/selectasteristico", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> selectAsteristico() {

		List<Model> response = services.select();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Busca o cliente utilizando o ID ", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "HttpStatus 200 = Executado com sucesso."),
							@ApiResponse(code = 400, message = "HttpStatus 400 = Falhas ao buscar."),
							@ApiResponse(code = 404, message = "HttpStatus 404 = Id não foi encontrado."),
							@ApiResponse(code = 500, message = "Código da falha: 500 = Erro interno sem causa mapeada.") })
	@GetMapping(path = "/selectParam/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> selectId(@ApiParam(name = "ID", value = " Id do cliente na tabela ", required = true, example = "123") @NotNull @PathVariable("id") Long id) {

		verificaSeIdExiste(id);
		
		Optional<Model> response = services.selectId(id);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Atualiza os dados do cliente passado como parametro", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "HttpStatus 200 = Executado com sucesso."),
							@ApiResponse(code = 400, message = "HttpStatus 400 = Falhas ao tentar atualizar."),
							@ApiResponse(code = 404, message = "HttpStatus 404 = Id não foi encontrado."),
							@ApiResponse(code = 500, message = "Código da falha: 500 = Erro interno sem causa mapeada.") })
	@PutMapping(path = "/updateParam/nome/{nome}/idade/{idade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateParam(@ApiParam(name = "id", value = "O id do cliente", required = true, example = "123") @NotNull @PathVariable("id") Long id,
									     @ApiParam(name = "Nome do cliente", value = "O nome do cliente", required = true, example = "pamcary") @NotNull @PathVariable("nome") String nome,
									     @ApiParam(name = "Idade do cliente", value = "A idade do cliente", required = true, example = "33") @NotNull @PathVariable("idade") Integer idade) {

		verificaSeIdExiste(id);
		
		Model response = services.updateParam(id, nome, idade);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Remover o cliente com o Id informado", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "HttpStatus 200 = Executado com sucesso."),
							@ApiResponse(code = 400, message = "HttpStatus 400 = Falha ao remover."),
							@ApiResponse(code = 404, message = "HttpStatus 404 = Id não foi encontrado."),
							@ApiResponse(code = 500, message = "Código da falha: 500 = Erro interno sem causa mapeada.") })
	@DeleteMapping(path = "/deleteIdParam/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteId(@ApiParam(name = "id cliente", value = "O id do cliente", required = true, example = "2")@PathVariable("id")  Long id) {

		services.deleteIdParam(id);

		return new ResponseEntity<>("to do", HttpStatus.OK);
	}

	@ApiOperation(value = "Insere um novo cliente na base", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "HttpStatus 200 = Executado com sucesso."),
							@ApiResponse(code = 400, message = "HttpStatus 400 = Falhas ao inserir."),
							@ApiResponse(code = 500, message = "Código da falha: 500 = Erro interno sem causa mapeada.") })
	@PostMapping(path = "/insereParam/nome/{nome}/idade/{idade}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> insereParam(@ApiParam(name = "Nome do cliente", value = "Nome do cliente",required = true , example = "pamcary")@NotNull @PathVariable("nome")  String nome,
										 @ApiParam(name = "Idade do cliente", value = "Idade do cliente", required = true, example = "33") @NotNull @PathVariable("idade") Integer idade) {

		Model response =  services.insereParam(nome, idade);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Insere um novo cliente na base de acordo objeto", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "HttpStatus 200 = Executado com sucesso."),
							@ApiResponse(code = 400, message = "HttpStatus 400 = Falhas ao inserir."),
							@ApiResponse(code = 500, message = "Código da falha: 500 = Erro interno sem causa mapeada.") })
	@PostMapping(path = "/insereObjeto", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> insereObjeto(@RequestBody @Valid Model model) {

		Model response =  services.insereObjeto(model);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Atualiza os dados do cliente passado com objeto", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "HttpStatus 200 = Executado com sucesso."),
							@ApiResponse(code = 400, message = "HttpStatus 400 = Falhas ao tentar atualizar."),
							@ApiResponse(code = 404, message = "HttpStatus 404 = Id não foi encontrado."),
							@ApiResponse(code = 500, message = "Código da falha: 500 = Erro interno sem causa mapeada.") })
	@PutMapping(path = "/updateObjeto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateObjeto(@RequestBody @Valid Model model) {

		verificaSeIdExiste(model.getId());
		
		services.updateObjeto(model);

		return new ResponseEntity<>("to do", HttpStatus.OK);
	}

	private void verificaSeIdExiste(long id) {

		services.selectId(id);
	}
}
