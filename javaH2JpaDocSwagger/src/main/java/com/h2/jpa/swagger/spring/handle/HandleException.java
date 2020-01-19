package com.h2.jpa.swagger.spring.handle;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.h2.jpa.swagger.spring.exception.ExceptionNotFoundExcepion;
import com.h2.jpa.swagger.spring.model.BuildExceptionHandle;
import com.h2.jpa.swagger.spring.model.BuildValidationHandle;

@ControllerAdvice
public class HandleException {


	@ExceptionHandler(ExceptionNotFoundExcepion.class)
	public ResponseEntity<?> handleNotFoundExcepion(ExceptionNotFoundExcepion exceptionNotFoundExcepion) {

		BuildExceptionHandle buildExceptionHandle = BuildExceptionHandle.builder()
				.timestamp(new Date().getTime())
				.status(HttpStatus.NOT_FOUND.value())
				.titulo("Recurso não encontrado")
				.detalheErro(exceptionNotFoundExcepion.getMessage())
				.classeErro(exceptionNotFoundExcepion.getClass().getName()).build();
		return new ResponseEntity<>(buildExceptionHandle, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {

		List<FieldError>  fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
		String campo = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
		String campoMsg = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

		BuildValidationHandle buildValidationHandle = BuildValidationHandle.builder()
				.timestamp(new Date().getTime())
				.status(HttpStatus.NOT_FOUND.value())
				.titulo("Valor do campo invalido")
				.detalheErro("Valor do campo invalido")
				.classeErro(methodArgumentNotValidException.getClass().getName())
				.campo(campo)
				.campoMessage(campoMsg) .build();
		return new ResponseEntity<>(buildValidationHandle, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException constraintViolationException) {

		BuildExceptionHandle buildExceptionHandle = BuildExceptionHandle.builder()
				.timestamp(new Date().getTime())
				.status(HttpStatus.NOT_FOUND.value())
				.titulo("Recurso não encontrado")
				.detalheErro(constraintViolationException.getMessage())
				.classeErro(constraintViolationException.getClass().getName()).build();
		return new ResponseEntity<>(buildExceptionHandle, HttpStatus.NOT_FOUND);
	}
	
	
	
	
}
