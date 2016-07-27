package com.polenta.db.command.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.polenta.db.Row;
import com.polenta.db.catalog.Catalog;
import com.polenta.db.catalog.CatalogItem;
import com.polenta.db.command.Command;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.object.type.Bag;
import com.polenta.db.object.type.User;

public class SelectCommand implements Command {

	private String statement;
	
	public void setStatement(String statement) {
		this.statement = statement.toUpperCase();
	}

	public String execute() throws PolentaException {
		String objectName = extractObjectName();
		if (objectName == null) {
			throw new InvalidStatementException("SELECT statement must have a FROM clausule");
		}
		
		CatalogItem catalogItem = Catalog.getInstance().get(objectName);  

		if (catalogItem == null) {
			throw new InvalidStatementException("Object does not exist.");
		}
		
		List<String> selectFields = extractSelectFields();
		if (selectFields == null || selectFields.isEmpty()) {
			throw new InvalidStatementException("SELECT must list fields to be returned.");
		}
		
		Map<String, Object> whereConditions = extractWhereConditions();
		List<String> orderByFields = extractOrderByFields();
		
		if (orderByFields.size() > 1) {
			throw new InvalidStatementException("ORDER BY does not support (yet!) more than a field.");
		}
		for (String orderField: orderByFields) {
			if (!selectFields.contains(orderField.split(" ")[0])) {
				throw new InvalidStatementException("Field in ORDER BY clausule needs to be listed on SELECT clausule.");
			}
		}
		List<Row> resultSet = performSelect(objectName, catalogItem.getClazz(), selectFields, whereConditions, orderByFields);
		return formatResultSetToTransport(resultSet);
	}
	
	protected String extractObjectName() throws PolentaException {
		try {
			if (statement.indexOf("FROM") == -1) {
				return null;
			} else if ((statement.indexOf("WHERE") == -1) && (statement.indexOf("ORDER BY") == -1)) {
				return statement.substring(statement.indexOf("FROM") + 5).trim();
			} else if ((statement.indexOf("WHERE") > 0) && (statement.indexOf("ORDER BY") == -1)) {
				return statement.substring(statement.indexOf("FROM") + 5, statement.indexOf("WHERE")).trim();
			} else if ((statement.indexOf("WHERE") == -1) && (statement.indexOf("ORDER BY") > 0)) {
				return statement.substring(statement.indexOf("FROM") + 5, statement.indexOf("ORDER BY")).trim();
			} else if ((statement.indexOf("WHERE") > 0) && (statement.indexOf("ORDER BY") > 0)) {
				return statement.substring(statement.indexOf("FROM") + 5, statement.indexOf("WHERE")).trim();
			}
		} catch (Exception e) {
			throw new PolentaException("Polenta couldn't parse SELECT and extract object name.");
		}
		return null;
	}

	protected List<String> extractSelectFields() throws PolentaException {
		List<String> fields = new ArrayList<String>();
		try {
			String selectFields = statement.trim().substring(statement.indexOf("SELECT") + 6, statement.indexOf("FROM"));
			for (String field: selectFields.split(",")) {
				fields.add(field.trim());
			}
		} catch (Exception e) {
			throw new PolentaException("Polenta couldn't parse SELECT and extract selected fields.");
		}
		return fields;
	}

	protected Map<String, Object> extractWhereConditions() throws PolentaException {
		Map<String, Object> conditions = new LinkedHashMap<String, Object>();
		return conditions;
	}

	protected List<String> extractOrderByFields() throws PolentaException {
		List<String> fields = new ArrayList<String>();
		if (statement.indexOf("ORDER BY") == -1) {
			return fields;
		}
		try {
			String orderByFields = statement.trim().substring(statement.indexOf("ORDER BY") + 8);
			if (orderByFields == null || orderByFields.trim().length() == 0) {
				return fields;
			}
			for (String field: orderByFields.split(",")) {
				fields.add(field.trim());
			}
		} catch (Exception e) {
			throw new PolentaException("Polenta couldn't parse ORDER BY and extract selected fields.");
		}
		return fields;
	}

	protected List<Row> performSelect(String name, @SuppressWarnings("rawtypes") Class clazz, List<String> selectFields, Map<String, Object> whereConditions, List<String> orderByFields) throws PolentaException {
		if (Bag.class.isAssignableFrom(clazz)) {
			return Bag.get(name).select(selectFields, whereConditions, orderByFields);
		} else if (User.class.isAssignableFrom(clazz)) {
			return null; //User.getInstance().select(selectFields, whereConditions, orderFields);
		} else {
			throw new InvalidStatementException("SELECT is not supported by this object type.");
		}
	}
	
	protected String formatResultSetToTransport(List<Row> resultSet) {
		if (resultSet == null || resultSet.isEmpty()) {
			return "EMPTY_RESULT_SET";
		} else {
			StringBuilder formatted = new StringBuilder("|");
			for (Row row: resultSet) {
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
			System.out.println(formatted.toString());
			return formatted.toString();
		}
	}
	
}
