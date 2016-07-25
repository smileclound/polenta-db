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
import com.polenta.db.object.type.User;

public class CreateCommand implements Command {

	private String statement;
	
	private static List<String> SUPPORTED_FIELD_TYPES = new ArrayList<String>();
	
	static {
		SUPPORTED_FIELD_TYPES.add("STRING");
		SUPPORTED_FIELD_TYPES.add("INTEGER");
		SUPPORTED_FIELD_TYPES.add("DATE");
		SUPPORTED_FIELD_TYPES.add("DOUBLE");
	}
	
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String execute() throws PolentaException {
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
		Map<String, String> objectDefinitions = extractObjectDefinitions();
		if (objectDefinitions == null && objectDefinitions.isEmpty()) {
			throw new InvalidStatementException("Fields must be defined on CREATE statement.");
		}
		
		Class clazz = MetadataStore.retrieveObjectTypeClass(objectType);
		if (clazz == null) {
			throw new InvalidStatementException("Object type is not supported by Polenta.");
		}
		
		performCreate(clazz, objectName.toUpperCase(), objectDefinitions);
		
		return "OK";
	}
	
	protected Map<String, String> extractObjectDefinitions() throws PolentaException {
		Map<String, String> definitions = new HashMap<String, String>();
		try {
			String definitionBlock = this.statement.substring(this.statement.indexOf("(") + 1, this.statement.indexOf(")"));
			String[] fieldsList = definitionBlock.trim().split(",");
			for (String fields: fieldsList) {
				String[] fieldDefinitions = fields.trim().split(" ");
				if (fieldDefinitions.length != 2) {
					throw new InvalidStatementException();
				}
				String fieldName = fieldDefinitions[0].toUpperCase();
				String fieldType = fieldDefinitions[1].toUpperCase();
				if (!SUPPORTED_FIELD_TYPES.contains(fieldType)) {
					throw new InvalidStatementException("Filed type for " + fieldName + " is not supported by Polenta.");
				}
				definitions.put(fieldName, fieldType);
			}
		} catch (InvalidStatementException e) {
			throw new InvalidStatementException(e.getMessage());
		} catch (Exception e) {
			throw new InvalidStatementException("It was not possible to parse CREATE statement and extract fields definition.");
		}
		return definitions;
	}

	protected void performCreate(Class clazz, String name, Map<String, String> definitionValues) throws PolentaException {
		//refactor to avoid if's
		if (Bag.class.isAssignableFrom(clazz)) {
			Bag.getInstance().create(name, definitionValues);
		} else if (User.class.isAssignableFrom(clazz)) {
			User.getInstance().create(name, definitionValues);
		} else {
			throw new InvalidStatementException("CREATE is not supported by this object type.");
		}
	}
	
	

}
