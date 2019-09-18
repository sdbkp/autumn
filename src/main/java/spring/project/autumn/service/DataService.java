package spring.project.autumn.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
		File[] list = getXml();
		for (File temp : list) {
			setXml(temp);
		}
	}
	
	// xml 다운로드하고 저장 된 위치 리턴
	public File[] getXml() {
		System.out.println("setXml()");
		
		String path = "D:\\Data\\Ionosonde\\";
		File dir = new File(path);
		
		return dir.listFiles();
	}
	
	// xml 파일 읽고 db에 데이터 저장
	public void setXml(File file) {
		System.out.println("setData()");
		
		try {
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
			Document doc =  dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			
			Element root = doc.getDocumentElement();
			System.out.println("root: " + root);
			
			NodeList nList = doc.getElementsByTagName("URSI");
			System.out.println("listNum: " + nList.getLength());
			
			DataVO dvo = new DataVO();
			for (int i = 0; i < nList.getLength(); i++) {
				Element el = (Element) nList.item(i);
				String name = el.getAttributes().getNamedItem("Name").getNodeValue().toString();
				if ("foF2".equals(name)) {
					dvo.setFoF2(Float.parseFloat(el.getAttributes().getNamedItem("Val").getNodeValue().toString()));
				} else if ("foEs".equals(name)) {
					dvo.setFoEs(Float.parseFloat(el.getAttributes().getNamedItem("Val").getNodeValue().toString()));
				}
			}
			dm.setData(dvo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
