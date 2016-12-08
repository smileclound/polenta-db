package com.polenta.db.data;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultSet {

	private List<String> fields;
	private List<Map<String, Object>> rows;

	public ResultSet(List<String> fields, List<Row> rows) {
		this.fields = new LinkedList<String>();
		this.fields.addAll(fields);
		
		this.rows = new LinkedList<Map<String,Object>>();
		this.rows.addAll(rows.stream().map(row -> row.asMap()).collect(Collectors.toList())); 
	}

	//TODO: return unmuttable object
	public List<String> getFields() {
		return fields;
	}

	//TODO: return unmuttable object
	public List<Map<String, Object>> getRows() {
		return rows;
	}
	
}
