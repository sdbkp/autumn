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
	DataService ss;
	
	@PostMapping("/setData")
	public void setData(HttpServletResponse res) {
		System.out.println("setData()");
		ss.setData();
	}
	
	@PostMapping("/setInfo")
	public void setInfo(HttpServletResponse res) {
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(ss.updateInfo().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
