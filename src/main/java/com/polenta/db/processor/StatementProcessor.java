package com.polenta.db.processor;

import com.polenta.db.Command;
import com.polenta.db.CommandBuilder;
import com.polenta.db.exception.InvalidStatementException;
import com.polenta.db.exception.NotSupportedOperationException;

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
		
		Command command = CommandBuilder.getInstance().build(statement);
		
		
		if (command == null) { 
			throw new NotSupportedOperationException();
		}
		
		return command.execute();
		
//		boolean objectTypeRequired = isObjectTypeRequired(operation);
//		boolean objectNameRequired = isObjectNameRequired(operation);
//		if (objectTypeRequired) {
//			//if object type is required statement needs to have at least two words 
//			if (words.length == 1 && objectTypeRequired) {
//				throw new InvalidStatementException();
//			}
//			String objectType = words[1].toUpperCase();
//			//check if object type is supported
//			Class clazz = ObjectManager.retrieveObjectTypeClass(objectType);
//			if (clazz == null) {
//				throw new InvalidStatementException();
//			}
//			//check if operation is supported by object type
//			if (!ObjectManager.isOperationSupportedByObjectType(clazz, operation)) {
//				throw new InvalidStatementException();
//			}
//			if (objectNameRequired) {
//				//if object name is required statement needs to have at least three words 
//				if (words.length == 2 && objectTypeRequired) {
//					throw new InvalidStatementException();
//				}
//				String objectName = words[2].toUpperCase();
//				if (operation.equalsIgnoreCase("CREATE")) {
//					ObjectManager.performCreate(clazz, objectName, extractNewObjectDefinitions());
//				}
//			} else {
//				if (operation.equalsIgnoreCase("ALTER")) {
//					ObjectManager.performAlter(clazz, extractExistingObjectNewDefinitions());
//				} else if (operation.equalsIgnoreCase("DELETE")) {
//					ObjectManager.performDelete(clazz, null);
//				} else if (operation.equalsIgnoreCase("INSERT")) {
//					ObjectManager.performInsert(clazz, null);
//				} else if (operation.equalsIgnoreCase("SELECT")) {
//					ObjectManager.performSelect(clazz, null, null);
//				} else if (operation.equalsIgnoreCase("UPDATE")) {
//					ObjectManager.performUpdate(clazz, null, null);
//				}
//			}
//		} else if (objectNameRequired) {
//			//if object name is required statement needs to have at least two words 
//			if (words.length == 1 && objectTypeRequired) {
//				throw new InvalidStatementException();
//			}
//			String objectName = words[1].toUpperCase();
//			Class clazz = MetadataStore.getInstance().retrieveObjectClass(objectName);
//			if (clazz == null) {
//				throw new InvalidStatementException();
//			} else {
//				if (operation.equalsIgnoreCase("DROP")) {
//					ObjectManager.performDrop(clazz, objectName);
//				} 
//			}
//		}
	}

//	protected boolean isOperationSupported(String operation) {
//		return operation.equalsIgnoreCase("ALTER") ||
//				operation.equalsIgnoreCase("CREATE") ||
//				operation.equalsIgnoreCase("DELETE") ||
//				operation.equalsIgnoreCase("DROP") ||
//				operation.equalsIgnoreCase("INSERT") ||
//				operation.equalsIgnoreCase("SELECT") ||
//				operation.equalsIgnoreCase("UPDATE");
//	}
//	
//	protected boolean isObjectTypeRequired(String operation) {
//		return operation.equalsIgnoreCase("ALTER") ||
//				operation.equalsIgnoreCase("CREATE") ||
//				operation.equalsIgnoreCase("DELETE") ||
//				operation.equalsIgnoreCase("DROP") ||
//				operation.equalsIgnoreCase("INSERT") ||
//				operation.equalsIgnoreCase("SELECT") ||
//				operation.equalsIgnoreCase("UPDATE");
//	}
//
//	protected boolean isObjectNameRequired(String operation) {
//		return operation.equalsIgnoreCase("ALTER") ||
//				operation.equalsIgnoreCase("CREATE") ||
//				operation.equalsIgnoreCase("DELETE") ||
//				operation.equalsIgnoreCase("DROP") ||
//				operation.equalsIgnoreCase("INSERT") ||
//				operation.equalsIgnoreCase("SELECT") ||
//				operation.equalsIgnoreCase("UPDATE");
//	}
//
//	protected Map<String, Object> extractExistingObjectNewDefinitions() {
//		return null;
//	}

}
