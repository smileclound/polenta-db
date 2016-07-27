package com.polenta.db.command.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.polenta.db.catalog.Catalog;
import com.polenta.db.catalog.CatalogItem;
import com.polenta.db.command.Command;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.object.type.Bag;

public class InsertCommand implements Command {

	private String statement;
	
	private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
	
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String execute() throws PolentaException {
		String intoStatement;
		try {
			intoStatement = statement.trim().toUpperCase().split(" ")[1];
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			throw new InvalidStatementException("Invalid syntax for INSERT statement.");
		}
		if (!intoStatement.equals("INTO")) {
			throw new InvalidStatementException("Invalid syntax for INSERT statement.");
		}
		String objectName;
		try {
			objectName = statement.trim().toUpperCase().split(" ")[2];
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			throw new InvalidStatementException("Object name must be defined on INSERT statement.");
		}
		//use regex here
		if (objectName.contains("(") || objectName.contains(")") || objectName.contains(",")) {
			throw new InvalidStatementException("Object name must be defined on INSERT statement.");
		}
		
		CatalogItem catalogItem = Catalog.getInstance().get(objectName);  

		if (catalogItem == null) {
			throw new InvalidStatementException("Object does not exist.");
		}
		
		//change this
		if (!Bag.class.isAssignableFrom(catalogItem.getClazz())) {
			throw new InvalidStatementException("INSERT is not supported by this object type.");	
		}
		
		List<String> fields = extractFieldNames();
		List<String> values = extractRawFieldValues();
		if (fields.size() != values.size()) {
			throw new InvalidStatementException("Number of fields and values on INSERT statement need to match.");
		}
		
		validateFieldNames(fields, catalogItem);
		
		List<Object> convertedFields = convertFields(fields, values, catalogItem);
		
		Map<String, Object> insertValues = new LinkedHashMap<String, Object>();
		
		for (int i = 0; i <= fields.size() - 1; i++) {
			insertValues.put(fields.get(i), convertedFields.get(i));
		}
		
		performInsert(objectName, catalogItem.getClazz(), insertValues);
			
		return "OK";
	}

	public void performInsert(String name, Class clazz, Map<String, Object> insertValues) throws PolentaException {
		if (Bag.class.isAssignableFrom(clazz)) {
			Bag.get(name).insert(insertValues);
		} else {
			throw new InvalidStatementException("INSERT is not supported by this object type.");
		}
	}
	
	public List<String> extractFieldNames() throws PolentaException {
		List<String> fieldsList = new ArrayList<String>();
		try {
			String fieldsBlock = this.statement.toUpperCase().substring(this.statement.indexOf("(") + 1, this.statement.indexOf(")")); 
			String[] fields = fieldsBlock.split(",");
			for (String field: fields) {
				fieldsList.add(field.trim());
			}
		} catch (Exception e) {
			throw new InvalidStatementException("It was not possible to parse INSERT statement and extract fields names.");
		}
		return fieldsList;
	}
	
	public List<String> extractRawFieldValues() throws PolentaException {
		List<String> valuesList = new ArrayList<String>();
		try {
			String valuesBlock = this.statement.toUpperCase().substring(this.statement.lastIndexOf("(") + 1, this.statement.lastIndexOf(")")); 
			String[] values = valuesBlock.split(",");
			for (String value: values) {
				valuesList.add(value.trim());
			}
		} catch (Exception e) {
			throw new InvalidStatementException("It was not possible to parse INSERT statement and extract fields names.");
		}
		return valuesList;
	}

	public void validateFieldNames(List<String> fields, CatalogItem catalogItem) throws PolentaException {
		for (String field: fields) {
			if (!catalogItem.hasDefinitionKey(field)) {
				throw new InvalidStatementException("Field " + field + " not defined for this object.");
			}
		}
	}	
	
	public List<Object> convertFields(List<String> fields, List<String> values, CatalogItem catalogItem) throws PolentaException {
		List<Object> valuesList = new ArrayList<Object>();
		
		for (int i = 0; i <= fields.size() - 1; i++) {
			String field = fields.get(i);
			String value = values.get(i);
			String type = catalogItem.getDefinitionValue(field);
			
			if (type.equals("STRING")) {
				valuesList.add(value.substring(1, value.length() - 1));
			} else if (type.equals("INTEGER")) {
				try {
					Integer convertedValue = Integer.parseInt(value);
					valuesList.add(convertedValue);
				} catch (Exception e) {
					throw new InvalidStatementException("Value of field " + field + " needs to be INTEGER.");
				}
			} else if (type.equals("DOUBLE")) {
				try {
					Double convertedValue = Double.parseDouble(value);
					valuesList.add(convertedValue);
				} catch (Exception e) {
					throw new InvalidStatementException("Value of field " + field + " needs to be DOUBLE.");
				}
			} else if (type.equals("DATE")) {
				try {
					Date convertedValue = DATE_FORMATTER.parse(value);
					valuesList.add(convertedValue);
				} catch (Exception e) {
					throw new InvalidStatementException("Value of field " + field + " needs to be DATE (YYYY-MM-DD).");
				}
			}
		}
		
		return valuesList;
	}
	
}
