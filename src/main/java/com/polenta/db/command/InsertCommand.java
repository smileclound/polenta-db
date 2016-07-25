package com.polenta.db.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.polenta.db.Command;
import com.polenta.db.MetadataStore;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.object.type.Bag;

public class InsertCommand implements Command {

	private String statement;
	
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
		
		Class clazz = MetadataStore.getInstance().retrieveObjectClass(objectName);
		if (clazz == null) {
			throw new InvalidStatementException("Object does not exist.");
		}
		
		//change this
		if (!Bag.class.isAssignableFrom(clazz)) {
			throw new InvalidStatementException("INSERT is not supported by this object type.");	
		}
		
		List<String> fields = extractFieldNames();
		List<String> values = extractRawFieldValues();
		if (fields.size() != values.size()) {
			throw new InvalidStatementException("Number of fields and values on INSERT statement need to match.");
		}
		
		Map<String, String> metadata = retrieveMetadata(clazz, objectName);
		
		validateFieldNames(fields, metadata);
		
		List<Object> convertedFields = convertFields(fields, values, metadata);
		
		Map<String, Object> insertValues = new HashMap<String, Object>();
		
		for (int i = 0; i <= fields.size() - 1; i++) {
			insertValues.put(fields.get(i), convertedFields.get(i));
		}
		
		performInsert(clazz, insertValues);
			
		return "OK";
	}

	public void performInsert(Class clazz, Map<String, Object> insertValues) throws PolentaException {
		if (Bag.class.isAssignableFrom(clazz)) {
			Bag.getInstance().insert(insertValues);
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

	public void validateFieldNames(List<String> fields, Map<String, String> metadata) throws PolentaException {
		for (String field: fields) {
			if (!metadata.containsKey(field)) {
				throw new InvalidStatementException("Field " + field + " not defined for this object.");
			}
		}
	}	
	
	public Map<String, String> retrieveMetadata(Class clazz, String objectName) {
		if (Bag.class.isAssignableFrom(clazz)) {
			return Bag.getInstance().retrieveMetadataForObject(objectName);
		} else {
			return null;
		}
	}
	
	public List<Object> convertFields(List<String> fields, List<String> values, Map<String, String> metadata) {
		List<Object> valuesList = new ArrayList<Object>();
		
		for (int i = 0; i <= fields.size() - 1; i++) {
			
			String field = fields.get(i);
			String value = values.get(i);
			String type = metadata.get(field);
			
			if (type.equals("STRING")) {
				valuesList.add(value);
			}
			
		}
		
		
		return valuesList;
	}
	
}
