package spring.project.autumn.vo;

public class DataVO {
	
	float foF2;
	float foEs;
	
	public float getFoF2() {
		return foF2;
	}
	
	public float getFoEs() {
		return foEs;
	}
	
	public void setFoF2(float foF2) {
		this.foF2 = foF2;
	}
	
	public void setFoEs(float foEs) {
		this.foEs = foEs;
	}

	@Override
	public String toString() {
		return "DataVO [foF2=" + foF2 + ", foEs=" + foEs + "]";
	}
	
}
