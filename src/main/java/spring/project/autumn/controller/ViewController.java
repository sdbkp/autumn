package spring.project.autumn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/update")
	public String update() {
		return "data/update";
	}
	
}
