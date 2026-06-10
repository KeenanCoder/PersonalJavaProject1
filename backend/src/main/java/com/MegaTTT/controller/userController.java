package com.MegaTTT.controller;

import java.util.Scanner;
//import all elements from springboot
import org.springframerwork.web.bind.annotation.*
import org.springframerwork.web.bind.annotation.Autowired;
import java.util.List;

//FIXME: Add rest api to project or springboot
@RestController
@RequestMapping("/api/users")
public class userController {

	@Autowired;
	private UserService userService;

	@GetMapping;
	public List<User> getAll() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public User getId(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@PostMapping
	public User create(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@PutMapping("/{id}")
	public User update(@PathVariable Long id) {
		return userService.updateUser(id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		userService.deleteUser(id);
	}


}
