package com.polenta.db.executor;

public class StatementParser {

	public static StatementType extractCommandType(String statement) {
		String word1 = (statement.trim().toUpperCase().split(" ")[0]).trim();
		String word2;
		try {
			word2 = (statement.trim().toUpperCase().split(" ")[1]).trim();
		} catch (Exception e) {
			word2 = null;
		}
		if (word1.equalsIgnoreCase("ALTER")) {
			return StatementType.ALTER;
		} else if (word1.equalsIgnoreCase("CREATE")) {
			return StatementType.CREATE;
		} else if (word1.equalsIgnoreCase("DELETE") && (word2 != null && word2.equalsIgnoreCase("FROM"))) {
			return StatementType.DELETE;
		} else if (word1.equalsIgnoreCase("DROP")) {
			return StatementType.DROP;
		} else if (word1.equalsIgnoreCase("INSERT") && (word2 != null && word2.equalsIgnoreCase("INTO"))) {
			return StatementType.INSERT;
		} else if (word1.equalsIgnoreCase("SELECT")) {
			return StatementType.SELECT;
		} else if (word1.equalsIgnoreCase("SHUTDOWN")) {
			return StatementType.SHUTDOWN;
		} else if (word1.equalsIgnoreCase("UPDATE")) {
			return StatementType.UPDATE;
		} else {
			return null;
		}
		
	}
	
	
}
