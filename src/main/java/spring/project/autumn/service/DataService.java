package spring.project.autumn.service;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import spring.project.autumn.mapper.DataMapper;
import spring.project.autumn.vo.DataVO;

@Service
public class DataService {
	
	@Autowired
	DataMapper dm;
	
	public void setData() {
//		File[] list = getXml();
//		for (File temp : list) {
//			setXml(temp);
//		}
		
		getXml("IC437");
	}
	
	// xml 다운로드하고 저장 된 위치 리턴
//	public File[] getXml() {
//		String path = "D:\\Data\\Ionosonde\\";
//		File dir = new File(path);
//		return dir.listFiles();
//	}
	
	public void getXml(String station) {
		String url = "ftp.ngdc.noaa.gov";
//		String ftpPath = "/ionosonde/data/" + station;
		String ftpPath = "/ionosonde/data/IC437/individual/2018/060/scaled/";
		String localPath = "D:\\Data\\";
		
		FTPClient ftp = new FTPClient();
		
		try {
			ftp.connect(url);
//			ftpStatus(ftp);
			
			ftp.enterLocalPassiveMode();
			ftp.login("anonymous", "");
			ftpStatus(ftp);
			
			ftp.changeWorkingDirectory(ftpPath);
//			ftpStatus(ftp);
			
			System.out.print("listDirectories: ");
			FTPFile[] files = ftp.listFiles();
			System.out.println(files.length);
//			for (int i = 0; i < files.length; i++) {
//				System.out.println("file: " + files[i].toString());
//			}
			ftpStatus(ftp);
			
//			System.out.print("listNames: ");
//			String[] names = client.listNames();
//			System.out.println(names.length);
			
			ftp.logout();
			System.out.println("logout");
			ftp.disconnect();
			System.out.println("disconnect");
			
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
	public void setXml(File file) {
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
			dm.setData(dvo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
