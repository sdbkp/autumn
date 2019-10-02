package spring.project.autumn.vo;

public class TableVO {
	
	String tableName;
	String frequency;
	String height;
	String group_1;
	String group_2;
	
	public TableVO() {
	}
	
	public TableVO(String tableName) {
		this.tableName = tableName;
	}
	
	public TableVO(String tableName, String frequency, String height, String group_1) {
		this.tableName = tableName;
		this.frequency = frequency;
		this.height = height;
		this.group_1 = group_1;
	}
	
}
