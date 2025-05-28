package com.webservicenosql.mongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservicenosql.mongo.domain.User;
import com.webservicenosql.mongo.dto.UserDTO;
import com.webservicenosql.mongo.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping
	/* public ResponseEntity<List<User>> findAll() { */
	
	public ResponseEntity<List<UserDTO>> findAll() {

		/*
		 * User maria = new User("1", "Maria Brown", "maria@gmail.com"); User alex = new
		 * User("2", "Alex Green", "alex@gmail.com"); List<User> list = new
		 * ArrayList<>(); list.addAll(Arrays.asList(maria, alex));
		 */

		List<User> list = service.findAll();

		/*
		 * return ResponseEntity.ok().body(list);
		 */
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}