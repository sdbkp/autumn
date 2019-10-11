package spring.project.autumn.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;
import spring.project.autumn.mapper.DataMapper;
import spring.project.autumn.vo.TableVO;

@Service
public class StationService {
	
	@Autowired
	DataMapper dm;
	
	public String getStations() {
		List<String> stations = dm.getStations(new TableVO());
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("stations", stations);
		return JSONObject.fromObject(resultMap).toString();
	}
	
	public String addStation(String stationName) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (stationName == null) {
			resultMap.put("result", "Insert station name");
		} else {
			try {
				String url = "ftp.ngdc.noaa.gov";
				String ftpPath = "/ionosonde/data";
				
				FTPClient ftp = new FTPClient();
				ftp.connect(url);
				ftp.enterLocalPassiveMode();
				ftp.login("anonymous", "");
				
				String tableName = "";
				FTPFile[] stationList = ftp.listFiles(ftpPath);
				for (FTPFile station : stationList) {
					if (stationName.equals(station.getName())) {
						tableName = station.getName();
						
						break;
					}
				}
				
				if (!"".equals(tableName)) {
					if (dm.getStations(new TableVO(tableName)).size() != 0) {
						resultMap.put("result", stationName + " station is already registrated.");
					} else {
						resultMap.put("result", stationName + " station registration completed successfully.");
					}
					
				} else {
					resultMap.put("result", stationName + " station does not exist.");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return JSONObject.fromObject(resultMap).toString();
	}
	
}
