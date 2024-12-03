package com.schedulingsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class sample {
	
	@GetMapping("test")
	public String test() {
		return "Application Test";
		
		
	}

}
