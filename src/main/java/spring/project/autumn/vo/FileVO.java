package spring.project.autumn.vo;

public class FileVO {
	
	String station;
	String year;
	String doy;
	String fileName;
	String setTime;
	
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
	
	public String getSetTime() {
		return setTime;
	}

	public void setSetTime(String setTime) {
		this.setTime = setTime;
	}

	@Override
	public String toString() {
		return "FileVO [station=" + station + ", year=" + year + ", doy=" + doy + ", fileName=" + fileName
				+ ", setTime=" + setTime + "]";
	}
	
}
