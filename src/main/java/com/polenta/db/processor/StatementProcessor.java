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
	}

}
