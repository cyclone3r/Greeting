package com.roland.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private static Map<Integer, Greeting> greetingList = new HashMap<>();
	

	@GetMapping("/greeting")
	public Map<Integer, Greeting> greetings(@RequestParam(value = "param1", required = false) String param1,
			@RequestParam(value = "param2", required = false) String param2 ) {
		 System.out.println(param1 + ", " + param2);
		return greetingList; //new GreetingsDao(greetingList);
	}
	
	@GetMapping("/greeting/{id}")
	public Greeting greetings(@PathVariable(value = "id") int id) {
		
		return greetingList.get(id);
	}
	
	@PostMapping("/greeting")
	public void greeting(@RequestBody Greeting greeting) {
		 greetingList.put(greeting.getId(), greeting);
		 System.out.println(greetingList.size());
	}
	
	@PutMapping("/greeting")
	public void greetingUpdate(@RequestBody Greeting greeting) {
		 greetingList.put(greeting.getId(), greeting);
		 System.out.println("update: " + greetingList.size());
	}
	
	@DeleteMapping("/greeting/{id}")
	public void greetingDelete(@PathVariable(value = "id") int id) {
		 greetingList.remove(id);
		 System.out.println("update: " + greetingList.size());
	}
	
	/*
	@PutMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(1, String.format(template, name));
	}
	
	@PatchMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(1, String.format(template, name));
	}
	
	@DeleteMapping("/greeting")
	public Greeting deleteGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(1, String.format(template, name));
	} */
}
