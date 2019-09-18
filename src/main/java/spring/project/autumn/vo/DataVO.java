package spring.project.autumn.vo;

public class DataVO {
	
	String station;
	int year;
	int doy;
	int hh;
	int mm;
	float foF2;
	float foEs;
	float hmF2;
	float hpEs;
	
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
		return "DataVO [year=" + year + ", doy=" + doy + ", hh=" + hh + ", mm=" + mm + ", foF2=" + foF2 + ", foEs="
				+ foEs + ", hmF2=" + hmF2 + ", hpEs=" + hpEs + "]";
	}
	
}
