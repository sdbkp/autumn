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
	
	public JSONObject avgAll(String station) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("avgEsYear", dm.avgF2(new TableVO(station, "foEs", "hpEs", "year")));
		resultMap.put("avgEsMonth", dm.avgF2(new TableVO(station, "foEs", "hpEs", "month")));
		resultMap.put("avgEsHour", dm.avgF2(new TableVO(station, "foEs", "hpEs", "hh")));
		
		return JSONObject.fromObject(resultMap);
	}
	
	public JSONObject avg() {
		
		return null;
	}
}
