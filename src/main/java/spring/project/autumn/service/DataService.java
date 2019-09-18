package spring.project.autumn.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import spring.project.autumn.mapper.DataMapper;
import spring.project.autumn.vo.DataVO;

@Service
public class DataService {
	
	@Autowired
	DataMapper dm;
	
	public void readData(HttpServletResponse res) {
		System.out.println("readData()");
		
		String path = "D:\\Data\\Ionosonde\\";
		String fileName = "IC437_2018060000000_SAO.XML";
		
//		try {
//			File file = new File(path + fileName);
//			FileReader fileReader = new FileReader(file);
//			BufferedReader reader = new BufferedReader(fileReader);
//			String line = "";
//			while((line = reader.readLine()) != null) {
//				System.out.println(line);
//			}
//			reader.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		try {
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
			Document doc =  dBuilder.parse(new File(path + fileName));
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
			
//			NodeList childeren = root.getChildNodes();
//			for (int i = 0; i < childeren.getLength(); i++) {
//				Node node = childeren.item(i);
//				System.out.println("i: " + node.toString());
//				
//				if (node.getNodeType() == Node.ELEMENT_NODE) {
//					Element ele = (Element) node;
//					String nodeName = ele.getNodeName();
//					System.out.println("node name: " + nodeName);
//					
//					if (nodeName.equals("SAORecord")) {
//						System.out.println("node attribute: " + ele.getAttribute("name"));
//					}
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
