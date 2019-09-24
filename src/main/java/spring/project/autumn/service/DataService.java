package spring.project.autumn.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import spring.project.autumn.mapper.DataMapper;
import spring.project.autumn.vo.DataVO;
import spring.project.autumn.vo.FileVO;

@Service
public class DataService {
	
	@Autowired
	DataMapper dm;
	
	public void setData() {
		String[] stations = {
			"IC437", "JJ433"	
		};
		
//		for (String station : stations) {		
//			getXml(station);
//		}
		
		getSao("IC437");
		System.out.println("End setData()");
	}
	
	public void getSao(String station) {
		try {
			String url = "ftp.ngdc.noaa.gov";
			String ftpPath = "/ionosonde/data/" + station + "/individual";
			String yearPath = "";
			String doyPath = "";
			String dirPath = "";
			String fileName = "";
			String ext = "";
			String localPath = "D:\\Data\\Ionosonde\\" + station + "\\";
			
			FTPClient ftp = new FTPClient();
			ftp.connect(url);
			
			ftp.enterLocalPassiveMode();
			ftp.login("anonymous", "");
			
			FTPFile[] yearList = ftp.listFiles(ftpPath);
			for (FTPFile year : yearList) {
				
				String tempYear = year.getName(); // year
				System.out.println(tempYear);
				if (Integer.parseInt(tempYear) >= 0) { // year check
					
					yearPath = ftpPath + "/" + tempYear;
					FTPFile[] doyList = ftp.listDirectories(yearPath);
					if (doyList.length != 0) {
						for (FTPFile doy : doyList) {
							
							String tempDoy = doy.getName(); // doy : day of year
							System.out.print(tempDoy + ": ");
							doyPath = yearPath + "/" + tempDoy;
							
							if (1 != 0) {
								FTPFile[] dirList = ftp.listDirectories(doyPath);
								for (FTPFile dir : dirList) {							
									if ("scaled".equals(dir.getName())) {
										dirPath = doyPath + "/scaled";
										ftp.changeWorkingDirectory(dirPath);
										ftpStatus(ftp);
										
										FTPFile[] fileList = ftp.listFiles();
										for (FTPFile file : fileList) {
											
											fileName = file.getName();
											ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
											if ("SAO".equals(ext)) {
												
												InputStream is = ftp.retrieveFileStream(fileName);
												if (setSao(is) == 1) {
													FileVO sao = new FileVO(station, tempYear, tempDoy, fileName);
											    	dm.setXmlList(sao);
											    	System.out.println(sao.toString());
												}
												
											    
											}
											
										}
										
									}
									
								}
								
							}
							
						}
						
					}
					
				}
				
			}
			
			ftp.logout();
			ftp.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ftpStatus(FTPClient ftp) {
		String[] replies = ftp.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String reply : replies) {
				System.out.println(reply);
			}
		}
	}
	
	public int setSao(InputStream is) {
		List<String> data = new ArrayList<String>();
		int result = -1;
		
		try {
//			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = br.readLine()) != null) {
				data.add(line);
			}
			
			String station = data.get(3).substring(11,16);
			int year = Integer.parseInt(data.get(4).substring(2, 6));
			int doy = Integer.parseInt(data.get(4).substring(6, 9));
			int month = Integer.parseInt(data.get(4).substring(9, 11));
			int day = Integer.parseInt(data.get(4).substring(11, 13));
			int hh = Integer.parseInt(data.get(4).substring(13, 15));
			int mm = Integer.parseInt(data.get(4).substring(15, 17));
			float foF2 = Float.parseFloat(data.get(5).substring(0, 8));
			float foEs = Float.parseFloat(data.get(5).substring(40, 48));
			float hmF2 = Float.parseFloat(data.get(7).substring(8, 16));
			float hpEs = Float.parseFloat(data.get(5).substring(104, 112));
			result = dm.setIonoData(new DataVO(station, year, doy, month, day, hh, mm, foF2, foEs, hmF2, hpEs));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// xml 파일 읽어서 db에 데이터 저장
	public int setXml(File file) {
		int result = -1;
		try {
			DataVO dvo = new DataVO();
			String fileName = file.getName().toString();
			dvo.setStation(fileName.substring(0, 5));
			dvo.setYear(Integer.parseInt(fileName.substring(6, 10)));
			dvo.setDoy(Integer.parseInt(fileName.substring(10, 13)));
			dvo.setHh(Integer.parseInt(fileName.substring(13, 15)));
			dvo.setMm(Integer.parseInt(fileName.substring(15, 17)));
			
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
			Document doc =  dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("URSI");

			for (int i = 0; i < nList.getLength(); i++) {
				Element el = (Element) nList.item(i);
				String name = el.getAttributes().getNamedItem("Name").getNodeValue().toString();
				
				switch (name) {
				case "foF2":
					dvo.setFoF2(Float.parseFloat(el.getAttributes().getNamedItem("Val").getNodeValue().toString()));
					break;
				case "foEs":
					dvo.setFoEs(Float.parseFloat(el.getAttributes().getNamedItem("Val").getNodeValue().toString()));
					break;
				case "hmF2":
					dvo.setHmF2(Float.parseFloat(el.getAttributes().getNamedItem("Val").getNodeValue().toString()));
					break;
				case "h`Es":
					dvo.setHpEs(Float.parseFloat(el.getAttributes().getNamedItem("Val").getNodeValue().toString()));
					break;
				}
			}
			result = dm.setIonoData(dvo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
