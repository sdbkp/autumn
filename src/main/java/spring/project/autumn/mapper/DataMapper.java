package spring.project.autumn.mapper;

import org.apache.ibatis.annotations.Mapper;

import spring.project.autumn.vo.DataVO;

@Mapper
public interface DataMapper {

	public int setData(DataVO dvo);
	
}
