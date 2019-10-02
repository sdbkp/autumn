package spring.project.autumn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/update")
	public String update() {
		return "board/update";
	}
	
	@GetMapping("/{sation}")
	public String plots() {
		return "board/plots";
	}
	
	@GetMapping("/{station}/{path}")
	public String detail() {
		return "board/detail";
	}
}
