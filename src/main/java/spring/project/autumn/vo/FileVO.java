package spring.project.autumn.vo;

public class FileVO {
	
	String station;
	String year;
	String doy;
	String fileName;
	
	public FileVO() {	
	}
	
	public FileVO(String station, String year, String doy, String fileName) {
		this.station = station;
		this.year = year;
		this.doy = doy;
		this.fileName = fileName;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDoy() {
		return doy;
	}

	public void setDoy(String doy) {
		this.doy = doy;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "XmlVO [station=" + station + ", year=" + year + ", doy=" + doy + ", fileName=" + fileName + "]";
	}
	
}
