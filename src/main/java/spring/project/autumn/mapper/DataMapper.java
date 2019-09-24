package spring.project.autumn.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import spring.project.autumn.vo.DataVO;
import spring.project.autumn.vo.XmlVO;

@Mapper
public interface DataMapper {

	public int setIonoData(DataVO dvo);
	public int setXmlList(XmlVO xvo);
	public XmlVO getXmlList(XmlVO xvo);
	
}
