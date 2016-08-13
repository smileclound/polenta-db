package com.polenta.db.executor.impl;

import java.util.ArrayList;
import java.util.List;

import com.polenta.db.catalog.Catalog;
import com.polenta.db.catalog.CatalogItem;
import com.polenta.db.data.DataType;
import com.polenta.db.data.Row;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.executor.StatementExecutor;
import com.polenta.db.object.behavior.Insertable;
import com.polenta.db.store.Store;

public class InsertExecutor implements StatementExecutor {

	public String execute(String statement) throws PolentaException {
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
		
		List<String> fields = extractFieldNames(statement);
		List<String> values = extractRawFieldValues(statement);
		if (fields.size() != values.size()) {
			throw new InvalidStatementException("Number of fields and values on INSERT statement need to match.");
		}
		
		validateFieldNames(fields, catalogItem);
		
		List<Object> convertedFields = convertFields(fields, values, catalogItem);
		
		Row newRow = new Row();
		
		for (int i = 0; i <= fields.size() - 1; i++) {
			newRow.set(fields.get(i), convertedFields.get(i));
		}
		
		try {
			((Insertable)Store.getInstance().get(objectName)).insert(newRow);
		} catch (ClassCastException e) {
			throw new InvalidStatementException("INSERT is not supported by this object type.");
		}
		
		return "OK";
	}

	public List<String> extractFieldNames(String statement) throws PolentaException {
		List<String> fieldsList = new ArrayList<String>();
		try {
			String fieldsBlock = statement.toUpperCase().substring(statement.indexOf("(") + 1, statement.indexOf(")")); 
			String[] fields = fieldsBlock.split(",");
			for (String field: fields) {
				fieldsList.add(field.trim());
			}
		} catch (Exception e) {
			throw new InvalidStatementException("It was not possible to parse INSERT statement and extract fields names.");
		}
		return fieldsList;
	}
	
	public List<String> extractRawFieldValues(String statement) throws PolentaException {
		List<String> valuesList = new ArrayList<String>();
		try {
			String valuesBlock = statement.toUpperCase().substring(statement.lastIndexOf("(") + 1, statement.lastIndexOf(")")); 
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
			DataType type = catalogItem.getDefinitionValue(field);
			try {
				valuesList.add(type.convert(value));
			} catch (Exception e) {
				throw new InvalidStatementException("Value of field " + field + " needs to be " + type.toString() +  ".");
			}
		}
		
		return valuesList;
	}
	
}
