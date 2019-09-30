package spring.project.autumn.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import spring.project.autumn.service.ChartService;
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
	
	@PostMapping("/setInfo")
	public void setInfo(HttpServletResponse res) {
		try {
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(ss.updateInfo().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Autowired
	ChartService cs;
	
	@PostMapping("/{station}/annualMean")
	public void test(@PathVariable("station") String station, HttpServletResponse res) {
		try {
			res.getWriter().write(cs.annualMean(station).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
