package com.polenta.db.command;

import java.util.HashMap;
import java.util.Map;

import com.polenta.db.Command;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.object.ObjectManager;

public class CreateCommand implements Command {

	String statement;
	
	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String execute() throws PolentaException {
		String objectType = statement.trim().split(" ")[1];
		String objectName = statement.trim().split(" ")[2];
		Map<String, String> objectDefinitions = extractObjectDefinitions();
		
		Class clazz = ObjectManager.retrieveObjectTypeClass(objectType);
		if (clazz == null) {
			throw new InvalidStatementException();
		}
		
		ObjectManager.performCreate(clazz, objectName, objectDefinitions);
		
		return "OK";
	}
	
	protected Map<String, String> extractObjectDefinitions() throws PolentaException {
		Map<String, String> definitions = new HashMap<String, String>();
		String definitionBlock = this.statement.substring(this.statement.indexOf("(") + 1, this.statement.indexOf(")"));
		String[] definitionParts = definitionBlock.trim().split(",");
		for (String definitionPart: definitionParts) {
			String[] definitionSubParts = definitionPart.trim().split(" ");
			if (definitionSubParts.length != 2) {
				throw new InvalidStatementException();
			}
			definitions.put(definitionSubParts[0], definitionSubParts[1]);
		}
		return definitions;
	}

	
	

}
