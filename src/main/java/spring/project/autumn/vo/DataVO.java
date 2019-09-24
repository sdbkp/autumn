package spring.project.autumn.vo;

public class DataVO {
	
	String station;
	int year;
	int doy;
	int month;
	int day;
	int hh;
	int mm;
	float foF2;
	float foEs;
	float hmF2;
	float hpEs;
	
	public DataVO() {
		
	}
	
	public DataVO(String station, int year, int doy, int month, int day, int hh, int mm, float foF2, float foEs, float hmF2, float hpEs) {
		this.station = station;
		this.year = year;
		this.doy = doy;
		this.month = month;
		this.day = day;
		this.hh = hh;
		this.mm = mm;
		this.foF2 = foF2;
		this.foEs = foEs;
		this.hmF2 = hmF2;
		this.hpEs = hpEs;
	}
	
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getDoy() {
		return doy;
	}
	public void setDoy(int doy) {
		this.doy = doy;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHh() {
		return hh;
	}
	public void setHh(int hh) {
		this.hh = hh;
	}
	public int getMm() {
		return mm;
	}
	public void setMm(int mm) {
		this.mm = mm;
	}
	public float getFoF2() {
		return foF2;
	}
	public void setFoF2(float foF2) {
		this.foF2 = foF2;
	}
	public float getFoEs() {
		return foEs;
	}
	public void setFoEs(float foEs) {
		this.foEs = foEs;
	}
	public float getHmF2() {
		return hmF2;
	}
	public void setHmF2(float hmF2) {
		this.hmF2 = hmF2;
	}
	public float getHpEs() {
		return hpEs;
	}
	public void setHpEs(float hpEs) {
		this.hpEs = hpEs;
	}
	
	@Override
	public String toString() {
		return "DataVO [station=" + station + ", year=" + year + ", doy=" + doy + ", month=" + month + ", day=" + day
				+ ", hh=" + hh + ", mm=" + mm + ", foF2=" + foF2 + ", foEs=" + foEs + ", hmF2=" + hmF2 + ", hpEs="
				+ hpEs + "]";
	}
	
}
