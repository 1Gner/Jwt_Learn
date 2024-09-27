package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.user;
import com.example.demo.service.userService;

@RestController
@RequestMapping(value = "/user")
public class userControler {
	
	@Autowired
	private userService service;

	@PostMapping(value = "/save")
	public ResponseEntity<user> salvaUser(@RequestBody user User){
		User = service.salvarUser(User);
		return ResponseEntity.ok().body(User);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<user> procuraUser(@PathVariable Integer id){
		user User = service.findById(id);
		return ResponseEntity.ok().body(User);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<user>> procuraTodos(){
		List<user> User = service.findAll();
		return ResponseEntity.ok().body(User);
	}
}
