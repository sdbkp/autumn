package spring.project.autumn.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;
import spring.project.autumn.mapper.DataMapper;
import spring.project.autumn.vo.TableVO;

@Service
public class PlotService {
	
	@Autowired
	DataMapper dm;
	
	public String avgAll(String station) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("avgEsYear", dm.avgAll(new TableVO(station, "foEs", "hpEs", "year")));
		resultMap.put("avgEsMonth", dm.avgAll(new TableVO(station, "foEs", "hpEs", "month")));
		resultMap.put("avgEsHour", dm.avgAll(new TableVO(station, "foEs", "hpEs", "hh")));
		
		return JSONObject.fromObject(resultMap).toString();
	}
	
	public String avg(String station, String path) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		switch (path) {
		case "annualMeanEs":
			System.out.println("annualMean");
			resultMap.put("result", dm.avgAll(new TableVO(station, "foEs", "hpEs", "year")));
			break;
			
		case "monthlyMeanEs":
			System.out.println("monthlyMean");
			resultMap.put("result", dm.avg(new TableVO(station, "foEs", "hpEs", "year", "month")));
			break;
			
		case "hourlyMeanEs":
			System.out.println("hourlyMean");
			resultMap.put("result", dm.avg(new TableVO(station, "foEs", "hpEs", "month", "hh")));
			break;
			
		}
		return JSONObject.fromObject(resultMap).toString();
	}
}
