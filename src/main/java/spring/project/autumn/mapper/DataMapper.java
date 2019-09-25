package spring.project.autumn.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import spring.project.autumn.vo.DataVO;
import spring.project.autumn.vo.FileVO;

@Mapper
public interface DataMapper {

	public int setIonoData(DataVO dvo);
	public int setSaoList(FileVO xvo);
	
}
