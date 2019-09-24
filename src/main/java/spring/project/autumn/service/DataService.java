package spring.project.autumn.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

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
import spring.project.autumn.vo.XmlVO;

@Service
public class DataService {
	
	@Autowired
	DataMapper dm;
	
	public void setData() {
		String[] stations = {
			"IC437", "JJ433"	
		};
		
		for (String station : stations) {		
			getXml(station);
		}
		
		System.out.println("End setData()");
	}
	
	public void getXml(String station) {
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
											if ("XML".equals(ext)) {
												File xmlFilePath = new File(localPath + fileName);
												OutputStream os = new FileOutputStream(xmlFilePath);
											    ftp.retrieveFile(fileName, os);
											    
											    if (setXml(xmlFilePath) == 1) {
											    	XmlVO xml = new XmlVO(station, tempYear, tempDoy, fileName);
											    	dm.setXmlList(xml);
											    	System.out.println(xml.toString());
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
