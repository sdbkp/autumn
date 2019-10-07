package spring.project.autumn.vo;

public class TableVO {
	
	String station;
	String frequency;
	String height;
	String group_1;
	String group_2;
	
	public TableVO() {
	}
	
	public TableVO(String station) {
		this.station = station;
	}
	
	public TableVO(String station, String frequency, String height, String group_1) {
		this.station = station;
		this.frequency = frequency;
		this.height = height;
		this.group_1 = group_1;
	}
	
	public TableVO(String station, String frequency, String height, String group_1, String group_2) {
		this.station = station;
		this.frequency = frequency;
		this.height = height;
		this.group_1 = group_1;
		this.group_2 = group_2;
	}
	
	public String getStation() {
		return station;
	}

	public void setStation(String stationName) {
		this.station = stationName;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getGroup_1() {
		return group_1;
	}

	public void setGroup_1(String group_1) {
		this.group_1 = group_1;
	}

	public String getGroup_2() {
		return group_2;
	}

	public void setGroup_2(String group_2) {
		this.group_2 = group_2;
	}

	@Override
	public String toString() {
		return "TableVO [station=" + station + ", frequency=" + frequency + ", height=" + height + ", group_1="
				+ group_1 + ", group_2=" + group_2 + "]";
	}
	
}
