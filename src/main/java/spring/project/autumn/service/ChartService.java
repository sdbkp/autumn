package spring.project.autumn.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;
import spring.project.autumn.mapper.DataMapper;
import spring.project.autumn.vo.TableVO;

@Service
public class ChartService {
	
	@Autowired
	DataMapper dm;
	
	public JSONObject annualMean(String station) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("avgF2", dm.avgF2(new TableVO(station, "year", "foF2", "hmF2")));
		return JSONObject.fromObject(resultMap);
	}
	
}
