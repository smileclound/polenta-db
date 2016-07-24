package com.polenta.db.processor;

import com.polenta.db.exception.InvalidStatementException;

public class StatementProcessorBuilder {
	
	private StatementProcessorBuilder() {
		
	}
	
	public static StatementProcessor build(String statement) throws InvalidStatementException {
		String[] word = statement.split(" ");
		if (word[0].equals("CREATE") && word[1].equals("BAG")) {
			return new CreateBagProcessor();
		}
		throw new InvalidStatementException();
	}

}
