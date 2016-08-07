package com.polenta.db.processor;

import com.polenta.db.command.Command;
import com.polenta.db.command.CommandBuilder;

public class StatementProcessor {
	
	private String statement;
	
	public StatementProcessor(String statement) {
		this.statement = statement;
	}
	
	public String execute() {
		if (this.statement == null || this.statement.trim().equals("")) {
			return "ERROR|Statement required.";
		}

		String[] words = statement.split(" ");
		if (words.length == 0) {
			return "ERROR|Statement required.";
		}
		
		Command command = CommandBuilder.getInstance().build(statement);
		
		if (command == null) { 
			return "ERROR|Operation not supported.";
		}
		
		try {
			return "OK|" + command.execute();
		} catch (Exception e) {
			return "ERROR|" + e.getMessage();
		}
		
	}

}
