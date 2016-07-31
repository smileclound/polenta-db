package com.polenta.db.data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Row {
	
	private Map<String, Object> columns = new LinkedHashMap<String, Object>();
	
	public Row() {
		
	}
	
	public Row(Map<String, Object> columns) {
		this.columns.putAll(columns);
	}
	
	public void set(String column, Object value) {
		columns.put(column, value);
	}
	
	public Object get(String column) {
		return columns.get(column);
	}
	
	public void clear() {
		this.columns.clear();
	}

	public Set<String> columnsSet() {
		return columns.keySet();
	}
	
}
