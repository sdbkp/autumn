package spring.project.autumn.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import spring.project.autumn.service.PlotService;
import spring.project.autumn.service.SaoService;
import spring.project.autumn.service.StationService;
import spring.project.autumn.vo.TableVO;

@Controller
public class DataController {
	
	@Autowired
	SaoService sao;
	
	@PostMapping("/updateData")
	public void updateData(String[] stations, HttpServletResponse res) {
		sao.setData(stations);
	}
	
	@PostMapping("/getUpdateInfo")
	public void getUpdateInfo(HttpServletResponse res, TableVO tvo) {
		try {
			res.getWriter().write(sao.updateInfo(tvo).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	PlotService plot;
	
	@PostMapping("/{station}")
	public void getData(@PathVariable("station") String station, HttpServletResponse res) {
		try {
			res.getWriter().write(plot.avgAll(station));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/{station}/{path}")
	public void detail(@PathVariable("station") String station, @PathVariable("path") String path, HttpServletResponse res) {
		try {
			res.getWriter().write(plot.avg(station, path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	StationService station;
	
	@PostMapping("/getStations")
	public void getStations(HttpServletResponse res) {
		try {
			res.getWriter().write(station.getStations());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/updateStation")
	public void updateStation(HttpServletResponse res, String stationName) {
		try {
			res.getWriter().write(station.addStation(stationName));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
