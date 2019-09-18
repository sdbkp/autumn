package spring.project.autumn.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import spring.project.autumn.service.DataService;

@Controller
public class DataController {
	
	@Autowired
	DataService ds;
	
	@PostMapping("/submit")
	public void submit(HttpServletResponse res) {
		System.out.println("submit()");
		ds.setData();
	}
	
}
