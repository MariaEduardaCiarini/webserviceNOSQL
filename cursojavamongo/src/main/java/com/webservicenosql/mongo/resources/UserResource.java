package com.webservicenosql.mongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}