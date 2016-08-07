package com.polenta.db.data;

import java.util.List;

public class ResultSet {
	
	private List<String> fields;
	private List<Row> rows;

	public ResultSet(List<String> fields, List<Row> rows) {
		this.fields = fields;
		this.rows = rows;
	}
	
	public String toString() {
		if (this.rows.isEmpty()) {
			return "EMPTY_RESULT_SET";
		} else {
			StringBuilder formatted = new StringBuilder("");
			for (int i = 0; i < fields.size(); i++) {
				formatted.append(fields.get(i));
				if (i < (fields.size() -1)) {
					formatted.append(",");	
				}
			}
			formatted.append("|");
			for (Row row: this.rows) {
				String formattedRow = "";
				for (String key: row.columnsSet()) {
					Object value = row.get(key);
					String formattedValue;
					if (value == null) {
						formattedValue = "NULL";
					} else if (value.getClass().isAssignableFrom(String.class)) {
						formattedValue = value.toString();
					} else {
						formattedValue = value.toString();
					}
					formattedRow += key + ":" + formattedValue + ",";
				}
				if (formattedRow.endsWith(",")) {
					formattedRow = formattedRow.substring(0, formattedRow.length() -1);
				}
				formatted.append(formattedRow + "|");
			}
			return formatted.toString();
		}
	}
	
}
