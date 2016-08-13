package com.polenta.db.executor.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.polenta.db.data.DataType;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.executor.StatementExecutor;
import com.polenta.db.object.ObjectType;
import com.polenta.db.store.Store;

public class CreateExecutor implements StatementExecutor {

	public String execute(String statement) throws PolentaException {
		String objectType;
		try {
			objectType = statement.trim().toUpperCase().split(" ")[1];
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			throw new InvalidStatementException("Object type must be defined on CREATE statement.");
		}
		String objectName;
		try {
			objectName = statement.trim().toUpperCase().split(" ")[2];
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			throw new InvalidStatementException("Object name must be defined on CREATE statement.");
		}
		//use regex here
		if (objectName.contains("(") || objectName.contains(")") || objectName.contains(",")) {
			throw new InvalidStatementException("Object name must be defined on CREATE statement.");
		}
		Map<String, DataType> objectDefinitions = extractObjectDefinitions(statement);
		if (objectDefinitions == null || objectDefinitions.isEmpty()) {
			throw new InvalidStatementException("Fields must be defined on CREATE statement.");
		}
		
		ObjectType type = ObjectType.valueOf(objectType);
		
		if (type == null) {
			throw new InvalidStatementException("Object type is not supported by Polenta.");
		}
		
		Store.getInstance().add(type, objectName.toUpperCase(), objectDefinitions);
		
		return "OK";
	}
	
	protected Map<String, DataType> extractObjectDefinitions(String statement) throws PolentaException {
		Map<String, DataType> definitions = new LinkedHashMap<String, DataType>();
		try {
			String definitionBlock = statement.substring(statement.indexOf("(") + 1, statement.indexOf(")"));
			String[] fieldsList = definitionBlock.trim().split(",");
			for (String fields: fieldsList) {
				String[] fieldDefinitions = fields.trim().split(" ");
				if (fieldDefinitions.length != 2) {
					throw new InvalidStatementException("Invalid syntax of CREATE command.");
				}
				String fieldName = fieldDefinitions[0].toUpperCase();
				String fieldType = fieldDefinitions[1].toUpperCase();
				DataType dataType = DataType.valueOf(fieldType);
				if (dataType == null) {
					throw new InvalidStatementException("Filed type for " + fieldName + " is not supported by Polenta.");
				}
				definitions.put(fieldName, dataType);
			}
		} catch (InvalidStatementException e) {
			throw e;
		} catch (Exception e) {
			throw new InvalidStatementException("It was not possible to parse CREATE statement and extract fields definition.");
		}
		return definitions;
	}

}
