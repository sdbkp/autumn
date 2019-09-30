package spring.project.autumn.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import spring.project.autumn.vo.DataVO;
import spring.project.autumn.vo.FileVO;
import spring.project.autumn.vo.TableVO;

@Mapper
public interface DataMapper {

	public int setIonoData(DataVO dvo);
	public int setSaoList(FileVO xvo);
	public int tableCount(TableVO tvo);
	public FileVO getSaoInfo(TableVO tvo);
	public List<HashMap<String, Object>> getStations();
	
	public List<HashMap<String, Object>> avgF2(TableVO tvo);
	
}
