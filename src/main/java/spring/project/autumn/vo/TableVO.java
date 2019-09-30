package spring.project.autumn.vo;

public class TableVO {
	
	String tableName;
	String column;
	String target_1;
	String target_2;
	String group_1;
	String group_2;
	
	public TableVO() {
	}
	
	public TableVO(String tableName) {
		this.tableName = tableName;
	}
	
	public TableVO(String tableName, String column, String target_1, String target_2) {
		this.tableName = tableName;
		this.column = column;
		this.target_1 = target_1;
		this.target_2 = target_2;
		this.group_1 = column;
	}
	
	public TableVO(String tableName, String column, String target_1, String target_2, String group_2) {
		this.tableName = tableName;
		this.column = column;
		this.target_2 = target_1;
		this.target_2 = target_2;
		this.group_1 = column;
		this.group_2 = group_2;
	}
	
}
