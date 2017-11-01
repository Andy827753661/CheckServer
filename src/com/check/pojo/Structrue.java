package com.check.pojo;

public class Structrue {

	private Integer Id;
	private String tableName;
	private String tableDesc;
	private String tableType;
	private String tableItem;
	private String url;
	private Integer tableOrder;
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableDesc() {
		return tableDesc;
	}
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public String getTableItem() {
		return tableItem;
	}
	public void setTableItem(String tableItem) {
		this.tableItem = tableItem;
	}
	public Integer getTableOrder() {
		return tableOrder;
	}
	public void setTableOrder(Integer tableOrder) {
		this.tableOrder = tableOrder;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
