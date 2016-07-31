package com.polenta.db.processor;

import com.polenta.db.command.CommandType;

public class StatementParser {

	public static CommandType extractCommandType(String statement) {
		String word1 = (statement.trim().toUpperCase().split(" ")[0]).trim();
		String word2;
		try {
			word2 = (statement.trim().toUpperCase().split(" ")[1]).trim();
		} catch (Exception e) {
			word2 = null;
		}
		if (word1.equalsIgnoreCase("ALTER")) {
			return CommandType.ALTER;
		} else if (word1.equalsIgnoreCase("CREATE")) {
			return CommandType.CREATE;
		} else if (word1.equalsIgnoreCase("DELETE") && (word2 != null && word2.equalsIgnoreCase("FROM"))) {
			return CommandType.DELETE;
		} else if (word1.equalsIgnoreCase("DROP")) {
			return CommandType.DROP;
		} else if (word1.equalsIgnoreCase("INSERT") && (word2 != null && word2.equalsIgnoreCase("INTO"))) {
			return CommandType.INSERT;
		} else if (word1.equalsIgnoreCase("SELECT")) {
			return CommandType.SELECT;
		} else if (word1.equalsIgnoreCase("SHUTDOWN")) {
			return CommandType.ADMIN_DATABASE;
		} else if (word1.equalsIgnoreCase("UPDATE")) {
			return CommandType.UPDATE;
		} else {
			return null;
		}
		
	}
	
	
}
