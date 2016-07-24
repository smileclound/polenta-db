package com.polenta.db.processor;

import java.util.Map;

import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.OperationNotSupportedException;
import com.polenta.db.object.ObjectManager;

public class StatementProcessor {
	
	private String statement;
	
	public StatementProcessor(String statement) {
		this.statement = statement;
	}
	
	public String execute() throws Exception {
		if (this.statement == null || this.statement.trim().equals("")) {
			throw new InvalidStatementException();
		}
		String[] words = statement.split(" ");
		if (words.length == 0) {
			throw new InvalidStatementException();
		}
		String operation = words[0];
		if (!isOperationSupported(operation)) { 
			throw new OperationNotSupportedException();
		}
		boolean objectRequired = isObjectTypeRequired(operation);
		if (words.length == 1 && objectRequired) {
			throw new InvalidStatementException();
		}
		if (objectRequired) {
			String objectType = words[1];
			Class clazz = ObjectManager.retrieveObjectTypeClass(objectType);
			if (clazz == null) {
				throw new InvalidStatementException();
			}
			if (!ObjectManager.isOperationSupportedByObjectType(clazz, operation)) {
				throw new InvalidStatementException();
			}
			if (operation.equalsIgnoreCase("ALTER")) {
				ObjectManager.performAlter(clazz, extractNewDefinitions(statement));
			} else if (operation.equalsIgnoreCase("CREATE")) {
				ObjectManager.performCreate(clazz, null);
			} else if (operation.equalsIgnoreCase("DELETE")) {
				ObjectManager.performDelete(clazz, null);
			} else if (operation.equalsIgnoreCase("DROP")) {
				ObjectManager.performDrop(clazz);
			} else if (operation.equalsIgnoreCase("INSERT")) {
				ObjectManager.performInsert(clazz, null);
			} else if (operation.equalsIgnoreCase("SELECT")) {
				ObjectManager.performSelect(clazz, null, null);
			} else if (operation.equalsIgnoreCase("UPDATE")) {
				ObjectManager.performUpdate(clazz, null, null);
			}
		}
		return "";
		//throw new InvalidStatementException();
	}

	protected boolean isOperationSupported(String operation) {
		return operation.equalsIgnoreCase("ALTER") ||
				operation.equalsIgnoreCase("CREATE") ||
				operation.equalsIgnoreCase("DELETE") ||
				operation.equalsIgnoreCase("DROP") ||
				operation.equalsIgnoreCase("INSERT") ||
				operation.equalsIgnoreCase("SELECT") ||
				operation.equalsIgnoreCase("UPDATE");
	}
	
	protected boolean isObjectTypeRequired(String operation) {
		return operation.equalsIgnoreCase("ALTER") ||
				operation.equalsIgnoreCase("CREATE") ||
				operation.equalsIgnoreCase("DELETE") ||
				operation.equalsIgnoreCase("DROP") ||
				operation.equalsIgnoreCase("INSERT") ||
				operation.equalsIgnoreCase("SELECT") ||
				operation.equalsIgnoreCase("UPDATE");
	}
	
	protected Map<String, Object> extractNewDefinitions(String statement) {
		return null;
	}
	
}
