package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exceptions.UserNullException;

@ControllerAdvice
public class userControllerAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserNullException.class)
	public ResponseEntity<Object> captureErroNull(){
		
		Map<String, Object> body = new HashMap<String,Object>();
		body.put("message", "verifique os campos");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
		
	}
}
