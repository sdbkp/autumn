package spring.project.autumn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import spring.project.autumn.service.AdminService;

@Controller
public class ViewController {
	
	@Autowired
	AdminService as;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/data")
	public String update(HttpServletRequest req, HttpSession ses) {
		as.admin(req, ses);
		return "board/data";
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
