package spring.project.autumn.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import spring.project.autumn.service.SaoService;

@Controller
public class DataController {
	
	@Autowired
	SaoService ss;
	
	@PostMapping("/setData")
	public void setData(HttpServletResponse res) {
		System.out.println("setData()");
		ss.setData();
	}
	
}
