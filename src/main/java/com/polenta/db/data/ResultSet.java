package com.polenta.db.data;

import java.util.ArrayList;
import java.util.List;

public class ResultSet extends ArrayList<Row> {

	private static final long serialVersionUID = 474582531633078667L;

	public ResultSet() {
		
	}
	
	public ResultSet(List<Row> rows) {
		super(rows);
	}
	
	
	public String toString() {
		if (this.isEmpty()) {
			return "EMPTY_RESULT_SET";
		} else {
			StringBuilder formatted = new StringBuilder("|");
			for (Row row: this) {
				String formattedRow = "";
				for (String key: row.columnsSet()) {
					Object value = row.get(key);
					String formattedValue;
					if (value == null) {
						formattedValue = "NULL";
					} else if (value.getClass().isAssignableFrom(String.class)) {
						formattedValue = "'" + value.toString() + "'";
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
